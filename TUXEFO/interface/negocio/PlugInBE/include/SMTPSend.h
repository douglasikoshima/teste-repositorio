#ifndef TXPB_SMTPSEND_H
#define TXPB_SMTPSEND_H 1

#include <tuxfw.h>
#include "Remote.h"

#define TXPB_SMTPSEND_SERVICE              "SMTPSEND"

#define TXPB_SMTPSEND_MAX_TO_SIZE          256
#define TXPB_SMTPSEND_MAX_SUBJECT_SIZE     512
#define TXPB_SMTPSEND_MAX_DATA_SIZE        1024

#define TXPB_SMTPSEND_TAG_XML_TO           "to"
#define TXPB_SMTPSEND_TAG_XML_SUBJECT      "subject"
#define TXPB_SMTPSEND_TAG_XML_DATA         "data"


class CSMTPSend: public CRemote
{
	public:

		void setTo(char*);
		void setSubject(char*);
		void setData(char*);

		char* getTo(void);
		char* getSubject(void);
		char* getData(void);

		virtual void createXmlToSend();

		int Send(void);

	private:

		char mvc_to[TXPB_SMTPSEND_MAX_TO_SIZE + 1];
		char mvc_subject[TXPB_SMTPSEND_MAX_SUBJECT_SIZE + 1];
		char mvc_data[TXPB_SMTPSEND_MAX_DATA_SIZE + 1];

};


#endif