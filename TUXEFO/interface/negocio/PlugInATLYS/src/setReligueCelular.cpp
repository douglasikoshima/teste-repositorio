#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"
#include "../../PlugInBE/include/Parametro.h"

void PlugInATLYS::setReligueCelular()
{


/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>setReligueCelular</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<xmlns>test.vivo.com.br/vo</xmlns>
			<refFatura>03/2005</refFatura>
			<dataPgto>10/03/2005</dataPgto>
			<codBanco>1</codBanco>
			<valor>27.00</valor>
		</msgBody>
	</msg>
*/
	char  cAcctNbr[256]     = "";
	char  cInvoiceMonth[12] = "";
	char  cPymtDt[12]       = "";
	char  cBankCd[12]       = "";
	char  cNow[32]          = "";
	char  cValor[256]       = "";
	char  c_date[32]        = "";
	
	char* pc_acctNbr = NULL;
	char* pcTagXmlIn = NULL;
	char* pc_Linha = NULL;
	CParametro param;
	char sgSistemaOrigem[256];
	
	memset(sgSistemaOrigem,0,sizeof(sgSistemaOrigem));
	param.getSistemaOrigem(getUser(), sgSistemaOrigem);
	

	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL || *pc_acctNbr == '\0')
	{
		tuxfw_getlogger()->debug("TAG idcontasistemaorigem não encontrada");

		pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

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
	
	// TAG_XML_IN_REF_FATURA
	pcTagXmlIn = tuxhp->walkTree(dnode, TAG_XML_IN_REF_FATURA, 0);

	if (pcTagXmlIn == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_REF_FATURA, EMSG_TAG_XML_IN_NE_REF_FATURA);
	
	if (*pcTagXmlIn == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_REF_FATURA, EMSG_TAG_XML_IN_VV_REF_FATURA);

	Util::formateDate(cInvoiceMonth, pcTagXmlIn, "MM/YYYY", "YYYYMM");
	tuxfw_getlogger()->debug("refFatura: %s", cInvoiceMonth);

	
	// TAG_XML_IN_DATA_PGTO
	pcTagXmlIn = tuxhp->walkTree(dnode, TAG_XML_IN_DATA_PGTO, 0);

	if (pcTagXmlIn == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_DATA_PGTO, EMSG_TAG_XML_IN_NE_DATA_PGTO);
	
	if (*pcTagXmlIn == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_DATA_PGTO, EMSG_TAG_XML_IN_VV_DATA_PGTO);

	Util::formateDate(cPymtDt, pcTagXmlIn, "DD/MM/YYYY", "YYYY-MM-DD");
	tuxfw_getlogger()->debug("dataPgto: %s", cPymtDt);


	// TAG_XML_IN_BANCO
	pcTagXmlIn = tuxhp->walkTree(dnode, TAG_XML_IN_BANCO, 0);

	if (pcTagXmlIn == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_BANCO, EMSG_TAG_XML_IN_NE_BANCO);
	
	if (*pcTagXmlIn == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_BANCO, EMSG_TAG_XML_IN_VV_BANCO);

	strcpy(cBankCd, pcTagXmlIn);
	tuxfw_getlogger()->debug("banco: %s", cBankCd);

	// TIME
	CAvailableService::getSysDateBD(cNow, "YYYY-MM-DD HH24:MI:SS");
	cNow[10] = 'T';
	tuxfw_getlogger()->debug("now: %s", cNow);

	// TAG_XML_IN_VALOR
	pcTagXmlIn = tuxhp->walkTree(dnode, TAG_XML_IN_VALOR, 0);

	if (pcTagXmlIn == NULL)
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_NE_VALOR, EMSG_TAG_XML_IN_NE_VALOR);
	
	if (*pcTagXmlIn == '\0')
		throw new TuxBasicSvcException(ECD_TAG_XML_IN_VV_VALOR, EMSG_TAG_XML_IN_VV_VALOR);

	strcpy(cValor, pcTagXmlIn);
	tuxfw_getlogger()->debug("valor: %s", cValor);

	
/*	FO -> GW

	<inputMarkInvoiceAsPaid acctNbr="0034414372" invoiceMonth="200512" pymtDt="20051212" pymtAmt="10" bankCd="12"/>
*/

	XMLGen o_XMLtoMarkInvoiceAsPaid;
	DOMNode* po_XMLfromMarkInvoiceAsPaid = NULL;

	o_XMLtoMarkInvoiceAsPaid.createTag(XML_ATL_INPUT_MARK_INVOICE_AS_PAID);
	o_XMLtoMarkInvoiceAsPaid.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);
	o_XMLtoMarkInvoiceAsPaid.addProp(XML_ATL_INVOICE_MONTH, cInvoiceMonth);
	o_XMLtoMarkInvoiceAsPaid.addProp(XML_ATL_PYMT_DT, cPymtDt);
	o_XMLtoMarkInvoiceAsPaid.addProp(XML_ATL_PYMT_AMT, cValor);
	o_XMLtoMarkInvoiceAsPaid.addProp(XML_ATL_BANK_CD, cBankCd);
	o_XMLtoMarkInvoiceAsPaid.addProp("PERSON_ID", sgSistemaOrigem);
	o_XMLtoMarkInvoiceAsPaid.closeTag();

	po_XMLfromMarkInvoiceAsPaid = callRemoteAPI(this->getServiceName(), &o_XMLtoMarkInvoiceAsPaid, XML_ATL_INPUT_MARK_INVOICE_AS_PAID);

	this->trataErroF2(po_XMLfromMarkInvoiceAsPaid, XML_ATL_INPUT_MARK_INVOICE_AS_PAID);
