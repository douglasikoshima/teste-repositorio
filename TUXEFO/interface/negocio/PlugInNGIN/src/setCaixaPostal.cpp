#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::setCaixaPostal()
{

/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setCaixaPostal</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<operacao>1</operacao>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/
	char* pcTagXmlIn = NULL;
	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* proxyLinha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);
	char  chrOperacao[5];

	if( ( (pcTagXmlIn = tuxhp->walkTree(dnode,"operacao", 0)) == NULL) ){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_INEXISTENTE");
	}
	if(!*pcTagXmlIn){
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_VAZIO");
	}

	if (!strcmp(pcTagXmlIn,"ReiniciaSenha"))		
		strcpy(chrOperacao,"1");
	else if (!strcmp(pcTagXmlIn,"ReiniciaCaixaPostal"))
		strcpy(chrOperacao,"2");
	else
		throw new TuxBasicSvcException("24E0000", "TAG_<operacao>_VALOR_INVALIDO");


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblTransResetVoiceMailPwd</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="inUsername">lcardoso</atribute>
			<atribute name="inUsername">1196969090</atribute>
			<atribute name="inReason">1</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = "7030";
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;


	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = 0;
	
	if(!strcmp(pcTagXmlIn,"ReiniciaSenha"))
		iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_TRANS_RESET_VOICE_MAIL);
	else
		iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_RESET_VOICE_MAIL);

	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	if(!strcmp(pcTagXmlIn,"ReiniciaSenha"))
	{
			// TAG '<attribute name="InSid">InSid_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="InSid">InSid_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN,XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "inReason", chrOperacao, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="inUsername">idUser_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE,XML_NGN_IN_ATTRIBUTE_NAME, "inUsername", pc_idUser, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="inCellNumber">idLinha_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE,XML_NGN_IN_ATTRIBUTE_NAME, "inCellNumber", proxyLinha, XML_NGN_IN_ATTRIBUTE);

	}
	else if(!strcmp(pcTagXmlIn,"ReiniciaCaixaPostal"))
	{
		// TAG '<attribute name="InSid">InSid_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="idUser">idUser_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_idUser, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="idTrans">idTrans_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="idLinha">idLinha_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_idLinha, XML_NGN_IN_ATTRIBUTE);
	}

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);


	// Chama o serviço e verifica o retorno
	if(!strcmp(pcTagXmlIn,"ReiniciaCaixaPostal"))
		fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_RESET_VOICE_MAIL);
	else
		fromGWNGIN = this->callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_TRANS_RESET_VOICE_MAIL);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);


	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);


	// Registro de contato
	this->registrarContato(xml_g,true);


}
