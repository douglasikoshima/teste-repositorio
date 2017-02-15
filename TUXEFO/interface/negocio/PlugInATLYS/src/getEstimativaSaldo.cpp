#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"

#include <cstring>

using namespace std;


void PlugInATLYS::getEstimativaSaldo()
{
/*	JAVA -> FO

	<msg>
		<msgHdr>
			<user>2</user>
			<service>TUXPROXYBE</service>
		</msgHdr>
		<msgBody>
			<ProxyOperacao>getEstimativaSaldo</ProxyOperacao>
			<ProxyLinha>1171010160</ProxyLinha>
			<idcontasistemaorigem>123</idcontasistemaorigem>
			<xmlns>test.vivo.com.br/vo</xmlns>
		</msgBody>
	</msg>
*/

	char  cSbscrpId[256];
	char  cAcctNbr[256];
	char* pc_acctNbr = NULL;

	// Trata a tag 'idcontasistemaorigem'
	pc_acctNbr = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);

	if (pc_acctNbr == NULL || *pc_acctNbr == '\0')
	{
		tuxfw_getlogger()->debug("TAG idcontasistemaorigem não encontrada");

		char* pc_Linha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);

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

	strcpy(cSbscrpId, this->getIdLinhaSistemaOrigem());
    tuxfw_getlogger()->debug("cSbscrpId:%s",cSbscrpId); 
	

