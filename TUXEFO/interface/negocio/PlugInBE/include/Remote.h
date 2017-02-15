// Remote.h: interface for the CRemote class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_REMOTE__
#define __C_REMOTE__

#include <tuxfw.h>

#define TXPB_REMOTE_MAX_USER_SIZE			128
#define TXPB_REMOTE_MAX_SERV_NAME_SIZE		128

#define TXPB_REMOTE_DEFAULT_USER			"2"
#define TXPB_REMOTE_FATAL_ERROR				"24E0999"

class CRemote
{

public:

	CRemote();
	virtual ~CRemote();

	// Getters
	char*	getXMLResponse();
	char*	getUser();
	char*   getServiceName();
	char*   getStatusCode();
	char*   getStatusText();

	// Setters
	void    setUser(char *value);	
	void    setServiceName(char *value);
	
	// Functions
	int     remoteCall();

	virtual void createXmlToSend() = 0;

private:

	char*	m_pXml;
	char*   m_pStatusText;
	char*   m_pStatusCode;
	
	char	m_cUser[TXPB_REMOTE_MAX_USER_SIZE];
	char    m_cServiceName[TXPB_REMOTE_MAX_SERV_NAME_SIZE];

	void    liberaMemoria();

protected:
	
	XMLGen	xmlInput;

};

#endif //__C_REMOTE__