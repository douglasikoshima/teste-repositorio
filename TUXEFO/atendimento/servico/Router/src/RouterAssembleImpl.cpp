#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterAssembleImpl.cpp

Abstract:
	Implements code assemble

Author:
    Ivan Mentone 2004-07-20

Environment:
    Router Core

Revision History:
	

--*/

RouterAssembleImpl::RouterAssembleImpl(RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	paccum(rAccum),ptrace(pTrace)
{}
int RouterAssembleImpl::_Assemble(CodeUnit*cunit,RouterLibManImpl*plib,RouterTaskImpl**pptask)
{
	RouterTaskImpl*task;
	int lastcmd;

	lastcmd=0;
	while(cunit)
	{
		if(cunit->optype==OP_CODE)
		{
			if(!strncmp(cunit->cmd.pcmd,"return",6))
			{
				strncpy(cunit->cmd.pcmd,"[@XML]=",7);
				lastcmd=1;
			}
			if(!strchr(cunit->cmd.pcmd,')')&&
				!strchr(cunit->cmd.pcmd,'(')&&
				!strchr(cunit->cmd.pcmd,'=')&&
				strncmp(cunit->cmd.pcmd,"SQL ",4)&&
				!RouterStringImpl::_HasIncDec(cunit->cmd.pcmd)&&
				strcmp(cunit->cmd.pcmd,"break")
				)
			{
				RouterHelper rh(paccum,ptrace);
				rh.DeclareVar(cunit->cmd.pcmd);
			}
			else
			{
				if(!*pptask)
					*pptask=task=new RouterTaskImpl(plib,paccum,ptrace);
				else
					task=task->AddNewTask();
				task->Assemble(cunit->cmd.pcmd);
			}
		}
		else if(cunit->optype==OP_DRCT)
		{
			char*st1;
			st1=cunit->cmd.pcmd+1;
			if(!strncmp(st1,"import ",7))
			{
				st1=strchr(st1,32);
				if(st1)
				{
					st1+=1;
					plib->MakeInstance(st1);
				}
			}
			else if(!strncmp(st1,"trace",5))
				ptrace->SetTrace();
			else if(!strncmp(st1,"print",5))
			{
				if(!*pptask)
					*pptask=task=new RouterTaskImpl(plib,paccum,ptrace);
				else
					task=task->AddNewTask();
				task->Assemble(st1);
			}
			else if(!strncmp(st1,"nocheck",7))
				ptrace->UnsetCheck();
			else if(!strncmp(st1,"title",5))
			{
				st1+=6;
				prei->SetScriptName(st1);
			}
			else if(!strncmp(st1,"debug",5))
				ptrace->SetDebug();
			else
			{
				char cmsg[1024];
				sprintf(cmsg,"Invalid directive - #%.800s",st1);
				REI_EPARSE(cmsg,REI_ERROR);
			}
		}
		else
		{
			if(!*pptask)
				*pptask=task=new RouterTaskImpl(plib,paccum,ptrace);
			else
				task=task->AddNewTask();
			task->Assemble(cunit->cmd.code);
		}
		if(lastcmd)
			break;
		cunit=cunit->nextUnit;
	}
	return 1;
}
