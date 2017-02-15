#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::setSuspendeCelular()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setSuspendeCelular</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<operacao>1</operacao>
			<motivo>3</motivo>
			<observacao></ observacao >
			<numeroSerial></numeroSerial>
			<cidade></cidade>
			<BONumero></BONumero>
			<BOData></BOData>
			<BODelegacia></BODelegacia>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_ACAO = NULL;
	char* pc_MOTIVO = NULL;

	// A verificacao ja foi realizada
	pc_CELULAR = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);


	// Trata a tag ACAO
	pc_ACAO = tuxhp->walkTree(dnode, TAG_XML_IN_OPERACAO, 0);

	if (pc_ACAO == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_OPERACAO, EMSG_TAG_XML_IN_NE_OPERACAO);
	}

	if (*pc_ACAO == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_OPERACAO, EMSG_TAG_XML_IN_VV_OPERACAO);
	}

	if ((! Util::isNum(pc_ACAO)) || (atoi(pc_ACAO) < 0) || (atoi(pc_ACAO) > 1))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_OPERACAO, EMSG_TAG_XML_IN_VI_OPERACAO);
	}


	// Trata a tag MOTIVO
	pc_MOTIVO = tuxhp->walkTree(dnode, TAG_XML_IN_MOTIVO, 0);


	if ((pc_MOTIVO == NULL) || (*pc_MOTIVO == '\0'))
	{
		pc_MOTIVO = "3";
	}

	/*
	if (pc_MOTIVO == NULL)
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_MOTIVO, EMSG_TAG_XML_IN_NE_MOTIVO);
	}


	if (*pc_MOTIVO == '\0')
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_MOTIVO, EMSG_TAG_XML_IN_VV_MOTIVO);
	}

	if (! Util::isNum(pc_MOTIVO))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);
		XMLString::release(&pc_MOTIVO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_MOTIVO, EMSG_TAG_XML_IN_VI_MOTIVO);
	}
	*/

/*	FO -> GW

	<ROOT>
		<TRANSACAO>2006</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<ACAO>A-ativa D-desativa</ACAO>
		<LOGIN>c_xpto</LOGIN>
		<MOTIVO>1</MOTIVO>
		<IP>99.999.999.9</IP>
	</ROOT>
*/

	char vc_TRANSACAO[] = TAG_XML_PPS_IN_VAL_TRANS_SET_SUSPENDE_CELULAR;
	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, vc_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, (atoi(pc_ACAO))? "A" : "D");
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_MOTIVO, pc_MOTIVO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGIN, TAG_XML_PPS_IN_VAL_LOGIN);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_IP, TAG_XML_PPS_IN_VAL_IP);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

	XMLString::release(&pc_ACAO);
	XMLString::release(&pc_MOTIVO);

	
	// Faz a chamada remota e guarda o xml de retorno
	po_XMLfromGW = this->callRemoteAPI(SERVICE_PPS, &o_XMLtoGW, NULL);

	// Verifica o retorno
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGW == NULL)
		throw new TuxBasicSvcException(ECD_PPS_GW_NOT_RESPOND, EMSG_PPS_GW_NOT_RESPOND);

/*	GW -> FO

	<ROOT>
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

	<SuspendeCelularVO>
	</SuspendeCelularVO>
*/

	xml_g->createTag(TAG_XML_OUT_SUSPENDE_CELULAR_VO);

		char *pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

	// Comunicar Usuario
	this->comunicarUsuario();

}


