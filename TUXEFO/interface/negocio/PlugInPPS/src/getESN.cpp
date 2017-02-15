#include "PlugInPPS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getESN()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getESN</ProxyOperacao>
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
				<TRANSACAO>1000</TRANSACAO>
				<CELULAR>1171010160</CELULAR>
			</ROOT
		</msgBody>
	</msg
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_ESN;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
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
		<MIMO_CODIGO>99999999</MIMO_CODIGO>
		<MIMO_MSISDN>1171010160</MIMO_MSISDN>
		<CD>06</CD>
		<ESN>E0101010</ESN>
		<MIMO_ACTIVADO>2003-01-01 00:01:10</MIMO_ACTIVADO>
		<MIMO_DESACTIVADO></MIMO_DESACTIVADO>
		<CLIENTE_CODIGO>123234</CLIENTE_CODIGO>
		<ANTERIOR_SITUACAO></ANTERIOR_SITUACAO>
		<POSTERIOR_SITUACAO></POSTERIOR_SITUACAO>
		<MIMO_DIVIDA>0.00</MIMO_DIVIDA>
		<CATEGORIA_DESC>Livre</CATEGORIA_DESC>
		<CODPERFIL_IN_CET>BABY</CODPERFIL_IN_CET>
		<CATEGORIA_DIVIDA>0.00</CATEGORIA_DIVIDA>
		<NOME>PTI Brasil</NOME>
		<OPCAO_CODIGO>1</OPCAO_CODIGO>
		<VALIDADE>2004-12-31</VALIDADE>
		<ESTADO>Activado</ESTADO>
		<SALDO_REAL>154.00</SALDO_REAL>
		<SALDO_ON_NET>4.00</SALDO_ON_NET>
		<SALDO_DIARIO>3.50</SALDO_DIARIO>
		<VALIDADE_REAL>2004-12-31</VALIDADE_REAL>
		<HOST_IN>Vl.Mariana</HOST_IN>
		<PLANTA>PRE</PLANTA>
		<PLATAFORMA_CODIGO>1</PLATAFORMA_CODIGO>
		<MARCA>NOKIA</MARCA>
		<MODELO>6324</MODELO>
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

	<HexaVO>
		<ESN>12345678</ESN>
	</HexaVO>
*/

	xml_g->createTag(TAG_XML_OUT_HEXA_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

		char* pc_ESN = tuxhp->walkTree(po_ROOT, TAG_XML_PPS_OUT_ESN, 0);

		xml_g->addItem(TAG_XML_OUT_ESN, (pc_ESN != NULL)? pc_ESN : "");

		if (pc_ESN != NULL) XMLString::release(&pc_ESN);

	xml_g->closeTag();
}