/*
	<outputMarkInvoiceAsPaid/>
*/

	// Verifica se existe o DOM 'outputMarkInvoiceAsPaid'
	DOMNode* po_outputMarkInvoiceAsPaid = NULL;
	char*acctStatus = NULL;
	bool findAccessNbr = false;

	// DOM outputPendingPymtRstrl
	po_outputMarkInvoiceAsPaid = tuxhp->walkDOM(po_XMLfromMarkInvoiceAsPaid, XML_ATL_OUTPUT_MARK_INVOICE_AS_PAID, 0);
	// recupera o status da conta
	acctStatus = tuxhp->walkAttr(po_XMLfromMarkInvoiceAsPaid,XML_ATL_OUTPUT_MARK_INVOICE_AS_PAID,"acctStatus",0);
	if(po_outputMarkInvoiceAsPaid == NULL)
	{
		tuxfw_getlogger()->debug("TAG outputMarkInvoiceAsPaid não encontrada");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);		
	}
	if(acctStatus == NULL)
	{
		tuxfw_getlogger()->debug("Atributo acctStatus não encontrado");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}
	else if(!strcmp(acctStatus,"N"))
	{
		tuxfw_getlogger()->debug("AcctStatus igual a N");
		throw new TuxBasicSvcException(EDC_ATL_CODE_BLOQUEIO_COBRANCA, MSG_ATL_CODE_BLOQUEIO_COBRANCA);	
	}
	else if(!strcmp(acctStatus,"A") || !strcmp(acctStatus,"F"))
	{
		XMLGen xmlGenInput;
		DOMNode* outputSearchCustAcctSbscrpByAttribute = NULL;
		xmlGenInput.createTag(XML_ATL_INPUT_SEARCH_SBSCRP_BY_ATTRIBUTE);
		xmlGenInput.addProp(XML_ATL_ATTR_NAME,"ACCESS_NBR");
		xmlGenInput.addProp(XML_ATL_ATTR_VALUE,pc_Linha);
		xmlGenInput.closeTag();
		outputSearchCustAcctSbscrpByAttribute = callRemoteAPI(this->getServiceName(), &xmlGenInput, XML_ATL_INPUT_SEARCH_SBSCRP_BY_ATTRIBUTE);
		if(outputSearchCustAcctSbscrpByAttribute == NULL)
		{
			tuxfw_getlogger()->debug("TAG outputSearchCustAcctSbscrpByAttribute não encontrada");
			throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);	
		}
		DOMNode* acsNbrInfo = NULL;
		char*accessNbr = NULL;
		char*acsNbrStatus = NULL;		
		for(int i=0;(acsNbrInfo = tuxhp->walkDOM(outputSearchCustAcctSbscrpByAttribute,"acsNbrInfo",i))!=NULL;i++)
		{
			accessNbr = tuxhp->walkAttr(acsNbrInfo,"acsNbrInfo","accessNbr",0);
			acsNbrStatus = tuxhp->walkAttr(acsNbrInfo,"acsNbrInfo","acsNbrStatus",0);
			// achou o numero de acesso
			if(accessNbr!= NULL && !strcmp(accessNbr,pc_Linha))
			{
				tuxfw_getlogger()->debug("acsNbrStatus = %s",acsNbrStatus);
				findAccessNbr = true;
				if(acsNbrStatus == NULL)
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_INVALIDO, MSG_ATL_CODE_STATUS_INVALIDO);
				else if(!strcmp(acsNbrStatus,"E"))
					break;
				else if(!strcmp(acsNbrStatus,"S"))
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_SUSPENSO, MSG_ATL_CODE_STATUS_SUSPENSO);
				else if(!strcmp(acsNbrStatus,"H"))
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_HOTLINE, MSG_ATL_CODE_STATUS_HOTLINE);
				else if(!strcmp(acsNbrStatus,"D"))
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_DESABILITADO, MSG_ATL_CODE_STATUS_DESABILITADO);
				else if(!strcmp(acsNbrStatus,"P"))
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_TROCA_NUM_PENDENTE, MSG_ATL_CODE_STATUS_TROCA_NUM_PENDENTE);
				else
					throw new TuxBasicSvcException(EDC_ATL_CODE_STATUS_OUTROS, MSG_ATL_CODE_STATUS_OUTROS);
			}
		}
	}
	else
	{
		tuxfw_getlogger()->debug("AcctStatus não esperado");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}

	if(!findAccessNbr)
	{
		tuxfw_getlogger()->debug("Não achou o número de acesso");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}

	xml_g->createTag(TAG_XML_OUT_RELIGUE_CELULAR_VO);
	
	if((pcTagXmlIn = tuxhp->walkTree(dnode,TAG_XML_IN_XMLNS, 0)) != NULL)
	{
		xml_g->addProp( PROP_XML_OUT_XMLNS, pcTagXmlIn );
	};	

	xml_g->closeTag();

	// Registro de contato
	this->registrarContato();

}