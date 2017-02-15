#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInATLYS::getInfoFaturamento()
{

	char* pcIdContaATL;
	int iIdContaATL;
	DOMNode* poResultNode;
	int i=0;
	char* pcCode;

	char sInCob[2];
	char sFormaPagto[256];
	char sCiclo[256];
	char sSaldo[256];
	char sDtVencto[256];
	
	// Recupera id da conta no atlys.
	pcIdContaATL = tuxhp->walkTree(dnode, TAG_XML_IN_ID_CONTA_SIS_ORIGEM, 0);
	if (pcIdContaATL == NULL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_NE, MSG_ID_CONTA_SIS_ORG_NE);
	if (!*pcIdContaATL)
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VV, MSG_ID_CONTA_SIS_ORG_VV);
	if ((iIdContaATL = atoi(pcIdContaATL)) <= 0) 
		throw new TuxBasicSvcException(NRO_ID_CONTA_SIS_ORG_VI, MSG_ID_CONTA_SIS_ORG_VI);

	// Chama a API inputGetCollAcctStateV2, pra saber se a conta está em cobranca
	XMLGen oXMLtoGetCollAcctStateV2;

	oXMLtoGetCollAcctStateV2.createTag(XML_ATL_INPUT_COLL_ACCT_STATE);
	oXMLtoGetCollAcctStateV2.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLtoGetCollAcctStateV2.closeTag();

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetCollAcctStateV2, XML_ATL_INPUT_COLL_ACCT_STATE);

	if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') 
		if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_CONTA_NAO_COB)) 
			sprintf(sInCob, "N");
		else
			throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	else
		sprintf(sInCob, "S");

	// Busca forma de pagamento	
	XMLGen oXMLtoAtlInputPaymtMethod;

	oXMLtoAtlInputPaymtMethod.createTag(XML_ATL_INPUT_PAYMT_METHOD);
	oXMLtoAtlInputPaymtMethod.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLtoAtlInputPaymtMethod.closeTag();

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoAtlInputPaymtMethod, XML_ATL_INPUT_PAYMT_METHOD);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
                throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	if (poResultNode == NULL)
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Faz a consulta no banco de dados para buscar forma de pagamento
	char *pymt = tuxhp->walkAttr(poResultNode, XML_ATL_OUTPUT_PAYMT_METHOD, XML_ATL_PYMT_TYPE_CODE, 0);
	char* traduzido = traduzFormaPagto(pymt);
	sprintf(sFormaPagto, "%s", traduzido);

	// Traz os dados de saldo e ciclo de faturamento
	XMLGen oXMLtoGetAtlInputBillV2;

	oXMLtoGetAtlInputBillV2.createTag(XML_ATL_INPUT_BILLV2);
	oXMLtoGetAtlInputBillV2.addProp(XML_ATL_ACCT_NBR, pcIdContaATL);
	oXMLtoGetAtlInputBillV2.closeTag();
	
	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetAtlInputBillV2, XML_ATL_INPUT_BILLV2);

	if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') 
	{
		// Checa caso de linha não encontrada.
		if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) 
		{
			sprintf(sSaldo,"");
			sprintf(sCiclo,"");
			sprintf(sDtVencto,"");
			sprintf(sInCob, "");
		} 
		else
			throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
    }
	else if (poResultNode == NULL)
	{
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}
	else
	{
		char* pcDtVencto = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, XML_ATL_PYMT_DUE_DATE, 0);
		formataData(pcDtVencto);
		sprintf(sDtVencto,"%2.2s", pcDtVencto);
		
		if(!strcmp(sInCob, "S"))
		{
			char c_date[16], c_dateRef[16];
			tm   tmVenc, tmSysDt;

			// zerando valores
			memset(&tmVenc, 0, sizeof(tm));
			memset(&tmSysDt, 0, sizeof(tm));

			// pegando data atual
			CAvailableService::getSysDateBD(c_dateRef);
			tuxfw_getlogger()->debug("SysDateDB: %s", c_dateRef);

			// atribuindo valores
			tmSysDt.tm_year = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "YYYY")) - 1900;
			tmSysDt.tm_mon  = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "MM")) - 1;
			tmSysDt.tm_mday = atoi(Util::formateDate(c_date, c_dateRef, "YYYY-MM-DD", "DD"));

			tuxfw_getlogger()->debug("pcDtVencto: %s", pcDtVencto);

			tmVenc.tm_year = atoi(Util::formateDate(c_date, pcDtVencto, "DD/MM/YYYY", "YYYY")) - 1900;
			tmVenc.tm_mon  = atoi(Util::formateDate(c_date, pcDtVencto, "DD/MM/YYYY", "MM")) - 1;
			tmVenc.tm_mday = atoi(Util::formateDate(c_date, pcDtVencto, "DD/MM/YYYY", "DD"));
			
			time_t tVenc  = mktime(&tmVenc);
			time_t tSysDt = mktime(&tmSysDt);

			// 86400 é um dia em segundos
			if(difftime(tVenc, tSysDt) > (0 * 86400) )
			{
				strcpy(sInCob, "N");
			}
		}		

		char* pcSaldo = tuxhp->walkAttr(tuxhp->walkDOM(poResultNode, XML_ATL_TOTAL_AMT_DUE, 0), XML_ATL_BASE, XML_ATL_AMNT_DUE, 0);	
		sprintf(sSaldo,"%s", pcSaldo);
		pcCode = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, XML_ATL_CYCLE_CD, 0);
		
		// Busca as dads do ciclo de faturamento
		char* pcCicloSt = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, "cycleStDt", 0);
		char* pcCicloEnd = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, "cycleEndDt", 0);
		formataData(pcCicloSt);
		formataData(pcCicloEnd);
		sprintf(sCiclo,"%5.5s - %5.5s", pcCicloSt, pcCicloEnd);
	}	

	// Monta o XML de saída
	xml_g->createTag(TAG_XML_OUT_TI_FATURAMENTO);
	xml_g->addProp(PROP_XML_OUT_XMLNS, PROP_XML_OUT_VAL_XMLNS);
	xml_g->addItem(TAG_XML_OUT_SALDO, sSaldo);
	xml_g->addItem(TAG_XML_OUT_DS_CICLO_FATURA, sCiclo);
	xml_g->addItem(TAG_XML_OUT_DT_VENCIMENTO, sDtVencto);
	xml_g->addItem(TAG_XML_OUT_IN_CONTA_COBRANCA, sInCob);
	xml_g->addItem(TAG_XML_OUT_DS_FORMA_PAGAMENTO, sFormaPagto);
	xml_g->closeTag();
}
