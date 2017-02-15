#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

#ifndef WIN32
#include<unistd.h>
#include<dlfcn.h>
#include<dirent.h>
#endif

/*++
Module Name:
    RouterLibManImpl.cpp

Abstract:
	Implements library loader manager

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterLibManImpl::RouterLibManImpl(RouterTraceImpl*pTrace):plib(0L),ptrace(pTrace)
{}
RouterLibManImpl::~RouterLibManImpl()
{
	if(plib)
		delete plib;
}
int RouterLibManImpl::MakeInstance(char*pln)
{
	char*pfnc;
	RouterLibImpl*lib;

	pfnc=strchr(pln,':');
	if(!pfnc)
	{
		char cmsg[1024];
		sprintf(cmsg,"Invalid directive format - expected ':' - %.800s",pln);
		REI_EPARSE(cmsg,REI_ERROR);
		return 0;
	}
	*pfnc++=0;
	if(!*pfnc)
	{
		REI_EPARSE("Empty function name",REI_WARNING);
		return 0;
	}

	if(plib)
	{
		if(!(lib=plib->RetrieveLibInstance(pln)))
			lib=plib->AddNewLib(pln);
	}
	else
		lib=plib=new RouterLibImpl(pln,ptrace);

	lib->LoadFunction(pfnc);

	return 1;
}
int RouterLibManImpl::GetInstance(char*pfnc,RouterAccumImpl*pout)
{
	char*fnc,*eq;
	RouterLibImpl*lib;
	int ind;

	if(!plib){ind=-1;goto __cascade_throw;}

	fnc=strchr(pfnc,'.');
	if(!fnc){ind=-2;goto __cascade_throw;}

	*fnc++=0;
	eq=strchr(pfnc,'=');
	if(eq)
		pfnc=eq+1;
	lib=plib->RetrieveLibInstance(pfnc);
	if(!lib){ind=-1;goto __cascade_throw;}

	pout->descriptor.var.othr=lib->GetInstance(fnc);
	pout->descriptor.vartype=AC_LIBFNC;
	return 1;

__cascade_throw:
	switch(ind)
	{
	case -1:
		REI_EPARSE("Library not loaded",REI_ERROR);
		break;
	case -2:
		REI_EPARSE("Missing '.' before identifier",REI_ERROR);
		break;
	}
	return 0;
}