/*	FO -> GW

	<inputGetUsageSummaryAndBalanceV1 acctNbr="" sbscrpId="" billUnbilledInd="U"/>
*/

	XMLGen o_XMLtoGetUsageSummaryAndBalanceV1;
	DOMNode* po_XMLfromGetUsageSummaryAndBalanceV1 = NULL;

	o_XMLtoGetUsageSummaryAndBalanceV1.createTag(XML_ATL_INPUT_GET_USAGE_BALANCE_V1);
	o_XMLtoGetUsageSummaryAndBalanceV1.addProp(XML_ATL_ACCT_NBR, pc_acctNbr);
	o_XMLtoGetUsageSummaryAndBalanceV1.addProp(XML_ATL_ACCT_SBSCRID, cSbscrpId);
	o_XMLtoGetUsageSummaryAndBalanceV1.addProp(XML_ATL_BILLUNBILLED, XML_VAL_UNBILLED);
	o_XMLtoGetUsageSummaryAndBalanceV1.closeTag();

	po_XMLfromGetUsageSummaryAndBalanceV1 = callRemoteAPI(this->getServiceName(), &o_XMLtoGetUsageSummaryAndBalanceV1, XML_ATL_INPUT_GET_USAGE_BALANCE_V1);

	if ((getLastStatusCode() != NULL) && (strlen(getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());

	if (po_XMLfromGetUsageSummaryAndBalanceV1 == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

/*
	<outputGetUsageSummaryAndBalanceV1>
		..................
		..................
		..................
		<estimatedBalance>  ==> não é mais usado
			<estBal>
				<base crncyCd="xxx" amntDue="xxx"/>
				.........
			</estBal>
			.........
			.........
			.........
		</estimatedBalance>

		<totalAmtDue>  => divida
			<base amntDue="3335.16" crncyCd="986"/>
		</totalAmtDue>
		<estAmtToBeBilled> ==> saldo estimado
			<base amntDue="261.42" crncyCd="986"/>
		</estAmtToBeBilled>


		...................
		...................
		...................
	</outputGetUsageSummaryAndBalanceV1>
*/

	// Verifica se existe o DOM 'outputGetUsageSummaryAndBalanceV1'
	DOMNode* po_outputGetUsageSummaryAndBalanceV1 = NULL;

	// DOM outputGetUsageSummaryAndBalanceV1
	po_outputGetUsageSummaryAndBalanceV1 = tuxhp->walkDOM(po_XMLfromGetUsageSummaryAndBalanceV1, XML_ATL_OUTPUT_GET_USAGE_BALANCE_V1, 0);

	if (po_outputGetUsageSummaryAndBalanceV1 == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_OUTPUTGETUSAGEBALANCEV1, EMSG_ATL_TNE_OUTPUTGETUSAGEBALANCEV1);


//  DOM estimatedBalance
/* 
	Estimativa Saldo, buscando agora da tag estAmtToBeBilled
  
*/

	// Saldo Estimado
	DOMNode* po_estimatedBalance = NULL;
	double   d_EstBal;
	
	po_estimatedBalance = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_EST_AMT_TO_BE_BILLED, 0);

	if (po_estimatedBalance == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_EST_AMT_TO_BE_BILLED, EMSG_ATL_TNE_EST_AMT_TO_BE_BILLED);

	d_EstBal = atof(tuxhp->walkAttr(po_estimatedBalance, XML_ATL_BASE, XML_ATL_AMNT_DUE, 0));
	
	// Dívida acumulada de contas anteriores 


/*	Atlys não foi atualizado em produção e portanto essas tags não serão encontradas (11/08/2005)
	DOMNode* po_divida = NULL;	
	double	 d_Divida;

	// DOM dívida das proximas faturas
	po_divida = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_TOTAL_AMT_DUE, 0);

	if (po_divida  == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_TOTAL_AMT_DUE, EMSG_ATL_TNE_TOTAL_AMT_DUE);
	
	d_Divida = atof(tuxhp->walkAttr(po_divida, XML_ATL_BASE, XML_ATL_AMNT_DUE, 0));
*/

	
	// Valor da tarifa anterior
	DOMNode* po_curBall = NULL;
	DOMNode* po_amountDue = NULL;
	double   d_curBall;

	// DOM curBal
	po_curBall = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_CUR_BALL, 0);

	if (po_curBall == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_CUR_BALL, EMSG_ATL_TNE_CUR_BALL);

	// DOM amountDue
	po_amountDue = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_AMOUNT_DUE, 0);

	if (po_amountDue == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_AMOUNT_DUE, EMSG_ATL_TNE_AMOUNT_DUE);

	d_curBall = atof(tuxhp->walkAttr(po_amountDue, XML_ATL_BASE, XML_ATL_AMNT_DUE, 0));	
	
	// Data do fechamento do ciclo
	DOMNode* po_acctBC = NULL;

	po_acctBC = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_ACCT_BC, 0);

	if (po_acctBC == NULL)
		throw new TuxBasicSvcException(ECD_ATL_TNE_ACCT_BC, EMSG_ATL_TNE_ACCT_BC);

	// Data da próxima fatura
	char c_date[16], c_dateRef[16], c_dateNextBill[16];	
	tm   tmActDueDt, tmSysDt;

	// zerando valores
	memset(&tmActDueDt, 0, sizeof(tm));
	memset(&tmSysDt, 0, sizeof(tm));

	// pegando data atual
	CAvailableService::getSysDateBD(c_dateRef);
	tuxfw_getlogger()->debug("SysDateDB: %s", c_dateRef);

	// atribuindo valores
	tmSysDt.tm_year = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "YYYY")) - 1900;
	tmSysDt.tm_mon  = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "MM")) - 1;
	tmSysDt.tm_mday = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "DD"));

	// data da próxima fatura
	strcpy(c_dateRef, tuxhp->walkAttr(po_curBall, XML_ATL_CUR_BALL, XML_ATL_ACT_DUE_DATE, 0));
	tuxfw_getlogger()->debug("curBal.actDueDt: %s", c_dateRef);

	tmActDueDt.tm_year = atoi(Util::formateDate(c_date, c_dateRef, DATE_FORMAT_ATL_OUT, "YYYY")) - 1900;
	tmActDueDt.tm_mon  = atoi(Util::formateDate(c_date, c_dateRef, DATE_FORMAT_ATL_OUT, "MM")) - 1;
	tmActDueDt.tm_mday = atoi(Util::formateDate(c_date, c_dateRef, DATE_FORMAT_ATL_OUT, "DD"));

	time_t tActDueDt = mktime(&tmActDueDt);
	time_t tSysDt    = mktime(&tmSysDt);

	if(difftime(tSysDt, tActDueDt) >= 0)
	{
		// vamos calcular a data da próxima fatura
		tm   tmNextBill;

		memcpy(&tmNextBill, &tmActDueDt, sizeof(tm));

		// dezembro
		if(tmNextBill.tm_mon == 11)
		{
			tmNextBill.tm_mon = 0;
			tmNextBill.tm_year += 1;
		}				
		else
			tmNextBill.tm_mon += 1;

		sprintf(c_dateNextBill, "%.2d/%.2d/%.4d", tmNextBill.tm_mday, tmNextBill.tm_mon + 1, tmNextBill.tm_year + 1900);

	}
	else
	{
		sprintf(c_dateNextBill, "%.2d/%.2d/%.4d", tmActDueDt.tm_mday, tmActDueDt.tm_mon + 1, tmActDueDt.tm_year + 1900);
	}

	// calculando o saldo parcial
	double d_saldoParcial;

	/* alteraram a api para não usar mais algorítimo de saldo
	if(difftime(tActDueDt, tSysDt) >= 0)
		d_saldoParcial = d_EstBal - d_curBall;
	else
		d_saldoParcial = d_EstBal;
	*/

	d_saldoParcial = d_EstBal;

	// gerando log do saldo parcial
	tuxfw_getlogger()->debug("estimatedBalance.amntDue: %.2f", d_EstBal);
	tuxfw_getlogger()->debug("curBal.amntDue: %.2f", d_curBall);
	tuxfw_getlogger()->debug("Saldo Parcial: %.2f", d_saldoParcial);

	// Somando Minutos e Torpedos utilizados
	double d_minutosUtilizados  = 0;
	int    i_torpedosUtilizados = 0;

	DOMNode* po_usgSummary = NULL;
	DOMNode* po_usageAccum = NULL;

	// DOM usgSummary
	po_usgSummary = tuxhp->walkDOM(po_outputGetUsageSummaryAndBalanceV1, XML_ATL_USG_SUMMARY, 0);

	if(po_usgSummary != NULL)
	{
		// DOM usageAccum
		for(int j = 0; (po_usageAccum = tuxhp->walkDOM(po_usgSummary, XML_ATL_USAGE_ACCUM, j)) != NULL; j++ )
		{
			DOMNode* po_details = tuxhp->walkDOM(po_usageAccum, XML_ATL_DETAILS, 0);
			if(po_details != NULL)
			{
				char *pc_eventType = tuxhp->walkAttr(po_details, XML_ATL_DETAILS, XML_ATL_EVENT_TYPE, 0);
				if(pc_eventType != NULL)
				{
					if(strcmp(pc_eventType, XML_VAL_VOICE) == 0)
						d_minutosUtilizados += atof(tuxhp->walkAttr(po_usageAccum, XML_ATL_USAGE_ACCUM, XML_ATL_INCL_UNITS_USED, 0));
					else if(strcmp(pc_eventType, XML_VAL_SMS) == 0)
						i_torpedosUtilizados += atoi(tuxhp->walkAttr(po_usageAccum, XML_ATL_USAGE_ACCUM, XML_ATL_INCL_UNITS_USED, 0));

				}
			}
		}
	}


