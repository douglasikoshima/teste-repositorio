// Remote.cpp: implementation of the CRemote class.
//
//////////////////////////////////////////////////////////////////////

#include "Remote.h"

CRemote::CRemote()
{
	this->m_pXml        = NULL;
	this->m_pStatusCode = NULL;
	this->m_pStatusText = NULL;

	this->setUser(TXPB_REMOTE_DEFAULT_USER);
}

CRemote::~CRemote()
{
	this->liberaMemoria();
}

void CRemote::liberaMemoria()
{
	try
	{
		if(this->m_pXml)
		{
			tuxfw_getlogger()->debug("CRemote::~CRemote:Liberando m_pXml");
			free(this->m_pXml);
		}
		
		if(this->m_pStatusCode)
		{
			tuxfw_getlogger()->debug("CRemote::~CRemote:Liberando m_pStatusCode");
			free(this->m_pStatusCode);
		}

		if(this->m_pStatusText)
		{
			tuxfw_getlogger()->debug("CRemote::~CRemote:Liberando m_pStatusText");
			free(this->m_pStatusText);
		}

	} catch(...) {	}

}

// Getters

char* CRemote::getXMLResponse()
{
	return this->m_pXml;
}

char* CRemote::getUser()
{
	return this->m_cUser;
}

char* CRemote::getServiceName()
{
	return this->m_cServiceName;
}

char* CRemote::getStatusCode()
{
	return this->m_pStatusCode;
}

char* CRemote::getStatusText()
{
	return this->m_pStatusText;
}

// Setters
void CRemote::setUser(char *value)
{
	strcpy(this->m_cUser, value);
}

void CRemote::setServiceName(char *value)
{
	strcpy(this->m_cServiceName, value);
}

// Functions
int CRemote::remoteCall()
{
	int			iXMLLen;
	int			iRet;

	this->liberaMemoria();

	// monta mensagens
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;

	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	
	inputMessage->setUser(this->getUser());
	inputMessage->setMessageBody(&xmlInput);
	inputMessage->setService(this->getServiceName());

	tuxfw_getlogger()->information("Remote::XML ENTRADA:\r\n%s\r\n", xmlInput.retrieveXML(&iXMLLen));

	try
	{
		// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
		remoteService->setServiceName(this->getServiceName());
		remoteService->setInputMessage(inputMessage);

		iRet = remoteService->remoteCall();
	}
	catch(...)
	{
		// vamos asseguar que iRet esteja realmente com erro
		iRet = TUXFWRET_ERROR;
	}

	if(iRet == TUXFWRET_OK)
	{
		// montagem do XML
		this->m_pStatusCode = remoteService->getOutputMessage()->getStatusCode();		
		this->m_pStatusText = remoteService->getOutputMessage()->getStatusText();

		if(this->m_pStatusCode == NULL || strlen(this->m_pStatusCode) < 3 || this->m_pStatusCode[2] != 'I')
		{
			delete inputMessage;
			delete remoteService;

			return -1;
		}

		// Guarda mensagem de retorno
		m_pXml = remoteService->getOutputMessage()->getMessageBody();
		
		tuxfw_getlogger()->information("Remote::XML SAIDA:\r\n%s\r\n", m_pXml);

	}

	delete inputMessage;
	delete remoteService;

	if(iRet != TUXFWRET_OK)
		throw TuxBasicSvcException(TXPB_REMOTE_FATAL_ERROR, "Remote::Erro fatal no Remote Call");

	return 0;
}
