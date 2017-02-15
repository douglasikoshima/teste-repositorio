#include "PlugInNGIN.h"
#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"


void PlugInNGIN::getPlano()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getPlano</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<proximoPlano>14></proximoPlano>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	//habilitando o controle de memória
	tuxhp->setRelease(true);

	// Obtem o numero da linha
	char       c_area [3] = "";
	char       c_linha[16] = "";
	char*      pc_Linha   = NULL;
	char*	   pc_proxPlano = NULL;
	char	   cProxPlanoFO[20] = "";
	int		   i_proxPlano = 0;
	char	   chrPlanoSP[5] = "VIVO";
	char	   chrPlano[10] = "";
	CParametro	oParametro;
	char		cProxPlanoLegado[256]="";	
	char		*pc_idCanal = NULL;


	pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	if(pc_Linha == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_LINHA, EMSG_TAG_XML_IN_NE_LINHA);

	if (*pc_Linha == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_LINHA, EMSG_TAG_XML_IN_VV_LINHA);

	if (strlen(pc_Linha) < 10 || strlen(pc_Linha) > 11 )
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VI_LINHA, EMSG_TAG_XML_IN_VI_LINHA);
	
	pc_proxPlano =  tuxhp->walkTree(dnode, "proximoPlano", 0);

	strncpy(c_area, pc_Linha, 2);
	//strncpy(c_linha, &pc_Linha[2], 8);
	sprintf(c_linha, "%s", (char*)&pc_Linha[2] );

	tuxfw_getlogger()->debug("DDD: %s", c_area);
	tuxfw_getlogger()->debug("Linha: %s", c_linha);


	if ((pc_proxPlano != NULL) && (*pc_Linha != '\0')){
	
		strncpy(cProxPlanoFO, pc_proxPlano, 2);

		oParametro.consultarNomeServicoLegado(cProxPlanoLegado, atoi(c_area),cProxPlanoFO, "ProximoPlano", this->getSgSistemaOrigem(),c_linha);

		
	}

/*	FO -> GWNGIN

<?xml version="1.0" encoding="ISO-8859-1"?>
<msg>
	<msgHdr>
		<service>mblGetProfile</service>
	</msgHdr>
	<msgBody>
		<attribute name="InSid">9500</attribute>
		<attribute name="MSISDN">4192490006</attribute>
		<attribute name="service">mblGetProfile</attribute>
		<attribute name="InNextProfile"/>
	</msgBody>
</msg>

*/
	char     vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char     vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen   toGWNGIN;
	DOMNode *fromGWNGIN = NULL;	

	pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	char* pc_idUser =  tuxhp->walkTree(dnode, "usuario", 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_PLANO);

	// Limpa o buffer de envido do XML para o GW
	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));

	// TAG '<attribute name="InSid">InSid_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	
	char c_Linha[12] = "";
		
	//strncpy(c_Linha, pc_Linha, 10);
   sprintf( c_Linha, "%s", pc_Linha );
		
	// TAG '<attribute name="MSISDN">MSISDN_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_MSISDN, c_Linha, XML_NGN_IN_ATTRIBUTE);

	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_SERVICE, XML_NGN_SVC_GET_PLANO, XML_NGN_IN_ATTRIBUTE);

	if (strlen (cProxPlanoLegado) > 0)
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_NEXTPROFILE, cProxPlanoLegado, XML_NGN_IN_ATTRIBUTE);
	else
		sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\"/>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_IN_NEXTPROFILE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_PLANO);

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
				<field name="PLANOATUAL" value="1"/>
				<field name="VLTARIFDIAV" value="0"/>
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

	
	if (Util::cmp(cSucceed,"no"))
	{

		fromGWNGIN	= tuxhp->walkDOM(fromGWNGIN,  XML_NGN_OUT_RESPONSE, 0);
	
		char *cErrCode	= tuxhp->walkAttr(fromGWNGIN, XML_NGN_OUT_ERROR, XML_NGN_OUT_ERROR_CODE, 0);
		

		if ( Util::cmp(cErrCode, ECD_NGN_CONTADOR_NAO_CONFIGURADO)  ||  Util::cmp(cErrCode, ECD_NGN_REGRA_CUSTO_NAO_CONFIGURADA) )
			throw new TuxBasicSvcException(ECD_CELULAR_NAO_COMPATIVEL, EMSG_CELULAR_NAO_COMPATIVEL);
		else
			this->trataErroURA(po_response);

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
			<PlanoVO>
				<indTarifa></indTarifa>
				<valTarifa></valTarifa>
				<planoAtual></planoAtual>
			</PlanoVO>
		</msgBody>
	</msg>
*/
	
	char    *pc_indTarifa  = NULL;
	char    *pc_valTarifa  = NULL;
	char    *pc_planoAtual = NULL;

	DOMNode *po_record     = NULL;
	char    *pc_field_name = NULL;
	char	chrPlanoAtual[15];

	po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, 0);

	for (unsigned int i = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, i)) != NULL; i++)
	{
		if (Util::cmp(pc_field_name, XML_NGN_FLDNM_IND_TARIFA_PLANO))
			pc_indTarifa = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);
		
		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_VL_TARIFA_PLANO))			
			pc_valTarifa = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

		else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_PLANO_ATUAL))			
			pc_planoAtual = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, i);

	}


	bool blPlanoIndefinido = false;
	char cPlanoAtual[256]="";

	oParametro.consultarNomeServicoFO(cPlanoAtual, atoi(c_area), pc_planoAtual, "getPlano", this->getSgSistemaOrigem());

	if (strlen(cPlanoAtual) == 0)		
		blPlanoIndefinido = true;

	
	if(this->bOperacaoInterna == false)
	{
		// somente vamos criar o xml se esta api não estiver sendo chamada por outra 

		xml_g->createTag(TAG_XML_OUT_PLANO_VO);

			char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

			if (pc_xmlns != NULL)
				xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			xml_g->addItem(TAG_XML_OUT_IN_TARIFA, pc_indTarifa);
			xml_g->addItem(TAG_XML_OUT_VAL_TARIFA, pc_valTarifa);
			xml_g->addItem(TAG_XML_OUT_PLANO_ATUAL, cPlanoAtual);
			xml_g->addItem(TAG_XML_OUT_PLANO_NGIN,  pc_planoAtual);  /*plano retornado pelo NGIN*/
			xml_g->addItem(TAG_XML_OUT_INSID, iNrInSid);  /*InSid utilizado para esta consulta*/


		xml_g->closeTag();
	}

	// outras funcionalidades precisam disso

	if (blPlanoIndefinido)
		this->iIdPlano = 16;
	else
		this->iIdPlano = atoi(cPlanoAtual);

}