/*	FO -> JAVA

	<LupaFaturamentoPosVO>
		<LFEstimativa>
				<dtFechamentoCiclo></dtFechamentoCiclo>
				<dtProximaFatura></dtProximaFatura>
				<qtMinutosUtilizados></qtMinutosUtilizados>
				<qtTorpedosUtilizados></qtTorpedosUtilizados>
				<dsEstimativaSaldo></dsEstimativaSaldo>
				<dsDivida></dsDivida>
				<dsEstimativaProxFatura></dsEstimativaProxFatura>
		</LFEstimativa>
	</LupaFaturamentoPosVO>
*/

	if(!this->bOperacaoInterna)
	{
		char c_Tag[256];

		xml_g->createTag(TAG_XML_OUT_LUPA_FATURAMENTO_POS_VO);

			// Pega o valor da tag 'xmlns' de entrada do xml
			char* pc_xmlns = NULL;

			pc_xmlns = tuxhp->walkTree(dnode, TAG_XML_IN_XMLNS, 0);

			if(pc_xmlns != NULL)
				xml_g->addProp(PROP_XML_OUT_XMLNS, pc_xmlns);

			xml_g->createTag(TAG_XML_OUT_LF_ESTIMATIVA_SALDO);

				char *pc_date = tuxhp->walkAttr(po_acctBC, XML_ATL_ACCT_BC, XML_ATL_BILL_CYCLE_END_DATE, 0);

				xml_g->addItem(TAG_XML_OUT_DT_FECHAMENTO_CICLO, Util::formateDate(c_date, pc_date, DATE_FORMAT_ATL_OUT, "DD/MM/YYYY"));

				xml_g->addItem(TAG_XML_OUT_DT_PROXIMA_FATURA, c_dateNextBill);

				sprintf(c_Tag, "%.2f", d_minutosUtilizados);
				xml_g->addItem(TAG_XML_OUT_QT_MINUTOS_UTILIZADOS, c_Tag);

				xml_g->addItem(TAG_XML_OUT_QT_TORPEDOS_UTILIZADOS, i_torpedosUtilizados);
				
				sprintf(c_Tag, "%.2f", d_EstBal);
				xml_g->addItem(TAG_XML_OUT_DS_ESTIMATIVA_SALDO, c_Tag);

				//sprintf(c_Tag, "%.2f", d_Divida);
				//xml_g->addItem(TAG_XML_OUT_DS_DIVIDA, c_Tag);

			
			xml_g->closeTag();

		xml_g->closeTag();
		// Registro de contato
		this->registrarContato();
	}
	else
	{
		this->m_saldoParcial = d_saldoParcial;
		this->m_minutosUtilizados = d_minutosUtilizados;
	}

}
