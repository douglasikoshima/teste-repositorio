#include "PlugInPPS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getHistoricoAtendimento()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoAtendimento</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<idcontasistemaorigem>123</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni>2004-07-01 00:00:00</dataIni>
			<dataFim>2004-07-10 23:59:59</dataFim>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_DTINI   = NULL;
	char* pc_DTFIM   = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	
	// Trata a tag DTINI
	pc_DTINI = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_INI, 0);

	if (pc_DTINI == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_INI, EMSG_TAG_XML_IN_NE_DATA_INI);
	}

	if (*pc_DTINI == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_INI, EMSG_TAG_XML_IN_VV_DATA_INI);
	}

	// compara o tamanho da data
	if (strlen(pc_DTINI) != 19)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_DATA_INI, EMSG_TAG_XML_IN_VI_DATA_INI);
	}

	// Trata a tag DTFIM
	pc_DTFIM = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_FIM, 0);

	if (pc_DTFIM == NULL)
	{	
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_FIM, EMSG_TAG_XML_IN_NE_DATA_FIM);
	}

	if (*pc_DTFIM == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_FIM, EMSG_TAG_XML_IN_VV_DATA_FIM);
	}

	// compara o tamanho da data
	if (strlen(pc_DTFIM) != 19)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_DTINI);
		XMLString::release(&pc_DTFIM);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_DATA_FIM, EMSG_TAG_XML_IN_VI_DATA_FIM);
	}

/*	FO -> GW

	<ROOT>
		<TRANSACAO>1001</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<DTFIM>2004-07-10 23:59:59</DTFIM>
		<DTINI>2004-07-01 00:00:00</DTINI>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_HISTORICO_ATENDIMENTO;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DTINI, pc_DTINI);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_DTFIM, pc_DTFIM);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
	
	XMLString::release(&pc_DTINI);
	XMLString::release(&pc_DTFIM);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);

/*	GW -> FO

	<ROOT>
		<REG>
			<EVENTO>Ativacao Celular</EVENTO>
			<DATA>2004-07-01 23:59:59</DATA>
			<ATRIBUTO>1</ATRIBUTO>
			<ORIGEM>HM</ORIGEM>
		</REG>
		<DTINIRET></DTINIRET>
		<DTFIMRET></DTFIMRET>
		<ERR>0</ERR>
		<ERR_MENS></ERR_MENS>
	</ROOT>
*/

	// Variavel para armazenar o DOM de 'ROOT'
	DOMNode* po_ROOT = NULL;

	// Verifica a tag ROOT
	po_ROOT = tuxhp->walkDOM(po_XMLfromGW, TAG_XML_PPS_OUT_ROOT, 0);

	if (po_ROOT == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_PPS_OUT_NE_ROOT, EMSG_TAG_XML_PPS_OUT_NE_ROOT);

	// trata erro genérico do PPS
	this->trataErro(po_ROOT);

/*	FO -> JAVA

	<HistoricoAtendimentoVO>
		<HistoricoAtendimentoItem>
			<descricao></descricao>
			<data></data>
			<servico></servico>
			<origem></origem>
			<inPrioridade></inPrioridade>
		</HistoricoAtendimentoItem>
	</HistoricoAtendimentoVO>
*/

	xml_g->createTag(TAG_XML_OUT_HISTORICO_ATENDIMENTO_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		DOMNode* po_REG;

		for (int i = 0; (po_REG = tuxhp->walkDOM(po_ROOT, TAG_XML_PPS_OUT_REG, i)) != NULL; i++)
		{
			xml_g->createTag(TAG_XML_OUT_HISTORICO_ATENDIMENTO_ITEM);
				
				char* pc_EVENTO   = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_EVENTO, 0);
				char* pc_DATA     = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_DATA, 0);
				char* pc_ATRIBUTO = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_ATRIBUTO, 0);
				char* pc_ORIGEM   = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_ORIGEM, 0);


				xml_g->addItem(TAG_XML_OUT_DESCRICAO, (pc_EVENTO != NULL)? pc_EVENTO : "");

				xml_g->addItem(TAG_XML_OUT_DATA, (pc_DATA != NULL)? pc_DATA : "");

				xml_g->addItem(TAG_XML_OUT_SERVICO, (pc_ATRIBUTO != NULL)? pc_ATRIBUTO : "");

				xml_g->addItem(TAG_XML_OUT_ORIGEM, (pc_ORIGEM != NULL)? pc_ORIGEM : "");

				xml_g->addItem(TAG_XML_OUT_IN_PRIORIDADE, "");


				if (pc_EVENTO != NULL)   XMLString::release(&pc_EVENTO);
				if (pc_DATA != NULL)     XMLString::release(&pc_DATA);
				if (pc_ATRIBUTO != NULL) XMLString::release(&pc_ATRIBUTO);
				if (pc_ORIGEM != NULL)   XMLString::release(&pc_ORIGEM);

			xml_g->closeTag();
		}

	xml_g->closeTag();
}
