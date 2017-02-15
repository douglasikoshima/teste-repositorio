#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/SMSSend.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>
#include <cstdlib>

using namespace std;


void PlugInNGIN::setServico()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setServico</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>

			<usuario></usuario>
			<operacao></operacao>
			<servico></servico>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_idOper = NULL;
	char* pc_idServico = NULL;


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);


	// Trata a TAG 'operacao'
	pc_idOper = tuxhp->walkTree(dnode, TAG_XML_IN_OPERACAO, 0);

	if (pc_idOper == NULL)
	{
		XMLString::release(&pc_idUser);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_OPERACAO, EMSG_TAG_XML_IN_NE_OPERACAO);
	}

	if (*pc_idOper == '\0')
	{
		XMLString::release(&pc_idUser);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_OPERACAO, EMSG_TAG_XML_IN_VV_OPERACAO);
	}

	if (! Util::isNum(pc_idOper))
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_idOper);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_OPERACAO, EMSG_TAG_XML_IN_VI_OPERACAO);
	}


	// Trata a TAG 'servico'
	pc_idServico = tuxhp->walkTree(dnode, TAG_XML_IN_SERVICO, 0);

	if (pc_idServico == NULL)
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_idOper);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_SERVICO, EMSG_TAG_XML_IN_NE_SERVICO);
	}

	if (*pc_idServico == '\0')
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_idOper);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_SERVICO, EMSG_TAG_XML_IN_VV_SERVICO);
	}

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblChangeService</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
			<atribute name="idOper">id. Operação</atribute>
			<atribute name="idServico">Código do Serviço</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_SET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;

	

	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

	//Inverte a operacao para os servicos com conceinto invertido.
	if (Util::cmp(pc_idServico, "UNBLOCKDDI") || Util::cmp(pc_idServico, "UNBLOCKDDD"))
		if (atoi(pc_idOper) == 1)
			strcpy(pc_idOper, "0");
		else
			strcpy(pc_idOper, "1");			


	int i_idOperacao = atoi(pc_idOper);

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	int iNrInSid = 0;

	if ((i_idOperacao == 2) && Util::cmp(pc_idServico, "VOICEMAIL"))
		iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_RESET_VOICE_MAIL);
	else
		iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_CHANGE_SERVICE);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idUser">idUser_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_USER, pc_idUser, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idTrans">idTrans_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_TRANS, vc_idTrans, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="idLinha">idLinha_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_LINHA, pc_idLinha, XML_NGN_IN_ATTRIBUTE);

	if ( ! ((i_idOperacao == 2) && Util::cmp(pc_idServico, "VOICEMAIL")) )
	{
		//quando for RESET VOICE não vamos colocar as tags abaixo

		// TAG '<attribute name="idOper">idOper_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_OPER, pc_idOper, XML_NGN_IN_ATTRIBUTE);

		// TAG '<attribute name="idServico">idServico_val</attribute>'
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ID_SERVICO, pc_idServico, XML_NGN_IN_ATTRIBUTE);

	}

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	// Chama o serviço certo e verifica o retorno
	if ((i_idOperacao == 2) && Util::cmp(pc_idServico, "VOICEMAIL"))
		fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_RESET_VOICE_MAIL);
	else
		fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_CHANGE_SERVICE);

	if ((getLastStatusCode() != NULL) && (Util::cmp(getLastStatusCode(), "46E8043")))
		throw new TuxBasicSvcException(ECD_BLOQUEIO_DDI_INVALIDO, EMSG_EXC_BLOQUEIO_DDI_INVALIDO);

	if ((getLastStatusCode() != NULL) && (Util::cmp(getLastStatusCode(), "46E8039")))
		throw new TuxBasicSvcException(ECD_REINICIA_CXPOSTAL_DES, EMSG_REINICIA_CXPOSTAL_DES);

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
	

	
	char *cSucceed = tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

	if (Util::cmp(cSucceed,"no") )
	{

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		if (Util::cmp(cErrCode, ECD_NGN_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN)) {		
			throw new TuxBasicSvcException(ECD_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN, EMSG_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN);
		} else if (Util::cmp(cErrCode, ECD_NGN_CONTADOR_NAO_CONFIGURADO)) {		
			throw new TuxBasicSvcException(ECD_CONTADOR_NAO_CONFIGURADO, EMSG_CONTADOR_NAO_CONFIGURADO);
		} else if (Util::cmp(cErrCode, ECD_NGN_REGRA_CUSTO_NAO_CONFIGURADA)) {
			throw new TuxBasicSvcException(ECD_REGRA_CUSTO_NAO_CONFIGURADA, EMSG_REGRA_CUSTO_NAO_CONFIGURADA);
		} else if (Util::cmp(cErrCode, ECD_NGN_SALDO_ALTERACOES_GRATUITAS)) {
			throw new TuxBasicSvcException(ECD_SALDO_ALTERACOES_GRATUITAS, EMSG_SALDO_ALTERACOES_GRATUITAS);
		} else if (Util::cmp(cErrCode, ECD_NGN_PARAMETROS_INVALIDOS)) {
			throw new TuxBasicSvcException(ECD_PARAMETROS_INVALIDOS, EMSG_PARAMETROS_INVALIDOS);
		} else if (Util::cmp(cErrCode, ECD_NGN_SERVICO_SUSPENSO)) {
			throw new TuxBasicSvcException(ECD_SERVICO_SUSPENSO, EMSG_SERVICO_SUSPENSO);
		} else if (Util::cmp(cErrCode, ECD_NGN_SERVICO_DESATIVADO_HERANCA)) {
			throw new TuxBasicSvcException(ECD_SERVICO_DESATIVADO_HERANCA, EMSG_SERVICO_DESATIVADO_HERANCA);
		} else if (Util::cmp(cErrCode, ECD_NGN_SERVICO_SUSPENSO_)) {
			throw new TuxBasicSvcException(ECD_SERVICO_SUSPENSO_, EMSG_SERVICO_SUSPENSO_);
		} else	// trata erro genérico do NGIN
			this->trataErroURA(po_response);


	}


	XMLString::release(&pc_idUser);
	XMLString::release(&pc_idOper);
	XMLString::release(&pc_idServico);


	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);


/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"     value="Tempo de resposta local"/>
			</record>
		</body>
	</response>
*/


/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ServicoVO>
			</ServicoVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_SERVICO_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);
		
		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
		
			XMLString::release(&pc_xmlns);
		}

	// Registro de contato
	this->registrarContato(xml_g,false);
	
	xml_g->closeTag();


	// Comunicar Usuario
	this->comunicarUsuario();

}
