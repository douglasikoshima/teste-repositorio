// Get.cpp: implementation of the CGet class.
//
//////////////////////////////////////////////////////////////////////

#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Get/Get.hpp>
#ifndef WIN32
	#include <netinet/in.h>
#endif
#include "WWWLib.h"
#include "WWWHTTP.h"
#include "WWWInit.h"

//---------------------------------------------------------------------------
PRIVATE int printer(const char * fmt, va_list pArgs)
{
    return (vfprintf(stdout, fmt, pArgs));
}

//---------------------------------------------------------------------------
PRIVATE int tracer(const char * fmt, va_list pArgs)
{
    return (vfprintf(stderr, fmt, pArgs));
}

//---------------------------------------------------------------------------
PRIVATE int myterminate_handler(HTRequest * request, HTResponse * response,
							  void * param, int status)
{
	int *pStatus;

	pStatus = (int *) param;
	
	if(pStatus != NULL)
		*pStatus = status;
	
	HTEventList_stopLoop();

	return HT_ERROR;
}

//---------------------------------------------------------------------------
CGet::CGet()
{
	m_pURL  = NULL;	
	m_pBody = NULL;

	m_pParameter = NULL;

	m_iRetries = 3;

	allocatedBody.clear();
}

//---------------------------------------------------------------------------
CGet::~CGet()
{
	while(allocatedBody.size() > 0)
	{
		delete allocatedBody.front();
		allocatedBody.pop_front();
	}	

	if(m_pURL)
		delete m_pURL;

	if(m_pParameter)
		delete m_pParameter;

	if(m_pBody)
		delete m_pBody;
}

// Getters
//---------------------------------------------------------------------------
int CGet::getRetries() const
{
	return m_iRetries;
}

//---------------------------------------------------------------------------
char* CGet::getURL(char *cURL) const
{
	ASSERT_GET_CHAR(m_pURL, cURL);
}

// Setters
//---------------------------------------------------------------------------
void CGet::setRetries(int value)
{
	m_iRetries = value;
}

//---------------------------------------------------------------------------
void CGet::setURL(char *value)
{
	ASSERT_SET_PCHAR(m_pURL, value);
}

//---------------------------------------------------------------------------
void CGet::setBody(char *value)
{
	ASSERT_SET_PCHAR(m_pBody, value);
}

//---------------------------------------------------------------------------
void CGet::addParameter(char *name, char *value)
{
	if((strlen(name) <= 0) || (strlen(value) <= 0))
		return;

	if(m_pParameter == NULL)
	{
		this->m_pParameter = new char[2];
		strcpy(m_pParameter, "?");
	}

	char *m_pParameterTemp = this->m_pParameter;
	char *e_value          = HTEscape(value, URL_DOSFILE);
	int   iLen             = strlen(m_pParameterTemp);

	//soma m_pParameterTemp + name + '=' + e_value + m_pParameter + '\0'
	m_pParameter = new char[strlen(m_pParameterTemp) + strlen(name) + 1 + strlen(e_value) + 1 ];

	strcpy(this->m_pParameter, m_pParameterTemp);
	strcat(this->m_pParameter, name);
	strcat(this->m_pParameter, "=");
	strcat(this->m_pParameter, e_value);

	//liberando memoria
	HT_FREE(e_value);
	delete m_pParameterTemp;
}

//---------------------------------------------------------------------------
void CGet::deleteParameter()
{
	if(m_pParameter)
		delete m_pParameter;

	m_pParameter = NULL;
}

//---------------------------------------------------------------------------
int CGet::Execute()
{
	int i;
	int iResult;

	for(i = 0; i < this->getRetries(); i++)
	{
		try
		{
			iResult = this->ProcessExecute();
		}
		catch(...)
		{
			iResult = 1;
		}

		if(!iResult)
			break;
	}

	return iResult;
}

//---------------------------------------------------------------------------
int CGet::ProcessExecute()
{
	if(this->m_pURL == NULL)
		return 1;

    HTRequest *request = HTRequest_new();
    HTChunk   *chunk   = NULL;
	char	  *url;

	int  iStatus;
	bool blPOK = false;

	int  iLen;

	// montando url
   	if(this->m_pParameter == NULL)
		iLen = 0;
	else
		iLen = strlen(this->m_pParameter);

	url = new char[strlen(this->m_pURL) + iLen + 1];

	strcpy(url, this->m_pURL);

	if(this->m_pParameter)
		strcat(url, this->m_pParameter);

    // iniciando libwww core 
	HTProfile_newNoCacheClient("ServiceRouterClient", "1.0");

	// debug
	//HTPrint_setCallback(printer);
    //HTTrace_setCallback(tracer);
    //HTSetTraceMessageMask("sop");

    HTRequest_setOutputFormat(request, HTAtom_for("text/xml"));

    // encerra conexao imediatamente
    HTRequest_addConnection(request, "close", "");

    // adicionando filtro
    HTNet_addAfter(myterminate_handler, NULL, (void *) &iStatus, HT_ALL, HT_FILTER_LAST);

	chunk = HTLoadToChunk(url, request);

	if(chunk)
	{
		tuxfw_getlogger()->information("\r\nGET:\r\n%s\r\n", HTUnEscape(url));

		HTEventList_loop(request);

		if(iStatus == HT_LOADED)
		{
			// temos informacao valida
			char * buffer = NULL;;

			buffer = HTChunk_toCString(chunk);

			this->setBody(buffer);

			tuxfw_getlogger()->information("\r\nGET BODY:\r\n%s\r\n", buffer);

			HT_FREE(buffer);

			blPOK = true;
		}

    }

	delete url;

    HTRequest_delete(request);
	HTFormat_deleteAll();	
    HTProfile_delete();

	if(blPOK)
		return 0;
	else
		return 1;
}

//---------------------------------------------------------------------------
char *CGet::bodyReceived()
{
	if(this->m_pBody != NULL)
	{
		char *pBody = new char[strlen(this->m_pBody) + 1];
		
		strcpy(pBody, this->m_pBody);
		
		allocatedBody.push_back(pBody);

		return pBody;
	}

	return NULL;
}