#ifndef SERVICEROUTER_H_HEADER_INCLUDED_BD90097A
#define SERVICEROUTER_H_HEADER_INCLUDED_BD90097A
#include "ServiceBase.h"
#include "XMLParseException.h"
#include "OperationNotFoundException.h"

class ServiceRouter
{
  public:
    // faz o parse de entrada do XML.
    static DOMNode* parseXmlIN(DOMNode* arg) throw(XMLParseException);

    // retorna um ServiceBase
    static ServiceBase* getServiceMethod(DOMNode* dnode) throw(OperationNotFoundException);

};



#endif /* SERVICEROUTER_H_HEADER_INCLUDED_BD90097A */
