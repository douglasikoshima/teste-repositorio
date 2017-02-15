#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstring>

using namespace std;
 

void PlugInATLYS::getHistoricoFaturamentos()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getHistoricoFaturamento</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<idcontasistemaorigem>1</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char  cAcctNbr[256];
	char  c_date[16];
	char* pc_acctNbr = NULL;
	bool  b_filtraFat = false;
	int   i_qtFaturamentos = 0;
	char* pc_qtFaturamentos = NULL;

	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL || *pc_acctNbr == '\0')
	{
		tuxfw_getlogger()->debug("TAG idcontasistemaorigem não encontrada");

		char* pc_Linha = pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

		if (pc_Linha == NULL || *pc_Linha == '\0')
		{
			tuxfw_getlogger()->debug("TAG ProxyLinha também não encontrada");

			// mensagem em relação ao idcontasistemaorigem
			if (pc_acctNbr == NULL)
				throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_NE_ID_CONTA_SIS_ORIGEM);

			if (*pc_acctNbr == '\0')
				throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM, EMSG_TAG_XML_IN_VV_ID_CONTA_SIS_ORIGEM);

		}

		pc_acctNbr = Util::getIdContaSistemaOrigem(cAcctNbr, pc_Linha);

		if(pc_acctNbr == NULL || *pc_acctNbr == '\0')
			throw new TuxBasicSvcException(ECD_ID_CONTA_NOT_FOUND, EMSG_ID_CONTA_NOT_FOUND);

	}

	tuxfw_getlogger()->debug("IdContaSistemaOrigem: %s", pc_acctNbr);
	this->setIdContaSistemaOrigem(pc_acctNbr);

	pc_qtFaturamentos = tuxhp->walkTree(dnode, TAG_XML_IN_QT_FATURAMENTOS, 0);

	if(pc_qtFaturamentos != NULL && *pc_qtFaturamentos != '\0')
	{
		i_qtFaturamentos = atoi(pc_qtFaturamentos);

		if(i_qtFaturamentos > 0)
			b_filtraFat = true;
	}

	if(b_filtraFat == true)
	{
		tuxfw_getlogger()->debug("Filtro de Quantidade Faturamento Acionado");
		tuxfw_getlogger()->debug("i_qtFaturamentos: %d", i_qtFaturamentos);
	}

/*  FO -> GW

	<inputGetReferenceDataV2 acctNbr="valor"/>
*/

	XMLGen o_XMLtoGetReferenceDataV2;
	DOMNode* po_XMLfromGetReferenceDataV2 = NULL;

	o_XMLtoGetReferenceDataV2.createTag("inputGetReferenceDataV2");
	
		o_XMLtoGetReferenceDataV2.createTag("tableNm");
		o_XMLtoGetReferenceDataV2.addProp("val", "CYCLE");
		o_XMLtoGetReferenceDataV2.closeTag();
				
	o_XMLtoGetReferenceDataV2.closeTag();

/*
	po_XMLfromGetReferenceDataV2 = callRemoteAPI(this->getServiceName(), &o_XMLtoGetReferenceDataV2, "inputGetReferenceDataV2");

	DOMNode *po_outputGetReferenceDataV2 = NULL;

	po_outputGetReferenceDataV2 = tuxhp->walkDOM(po_XMLfromGetReferenceDataV2, "outputGetReferenceDataV2", 0);

	if (po_outputGetReferenceDataV2 == NULL)
		throw new TuxBasicSvcException("24E0000", "DOM 'outputGetReferenceDataV2' não encontrado!");*/






	/*  FO -> GW

	<inputGetBillDates acctNbr="valor de cAcctNbr"/>
	*/

	XMLGen o_XMLtoGetBillDates;
	DOMNode* po_XMLfromGetBillDates = NULL;

	o_XMLtoGetBillDates.createTag("inputGetBillDates");
	o_XMLtoGetBillDates.addProp("acctNbr", pc_acctNbr);
	o_XMLtoGetBillDates.closeTag();


	// Faz a chamada remota e verifica o retorno
	po_XMLfromGetBillDates = callRemoteAPI(this->getServiceName(), &o_XMLtoGetBillDates, "inputGetBillDates");

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGetBillDates == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);


	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGetBillDates == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	/*	GW -> FO

	<outputGetBillDates>
		<billDt cycleCd="xxx" cycleStDt="xxx" cycleEndDt="xxx"/>
		........................................................
	</outputGetBillDates>
	*/

	// Faz tratamento de erros na API inputGetBillDates
	trataErro(po_XMLfromGetBillDates,XML_ATL_INPUT_GET_BILL_DATES);

	// Verifica se existe o DOM 'outputGetBillDates'
	DOMNode *po_outputGetBillDates = NULL;

	po_outputGetBillDates = tuxhp->walkDOM(po_XMLfromGetBillDates, "outputGetBillDates", 0);

	if (po_outputGetBillDates == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_OUTPUTGETBILLDATES, EMSG_ATL_TNE_OUTPUTGETBILLDATES);


	DOMNode* po_XMLfromGetDocumentDetailsByAccount = NULL;


