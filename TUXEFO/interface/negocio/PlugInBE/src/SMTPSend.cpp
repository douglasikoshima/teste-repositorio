#include "SMTPSend.h"

#include "ManagerBackEndDOMNode.h"
#include <tuxfw.h>

#include <cstring>

using namespace std;


/*
** Metodos SEND
*/

// setTo
void CSMTPSend::setTo(char* to)
{
	unsigned int bufferSize = sizeof(mvc_to);


	if (to != NULL)
	{
		if (strlen(to) < bufferSize)
			strcpy(mvc_to, to);
		else
		{
			strncpy(mvc_to, to, (bufferSize - 1));
			
			mvc_to[bufferSize] = '\0';
		}
	}
	else
		memset(mvc_to, '\0', bufferSize);
}

// setSubject
void CSMTPSend::setSubject(char* subject)
{
	unsigned int bufferSize = sizeof(mvc_subject);


	if (subject != NULL)
	{
		if (strlen(subject) < bufferSize)
			strcpy(mvc_subject, subject);
		else
		{
			strncpy(mvc_subject, subject, (bufferSize - 1));
			
			mvc_subject[bufferSize] = '\0';
		}
	}
	else
		memset(mvc_subject, '\0', bufferSize);
}

// setData
void CSMTPSend::setData(char* data)
{
	unsigned int bufferSize = sizeof(mvc_data);


	if (data != NULL)
	{
		if (strlen(data) < bufferSize)
			strcpy(mvc_data, data);
		else
		{
			strncpy(mvc_data, data, (bufferSize - 1));
			
			mvc_data[bufferSize] = '\0';
		}
	}
	else
		memset(mvc_data, '\0', bufferSize);
}

/*
** Metodos GET
*/

// getTo
char* CSMTPSend::getTo(void)
{
	return mvc_to;
}

// getSubject
char* CSMTPSend::getSubject(void)
{
	return mvc_subject;
}

// getData
char* CSMTPSend::getData(void)
{
	return mvc_data;
}

void CSMTPSend::createXmlToSend()
{
	xmlInput.addItem(TXPB_SMTPSEND_TAG_XML_TO, this->getTo());
	xmlInput.addItem(TXPB_SMTPSEND_TAG_XML_SUBJECT, this->getSubject());
	xmlInput.addItem(TXPB_SMTPSEND_TAG_XML_DATA, this->getData());

}

int CSMTPSend::Send(void)
{
	int iRet;

	try
	{
		this->createXmlToSend();

		this->setServiceName(TXPB_SMTPSEND_SERVICE);

		iRet = this->remoteCall();
		
		if(iRet)
		{
			tuxfw_getlogger()->debug("CSMTPSend::Send:remoteCall - ERRO");
			return -1;
		}

		return 0;

	}
	catch(...)
	{
		tuxfw_getlogger()->debug("CSMTPSend::Send - Erro interno");
		return -1;
	}
}