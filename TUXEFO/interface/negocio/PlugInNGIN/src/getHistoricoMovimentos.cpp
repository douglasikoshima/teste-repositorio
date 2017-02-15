#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getHistoricoMovimentos()
{
/*	JAVA -> FO

 	<msg>
 		<msgHdr>
 			<user>1</user>
 			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoMovimentos</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<dataIni>Data do Inicio</dataIni>
			<dataFim>Data do Fim</dataFim>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_inStartDate = NULL;
	char* pc_inEndDate = NULL;


	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);


	// Trata a TAG 'dataIni'
	pc_inStartDate = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_INI, 0);

	if (pc_inStartDate == NULL)
	{
		XMLString::release(&pc_idUser);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_INI, EMSG_TAG_XML_IN_NE_DATA_INI);
	}

	if (*pc_inStartDate == '\0')
	{
		XMLString::release(&pc_idUser);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_INI, EMSG_TAG_XML_IN_VV_DATA_INI);
	}


	// Verifica a TAG 'dataFim'
	pc_inEndDate = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_FIM, 0);

	if (pc_inEndDate == NULL)
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_inStartDate);

		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_FIM, EMSG_TAG_XML_IN_NE_DATA_FIM);
	}

	if (*pc_inEndDate == '\0')
	{
		XMLString::release(&pc_idUser);
		XMLString::release(&pc_inStartDate);
		
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_FIM, EMSG_TAG_XML_IN_VV_DATA_FIM);
	}

/*	FO -> GWNGIN

	<?xml version="1.0" encoding="UTF-8"?>
	<msg>	
		<msgHdr>		
			<service>mblGetRechargeHistory</service>
		</msgHdr>	
		<msgBody>		
			<atribute name="InSid">Identificação do Serviço</atribute>
			<atribute name="idUser">Usuário</atribute>
			<atribute name="idTrans">Identificador único do pedido</atribute>
			<atribute name="idLinha">Conta Privada no Care</atribute>
			<atribute name="inStartDate">Data Inicial</atribute>
			<atribute name="inEndDate">Data Final</atribute>
		</msgBody>
	</msg>
*/

	char vc_InSid[] = XML_NGN_ATTNM_VAL_IN_SID;
	char vc_idTrans[256];
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;
	char vc_date[32];
	CParametro oParametro;

	char* pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	char* pc_Linha   =   tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

	int iNrInSid = oParametro.getNrInSid(pc_Linha, pc_idCanal, pc_idUser, XML_NGN_SVC_GET_RECHARGE_HIST);

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

	// TAG '<attribute name="inStartDate">inStartDate_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_START_DT, Util::formateDate(vc_date, pc_inStartDate, DATE_FORMAT_JAVA, DATE_FORMAT_NGN_IN), XML_NGN_IN_ATTRIBUTE);

	// TAG '<attribute name="inEndDate">inEndDate_val</attribute>'
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_END_DT, Util::formateDate(vc_date, pc_inEndDate, DATE_FORMAT_JAVA, DATE_FORMAT_NGN_IN), XML_NGN_IN_ATTRIBUTE);

	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);


	XMLString::release(&pc_idUser);
	XMLString::release(&pc_inStartDate);
	XMLString::release(&pc_inEndDate);


	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_SVC_GET_RECHARGE_HIST);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGIN == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

		
/*	GWNGIN -> FO

	<?xml version="1.0" encoding="UTF-8"?>
	<response succeed="yes">
		<body>
			<record>
				<field name="outResponseTime"        value="Tempo de resposta local"/>
				<field name="outRechargeValue"       value="Valor de Recarga"/>
				<field name="outRechargeDate"        value="Data do movimento"/>
				<field name="outRechargeProcessDate" value="Data de processamento do movimento"/>
				<field name="outSource"              value="Origem"/>
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
			<HistoricoMovimentosVO>
				<HistoricoMovimentosItem>
					<recargaValor>Valor de outRechargeValue</recargaValor>
					 <recargaData>Valor de outRechargeDate</recargaData>
				 <recargaDataProc>Valor de outRechargeProcessDate</recargaDataProc>
						  <origem>Valor de outSource</origem>

					<movimento>"Recarga de Celular"</movimento>
					<notaFiscal></notaFiscal>
					<flag></flag>

				</HistoricoMovimentosItem>
			</HistoricoMovimentosVO>
		</msgBody>
	</msg>
*/

	xml_g->createTag(TAG_XML_OUT_HISTORICO_MOVIMENTOS_VO);

		char* pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

		if (pc_xmlns != NULL)
		{
			xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			XMLString::release(&pc_xmlns);
		}
				

		DOMNode *po_record = NULL;

		for (unsigned int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
		{
			char* responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

			if (Util::cmp(responseTM, XML_NGN_FLDNM_RESPONSE_TM) || Util::cmp(responseTM, "OUTRESPONSETIME"))
			{
				if (responseTM != NULL) XMLString::release(&responseTM);
				
				continue;
			}

			if (responseTM != NULL) XMLString::release(&responseTM);

			xml_g->createTag(TAG_XML_OUT_HISTORICO_MOVIMENTOS_ITEM);

			char *pc_field_name = NULL;

			for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
			{
				if (Util::cmp(pc_field_name, XML_NGN_FLDNM_RECHARGE_VALUE))
				{
					char* pc_outRechargeValue = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
					
					xml_g->addItem(TAG_XML_OUT_RECARGA_VALOR, (pc_outRechargeValue != NULL)? pc_outRechargeValue : "");
				
					if (pc_outRechargeValue != NULL) XMLString::release(&pc_outRechargeValue);
				}

				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_RECHARGE_DT))
				{
					char* pc_outRechargeDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
                    xml_g->addItem(TAG_XML_OUT_RECARGA_DATA, Util::formateDate(vc_date, pc_outRechargeDate, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));
				
					if (pc_outRechargeDate != NULL) XMLString::release(&pc_outRechargeDate);
				}

				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_RECHARGE_PROCESS_DT))			
				{
					char* pc_outRechargeProcessDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
                    xml_g->addItem(TAG_XML_OUT_RECARGA_DATA_PROC, Util::formateDate(vc_date, pc_outRechargeProcessDate, DATE_FORMAT_NGN_OUT, DATE_FORMAT_JAVA_ZERO_TIME));
				
					if (pc_outRechargeProcessDate != NULL) XMLString::release(&pc_outRechargeProcessDate);
				}

				else if (Util::cmp(pc_field_name, XML_NGN_FLDNM_SOURCE))			
				{
					char* pc_outSource = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);

					xml_g->addItem(TAG_XML_OUT_ORIGEM, (pc_outSource != NULL)? pc_outSource : "");

					if (pc_outSource != NULL) XMLString::release(&pc_outSource);
				}
			
				XMLString::release(&pc_field_name);
			}

			xml_g->addItem(TAG_XML_OUT_MOVIMENTO, "Recarga de Celular");
			xml_g->addItem(TAG_XML_OUT_NOTA_FISCAL, "");
			xml_g->addItem(TAG_XML_OUT_FLAG, "");

			xml_g->closeTag();
		}

	xml_g->closeTag();


	// Registro de contato
	this->registrarContato();

}
