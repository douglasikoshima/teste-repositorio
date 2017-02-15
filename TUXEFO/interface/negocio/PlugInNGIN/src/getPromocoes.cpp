#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getPromocoes()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>1</user>
			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getPromocoes</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;


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
			<service>mblGetPromotions</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	CParametro oParametro;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_PROMOTIONS);


	// Obtem numero randomico para idTrans
	Util::rand(vc_idTrans);

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
	
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);


	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_PROMOTIONS);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	
	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"              value="Tempo de resposta local"/>
				<field name="outPromotionDescription"      value="Nome"/>
				<field name="outPromotionSubscriptionDate" value="Data de subscrição na promoção"/>
				<field name="outPromotionStartDate"        value="Data de início da promoção"/>
				<field name="outPromotionEndDate"          value="Data do fim da promoção"/>
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
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

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
			<PromocoesVO>
				<PromocoesItem>
						  <descricao>Valor de outPromotionDescription</descricao>
					<dataSubscripcao>Valor de outPromotionSubscriptionDate</dataSubscripcao>
							<dataIni>Valor de outPromotionStartDate</dataIni>
							<dataFim>Valor de outPromotionEndDate</dataFim>
				</PromocoesItem>
			</PromocoesVO>
		</msgBody>
	</msg>
*/

	char vc_date[32];

	xml_g->createTag(TAG_XML_OUT_PROMOCOES_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			XMLString::release(&pc_xmlns);
		}


		DOMNode *po_record = NULL;

		for (unsigned int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
		{
			char* pc_responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

			if (Util::cmp(pc_responseTM, XML_NGN_FLDNM_RESPONSE_TM))
			{
				if (pc_responseTM != NULL) XMLString::release(&pc_responseTM);

				continue;
			}

			if (pc_responseTM != NULL) XMLString::release(&pc_responseTM);

			xml_g->createTag(TAG_XML_OUT_PROMOCOES_ITEM);

			char *pc_field_name = NULL;

			for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
			{
				if (Util::cmp(pc_field_name, XML_NGN_FLDNM_PROMOTION_DESC))
				{
					char* pc_outPromotionDescription = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					xml_g->addItem(TAG_XML_OUT_DESCRICAO, (pc_outPromotionDescription != NULL)? pc_outPromotionDescription : "");
				
					if (pc_outPromotionDescription != NULL) XMLString::release(&pc_outPromotionDescription);
				}
				
				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_PROMOTION_SUBSCRIPT_DT))			
				{
					char* pc_outPromotionSubscriptionDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					xml_g->addItem(TAG_XML_OUT_DATA_SUBSCRIPCAO, Util::formateDate(vc_date, pc_outPromotionSubscriptionDate, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));			
				
					if (pc_outPromotionSubscriptionDate != NULL) XMLString::release(&pc_outPromotionSubscriptionDate);
				}
				
				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_PROMOTION_START_DT))			
				{
					char* pc_outPromotionStartDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					xml_g->addItem(TAG_XML_OUT_DATA_INI, Util::formateDate(vc_date, pc_outPromotionStartDate, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));

					if (pc_outPromotionStartDate != NULL) XMLString::release(&pc_outPromotionStartDate);
				}

				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_PROMOTION_END_DT))			
				{
					char* pc_outPromotionEndDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					xml_g->addItem(TAG_XML_OUT_DATA_FIM, Util::formateDate(vc_date, pc_outPromotionEndDate, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));

					if (pc_outPromotionEndDate != NULL) XMLString::release(&pc_outPromotionEndDate);
				}
			
				XMLString::release(&pc_field_name);
			}

			xml_g->closeTag();
		}

	xml_g->closeTag();
}
