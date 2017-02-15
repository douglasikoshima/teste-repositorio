#ifndef TXPB_SMSSEND_H
#define TXPB_SMSSEND_H 1

#include <tuxfw.h>
#include "Remote.h"

#define TXPB_SMSSEND_MAX_MESSAGE_SIZE     1024
#define TXPB_SMSSEND_PHONE_SIZE           10

#define TXPB_SMSSEND_SERVICE              "SMSSend"
#define TXPB_SMSSEND_TAG_XML_MESSAGE      "message"
#define TXPB_SMSSEND_TAG_XML_PHONE        "recipient"


class CSMSSend: public CRemote
{

public:

	CSMSSend(void);

	void setPhone(char*);
	void setPhone(unsigned int*);
	void setMessage(char*);

	char* getPhone(void);
	char* getMessage(void);

	virtual void createXmlToSend();

	int   Send(void);

private:

	char mvc_Phone[TXPB_SMSSEND_PHONE_SIZE + 1];
	char mvc_Message[TXPB_SMSSEND_MAX_MESSAGE_SIZE + 1];

};

#endif
