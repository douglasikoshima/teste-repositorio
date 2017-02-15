#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif
#include<time.h>

/*++
Module Name:
    RouterVarNameImpl.cpp

Abstract:
	Implements ramdom var name

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterVarNameImpl::RouterVarNameImpl(RouterTraceImpl*pTrace):ptrace(pTrace)
{
	static int SHAND_INITIALIZED=0;

	if(!SHAND_INITIALIZED)
	{
		SHAND_INITIALIZED=1;
		srand(1);
	}
}
RouterVarNameImpl::~RouterVarNameImpl()
{}
extern char*cbVT[];
char*RouterVarNameImpl::VarName(int type)
{
	time_t tmt,tmr;
	int rnd;

	ptrace->DumpText("Making random var name");
	pvname[0]=0;
	if(!(type/16))
		strcpy(pvname,cbVT[type]);
	else
		strcpy(pvname,"UK");
	time(&tmt);
	sprintf(pvname+2,"%08x",tmt);
	tmt>>=8;
	time(&tmr);
	tmt^=tmr;
	rnd=rand();
	tmt^=rnd;
	sprintf(pvname+6,"%08x",tmt);
	ptrace->DumpText(pvname);
	return pvname;
}