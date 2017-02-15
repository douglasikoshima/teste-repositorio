#include "PlugInATLYS.h"

#include "../../PlugInBE/include/PlugInBE.h"
#include "../../PlugInBE/include/MemoryManager.h"
#include "../../PlugInBE/include/Parametro.h"

#include <tuxfw.h>

#include <cstring>

using namespace std;


PlugInATLYS::PlugInATLYS(DOMNode* dn, XMLGen* xg)
{
	tuxfw_getlogger()->debug("PlugInATLYS::PlugInATLYS");

	this->bOperacaoInterna = false;
	this->m_qtContasAberto = -1;
	this->m_qtContasVencidas = 0;

	tuxhp = new CTuxHelperClever(true);
	dnode = dn;
	xml_g = xg;

	this->setIdLinhaSistemaOrigem("");


	// Manager BackEndDOMNode
	m_pManangerDOMNode = new ManagerBackEndDOMNode();
}

PlugInATLYS::PlugInATLYS(DOMNode* dn, XMLGen* xg, char* pcIdLinhaSisOri)
{
	tuxfw_getlogger()->debug("PlugInATLYS::PlugInATLYS");

	this->bOperacaoInterna = false;
	this->m_qtContasAberto = -1;
	this->m_qtContasVencidas = 0;

	tuxhp = new CTuxHelperClever(true);
	dnode = dn;
	xml_g = xg;

	this->setIdLinhaSistemaOrigem(pcIdLinhaSisOri);


	// Manager BackEndDOMNode
	m_pManangerDOMNode = new ManagerBackEndDOMNode();
}

PlugInATLYS::~PlugInATLYS()
{
	tuxfw_getlogger()->debug("PlugInATLYS::~PlugInATLYS()");

	if (tuxhp)
		delete tuxhp;		

	// Manager BackEndDOMNode	
	if (m_pManangerDOMNode)
		delete m_pManangerDOMNode;
}

// Operacoes nao usadas
void PlugInATLYS::getDetalhesSaldo(){}
void PlugInATLYS::getHistoricoMovimentos(){}
void PlugInATLYS::getExtrato(){}
void PlugInATLYS::getPromocoes(){}
void PlugInATLYS::setInterceptacao(){}
void PlugInATLYS::setCliente(){}


char* PlugInATLYS::traduzFormaPagto(char* pcCdPagto)
{
	if (pcCdPagto == NULL)
		return NULL;

	else if (strcmp(pcCdPagto,"CASH") == 0)
		return "DINHEIRO";
	
	else if (strcmp(pcCdPagto,"MONEYORDER") == 0)
		return "ORDEM DE PAGAMENTO";
	
	else if (strcmp(pcCdPagto,"CHEQUE") == 0)
		return "CHEQUE";
	
	else if (strcmp(pcCdPagto,"CREDITCARD") == 0)
		return "CARTÃO DE CRÉDITO";
	
	else if (strcmp(pcCdPagto,"EFT") == 0)
		return "TRANSFERÊNCIA ELETRÔNICA DE VALOR";
	
	else if (strcmp(pcCdPagto,"OTCC") == 0)
		return "TRANSAÇÃO DE CARTÃO DE CRÉDITO ONLINE";
	
	else if (strcmp(pcCdPagto,"DIRCTDEBIT") == 0)
		return "DÉBITO AUTOMÁTICO";
	
	else if (strcmp(pcCdPagto,"ELECTCHK") == 0)
		return "CHEQUE ELETRÔNICO";
	
	else if (strcmp(pcCdPagto,"ELECTSAV") == 0)
		return "POUPANÇA ELETRÔNICA";
	
	else if (strcmp(pcCdPagto,"LEG_EFT") == 0)
		return "DOAÇÃO - EFT";
	
	else if (strcmp(pcCdPagto,"LEG_UNKNWN") == 0)
		return "DOAÇÃO DESCONHECIDA";
	
	else if (strcmp(pcCdPagto,"PND_CHQ") == 0)
		return "PAGAMENTO EM CHEQUE PENDENTE";
	
	else if (strcmp(pcCdPagto,"PND_CASH") == 0)
		return "PAGAMENTO EM DINHEIRO PENDENTE";
	
	else if (strcmp(pcCdPagto,"AC") == 0)
		return "CARTÃO DE CRÉDITO PRÉ-PAGO AUTOMÁTICO";
	
	else if (strcmp(pcCdPagto,"DD") == 0)
		return "Direct Deposit";
	
	else
		return pcCdPagto;
}

