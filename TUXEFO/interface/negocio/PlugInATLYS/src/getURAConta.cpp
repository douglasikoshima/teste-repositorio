#include "PlugInATLYS.h"

#include <tuxfw.h>
#include "../../PlugInBE/include/Util.h"
#include "AvailableService.h"
#include "../../commons/include/vectorlist.h"
#include <cstdlib>
#include <cstdio>
#include <cstring>

using namespace std;

struct stValorConta
{
	char valorConta[50];
	char dtVencimento[20];
	char dtFechamento[20];
};

typedef CVectorList<stValorConta> ListValorConta;


FATURAMENTO::iterator itFaturamento;
 

// exemplo de uso (dentro de um cpp)

FATURAMENTO mapFaturamento;

void  PlugInATLYS::	Insere(char *docDueDt, char *docTypeCd, char *docTypeDesc, char *docRmngAmt, char *cfnclPblnId, char *cCycleEndDt, char *CycleCd, char *docDueAmt)
{
    stFaturamento stFaturamentoCliente;

	stFaturamentoCliente.docDueDt	=  docDueDt;
	stFaturamentoCliente.docTypeCd   = docTypeCd;
    stFaturamentoCliente.docTypeDesc = docTypeDesc;
    stFaturamentoCliente.docRmngAmt  = docRmngAmt;
    stFaturamentoCliente.cfnclPblnId = cfnclPblnId;
    stFaturamentoCliente.cCycleEndDt = cCycleEndDt;
    stFaturamentoCliente.cCycleCd	= CycleCd;
	stFaturamentoCliente.docDueAmt	= docDueAmt;

	mapFaturamento[stFaturamentoCliente.docDueDt] = stFaturamentoCliente;

}

