#include "PlugInPPS.h"

#include <tuxfw.h>

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::getFavoritos()
{
/*	JAVA -> FO

	<?xml version="1.0" encoding="ISO-8859-1"?>
	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getFavoritos</ProxyOperacao>
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
				<TRANSACAO>1008</TRANSACAO>
				<CELULAR>1171010160</CELULAR>
				<LOGIN>c_xpot</LOGIN>
				<IP>123.123.123.1</IP>
			</ROOT
		</msgBody>
	</msg
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_GET_FAVORITOS;
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
			<IDSERVICO>25</IDSERVICO>
			<DESCRICAO>Favoritos</DESCRICAO>
			<PARTICIPANTE>1171010160</PARTICIPANTE>
			<ESCOLHIDO>1171010159</ESCOLHIDO>
			<DTINI>yyyy-mm-dd</DTINI>
			<DTFIM>yyyy-mm-dd</DTFIM>
			<USUFRUI>PARTICIPANTE</USUFRUI>
			<STATUS>ATIVADO</STATUS>
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

	<FavoritosVO>
		<FavoritosItem>
			<numero></numero>
		</FavoritosItem>
		<FavoritosItem>
			<numero></numero>
		</FavoritosItem>
	</FavoritosVO>	
*/

	xml_g->createTag(TAG_XML_OUT_FAVORITOS_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
			
			XMLString::release(&pc_xmlns);
		}

		DOMNode* po_REG;

		for (int i = 0; (po_REG = tuxhp->walkDOM(po_ROOT, TAG_XML_PPS_OUT_REG, i)) != NULL; i++)
		{
			xml_g->createTag(TAG_XML_OUT_FAVORITOS_ITEM);
	
				char* pc_ESCOLHIDO = tuxhp->walkTree(po_REG, TAG_XML_PPS_OUT_ESCOLHIDO, 0);

				xml_g->addItem(TAG_XML_OUT_NUMERO, (pc_ESCOLHIDO != NULL)? pc_ESCOLHIDO : "");

				if (pc_ESCOLHIDO != NULL) XMLString::release(&pc_ESCOLHIDO);

			xml_g->closeTag();
		}

	xml_g->closeTag();

	// Registro de contato
	this->registrarContato();

}