int PlugInATLYS::checaDebitoAutomatico(char* pcCdPagto)
{
	if (strcmp(pcCdPagto, "DIRECTDEBIT") == NULL)
		return 1;
	else
		return 0;
}

void PlugInATLYS::formataData(char* pcData)
{
	char sData[11];

	memset(sData, '\0', sizeof(sData));

	if ((pcData != NULL) && (strlen(pcData) == 10))
	{
		strncpy(sData, (pcData + 8), 2);
		
		strncat(sData, "/", 1);

		strncat(sData, (pcData + 5), 2);
		
		strncat(sData, "/", 1);
		
		strncat(sData, pcData, 4);
		
		sprintf(pcData, "%s", sData);
	}
}

void PlugInATLYS::formataHora(char *pszHora)
{
    char szHora[8 + 1];

    if (pszHora != NULL && strlen(pszHora) == 8)
	{
        sprintf(szHora, "%.*s:%.*s:%.*s",
            2, pszHora,
            2, pszHora+2,
            2, pszHora+4);

        strcpy(pszHora, szHora);
    }
}

char *PlugInATLYS::formataDataHora(char *pszData, char *pszHora)
{
    static char szDataHora[10 + 1 + 8 + 1];

    this->formataData(pszData);
    this->formataHora(pszHora);

    sprintf(szDataHora, "%s %s", pszData, pszHora);

    return szDataHora;
}

char* PlugInATLYS::traduzNomeServicoToATLYS(char* pcNomeServico, int iCdAreaRegistro)
{
	CParametro		oParametro;

	oParametro.setCdAreaRegistro(iCdAreaRegistro);

    if(strcmp(pcNomeServico,COD_CAIXA_POSTAL) == 0){
		oParametro.setChave(COD_FO_CAIXA_POSTAL);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_IDENTIFICADOR) == 0){
		oParametro.setChave(COD_FO_IDENTIFICADOR);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_BLOQUEIO_DDI) == 0){
		oParametro.setChave(COD_FO_BLOUQEIO_DDI);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_BLOQUEIO_DDD) == 0){
		oParametro.setChave(COD_FO_BLOQUEIO_DDD);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_WAP) == 0){
		oParametro.setChave(COD_FO_SERVICO_WAP);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_CHAMADA_ESPERA) == 0){
		oParametro.setChave(COD_FO_CHAMADA_ESPERA);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_TRANSF_CHAMADAS) == 0){
		oParametro.setChave(COD_FO_DESVIO_CHAMADAS);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,"FAVORITOS") == 0){
		oParametro.setChave(COD_FO_FAVORITOS);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,"CONFERENCIA") == 0){
		oParametro.setChave(COD_FO_CONFERENCIA);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();

	}else if(strcmp(pcNomeServico,"BN") == 0){
		oParametro.setChave(COD_FO_BASICO_NUMERO);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico,COD_ANTIBINA) == 0){
		oParametro.setChave(COD_FO_ANTI_IDENTIF_CHAMADA);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}
	else if(strcmp(pcNomeServico, COD_BLOQCOBRAR) == 0){
		oParametro.setChave(COD_BLOQCOBRAR);
		oParametro.consultarNomeServico();
		return oParametro.getConsulta();
	}


	else
		return pcNomeServico;

/*
	Caixa Postal	CX 5 MSGS DIGITAL
	Identificador de Chamada	IDENTIF CHAM
	Bloqueio DDI	   DESBLOQ INTERNAC
	Bloqueio DDD/DDI	BLOQUEIO LDN/LDI
	WAP             	BLOQUEIO WAP
	Chamada em Espera	CHAM ESPERA
	Desvio de Chamada	TRANS CHAMADAS
	Favoritos	FAVORITOS
	Conferência	CONS CONFERENCIA
	Básico (usado internamente)	BASICO NUMÈRO
*/
}

