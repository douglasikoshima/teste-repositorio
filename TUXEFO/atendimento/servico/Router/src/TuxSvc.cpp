#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif


/*++
Module Name:
    TuxSvc.cpp

Abstract:
	Interface Provider

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

DECLARE_TUXEDO_SERVICE(SSKlunk10);

void implSSKlunk10::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
	char*svcN;
	svcN=getHdrAttr("service");
	if(!svcN)
		throw new TuxBasicSvcException("00E9999","Cannot find script service name");
#ifdef WIN32
	RouterControl*rc;

	rc=new RouterControl(this,svcN);
	try
	{
		rc->AttachParams(XMLIn,XMLOut);
		rc->Dispatch();
		delete rc;
	}
	catch(...)
	{
		delete rc;
		throw;
	}
#else
	RouterControl rc(this,svcN);
	rc.AttachParams(XMLIn,XMLOut);
	rc.Dispatch();
#endif
	setStatusCode("00I0000","Execution Completed Success");
}