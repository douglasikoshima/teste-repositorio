#include "SMSSend.h"

#include "Util.h"
#include "ManagerBackEndDOMNode.h"
#include <tuxfw.h>

#include <cstring>
#include <cstdio>

using namespace std;


CSMSSend::CSMSSend(void)
{
	memset(mvc_Phone, '\0', sizeof(mvc_Phone));
	memset(mvc_Message, '\0', sizeof(mvc_Message));
}


void CSMSSend::setPhone(char* phone)
{
	if ((phone != NULL) && (strlen(phone) == TXPB_SMSSEND_PHONE_SIZE) && Util::isNum(phone))
		strcpy(mvc_Phone, phone);
	else
		strcpy(mvc_Phone, "");
}

void CSMSSend::setPhone(unsigned int* phone)
{
	sprintf(mvc_Phone, "%d", phone);

	if (strlen(mvc_Phone) != TXPB_SMSSEND_PHONE_SIZE)
		strcpy(mvc_Phone, "");
}

void CSMSSend::setMessage(char* buffer)
{
	if ((buffer != NULL) && (strlen(buffer) <= TXPB_SMSSEND_MAX_MESSAGE_SIZE))
		strcpy(mvc_Message, buffer);
	else
		strcpy(mvc_Message, "");
}

char* CSMSSend::getPhone(void)
{
	return this->mvc_Phone;
}

char* CSMSSend::getMessage(void)
{
	return this->mvc_Message;
}

void CSMSSend::createXmlToSend()
{
	xmlInput.addItem(TXPB_SMSSEND_TAG_XML_MESSAGE, this->getMessage());
	xmlInput.addItem(TXPB_SMSSEND_TAG_XML_PHONE, this->getPhone());
}

int CSMSSend::Send(void)
{
	int iRet;

	try
	{
		this->createXmlToSend();

		this->setServiceName(TXPB_SMSSEND_SERVICE);

		iRet = this->remoteCall();
		
		if(iRet)
		{
			tuxfw_getlogger()->debug("CSMSSend::Send:remoteCall - ERRO");
			return -1;
		}

		return 0;

	}
	catch(...)
	{
		tuxfw_getlogger()->debug("CSMSSend::Send - Erro interno");
		return -1;
	}
}
