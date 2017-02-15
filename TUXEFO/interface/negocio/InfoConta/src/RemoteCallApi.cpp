#include "RemoteCallApi.h"

RemoteCallApi::RemoteCallApi()
{
	m_log = new CRemoteLog();
	memset(this->m_idUser,0,sizeof(this->m_idUser));
}

RemoteCallApi::~RemoteCallApi()
{
	delete m_log;
}

DOMNode* RemoteCallApi::getRemoteAPI(char* serviceName, XMLGen* xml,char*nmAPI)
{
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI");
	TuxMessage o_inputMessage;
	TuxRemoteService o_remoteService;
	o_inputMessage.setMessageBody(xml);
	o_inputMessage.setService(serviceName);
	o_remoteService.setServiceName(serviceName);
	o_remoteService.setInputMessage(&o_inputMessage);

	getLog()->setNmAPI(nmAPI);
	getLog()->getTimeSysdate();

	try
	{
		if(o_remoteService.remoteCall() != TUXFWRET_OK)
			throw new TuxBasicSvcException("11E0999","Erro de comunicação com sistema Legado");
	}
	catch(...)
	{
		// registrar log		
		getLog()->log(xml,o_remoteService.getOutputMessage()->getStatusCode(),o_remoteService.getOutputMessage()->getStatusText(),getUser(),4);
		throw;
	}

	// Registra o último status e texto
	char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
	char* statusText = o_remoteService.getOutputMessage()->getStatusText();
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:LastStatusCode: '%s'", statusCode);
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:LastStatusText: '%s'", statusText);
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:Liberando statusCode");
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:Liberando statusText");

	char statusCodeStatic[7];
	char statusTextStatic[1024];
	memset(statusCodeStatic,0,sizeof(statusCodeStatic));
	memset(statusTextStatic,0,sizeof(statusTextStatic));
	if(statusCode!=NULL)
		strncpy(statusCodeStatic,statusCode,(sizeof(statusCodeStatic) - 1));
	if(statusText!=NULL)
		strncpy(statusTextStatic,statusText,(sizeof(statusTextStatic) - 1));

	if((statusCode != NULL) && (strlen(statusCode) > 3) && (statusCode[2] == 'E'))
	{
		tuxfw_getlogger()->debug("Erro de acesso ao ATLYS E");
		free(statusCode);	
		free(statusText);
		getLog()->log(xml,o_remoteService.getOutputMessage()->getStatusCode(),o_remoteService.getOutputMessage()->getStatusText(),getUser(),4);
		throw new TuxBasicSvcException("00W0100","Erro de comunicação com sistema Legado");
	}
	free(statusCode);	
	free(statusText);
	char* pc_textMessage = o_remoteService.getOutputMessage()->getMessageBody();
	if (pc_textMessage == NULL)
	{
		tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI - NULL");
		getLog()->log(xml,statusCodeStatic,statusTextStatic,getUser(),3);
		return NULL;
	}
	else
	{
		char* outputFull = (char*) malloc(strlen(pc_textMessage) + 100);
		sprintf(outputFull, "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>%s", pc_textMessage);
		tuxfw_getlogger()->debug("getMessageBody() nova chamada");

		DOMNode* po_textMessage = NULL;
		po_textMessage = this->m_memory.createDOMNode(outputFull);
		tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:Liberando pc_textMessage");
		free(pc_textMessage);
		free(outputFull);
		if(po_textMessage == NULL)
		{
			tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:createDOMNode - ERRO");		
			getLog()->log(xml,statusCodeStatic,statusTextStatic,getUser(),2);
			return NULL;
		}
		else
		{
			tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:createDOMNode - OK");
			getLog()->log(xml,statusCodeStatic,statusTextStatic,getUser(),1);
			TuxHelper o_tuxhp;
			return o_tuxhp.walkDOM(po_textMessage, "msgBody", 0);
		}
	}
}

CRemoteLog* RemoteCallApi::getLog()
{
	return m_log;
}

char*RemoteCallApi::getUser()
{
	return this->m_idUser;
}

void RemoteCallApi::setUser(char*user)
{
	strcpy(m_idUser,user);
}