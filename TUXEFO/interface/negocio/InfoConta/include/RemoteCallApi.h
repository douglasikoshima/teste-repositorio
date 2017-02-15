#ifndef REMOTECALLAPI_H_HEADER_INCLUDED_BD901957
#define REMOTECALLAPI_H_HEADER_INCLUDED_BD901957
#include <tuxfw.h>
#include "Constants.h"
#include "MemoryManager.h"
#include "RemoteLog.h"

class RemoteCallApi
{
public:
	RemoteCallApi();
	~RemoteCallApi();

	// retorna a classe de log
	CRemoteLog*getLog();

	// recupera o usuario
	char* getUser();

	// copia o usuario da transacao
	void setUser(char*);

  protected:
    // Chamada a um serviço remoto do Atlys
    DOMNode* getRemoteAPI(char* serviceName, XMLGen* xml,char*nmAPI)/*throw (TuxBasicSvcException)*/;
  
  protected:
	CMemoryManager m_memory;
  
  private:
	  CRemoteLog *m_log;
	  char m_idContaSistemaOrigem[256];
	  char m_idUser[256];
};



#endif /* REMOTECALLAPI_H_HEADER_INCLUDED_BD901957 */
