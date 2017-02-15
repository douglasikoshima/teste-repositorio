#ifndef REMOTECALLAPI_H_HEADER_INCLUDED_BD901957
#define REMOTECALLAPI_H_HEADER_INCLUDED_BD901957
#include <tuxfw.h>
#include "MemoryManager.h"

class RemoteCallApi
{
  protected:
    // Chamada a um serviço remoto do Atlys
    DOMNode* getRemoteAPI(char* serviceName, XMLGen* xml)/*throw (TuxBasicSvcException)*/;
  protected:
	CMemoryManager m_memory;
};



#endif /* REMOTECALLAPI_H_HEADER_INCLUDED_BD901957 */
