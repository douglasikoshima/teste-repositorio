#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

void PlugInNGIN::getTarifaReduzida()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getTarifaReduzida</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	//habilitando o controle de memória
	tuxhp->setRelease(true);

	// validando plano atual
	this->bOperacaoInterna = true;
	this->getPlano();
	this->bOperacaoInterna = false;

	if (this->iIdPlano < XML_NGN_VAL_PLANO_DIA || this->iIdPlano > XML_NGN_VAL_PLANO_NOITE)
		throw new TuxBasicSvcException(ECD_PLANO_INVALIDO, EMSG_EXC_PLANO_INVALIDO);

	// Obtém o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;
   char nrLinhaEnvio[16];

	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11 )
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);
	
   sprintf( nrLinhaEnvio, "%s", pc_Linha );
   tuxfw_getlogger()->debug( "nrLinhaEnvio: [%s]", nrLinhaEnvio );

  strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
  sprintf(c_linha, "%s", (char*)&pc_Linha[2] );
  
  sprintf( nrLinhaEnvio, "%s", pc_Linha );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);

/*	FO -> GWNGIN

<?xml version="1.0" encoding="UTF-8"?>
<msg>
	<msgHdr>
		<service>mblGetDiaV</service>
	</msgHdr>
	<msgBody>			
		<attribute name="InSid">9500</attribute>
		<attribute name="MSISDN">1297969145</attribute>	
		<attribute name="service">mblGetDiaV</attribute>	
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


	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_TARIFA_REDUZIDA);


	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);

	char c_Linha[12] = "";
		
	//strncpy(c_Linha, pc_Linha, 10);
   
   // Chamado 20191914
   sprintf( c_Linha, "%s", pc_Linha );
   tuxfw_getlogger()->debug( "c_Linha: [%s]", c_Linha );
   
   tuxfw_getlogger()->debug( "2. nrLinhaEnvio: [%s]", nrLinhaEnvio );

		
	// TAG '<attribute name="MSISDN">MSISDN_val</attribute>'
	//sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_MSISDN, c_Linha, XML_NGN_IN_ATTRIBUTE);
   
   sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_MSISDN, nrLinhaEnvio, XML_NGN_IN_ATTRIBUTE);

	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_SERVICE, XML_NGN_SVC_GET_TARIFA_REDUZIDA, XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_TARIFA_REDUZIDA);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="INDITARIFDIAV" value="N"/>
				<field name="VLTARIFDIAV" value="0"/>
				<field name="DIAV" value="02"/>
			</record>
		</body>
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

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN, ECD_NGN_OUT_TNE_RESPONSE, 0);
	
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
	
	
	// Objeto para armazenar o corpo da resposta
	DOMNode *po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);

/*	FO -> JAVA

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<TarifaReduzidaVO>
				<indTarifa></indTarifa>
				<valTarifa></valTarifa>
				<diaTarifaReduzida></planoAtual>
			</PlanoVO>
		</msgBody>
	</msg>
*/

	char    *pc_indTarifa  = NULL;
	char    *pc_valTarifa  = NULL;
	char    *pc_diaTarifaReduzida = NULL;

	DOMNode *po_record     = NULL;
	char    *pc_field_name = NULL;

	po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, 0);

	for (unsigned int i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
	{
		if (Util::cmp(pc_field_name, XML_NGN_FLDNM_IND_TARIFA_REDUZIDA))
			pc_indTarifa = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
		
		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VL_TARIFA_REDUZIDA))			
			pc_valTarifa = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_DIA_TARIFA_REDUZIDA))			
			pc_diaTarifaReduzida = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

	}

	if(this->bOperacaoInterna == false)
	{
		// somente vamos criar o xml se esta api não estiver sendo chamada por outra 

		xml_g->createTag(TAG_XML_OUT_TARIFA_REDUZIDA_VO);

			char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

			if (pc_xmlns != NULL)
				xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			xml_g->addItem(TAG_XML_OUT_IN_TARIFA,  pc_indTarifa);
			xml_g->addItem(TAG_XML_OUT_VAL_TARIFA, pc_valTarifa);
			xml_g->addItem(TAG_XML_OUT_DIA_TARIFA_REDUZIDA, pc_diaTarifaReduzida);

		xml_g->closeTag();

	}

	// outras funcionalidades precisam disso
	this->iIdDiaTarifaReduzida = atoi(pc_diaTarifaReduzida);

}