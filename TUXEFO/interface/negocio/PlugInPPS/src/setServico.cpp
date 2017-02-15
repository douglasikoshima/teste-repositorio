#include "PlugInPPS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/SMSSend.h"


#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInPPS::setServico()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setServico</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<usuario>FO</usuario>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<operacao>1</operacao>
			<servico>VOICEMAIL</servico>
		</msgBody>
	</msg>
*/

	char* pc_CELULAR = NULL;
	char* pc_ACAO = NULL;
	char* pc_TRANSACAO = NULL;
	int i_TRANSACAO = 0;

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

	// O valor da acao deve ser 0, 1 ou 2
	if ((! Util::isNum(pc_ACAO)) || (atoi(pc_ACAO) < 0) || (atoi(pc_ACAO) > 2))
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_OPERACAO, EMSG_TAG_XML_IN_VI_OPERACAO);
	}


	// Trata a tag TRANSACAO
	pc_TRANSACAO = tuxhp->walkTree(dnode, TAG_XML_IN_SERVICO, 0);

	if (pc_TRANSACAO == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_SERVICO, EMSG_TAG_XML_IN_NE_SERVICO);

	if (*pc_TRANSACAO == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_SERVICO, EMSG_TAG_XML_IN_VV_SERVICO);

	if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_VOICEMAIL))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_VOICEMAIL);
	}
	
	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_CALLID))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLID);
	}
	
	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_WARN))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_WARN);
	}
	
	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_CALLWAIT))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLWAIT);
	}
	
	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_CALLFWD))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLFWD);
	}
	
	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_UNBLOCKDDI))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_UNBLOCKDDI);
	}

	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_UNBLOCKDDD))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_UNBLOCKDDD);
	}

	else if (Util::cmp(pc_TRANSACAO, TAG_XML_PPS_IN_VAL_TRANS_WAP))
	{
		i_TRANSACAO = atoi(TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_WAP);
	}

	else
	{
		if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

		XMLString::release(&pc_ACAO);
		XMLString::release(&pc_TRANSACAO);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_SERVICO, EMSG_TAG_XML_IN_VI_SERVICO);
	}

/*	FO -> GW

	<ROOT>
		<TRANSACAO>XXXX</TRANSACAO>
		<CELULAR>1171010160</CELULAR>
		<ACAO></ACAO>
		<LOGIN></LOGIN>
		<IP></IP>
	</ROOT>
*/

	XMLGen o_XMLtoGW;
	DOMNode* po_XMLfromGW = NULL;


    o_XMLtoGW.createTag(TAG_XML_PPS_IN_ROOT);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_TRANS, i_TRANSACAO);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_CELULAR, pc_CELULAR);

		switch(atoi(pc_ACAO))
		{
			case 0:
				o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, "D");

				break;
			
			case 1:
				o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, "A");

				break;
			
			case 2:
				o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, "I");

				break;

			default:
				o_XMLtoGW.addItem(TAG_XML_PPS_IN_ACAO, "");
		}

		o_XMLtoGW.addItem(TAG_XML_PPS_IN_LOGIN, TAG_XML_PPS_IN_VAL_LOGIN);
		o_XMLtoGW.addItem(TAG_XML_PPS_IN_IP, TAG_XML_PPS_IN_VAL_IP);
	o_XMLtoGW.closeTag();


	if (pc_CELULAR != NULL) XMLString::release(&pc_CELULAR);

	XMLString::release(&pc_ACAO);
	XMLString::release(&pc_TRANSACAO);


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

	<ServicoVO>
	</ServicoVO>
*/

	xml_g->createTag(TAG_XML_OUT_SERVICO_VO);

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