/*	GW -> FO

	<outputGetDocumentDetailsByAccount>
		<billDt cycleCd="xxx" cycleStDt="xxx" cycleEndDt="xxx"/>
		........................................................
	</outputGetDocumentDetailsByAccount>
*/



	XMLGen oXMLtoGetDocumentDetailsByAccount;
	oXMLtoGetDocumentDetailsByAccount.createTag(XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT);
	oXMLtoGetDocumentDetailsByAccount.addProp(XML_ATL_ACCT_NBR,pc_acctNbr);
	oXMLtoGetDocumentDetailsByAccount.addProp("details","");
	po_XMLfromGetDocumentDetailsByAccount  = callRemoteAPI(this->getServiceName(), &oXMLtoGetDocumentDetailsByAccount, XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT);

	// Faz a chamada remota e verifica o retorno	

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGetDocumentDetailsByAccount == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);




	// Faz tratamento de erros na API inputGetBillDates
	trataErro(po_XMLfromGetDocumentDetailsByAccount,XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT);


	char* pc_code = NULL;


	xml_g->createTag("LupaFaturamentoPosVO");

	char* pc_xmlns = NULL;

	pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

	if(pc_xmlns != NULL)
		xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

	
	po_XMLfromGetDocumentDetailsByAccount= tuxhp->walkDOM(po_XMLfromGetDocumentDetailsByAccount, "outputGetDocumentDetailsByAccount",0);

	DOMNode* po_record;

	char cCycleEndDt[20] = "";
	char cCycleCd[30] = "";
	char cDocTypeCd[100] = "";
	char cDocRmngAmt[20] = "";		
	double valor = 0.0;	
	char cDesc[50] = "";
	char cfnclPblnId[20] = "";
	char* pc_cycleEndDt = NULL;
	char cCycleEndDtBillDates[12] = "";
	char* cycleCdBillDates = NULL;
	DOMNode* po_billDt = NULL;
	unsigned int i = 0;
	unsigned int k = 0;
	
	for (i = 0; (po_billDt = tuxhp->walkDOM(po_outputGetBillDates, "billDt", i)) != NULL, i_qtFaturamentos > 0; i++, i_qtFaturamentos--)
	{
		pc_cycleEndDt	 = tuxhp->getAttrValue(po_billDt, "cycleEndDt");		

		cycleCdBillDates = tuxhp->getAttrValue(po_billDt, "cycleCd");

		Util::formateDate(cCycleEndDtBillDates, pc_cycleEndDt, "YYYY-MM-DD", "YYYYMMDD");

		for (k = 0; (po_record= tuxhp->walkDOM(po_XMLfromGetDocumentDetailsByAccount, "documentDetails", k)) != NULL; k++)
		{
			strcpy(cDocTypeCd, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, XML_ATL_DOC_TYPE_CD, 0));		
			
			if(strcmp(cDocTypeCd,"1") == 0 )  //Tipo Fatura
			{
				strcpy(cfnclPblnId, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, "fnclPblnId", 0));

				sprintf( cCycleEndDt, "%s", (char*)&cfnclPblnId[2]);

				strncpy(cCycleCd, cfnclPblnId, 2);

				if (strcmp (cCycleEndDt, cCycleEndDtBillDates) == 0 && strcmp(cCycleCd, cycleCdBillDates) == 0)
				{					
					xml_g->createTag("LFFaturamento");

					xml_g->addItem("idConta", pc_acctNbr);
					
					xml_g->addItem("nrDia", Util::formateDate(c_date, cCycleEndDt, "YYYYMMDD", "DD"));
					// Attribui o valor do mes na tag de saida 'nrMes'

					xml_g->addItem("nrMes", Util::formateDate(c_date, cCycleEndDt, "YYYYMMDD", "MM"));
					// Attribui o valor do mes na tag de saida 'nrAno'

					xml_g->addItem("nrAno", Util::formateDate(c_date, cCycleEndDt, "YYYYMMDD", "YYYY"));		
					// Se a descrição for FATURA MENSAL e o tipo de código for igual a i
			
				/*	for (unsigned int x = 0; (pc_code = tuxhp->walkAttr(po_outputGetReferenceDataV2, "refDataLst", "code", x)) != NULL; x++)
						if (Util::cmp(cCycleCd, pc_code))
							strcpy(cDesc, tuxhp->walkAttr(po_outputGetReferenceDataV2, "refDataLst", "desc", x));*/

					// Atribui o valor de 'bill:cycleCd' traduzido (refDataLst:desc) ou sem traducao se 'refDataLst:code' nao for encontrado
					//xml_g->addItem("dsCiclo", (strcmp(cDesc,""))? cDesc : cCycleCd);
		
		            xml_g->addItem("dsCiclo",  cCycleCd);
		            
					xml_g->addItem("dtVencimento", tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, "docDueDt", 0));

					xml_g->addItem("vlTotal", tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, "docDueAmt", 0));
			
					strcpy(cDocRmngAmt, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, XML_ATL_DOC_RMNG_AMT, 0));

					if(strcmp(cDocRmngAmt,"") > 0)
						valor = atof(cDocRmngAmt);		
					else
						valor = 0.0;
				
					// Conta em aberto

					if(valor > 0.0)							
						xml_g->addItem("dsStatus", "Pendente");
			
					else				
						xml_g->addItem("dsStatus", "Pago");

			
					xml_g->closeTag();	

				}

			}

		}	

	}


	xml_g->closeTag();
	

	// Registro de contato
	this->registrarContato();


}






