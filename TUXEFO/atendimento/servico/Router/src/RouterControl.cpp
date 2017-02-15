#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    SI2Core/Client/Custom/ClCall.cpp

Abstract:
	This module prevent memory leaks on SI2.
	Usage in external CPP modules.
	Use this only with SI2Supp.dll.

Author:
    Ivan Mentone 2003-05-10

Environment:
    SI2 Core

Revision History:
	2003-06-10 Update SI2Supp.dll - new operator to support new[] operator
	2004-06-15 Derived copy to Router service - RouterControl class
	2004-06-16 Update class to function correctly in Unix environment

--*/

RouterControl::RouterControl(void*pvi,char*pci):pr(0L),prsm(0L),pv(pvi),pc(0L)
{
	pc=derivStr(pci);
	EnterContext();
}
RouterControl::~RouterControl()
{
	ExitContext();
	if(pc)
		free(pc);
	CleanAndDestroy();
}
int RouterControl::EnterContext()
{
	if(!pc)
		return 0;
#ifndef WIN32
	tpadvertise(pc,0L);
#endif
	return 1;
}
int RouterControl::ExitContext()
{
	if(!pc)
		return 0;
#ifndef WIN32
	tpunadvertise(pc);
#endif
	return 1;
}
int RouterControl::AttachParams(DOMNode*pdom,XMLGen*pxml)
{
	try
	{
		if(prsm)
		{delete prsm;prsm=0L;}
		prsm=new RouterSvcMan();
		prsm->Parse(pdom);
		if(pr)
		{delete pr;pr=0L;}
		pr=new Router(pdom,pxml,pv);
		pr->Assemble(prsm->RetrieveSource());
	}
	catch(...)
	{
		try
		{
			CleanAndDestroy();
		}
		catch(...)
		{
			throw new TuxBasicSvcException("00E9999","Fatal Error - destruction failed, memory leaks are possible");
		}
		throw;
	}
	return 1;
}
int RouterControl::Dispatch()
{
	try
	{
		pr->Execute();
	}
	catch(...)
	{
		try
		{
			CleanAndDestroy();
		}
		catch(...)
		{
			throw new TuxBasicSvcException("00E9999","Fatal Error - destruction failed, memory leaks are possible");
		}
		throw;
	}
	return 1;
}
int RouterControl::CleanAndDestroy()
{
	if(prsm)
		delete prsm;
	if(pr)
		delete pr;
	prsm=0L;	//TR7
	pr=0L;		//TR7
	return 1;
}
