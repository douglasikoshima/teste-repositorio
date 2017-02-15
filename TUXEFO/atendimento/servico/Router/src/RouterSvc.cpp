#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterSvc.cpp

Abstract:
	Router base class - entry point

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

Router::Router(DOMNode*dom,XMLGen*xml,void*pvi):rimpl(0L),pv(pvi)
{
	rimpl=new RouterImpl(pv);
	rimpl->SetupSession(dom,xml);
}
Router::~Router()
{
	if(rimpl)
		delete rimpl;
}
int Router::Assemble(char*psrc)
{
	return rimpl->Assemble(psrc);
}
int Router::Execute()
{
	return rimpl->Execute();
}
