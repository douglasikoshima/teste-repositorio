// ServiceAtlys.h: interface for the ServiceAtlys class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_SERVICEATLYS_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_)
#define AFX_SERVICEATLYS_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "RemoteCallApi.h"
#include "../../commons/include/list.h"
#include "Servico.h"

typedef CList<DOMNode> ListDOMNodes;

class ServiceAtlys : public RemoteCallApi  
{
public:
	ServiceAtlys();
	virtual ~ServiceAtlys();
	// Faz a chamada a essa API remota
	DOMNode* searchSubscriptionByAttribute(ListDOMNodes *list,char*attrName,char*attrValue,char*startKey,bool aremore);
	// Faz a chamada a essa API remota
	DOMNode* getSubscriptionInfo(char*sbscrpId);
	// Faz a chamada a essa API remota
	DOMNode* maintainSubscriptionServices(ListServicos *list,char*sbscrpId,char* service,bool ativar,char*svcId,char* dia);
	// Faz a chamada a essa API remota
	DOMNode* onDemandMovistarNtfctn(char*accessNbr);

protected:
	void tratarErro(DOMNode*domNode);

  private:
	  // atributos
	TuxHelper tuxhp;
	CMemoryManager m_mem;

};

#endif // !defined(AFX_SERVICEATLYS_H__7023986E_2B52_490A_BFAE_8F2746459C66__INCLUDED_)
