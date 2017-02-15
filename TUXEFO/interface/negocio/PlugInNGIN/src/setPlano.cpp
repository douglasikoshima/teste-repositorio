#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

void PlugInNGIN::setPlano()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setPlano</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
			<novoPlano></novoPlano>
		</msgBody>
	</msg>
*/
	//habilitando o controle de memória
	tuxhp->setRelease(true);

	// validando plano atual
	this->bOperacaoInterna = true;
	this->getPlano();

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";	
	char*      pc_Linha   = NULL;
	char*	   pc_Usuario = NULL;
	char      c_Regional[30] ="";
	CParametro	oParametro;


	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);

	pc_Usuario = tuxhp->walkTree(dnode,TAG_XML_IN_USUARIO, 0);

	if (pc_Usuario == NULL)
		throw new TuxBasicSvcException("20E1111", "ERRO NA LINHA");

	strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
	
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

	char*   pc_novoPlano = NULL;	
	int     i_novoPlano;
	char	chrNovoPlano[20] = "";

	pc_novoPlano = tuxhp->walkTree(dnode, TAG_XML_IN_NOVO_PLANO, 0);

	if(pc_novoPlano == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_NOVO_PLANO, EMSG_TAG_XML_IN_NE_NOVO_PLANO);

	if (*pc_novoPlano == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_NOVO_PLANO, EMSG_TAG_XML_IN_VV_NOVO_PLANO);

	i_novoPlano = atoi(pc_novoPlano);



	if (i_novoPlano == this->iIdPlano)		  
		throw new TuxBasicSvcException(ECD_PLANO_INVALIDO, EMSG_EXC_MESMO_PLANO);


	oParametro.consultarNomeServicoLegado(chrNovoPlano, atoi(c_area), pc_novoPlano,"setPlano",this->getSgSistemaOrigem(),c_linha);

	tuxfw_getlogger()->debug("NovoPlano: %s", chrNovoPlano);

	
/*	FO -> GWNGIN	

	<msg>
	<msgHdr>
		<service>mblGenChangeProfile</service>
	</msgHdr>
	<msgBody>
		<attribute name="service">mblGenChangeProfile</attribute>
		<attribute name="inCellNumber">4192490006</attribute>
		<attribute name="InSid">8070</attribute>
		<attribute name="inUsername">teste</attribute>
		<attribute name="inProfile">TODA</attribute>
		<attribute name="inReasonCode">1</attribute>
		<attribute name="inObs">TESTE</attribute>
		<attribute name="inOperator"/>
		<attribute name="inMedia"/>
	</msgBody>
</msg>

*/
	
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen   toGWNGIN;
	int		 i_Motivo= 1; //a pedido do cliente
	DOMNode  *fromGWNGIN = NULL;
	char   vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);	

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_Usuario, XML_NGN_SVC_SET_PLANO);


	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));		
		

	// TAG '<attribute name="InService">mblGenChangeProfile</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%s</%s>",XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_SERVICE, XML_NGN_SVC_SET_PLANO, XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%d</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	
	// TAG '<field name="InUserName" value = ""/>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_USERNAME,  pc_Usuario, XML_NGN_IN_ATTRIBUTE);

	char c_Linha[12] = "";
	
	//strncpy(c_Linha, pc_Linha, 10);
   sprintf( c_Linha, "%s", pc_Linha );
		
	// TAG '<field name="inCellNumber" value = ""/>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_CELLNUMBER, c_Linha, XML_NGN_IN_ATTRIBUTE);
	
	//<field name="inProfile" value=""/>
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_PROFILE, chrNovoPlano, XML_NGN_IN_ATTRIBUTE);

	// TAG '<field name="ReasonCode">ReasonCode_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%d</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_REASONCODE, i_Motivo, XML_NGN_IN_ATTRIBUTE);

	// TAG <field name="inObs"/>	
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\"/>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, "InObs");

	// TAG <field name="inOperator"/>	
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\"/>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_OPERATOR);

	// TAG <field name="inMedia"/>		
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\"/>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_MEDIA);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno	
	
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_SET_PLANO);

	
	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body/>
	</response>

*/

	// Objeto para armazenar a resposta
	DOMNode *po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErroURA(po_response);

	// monta retorno
	xml_g->createTag(TAG_XML_OUT_PLANO_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();



}