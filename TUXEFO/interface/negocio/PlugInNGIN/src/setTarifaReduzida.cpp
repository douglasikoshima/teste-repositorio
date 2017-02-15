#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

void PlugInNGIN::setTarifaReduzida()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setTarifaReduzida</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
			<diaTarifaReduzida></diaTarifaReduzida>
		</msgBody>
	</msg>
*/
	//habilitando o controle de memória
	tuxhp->setRelease(true);

	// validando plano atual
	this->bOperacaoInterna = true;
	this->getPlano();
	this->getTarifaReduzida();	

	if(this->iIdPlano < XML_NGN_VAL_PLANO_DIA || this->iIdPlano > XML_NGN_VAL_PLANO_NOITE)
		throw new TuxBasicSvcException(ECD_PLANO_INVALIDO, EMSG_EXC_PLANO_INVALIDO);

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11 )
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);
	
	strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
	
  sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

	///
	char*      pc_diaTarifaReduzida = NULL;
	int        i_diaTarifaReduzida;

	pc_diaTarifaReduzida = tuxhp->walkTree(dnode, TAG_XML_IN_DIA_TARIFA_REDUZIDA, 0);

	if(pc_diaTarifaReduzida == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DIA_TARIFA_REDUZIDA, EMSG_TAG_XML_IN_NE_DIA_TARIFA_REDUZIDA);

	if (*pc_diaTarifaReduzida == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DIA_TARIFA_REDUZIDA, EMSG_TAG_XML_IN_VV_DIA_TARIFA_REDUZIDA);

	i_diaTarifaReduzida = atoi(pc_diaTarifaReduzida);

	if (i_diaTarifaReduzida < XML_NGN_VAL_TARIFA_REDUZINDA_SEGUNDA || i_diaTarifaReduzida > XML_NGN_VAL_TARIFA_REDUZINDA_SABADO)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_DIA_TARIFA_REDUZIDA, EMSG_TAG_XML_IN_VI_DIA_TARIFA_REDUZIDA);

	if (i_diaTarifaReduzida == this->iIdDiaTarifaReduzida)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_MV_DIA_TARIFA_REDUZIDA, EMSG_TAG_XML_IN_VM_DIA_TARIFA_REDUZIDA);

	tuxfw_getlogger()->debug("diaTarifaReduzida: %d", i_diaTarifaReduzida);

/*	FO -> GWNGIN

<?xml version="1.0" encoding="ISO-8859-1"?>
<msg>
	<msgHdr>
		<service>mblChangeDiaV</service>
	</msgHdr>
	<msgBody>
		<attribute name="service">mblChangeDiaV</attribute>
		<attribute name="InSid">9500</attribute>
		<attribute name="MSISDN">1297969145</attribute>
		<attribute name="DIAV">4</attribute>
	</msgBody>
</msg>
*/

	char     vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen   toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	char* pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);
	
	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_SET_TARIFA_REDUZIDA);
	
	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	
	char c_Linha[12] = "";
	
	// strncpy(c_Linha, pc_Linha, 10);
   
   sprintf( c_Linha, "%s", pc_Linha );
	
	// TAG '<attribute name="MSISDN">MSISDN_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_MSISDN, c_Linha, XML_NGN_IN_ATTRIBUTE);

	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_SERVICE, XML_NGN_SVC_SET_TARIFA_REDUZIDA, XML_NGN_IN_ATTRIBUTE);

	
	// TAG '<attribute name="DIAV">DIAV_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%d</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_DIA_TARIFA_REDUZIDA, i_diaTarifaReduzida, XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_SET_TARIFA_REDUZIDA);

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


	char *cSucceed = tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_RESPONSE, XML_NGN_OUT_RESPONSE_SUCCEED, 0);

	if ( Util::cmp(cSucceed,"no"))
	{
		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN, XML_NGN_OUT_RESPONSE, 0);

		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);

		if (Util::cmp(cErrCode, ECD_NGN_REGRA_CUSTO_NAO_CONFIGURADA)) {
			throw new TuxBasicSvcException(ECD_REGRA_CUSTO_NAO_CONFIGURADA, EMSG_REGRA_CUSTO_NAO_CONFIGURADA);
		} else if (Util::cmp(cErrCode, ECD_CUSTO_NAO_VALIDO)) {
			throw new TuxBasicSvcException(ECD_CUSTO_NAO_VALIDO_TUXEDO, EMSG_CUSTO_NAO_VALIDO_TUXEDO);
		} else if (Util::cmp(cErrCode, ECD_NGN_PARAMETROS_INVALIDOS)) {
			throw new TuxBasicSvcException(ECD_PARAMETROS_INVALIDOS, EMSG_PARAMETROS_INVALIDOS);
		} else if (Util::cmp(cErrCode, ECD_NGN_SALDO_ALTERACOES_GRATUITAS)) {
			throw new TuxBasicSvcException(ECD_SALDO_ALTERACOES_GRATUITAS, EMSG_SALDO_ALTERACOES_GRATUITAS);
		} else if (Util::cmp(cErrCode, ECD_NGN_OPERACAO_NAO_PERMITIDA)) {
			throw new TuxBasicSvcException(ECD_OPERACAO_NAO_PERMITIDA, EMSG_OPERACAO_NAO_PERMITIDA);
		} else if (Util::cmp(cErrCode, ECD_NGN_SALDO_INSUFICIENTE)) {
			throw new TuxBasicSvcException(ECD_SALDO_INSUFICIENTE, EMSG_SALDO_INSUFICIENTE);
		} else {
			this->trataErroURA(po_response);	//trata erro genérico do NGIN
		}

	}


	// monta retorno
	xml_g->createTag(TAG_XML_OUT_TARIFA_REDUZIDA_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);
	// Registro de contato
	this->registrarContato(xml_g,false);
	xml_g->closeTag();


}