#include "RemoteCallApi.h"


DOMNode* RemoteCallApi::getRemoteAPI(char* serviceName, XMLGen* xml)
{
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI");
	TuxMessage o_inputMessage;
	TuxRemoteService o_remoteService;
	o_inputMessage.setMessageBody(xml);
	o_inputMessage.setService(serviceName);
	o_remoteService.setServiceName(serviceName);
	o_remoteService.setInputMessage(&o_inputMessage);
	if(o_remoteService.remoteCall() != TUXFWRET_OK)
	{
		tuxfw_getlogger()->debug("Erro de acesso ao ATLYS");
		throw new TuxBasicSvcException("11E0999","Erro de comunicação com sistema Legado");
	}
	// Registra o último status e texto
	char* statusCode = o_remoteService.getOutputMessage()->getStatusCode();
	char* statusText = o_remoteService.getOutputMessage()->getStatusText();
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:LastStatusCode: '%s'", statusCode);
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:LastStatusText: '%s'", statusText);
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:Liberando statusCode");
	tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:Liberando statusText");

	if((statusCode != NULL) && (strlen(statusCode) > 3) && (statusCode[2] == 'E'))
	{
		tuxfw_getlogger()->debug("Erro de acesso ao ATLYS E");
		free(statusCode);	
		free(statusText);
		throw new TuxBasicSvcException("00W0100","Erro de comunicação com sistema Legado");
	}
	free(statusCode);	
	free(statusText);
	char* pc_textMessage = o_remoteService.getOutputMessage()->getMessageBody();
	if (pc_textMessage == NULL)
	{
		tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI - NULL");
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
			return NULL;
		}
		else
		{
			tuxfw_getlogger()->debug("RemoteCallApi::getRemoteAPI:createDOMNode - OK");
			TuxHelper o_tuxhp;
			return o_tuxhp.walkDOM(po_textMessage, "msgBody", 0);
		}
	}
}

