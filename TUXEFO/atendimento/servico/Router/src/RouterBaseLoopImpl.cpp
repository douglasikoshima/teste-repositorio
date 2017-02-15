#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    SI2Core/Client/Custom/Prof/ClBl.cpp

Abstract:
	Implements base of all loops
	Usage in client CPP modules.
	Use this only with SI2Asm.dll.

Author:
    Ivan Mentone 2003-05-10

Environment:
    SI2 Core

Revision History:
	2003-06-15 Modify _Execute method
	2003-12-05 Drop _rbliOpAO method
	2003-12-06 Drop _rbliCCast method
	2004-07-12 Deriv copy to Router service - RouterBaseLoopImpl class
			   Drop _rbliSIGet method
			   Drop _rbliStpGet method
			   Drop _rbliAssemble method (requires SI2Asm.dll)
			   Modify _rbliExecute method to pure virtual
	2004-07-14 Implement RouterWhileImpl class
			   Deriv TR22
	2004-07-29 Correct to retrieve correctly RETURN indicator

--*/

int RouterBaseLoopImpl::_rbliBefore()
{return 0;}
int RouterBaseLoopImpl::_rbliAfter()
{return 0;}
int RouterBaseLoopImpl::_Execute()
{int pv=1;_rbliBefore();while(_rbliGetInd()&&pv)if(!_rbliExecute())pv=0;_rbliAfter();return pv;}
RouterWhileImpl::RouterWhileImpl(RouterAccumImpl*rAccum,RouterTraceImpl*pTrace,RouterLibManImpl*pLib):
	rootAccum(rAccum),ptrace(pTrace),plib(pLib),ptask(0L)
{}
RouterWhileImpl::~RouterWhileImpl()
{
	if(ptask)
		delete ptask;
}
int RouterWhileImpl::_Assemble(CodeUnitCond*cuc)
{
	CodeUnit*cunit;
	RouterAssembleImpl rai(rootAccum,ptrace);

	cunit=cuc->pcunit;
	rai._Assemble(cunit,plib,&ptask);
	return 0;
}
int RouterWhileImpl::_rbliExecute()
{
	RouterTaskImpl*task;

	task=ptask;
	while(task)
	{
		task=task->Execute();
		if(task==(RouterTaskImpl*)-1)	//TR22
			return 0;					//TR40
		else if(task==(RouterTaskImpl*)-2)
			return 0;
	}
	return 1;
}
