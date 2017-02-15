#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getHistoricoMovimentos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoMovimentos</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<dataIni>2004-08-10 00:00:00</dataIni>
			<dataFim>2004-08-20 00:00:00</dataFim>
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
	  <TRANSACAO>1003</TRANSACAO>
	  <CELULAR>1171010160</CELULAR>
	  <DTFIM>2004-07-10 23:59:59</DTFIM>
	  <DTINI>2004-07-01 00:00:00</DTINI>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_HISTORICO_MOVIMENTOS;
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
			<MOVIMENTO>Recarga de Celular</MOVIMENTO>
			<VALOR>30.00</VALOR>
			<CAIXA>ATM</CAIXA>
			<DATACLIENTE>2004-07-01 10:10:45</DATACLIENTE>
			<DATAPROCESSO>2004-07-01 10:12:10</DATAPROCESSO>
			<NOTAFISCAL>120254</NOTAFISCAL>
			<FLAG>N</FLAG>
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

	<HistoricoMovimentosVO>
		<HistoricoMovimentosItem>
			<recargaValor></recargaValor>
			<recargaData></recargaData>
			<recargaDataProc></recargaDataProc>
			<origem></origem>
			<movimento></movimento>
			<notaFiscal></notaFiscal>
			<flag></flag>
			<classe></classe>
		</HistoricoMovimentosItem>
	</HistoricoMovimentosVO>
*/

	xml_g->createTag(TAG_XML_OUT_HISTORICO_MOVIMENTOS_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);
	
		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		DOMNode* po_REG;

		for (int i = 0; (po_REG = tuxhp->walkDOM(po_ROOT, TAG_XML_PPS_OUT_REG, i)) != NULL; i++)
		{
			xml_g->createTag(TAG_XML_OUT_HISTORICO_MOVIMENTOS_ITEM);
				
				char* pc_VALOR            = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_VALOR, 0);
				char* pc_DATACLIENTE      = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_DATACLIENTE, 0);
				char* pc_DATAPROCESSO     = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_DATAPROCESSO, 0);
				char* pc_CAIXA            = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_CAIXA, 0);
				char* pc_MOVIMENTO        = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_MOVIMENTO, 0);
				char* pc_NOTAFISCAL       = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_NOTAFISCAL, 0);
				char* pc_FLAG             = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_FLAG, 0);
				char* pc_CLASSE_MOVIMENTO = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_CLASSE_MOVIMENTO, 0);


				xml_g->addItem(TAG_XML_OUT_RECARGA_VALOR, (pc_VALOR != NULL)? pc_VALOR : "");

				xml_g->addItem(TAG_XML_OUT_RECARGA_DATA, (pc_DATACLIENTE != NULL)? pc_DATACLIENTE : "");

				xml_g->addItem(TAG_XML_OUT_RECARGA_DATA_PROC, (pc_DATAPROCESSO != NULL)? pc_DATAPROCESSO : "");

				xml_g->addItem(TAG_XML_OUT_ORIGEM, (pc_CAIXA != NULL)? pc_CAIXA : "");

				xml_g->addItem(TAG_XML_OUT_MOVIMENTO, (pc_MOVIMENTO != NULL)? pc_MOVIMENTO : "");

				xml_g->addItem(TAG_XML_OUT_NOTA_FISCAL, (pc_NOTAFISCAL != NULL)? pc_NOTAFISCAL : "");

				xml_g->addItem(TAG_XML_OUT_FLAG, (pc_FLAG != NULL)? pc_FLAG : "");

				xml_g->addItem(TAG_XML_OUT_CLASSE, (pc_CLASSE_MOVIMENTO != NULL)? pc_CLASSE_MOVIMENTO : "");


				if (pc_VALOR != NULL)            XMLString::release(&pc_VALOR);
				if (pc_DATACLIENTE != NULL)      XMLString::release(&pc_DATACLIENTE);
				if (pc_DATAPROCESSO != NULL)     XMLString::release(&pc_DATAPROCESSO);
				if (pc_CAIXA != NULL)            XMLString::release(&pc_CAIXA);
				if (pc_MOVIMENTO != NULL)        XMLString::release(&pc_MOVIMENTO);
				if (pc_NOTAFISCAL != NULL)       XMLString::release(&pc_NOTAFISCAL);
				if (pc_FLAG != NULL)             XMLString::release(&pc_FLAG);
				if (pc_CLASSE_MOVIMENTO != NULL) XMLString::release(&pc_CLASSE_MOVIMENTO);

			xml_g->closeTag();
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
