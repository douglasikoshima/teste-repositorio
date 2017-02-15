#include "PlugInNGIN.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "../../PlugInBE/include/Parametro.h"

#include <cstdio>
#include <cstring>

using namespace std;


void PlugInNGIN::getHistoricoFaturamentos()
{
/*	XML de entrada

 	<msg>
 		<msgHdr>
 			<user>1</user>
 			<service>TuxProxyBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoFaturamentos</ProxyOperacao>
			<ProxyLinha>4191009650</ProxyLinha>
			<usuario>Portal</usuario>
			<dataIni>Data do Inicio</dataIni>
			<dataFim>Data do Fim</dataFim>
			<xmlns>cliente.fo.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/
	CParametro oParametro;
	char* pc_idLinha = getIdLinhaSistemaOrigem();
	char* pc_idUser = NULL;
	char* pc_proxyLinha = NULL;
	char* pc_idCanal = NULL;
	char* pc_inStartDate = NULL;
	char* pc_inEndDate = NULL;

	// Trata a TAG 'usuario'
	pc_idUser = tuxhp->walkTree(dnode, TAG_XML_IN_USUARIO, 0);

	if (pc_idUser == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_USUARIO, EMSG_TAG_XML_IN_NE_USUARIO);
	
	if (*pc_idUser == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_USUARIO, EMSG_TAG_XML_IN_VV_USUARIO);

	pc_inStartDate = "";
	pc_inEndDate = "";


	// recupera a linha 	
	pc_proxyLinha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);
	// Canal
	pc_idCanal =  tuxhp->walkTree(dnode, "idCanal", 0);

	int iNrInSid = oParametro.getNrInSid(pc_proxyLinha, pc_idCanal, pc_idUser, XML_NGN_GET_POST_PAID_INVOICE_ACCOUNT);

	// montar XML de entrada 
	char vc_XMLToGWNGIN[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGIN;
	DOMNode *fromGWNGIN = NULL;

	memset(vc_XMLToGWNGIN, '\0', sizeof(vc_XMLToGWNGIN));
	sprintf(vc_XMLToGWNGIN, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	sprintf(vc_XMLToGWNGIN, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGIN, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_MSISDN, pc_proxyLinha, XML_NGN_IN_ATTRIBUTE);
	toGWNGIN.aggregateXML(vc_XMLToGWNGIN);

	// liberar tag de user
	XMLString::release(&pc_idUser);

	// Chama o serviço e verifica o retorno
	fromGWNGIN = callRemoteAPI(this->getServiceName(), &toGWNGIN, XML_NGN_GET_POST_PAID_INVOICE_ACCOUNT);

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

	
	DOMNode *po_record = NULL;
	char inOutInvoiceAccount[255];
	memset(&inOutInvoiceAccount,0,sizeof(inOutInvoiceAccount));

	for (int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
	{
		char* responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

		if (Util::cmp(responseTM, XML_NGN_FLDNM_RESPONSE_TM) || Util::cmp(responseTM, "OUTRESPONSETIME"))
		{
			if (responseTM != NULL) XMLString::release(&responseTM);
			
			continue;
		}

		if (responseTM != NULL) XMLString::release(&responseTM);


		char *pc_field_name = NULL;

		for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
		{
			if (Util::cmp(pc_field_name, XML_NGN_OUT_INVOICE_ACCOUNT))
			{
				char* pc_inOutInvoiceAccount = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				
				if (pc_inOutInvoiceAccount != NULL)
					strcpy(inOutInvoiceAccount,pc_inOutInvoiceAccount);
			
				if (pc_inOutInvoiceAccount != NULL) XMLString::release(&pc_inOutInvoiceAccount);
			}
		}

		XMLString::release(&pc_field_name);
	}

	tuxfw_getlogger()->debug("inOutInvoiceAccount = %s",inOutInvoiceAccount);


	// chamar a API de consulta de historico de faturas
	iNrInSid = oParametro.getNrInSid(pc_proxyLinha, pc_idCanal, pc_idUser, XML_NGN_GET_POST_PAID_INVOICE_ID_HISTORY);

	// montar XML de entrada 
	char vc_XMLToGWNGINHistorico[BUFFER_SIZE_NGN_XML_IN_GET];
	XMLGen toGWNGINHistorico;
	DOMNode *fromGWNGINHistorico = NULL;

	memset(vc_XMLToGWNGINHistorico, '\0', sizeof(vc_XMLToGWNGINHistorico));
	sprintf(vc_XMLToGWNGINHistorico, "<%s %s=\"%s\">%d</%s>", XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_SID, iNrInSid, XML_NGN_IN_ATTRIBUTE);
	sprintf(vc_XMLToGWNGINHistorico, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGINHistorico, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_ININVOICE_ACCOUNT, inOutInvoiceAccount, XML_NGN_IN_ATTRIBUTE);
	sprintf(vc_XMLToGWNGINHistorico, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGINHistorico, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_START_DT, pc_inStartDate, XML_NGN_IN_ATTRIBUTE);
	sprintf(vc_XMLToGWNGINHistorico, "%s<%s %s=\"%s\">%s</%s>", vc_XMLToGWNGINHistorico, XML_NGN_IN_ATTRIBUTE, XML_NGN_IN_ATTRIBUTE_NAME, XML_NGN_ATTNM_IN_END_DT, pc_inEndDate, XML_NGN_IN_ATTRIBUTE);
	toGWNGINHistorico.aggregateXML(vc_XMLToGWNGINHistorico);

	// Chama o serviço e verifica o retorno
	fromGWNGINHistorico = callRemoteAPI(this->getServiceName(), &toGWNGINHistorico, XML_NGN_GET_POST_PAID_INVOICE_ID_HISTORY);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (fromGWNGINHistorico == NULL)
		throw new TuxBasicSvcException(ECD_NGN_GW_NOT_RESPOND, EMSG_NGN_GW_NOT_RESPOND);

	// Objeto para armazenar a resposta
	po_response = NULL;

	// Verifica a tag 'response'
	po_response = tuxhp->walkDOM(fromGWNGINHistorico, XML_NGN_OUT_RESPONSE, 0);
	
	if (po_response == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_RESPONSE, EMSG_NGN_OUT_TNE_RESPONSE);
	
	// trata erro genérico do NGIN
	this->trataErro(po_response);

	// Objeto para armazenar o corpo da resposta
	po_body = NULL;

	// Verifica a tag 'body'
	po_body = tuxhp->walkDOM(po_response, XML_NGN_OUT_BODY, 0);

	if (po_body == NULL)
		throw new TuxBasicSvcException(ECD_NGN_OUT_TNE_BODY, EMSG_NGN_OUT_TNE_BODY);
	
	/*
		retorno
		<?xml version="1.0" encoding="ISO-8859-1" ?>
		<response succeed="yes" timestamp="2006-06-20T13:02:02.976-03:00">
		<body>
		<record>
		<field name="outInvoiceId" value="3927189273"/>
		<field name="outRefPeriod" value="2007/06"/>
		<field name="outBillingCycle" value="99"/>
		<field name="outExpireDate" value="28/06/2007"/>
		<field name="outTotalValue" value="32.00"/>
		<field name="outInvoiceIdStatus" value="NAO PAGA"/>
		</record>
		</body>
		</response>
	*/

	xml_g->createTag("LupaFaturamentoPosVO");
	xml_g->addProp("xmlns","tuxproxy.vivo.com.br/vo");

	po_record = NULL;
	for (unsigned int i = 0; (po_record = tuxhp->walkDOM(po_body, XML_NGN_OUT_RECORD, i)) != NULL; i++)
	{
		char* responseTM = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, 0);

		if (Util::cmp(responseTM, XML_NGN_FLDNM_RESPONSE_TM) || Util::cmp(responseTM, "OUTRESPONSETIME"))
		{
			if (responseTM != NULL) XMLString::release(&responseTM);			
			continue;
		}

		if (responseTM != NULL) XMLString::release(&responseTM);

		char *pc_field_name = NULL;

		xml_g->createTag("LFFaturamento");
		for (unsigned int j = 0; (pc_field_name = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_NAME, j)) != NULL; j++)
		{			
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_INVOICE_ID))
			{			
				char* pc_inOutInvoiceID = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				
				xml_g->addItem("idConta", pc_inOutInvoiceID);
			
				if (pc_inOutInvoiceID != NULL) XMLString::release(&pc_inOutInvoiceID);
			}
			else
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_REF_PERIOD))
			{
				char* pc_inOutPeriod = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
			
				if (pc_inOutPeriod != NULL) XMLString::release(&pc_inOutPeriod);
			}
			else
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_BILLING_CYCLE))
			{
				char* pc_inOutBillingCycle = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				
				xml_g->addItem("dsCiclo",pc_inOutBillingCycle);
			
				if (pc_inOutBillingCycle != NULL) XMLString::release(&pc_inOutBillingCycle);
			}
			else
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_EXPIRE_DATE))
			{
				char* pc_inOutExpirateDate = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				char cCycleEndDt[20];
				memset(&cCycleEndDt,0,sizeof(cCycleEndDt));
				xml_g->addItem("dtVencimento",pc_inOutExpirateDate);

				xml_g->addItem("nrDia", Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "YYYY-MM-DD", "DD")); //Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "DDMMYYYY", "DD"));
				xml_g->addItem("nrMes", Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "YYYY-MM-DD", "MM")); //Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "DDMMYYYY", "MM"));
				xml_g->addItem("nrAno", Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "YYYY-MM-DD", "YYYY"));	//Util::formateDate(cCycleEndDt, pc_inOutExpirateDate, "DDMMYYYY", "YYYY"));
			
				if (pc_inOutExpirateDate != NULL) XMLString::release(&pc_inOutExpirateDate);
			}
			else
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_TOTAL_VALUE))
			{
				char* pc_inOutTotalValue = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
				
				xml_g->addItem("vlTotal",pc_inOutTotalValue);
			
				if (pc_inOutTotalValue != NULL) XMLString::release(&pc_inOutTotalValue);
			}
			else
			if (Util::cmp(pc_field_name, XML_NGN_ATTNM_OUT_INVOICE_ID_STATUS))
			{
				char* pc_inOutInvoiceIDStatus = tuxhp->walkAttr(po_record, XML_NGN_OUT_FIELD, XML_NGN_OUT_FIELD_VALUE, j);
							
				xml_g->addItem("dsStatus", "Pendente");
			
				if (pc_inOutInvoiceIDStatus != NULL) XMLString::release(&pc_inOutInvoiceIDStatus);
			}			
		}
		xml_g->closeTag();		

		XMLString::release(&pc_field_name);
	}

	xml_g->closeTag();
}