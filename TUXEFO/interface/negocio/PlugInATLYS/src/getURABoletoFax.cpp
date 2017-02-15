#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"

#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;


void PlugInATLYS::getURABoletoFax()
{
	char* pcProxyLinha;
	int iProxyLinha;
	DOMNode* poResultNode;
	
	// Recupera numero da linha
	pcProxyLinha = tuxhp->walkTree(dnode, TAG_XML_IN_LINHA, 0);
	if (pcProxyLinha == NULL)
		throw new TuxBasicSvcException(NRO_NR_LINHA_SIS_ORG_NE, MSG_NR_LINHA_SIS_ORG_NE);
	if (!*pcProxyLinha)
		throw new TuxBasicSvcException(NRO_NR_LINHA_SIS_ORG_VV, MSG_NR_LINHA_SIS_ORG_VV);
	if ((iProxyLinha = atoi(pcProxyLinha)) <= 0) 
		throw new TuxBasicSvcException(NRO_NR_LINHA_SIS_ORG_VI, MSG_NR_LINHA_SIS_ORG_VI);

	char idContaSistemaOrigem[256];
	char* pcIdConta = Util::getIdContaSistemaOrigem(idContaSistemaOrigem, pcProxyLinha);
	if (pcIdConta == NULL)
		throw new TuxBasicSvcException(NRO_CONTA_NAO_ENCONTRADA, MSG_CONTA_NAO_ENCONTRADA);

	// Chama a API getBillDates para achar o ctEndCycle
	XMLGen oXMLtoGetInputBillDates;

	oXMLtoGetInputBillDates.createTag(XML_ATL_INPUT_GET_BILL_DATES);
	oXMLtoGetInputBillDates.addProp(XML_ATL_ACCT_NBR, pcIdConta);

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputBillDates, XML_ATL_INPUT_GET_BILL_DATES);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') {
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) {
			xml_g->addItem("statCom",1);
                        xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO,"00");
                        return;
                } else
                        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        }
        if (poResultNode == NULL)
                throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	char *cycleEndDt = NULL;
	char *pcCycleEndDt = NULL;
	char *mesAno = NULL;
	int k=0;
	mesAno = tuxhp->walkTree(dnode, "mesRef", 0); // MMYYYY
	if(mesAno != NULL)
	{
		while ((cycleEndDt = tuxhp->walkAttr(poResultNode, "billDt", XML_ALT_CYCLEENDDT, k++)) != NULL) 
		{		
			// cycleEndDt YYYY-MM-DD
			if(strlen(cycleEndDt) > 9 && strlen(mesAno) > 5)
			{		
				// Compara o mês e o ano
				if(mesAno[0]  == cycleEndDt[5] && mesAno[1]  == cycleEndDt[6] &&
					mesAno[2]  == cycleEndDt[0] && mesAno[3]  == cycleEndDt[1] &&
					mesAno[4]  == cycleEndDt[2] && mesAno[5]  == cycleEndDt[3])
				{
					pcCycleEndDt = cycleEndDt;
				}
			}
		}
	}

	// Chama a API getBill para recuperar as informações da conta
	XMLGen oXMLtoGetInputBillV2;

	oXMLtoGetInputBillV2.createTag(XML_ATL_INPUT_BILLV2);
	oXMLtoGetInputBillV2.addProp(XML_ATL_ACCT_NBR, pcIdConta);	
	if(pcCycleEndDt != NULL)
	{
		oXMLtoGetInputBillV2.addProp(XML_ALT_CYCLEENDDT, pcCycleEndDt);	
	}

	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputBillV2, XML_ATL_INPUT_BILLV2);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') {
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) {
			xml_g->addItem("statCom",1);
                        xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO,"00");
                        return;
                } else
                        throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        }
        if (poResultNode == NULL)
                throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Recupera informações da api GetBill
	char* pcValorConta = tuxhp->walkAttr(tuxhp->walkDOM(poResultNode, XML_ATL_TOTAL_AMT_DUE, 0), XML_ATL_BASE, XML_ATL_AMNT_DUE, 0);
	char* pcDataFaturam = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, XML_ATL_LAST_BILL_DATE, 0);
	formataData(pcDataFaturam); 
	char* pcDataVencto = tuxhp->walkAttr(poResultNode, XML_ATL_BILL, XML_ATL_PYMT_DUE_DATE, 0);
	formataData(pcDataVencto);

	// Chama a API para recuperar a forma de pagamento
	XMLGen oXMLGetInputPymtMethodAcct;

	oXMLGetInputPymtMethodAcct.createTag(XML_ATL_INPUT_PYMT_METHOD_ACCT);
	oXMLGetInputPymtMethodAcct.addProp(XML_ATL_ACCT_NBR, pcIdConta);
	
    poResultNode = callRemoteAPI(this->getServiceName(), &oXMLGetInputPymtMethodAcct, XML_ATL_INPUT_PYMT_METHOD_ACCT);
        if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E')
                throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
        if (poResultNode == NULL)
                throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);

	// Verifica se o cliente tem débito automático
	char* pcPymtCd;
	char sIndDeb[2];
	sprintf(sIndDeb, "2");
	int i=0;
	while ((pcPymtCd = tuxhp->walkAttr(poResultNode, XML_ATL_PYMT_METHOD, XML_ATL_PYMT_CODE, i++)) != NULL) 
		if (checaDebitoAutomatico(pcPymtCd))
			sprintf(sIndDeb, "1");

	// Gera XML de saída.
	char sMes[3];
	char sNMFull[1024];
	char pcNrDocto[256];
	memset(&sNMFull, 0, 1024);
	memset(&pcNrDocto,0,256);
	char line[10];
	char ddd[3];
	memset(&line,0,sizeof(line));
	memset(&ddd,0,sizeof(ddd));
	sprintf( ddd, "%.2s", pcProxyLinha );
	sprintf( line, "%s", (char*)&pcProxyLinha[2] );

	int retorno = Util::getDadosPessoa(sNMFull,pcNrDocto,line,ddd);
	sprintf(sMes, "%2.2s", pcDataFaturam);
	xml_g->addItem("statCom", 1);
	xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO, "00");
	montaXMLEndereco(pcIdConta);
	xml_g->addItem(TAG_XML_OUT_DT_MES_REFERENCIA_PAGAMENTO, sMes);
	xml_g->addItem(TAG_XML_OUT_DS_NOME_COMPLETO_CLIENTE, sNMFull);
	xml_g->addItem(TAG_XML_OUT_DT_DATA_EMISSAO_CONTA, pcDataFaturam);
	xml_g->addItem(TAG_XML_OUT_DS_NUMERO_CPF_CLIENTE, pcNrDocto);
	xml_g->addItem(TAG_XML_OUT_DS_NUMERO_DA_CONTA, pcIdConta);
	xml_g->addItem(TAG_XML_OUT_NUM_IDENTIFICACAO_DEBITO_AUTOMATICO, sIndDeb);
	xml_g->addItem(TAG_XML_OUT_DT_DATA_VENCIMENTO_CONTA, pcDataVencto);
	xml_g->addItem(TAG_XML_OUT_NUM_SALDO_CONTA_ABERTO, pcValorConta);
	xml_g->addItem(TAG_XML_OUT_NUM_PAGAMENTO_ABERTO, pcValorConta);

	// Registro de contato
	this->registrarContato();

}