void PlugInATLYS::getURAConta()
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


	// Recupera a quantidade de contas em aberto
	// implementação nova segundo o change request
	tuxfw_getlogger()->debug("Chamando a API %s, com %s = %s",XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT,XML_ATL_ACCT_NBR,pcIdConta);
	XMLGen oXMLtoGetDocumentDetailsByAccount;
	oXMLtoGetDocumentDetailsByAccount.createTag(XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT);
	oXMLtoGetDocumentDetailsByAccount.addProp(XML_ATL_ACCT_NBR,pcIdConta);
	oXMLtoGetDocumentDetailsByAccount.addProp("details","O");
	poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetDocumentDetailsByAccount, XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT);
    if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') {
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) {
						xml_g->addItem("statCom", 1);
                        xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO,"00");
                        return;
                } else
                	throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	}
    if (poResultNode == NULL)
         throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);	



	////////////////////////////// novo código     /////////////////////////////////////////////////
	// Buscar todas as datas
	DOMNode*nodeBillDates = NULL;
	XMLGen xmlGenBillDates;
	xmlGenBillDates.createTag(XML_ATL_INPUT_GET_BILL_DATES);
	xmlGenBillDates.addProp(XML_ATL_ACCT_NBR,pcIdConta);
	nodeBillDates = callRemoteAPI(this->getServiceName(), &xmlGenBillDates, XML_ATL_INPUT_GET_BILL_DATES);
    if (getLastStatusCode() != NULL && strlen(getLastStatusCode()) > 3 && getLastStatusCode()[2] == 'E') {
                if (strlen(getLastStatusCode()) == 7 && !strcmp(getLastStatusCode(),ERR_ATL_NO_BILL)) {
						xml_g->addItem("statCom", 1);
                        xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO,"00");
                        return;
                } else
                	throw new TuxBasicSvcException(getLastStatusCode(), getLastStatusText());
	}
    if (nodeBillDates == NULL)
         throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);	


	ListValorConta listValorConta;
	char*cycleEndDt =  tuxhp->walkAttr(nodeBillDates,"billDt",XML_ALT_CYCLEENDDT,0);

	////////////////////////////// novo código fim /////////////////////////////////////////////////

	// percorrer o resultado buscando todas as contas em aberto
	int qtdeContasEmAberto = 0;
	double valorTotalConta = 0;
	char cCycleEndDtBillDates[12] = "";
	char cCycleEndDt[20]   = "";
	char cfnclPblnId[20]   = "";
	char* pc_cycleEndDt    = NULL;
	char* cycleCdBillDates = NULL;
	char cCycleCd[30] = "";

	double valor	  = 0.0;

	DOMNode* po_billDt;
	DOMNode* po_record;
	
	char cDocTypeCd[10]	= "";
	char cDocTypeDesc[100]  = "";
	char cDocRmngAmt[20]    = "";
	char cDocDueDt[30]		= "";			
	char cPymtDueDt[30]		= "";	
	
	char cDocDueAmt [20]="";
	int k = 0;
	mapFaturamento.clear();
	
	double  iValorPendente = 0;

	while((po_record= tuxhp->walkDOM(poResultNode, "documentDetails", k++)) != NULL)
	{
		strcpy(cDocRmngAmt, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, XML_ATL_DOC_RMNG_AMT, 0));

		iValorPendente =  strlen(cDocRmngAmt) == 0 ? 0 : atof(cDocRmngAmt);
	
		strcpy(cDocTypeCd, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, XML_ATL_DOC_TYPE_CD, 0));
			
		if( strcmp(cDocTypeCd,"1") == 0  && iValorPendente > 0) //se é do tipo fatura e está em aberto, insere na lista
		{

			strcpy(cDocTypeDesc, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, XML_ATL_DOC_TYPE_DESC, 0));
		
			strcpy(cDocDueDt, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO,  "docDueDt", 0));

			strcpy(cfnclPblnId, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, "fnclPblnId", 0));

			strcpy(cDocDueAmt, tuxhp->walkAttr(po_record, XML_ATL_DOCINFO, "docDueAmt", 0));

			sprintf( cCycleEndDt, "%s", (char*)&cfnclPblnId[2] );

			strncpy(cCycleCd, cfnclPblnId, 2);	
			
			Insere(cDocDueDt, cDocTypeCd, cDocTypeDesc, cDocRmngAmt, cfnclPblnId, cCycleEndDt, cCycleCd, cDocDueAmt);
		
		}

	}

    FATURAMENTO::iterator itFaturamentoFim;   

	for (unsigned int i = 0; (po_billDt = tuxhp->walkDOM(nodeBillDates, "billDt", i)) != NULL && mapFaturamento.size() ; i++)
	{
	
	    itFaturamentoFim = mapFaturamento.end();

		itFaturamentoFim--;
		
		pc_cycleEndDt	 = tuxhp->getAttrValue(po_billDt, "cycleEndDt");
		
		cycleCdBillDates = tuxhp->getAttrValue(po_billDt, "cycleCd");

		Util::formateDate(cCycleEndDtBillDates, pc_cycleEndDt, "YYYY-MM-DD", "YYYYMMDD");

		k = 0;

		for (k = 0; k < mapFaturamento.size(); k++ )
		{

			strcpy(cDocTypeCd, itFaturamentoFim->second.docTypeCd.c_str());

			strcpy (cDocTypeDesc,  itFaturamentoFim->second.docTypeDesc.c_str());

			strcpy(cDocRmngAmt, itFaturamentoFim->second.docRmngAmt.c_str());

			strcpy(cDocDueDt, itFaturamentoFim->second.docDueDt.c_str());
			
			strcpy(cfnclPblnId, itFaturamentoFim->second.cfnclPblnId.c_str());

			sprintf( cCycleEndDt, "%s", (char*)&cfnclPblnId[2] );

			strncpy(cCycleCd, cfnclPblnId, 2);
		
			if (strcmp (cCycleEndDt, cCycleEndDtBillDates) == 0 && strcmp(cCycleCd, cycleCdBillDates) == 0)
			{					

				if(strlen(cDocRmngAmt)!= 0)
					valor = atof(cDocRmngAmt);	
					
				// Conta vencida
				if(Util::IsValidDate(cDocDueDt))
					m_qtContasVencidas++;
				
				// Conta em aberto
					
				valorTotalConta += valor;
				qtdeContasEmAberto++;	

				if (listValorConta.sizeOf() < 4)
				{
					stValorConta *novo = new stValorConta;
					memset(novo,0,sizeof(novo));
					strcpy(novo->valorConta,cDocRmngAmt);
					strcpy(novo->dtVencimento,cDocDueDt);
					strcpy(novo->dtFechamento, "");

					if (listValorConta.sizeOf() == 0) //mais recente coloca data de fechamento de ciclo										
						Util::formateDate(novo->dtFechamento, cCycleEndDtBillDates, "YYYYMMDD", "DD/MM/YYYY");
							
					listValorConta.add(novo);														
				}				
					
				mapFaturamento.erase(itFaturamentoFim);							

				break;
			
			}
			
			itFaturamentoFim--;

		}

	}

	
	tuxfw_getlogger()->debug("Contas em aberto fim");

	///
	if(this->bOperacaoInterna == true)
	{
		this->m_qtContasAberto = qtdeContasEmAberto;

		tuxfw_getlogger()->debug("Contas Pendentes: %d", this->m_qtContasAberto);

		return;
	}

	// Chama a API para recuperar a forma de pagamento
	XMLGen oXMLtoGetInputPymtMethodAcct;

	oXMLtoGetInputPymtMethodAcct.createTag(XML_ATL_INPUT_PYMT_METHOD_ACCT);
	oXMLtoGetInputPymtMethodAcct.addProp(XML_ATL_ACCT_NBR, pcIdConta);
	
        poResultNode = callRemoteAPI(this->getServiceName(), &oXMLtoGetInputPymtMethodAcct, XML_ATL_INPUT_PYMT_METHOD_ACCT);
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

	// Recuperar o Saldo Parcial
	this->bOperacaoInterna = true;
	this->getEstimativaSaldo();

	// Gera XML de saída.
	xml_g->addItem("statCom", 1);
	xml_g->addItem(TAG_XML_OUT_CD_CODIGO_RETORNO, "00");
	//xml_g->addItem(TAG_XML_OUT_NUM_VALOR_CONTA_CLIENTE_USUARIO, pcValorConta);
	// Retirado esse item segundo novo layout
	//xml_g->addItem(TAG_XML_OUT_SG_CREDOR_DEVEDOR, sSgIndMaisMenos);
	//xml_g->addItem(TAG_XML_OUT_DT_FECHAMENTO_VALORES, pcDataConta);
	//xml_g->addItem(TAG_XML_OUT_DT_VENCIMENTO_CONTA, pcDataVencto);
	xml_g->addItem(TAG_XML_OUT_SG_VERIFICACAO_DEBITO_AUTOMATICO, sIndDeb);
	// Criar a tag de quantidade de contas em aberto
	if(qtdeContasEmAberto == 0)
		xml_g->addItem(TAG_XML_OUT_QTDE_CONTAS_ABERTO,"");
	else
		xml_g->addItem(TAG_XML_OUT_QTDE_CONTAS_ABERTO,qtdeContasEmAberto);

	// valor total das contas em aberto
	char valorTotalContaChar[50];
	sprintf(valorTotalContaChar,"%.2f",valorTotalConta);
	xml_g->addItem("valorSaldoTotal",valorTotalContaChar);

	// quantidade de minutos
	char qtdeMinutosUtilizados[50];
	sprintf(qtdeMinutosUtilizados,"%.2f",this->m_minutosUtilizados);
	xml_g->addItem("qtdeMinutos",qtdeMinutosUtilizados);

	// saldo parcial
	char valorSaldoParcial[50];
	sprintf(valorSaldoParcial,"%.2f",this->m_saldoParcial);
	xml_g->addItem(TAG_XML_OUT_SALDO_PARCIAL,valorSaldoParcial);

	// Fechamento
	formataData(cycleEndDt);	
	int size = listValorConta.sizeOf();


	tuxfw_getlogger()->debug("listValorConta.sizeOf(): %d\r\n",listValorConta.sizeOf());
	for(i = listValorConta.sizeOf() - 1; i >= 0; i--)		
	{
		stValorConta *valorConta = (stValorConta*)listValorConta.get(i);
		xml_g->createTag("conta");
		
		formataData(valorConta->dtVencimento);
		
		xml_g->addItem(TAG_XML_OUT_NUM_VALOR_CONTA_CLIENTE_USUARIO, valorConta->valorConta);

		xml_g->addItem(TAG_XML_OUT_DT_VENCIMENTO_CONTA, valorConta->dtVencimento);		
	
		xml_g->addItem(TAG_XML_OUT_DT_FECHAMENTO_VALORES, valorConta->dtFechamento);

		xml_g->closeTag();
	}

	// Registro de contato
	this->registrarContato();

}