void PlugInATLYS::callSetServico(char* pcXmlInSetServico)
{
	tuxfw_getlogger()->debug("PlugInATLYS::callSetServico - INICIO"); 

	if(pcXmlInSetServico == NULL)
	{	
		tuxfw_getlogger()->debug("PlugInATLYS::callSetServico: Parametro XML Invalido"); 

		throw new TuxBasicSvcException("24E0000", "Parametro XML não Definidos Corretamente"); 
	}

	// Preparar instancias de DOMNode e o XMLGen locais, 
	// para a instancia Local do PlugInATLYS. 
	DOMNode* dnodeIn = createDOMNode(pcXmlInSetServico);
	XMLGen*  xmlRet  = new XMLGen();

	// Instancia Local do PlugInATLYS, para chamar setServico().
	PlugInBE* pPlug = new PlugInATLYS(dnodeIn , xmlRet, getIdLinhaSistemaOrigem());

	try
	{
		// Chamada do Metodo.
		pPlug->setServico();
	}
	catch(...)
	{
		tuxfw_getlogger()->debug("PlugInATLYS::callSetServico: catch na Chamada Local pPlug->setServico()"); 
		// destruir os objs criados neste escopo					
		tuxfw_getlogger()->debug("delete pPlug"); 
		delete pPlug;  // PlugInATLYS  
		delete xmlRet; // XMLGen					
		throw;
	}

	// Obter o Retorno
	TuxMessage* outXml = new TuxMessage();
	outXml->setMessageBody(xmlRet);

	DOMNode* dnodeOut = createDOMNode(outXml->getMessageBody());

	if((tuxhp->walkDOM(dnodeOut,"ServicoVO", 0)) == NULL)
	{	
		tuxfw_getlogger()->debug("PlugInATLYS::callSetServico: Erro em setServico(), Nao Retornou TAG <ServicoVO>"); 

		// destruir os objs criados neste escopo
		delete outXml; // TuxMessage
		delete pPlug;  // PlugInATLYS  
		delete xmlRet; // XMLGen

		throw new TuxBasicSvcException("24E0000", "Nao foi possivel Realizar a Operacao com setServico()");
	}  	

	// destruir os objs criados neste escopo
	delete outXml; // TuxMessage
	delete pPlug;  // PlugInATLYS  
	delete xmlRet; // XMLGen		

	tuxfw_getlogger()->debug("PlugInATLYS::callSetServico - FIM");
}

