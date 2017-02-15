//---------------------------------------------------------------------
//*
//* Class: SaldoPontos
//---------------------------------------------------------------------
//*
//* Retorna XML de saldo de pontos.
//* 
//---------------------------------------------------------------------

#include<tuxfw.h>
#include"SaldoPontos.h"

//
// Construtor e Destrutor
CSaldoPontos::CSaldoPontos()
{
	sprintf(sUser, "1");
	pParser = NULL;
	pMemBuf = NULL;
	iNrLanctos = 0;
}

CSaldoPontos::~CSaldoPontos()
{
	if (pParser)
		delete pParser;
	if (pMemBuf)
		delete pMemBuf;
}

DOMNode* CSaldoPontos::getXMLRetorno(void)
{
    return (xmlRet);
}

//
// Metodos setter
void CSaldoPontos::setDDD(char* pDado)
{
    strcpy(sDDD, pDado);
}

void CSaldoPontos::setNrFone(char* pDado)
{
    strcpy(sNrFone, pDado);
}

void CSaldoPontos::setUser(char* pDado)
{
    strcpy(sUser, pDado);
}

void CSaldoPontos::setNrLanctos(int iDado)
{
    iNrLanctos = iDado;
}

//
// Chama
void CSaldoPontos::ConsultaPontos() {

        XercesDOMParser *pParser;
        MemBufInputSource *pMemBuf;
        const char *pMemBufId = "inputInfo";
        TuxHelper tuxhp;

	// Monta XML de entrada para consulta no serviço de Atlys.
	XMLGen oEntrada;
	oEntrada.addItem("COD_AREA", sDDD);
	oEntrada.addItem("NUM_LINE", sNrFone);
	if (iNrLanctos > 0) {
		oEntrada.addItem("TRAZ_EXTRATO", "S");
		oEntrada.addItem("NUM_LANCAMENTOS", iNrLanctos);
		oEntrada.addItem("TRAZ_EXPIRACAO","N");
	}

	// Faz a solicitação ao serviço de consulta de HEXA no Atlys.
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;
	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	inputMessage->setUser("FO");
	inputMessage->setMessageBody(&oEntrada);
	// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
	if (iNrLanctos > 0)
		remoteService->setServiceName("SaldoPontos");
	else
		remoteService->setServiceName("ConsultaPontos");
	remoteService->setInputMessage(inputMessage);
	
	if(remoteService->remoteCall() != TUXFWRET_OK)
	{
		delete remoteService;
		delete inputMessage;

		throw new TuxBasicSvcException("24E0999","Erro de comunicação com sistema de pontos");
	}
	
	
	char  c_statusCode[10]  = "";
	char  c_statusText[256] = "";

	char *pc_statusCode = remoteService->getOutputMessage()->getStatusCode();
	char *pc_statusText = remoteService->getOutputMessage()->getStatusText();

	if(pc_statusCode)
		strcpy(c_statusCode, pc_statusCode);

	if(pc_statusText)
	{
		if(strlen(pc_statusText) >= sizeof(c_statusText))
			strncpy(c_statusText, pc_statusText, sizeof(c_statusText) - 1);
		else
			strcpy(c_statusText, pc_statusText);
	}

	free(pc_statusCode);
	free(pc_statusText);

	if(strlen(c_statusCode) >= 3 && c_statusCode[2] !='I')
	{
		delete remoteService;
		delete inputMessage;

		throw new TuxBasicSvcException(c_statusCode, c_statusText);
	}


	// Guarda mensagem de retorno
	char* ret = remoteService->getOutputMessage()->getMessageBody();
	char* fullRet = (char*) malloc(strlen(ret) + 100);

	sprintf(fullRet, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", ret);

    pParser =  new XercesDOMParser;
    pMemBuf =  new MemBufInputSource( (const XMLByte*)fullRet, strlen(fullRet), pMemBufId);

    pParser->parse(*pMemBuf);
    DOMNode* pDoc = pParser->getDocument();
    xmlRet = tuxhp.walkDOM(pDoc, "msgBody", 0);

    delete remoteService;
	delete inputMessage;

	free(fullRet);
	free(ret);
}
