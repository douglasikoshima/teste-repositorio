// Get.hpp: interface for the CGet class.
//
//////////////////////////////////////////////////////////////////////

#ifndef __C_GET__
#define __C_GET__

#include <list>

class CGet
{

public:

	CGet();
	virtual ~CGet();

	// Métodos de acesso aos atributos
 	// Getters
	int   getRetries() const;
	char* getURL(char *cURL) const;
		
	// Setters
	void setRetries(int value);
	void setURL(char *value);
	
	// 
	void addParameter(char *name, char *value);
	void deleteParameter();
	int  Execute();
	
	char *bodyReceived();

private:

	std::list< char * > allocatedBody;

	int   m_iRetries;
	char *m_pURL;
	char *m_pBody;

	char *m_pParameter;
	
	// Setters
	void setBody(char *value);

	int ProcessExecute();

};

#endif //__C_GET__