void PlugInATLYS::trataErro(DOMNode* po_Body, char* pc_API)
{
	if((this->getLastStatusCode() != NULL) && (strlen(this->getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(this->getLastStatusCode(), this->getLastStatusText());

	if(po_Body == NULL)
	{
		tuxfw_getlogger()->debug("po_Body trataErro vazio");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}	

	// Verifica se existe o DOM 'fault'
	DOMNode* po_fault = NULL;

	// DOM fault
	po_fault = tuxhp->walkDOM(po_Body, XML_ATL_FAULT, 0);
	
	if(po_fault == NULL)
		return;
	
	tuxfw_getlogger()->debug("Tratando Erro");

	char* pc_errCode = tuxhp->walkAttr(po_fault, XML_ATL_FAULT, XML_ATL_ERR_CD, 0);
	char* pc_msg     = tuxhp->walkAttr(po_fault, XML_ATL_FAULT, XML_ATL_MSG, 0);
	int   i_errCode  = atoi(pc_errCode);

	if(pc_API != NULL)
	{
		if(strcmp(XML_ATL_INPUT_REMOVE_SUBSCRIPTION_HOTLINE, pc_API) == 0)
		{
			// vamos procurar mensagens padrão
			if(i_errCode == ERR_ATL_CODE_HOTLINE_NOT_SET)
				throw new TuxBasicSvcException(ECD_ATL_CODE_HOTLINE_NOT_SET, MSG_ATL_CODE_HOTLINE_NOT_SET);
		}
		else if(strcmp(XML_ATL_INPUT_RESET_VOICEMAIL, pc_API) == 0){

			if(i_errCode == ERR_ATL_CODE_INVALID_ACCESS_NUMBER)
				throw new TuxBasicSvcException(ECD_ATL_CODE_INVALID_ACCESS_NUMBER, MSG_ATL_INVALID_ACCESS_NUMBER);

		}
		else if(strcmp(XML_ATL_INPUT_GET_BILL_DATES,pc_API) == 0)
		{
			if(i_errCode == 29004)
				throw new TuxBasicSvcException(ECD_ATL_TNE_SEM_CONTAS, MSG_ATL_SEM_CONTAS);
			else
				throw new TuxBasicSvcException(ECD_ATL_TNE_OUTPUTGETBILLDATES, EMSG_ATL_TNE_OUTPUTGETBILLDATES);
		}

	}
	
	// não sabemos qual é o erro, então vamos colocar a mensagem padrão do ATLYS
	char  c_msg[256];

	strcpy(c_msg, pc_errCode);
	strcat(c_msg, " - ");

	if(strlen(pc_msg) < 240)
		strcat(c_msg, pc_msg);
	else
		strncat(c_msg, pc_msg, 240);

	throw new TuxBasicSvcException(ECD_ATL_TNE_FAULT, c_msg);

}


void PlugInATLYS::trataErroF2(DOMNode* po_Body, char* pc_API)
{
	if((this->getLastStatusCode() != NULL) && (strlen(this->getLastStatusCode()) > 3) && (getLastStatusCode()[2] == 'E'))
		throw new TuxBasicSvcException(this->getLastStatusCode(), this->getLastStatusText());

	if(po_Body == NULL)
	{
		tuxfw_getlogger()->debug("po_Body trataErro vazio");
		throw new TuxBasicSvcException(NRO_ERR_ATLYS, MSG_ERR_ATLYS);
	}	

	// Verifica se existe o DOM 'outputMarkInvoiceAsPaid'
	DOMNode* po_outputMarkInvoiceAsPaid = NULL;
	DOMNode* po_fault = NULL;

	// DOM fault
	po_fault = tuxhp->walkDOM(po_Body, XML_ATL_FAULT, 0);
	
	// DOM outputMarkInvoiceAsPaid
	po_outputMarkInvoiceAsPaid = tuxhp->walkDOM(po_Body, XML_ATL_OUTPUT_MARK_INVOICE_AS_PAID, 0);
	
	if(po_outputMarkInvoiceAsPaid == NULL && po_fault == NULL)
		return;
	
	tuxfw_getlogger()->debug("Tratando Erro");

	char* pc_errCode = NULL;
	
	if(po_outputMarkInvoiceAsPaid != NULL)
		pc_errCode = tuxhp->walkAttr(po_outputMarkInvoiceAsPaid, XML_ATL_OUTPUT_MARK_INVOICE_AS_PAID, "errCd", 0);
	else
		pc_errCode = tuxhp->walkAttr(po_fault, XML_ATL_FAULT, XML_ATL_ERR_CD, 0);

	if(pc_errCode == NULL || strlen(pc_errCode) == 0)
	{
		tuxfw_getlogger()->debug("MarkInvoiceAsPaid Ok");
		return;
	}

	int   i_errCode  = atoi(pc_errCode);

	 if(i_errCode == 0)
		return;

	if(pc_API != NULL)
	{
		if(strcmp(XML_ATL_INPUT_MARK_INVOICE_AS_PAID, pc_API) == 0)
		{
			// vamos procurar mensagens padrão
			// 1269, 1289, 1316, 2462, 3031, 4307, 4308, 4222, 4335, 4309
			if(i_errCode == 1269)
				throw new TuxBasicSvcException(EDC_ATL_CODE_CAMPO_INVALIDO,MSG_ATL_CODE_CAMPO_INVALIDO);
			else if(i_errCode == 1289)
				throw new TuxBasicSvcException(EDC_ATL_CODE_FALTA_CAMPO_OBRIGATORIO, MSG_ATL_CODE_FALTA_CAMPO_OBRIGATORIO);
			else if(i_errCode == 1316)
				throw new TuxBasicSvcException(ECD_ATL_CODE_ACCOUNT_NOT_FOUND,MSG_ATL_CODE_ACCOUNT_NOT_FOUND);
			else if(i_errCode == 2462)
				throw new TuxBasicSvcException(EDC_ATL_CODE_DATA_INVALIDA,MSG_ATL_CODE_DATA_INVALIDA);
			else if(i_errCode == 3031)
				throw new TuxBasicSvcException(EDC_ATL_CODE_ERRO_APLICACAO,MSG_ATL_CODE_ERRO_APLICACAO);
			else if(i_errCode == 4307)
				throw new TuxBasicSvcException(EDC_ATL_CODE_FATURA_INFORMADA, MSG_ATL_CODE_FATURA_INFORMADA);
			else if(i_errCode == 4308)
				throw new TuxBasicSvcException(EDC_ATL_CODE_FATURA_EXCLUIDA,MSG_ATL_CODE_FATURA_EXCLUIDA);
			else if(i_errCode == 4222)
				throw new TuxBasicSvcException(EDC_ATL_CODE_FATURA_NAO_LOCALIZADA,MSG_ATL_CODE_FATURA_NAO_LOCALIZADA);
			else if(i_errCode == 4335)
				throw new TuxBasicSvcException(EDC_ATL_CODE_MULTIPLAS_FATURAS,MSG_ATL_CODE_MULTIPLAS_FATURAS);
			else if(i_errCode == 4309)
				throw new TuxBasicSvcException(EDC_ATL_CODE_VALOR_INVALIDO,MSG_ATL_CODE_VALOR_INVALIDO);
		}		
	}

	throw new TuxBasicSvcException(ECD_ATL_TNE_FAULT, "Erro não esperado");

}