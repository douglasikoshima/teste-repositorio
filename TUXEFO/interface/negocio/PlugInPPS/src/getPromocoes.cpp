#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getPromocoes()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getPromocoes</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

/*	FO -> GW

	<msg>
		<msgHdr>
			<service>GWPPS</service>
		</msgHdr>
		<msgBody>
			<ROOT>
				<TRANSACAO>1009</TRANSACAO>
				<CELULAR>1171010160</CELULAR>
				<LOGIN>c_xpot</LOGIN>
				<IP>123.123.123.1</IP>
			</ROOT>
		</msgBody>
	</msg>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_PROMOCOES;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGIN, TAG_XML_PPS_IN_VAL_LOGIN);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_IP, TAG_XML_PPS_IN_VAL_IP);
	o_XMLtoGW.closeTag();

	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
	
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
			<IDPROMOCAO>60</IDPROMOCAO>
			<DESCRICAO>Aquisicao Namorados</DESCRICAO>
			<DTINI>yyyy-mm-dd</DTINI>
			<DTFIM>yyyy-mm-dd</DTFIM>
			<DTPARTICIPA>yyyy-mm-dd</DTPARTICIPA>
		</REG>
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

	<PromocoesVO>
		<PromocoesItem>
			<descricao></descricao>
			<dataSubscripcao></dataSubscripcao>
			<dataIni></dataIni>
			<dataFim></dataFim>
		</PromocoesItem>
	</PromocoesVO>
*/

	xml_g->createTag(TAG_XML_OUT_PROMOCOES_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}


		DOMNode* po_REG;

		for (int i = 0; (po_REG = tuxhp->walkDOM(po_ROOT, TAG_XML_PPS_OUT_REG, i)) != NULL; i++)
		{
			xml_g->createTag(TAG_XML_OUT_PROMOCOES_ITEM);
	
				char* pc_PROMOCAO = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_PROMOCAO, 0);
				char* pc_DTINI    = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_DTINI, 0);
				char* pc_DTFIM    = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_DTFIM, 0);
				
				char  vc_date[32];
				
				xml_g->addItem(TAG_XML_OUT_DESCRICAO, (pc_PROMOCAO != NULL)? pc_PROMOCAO : "");

				xml_g->addItem(TAG_XML_OUT_DATA_SUBSCRIPCAO, "");

				xml_g->addItem(TAG_XML_OUT_DATA_INI, Util::formateDate(vc_date, pc_DTINI, DATE_FORMAT_PPS_OUT, DATE_FORMAT_JAVA_ZERO_TIME));

				xml_g->addItem(TAG_XML_OUT_DATA_FIM, Util::formateDate(vc_date, pc_DTFIM, DATE_FORMAT_PPS_OUT, DATE_FORMAT_JAVA_ZERO_TIME));

				if (pc_PROMOCAO != NULL) XMLString::release(&pc_PROMOCAO);
				if (pc_DTINI != NULL)    XMLString::release(&pc_DTINI);
				if (pc_DTFIM != NULL)    XMLString::release(&pc_DTFIM);

			xml_g->closeTag();
		}

	xml_g->closeTag();
}
