#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterStepStockImpl.cpp

Abstract:
	Implements step manager

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-29 - Ivan Mentone - Correct to retrieve correctly RETURN indicator

--*/

RouterStepStockImpl::RouterStepStockImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	pstep(0L),ploop(0L),nextStockStep(0L),rootAccum(rAccum),plib(pLib),ptrace(pTrace),ptaskc(0L)
{}
RouterStepStockImpl::~RouterStepStockImpl()
{
	if(nextStockStep)
		delete nextStockStep;
	if(pstep)
		delete pstep;
	if(ploop)
		delete ploop;
	if(ptaskc)
		delete ptaskc;
}
int RouterStepStockImpl::HasMacro(char*pln)
{
	char*p,*p1;

	p=strchr(pln,'=');
	if(!p)return 0;
	p1=RouterStringImpl::_FindFromCode(p,"+");
	if(p+1!=p1&&p1)
	{
		char*p;
		p=RouterStringImpl::_LTrim(pln);
		if(!strncmp(p,"println",7))
			return 0;
		return 1;
	}
	return 0;
}
int RouterStepStockImpl::Assemble(CodeUnit*cunit)
{
	type=cunit->optype;
	switch(type)
	{
	case OP_DRCT:
		cunit->cmd.pcmd++;
	case OP_CODE:
		Assemble(cunit->cmd.pcmd);
		break;
	case OP_LOOP:
		ploop=new RouterLoopImpl(plib,rootAccum,ptrace);
		ploop->Assemble(cunit->cmd.code);
		break;
	case OP_COND:
		ptaskc=new RouterTaskCondImpl(plib,rootAccum,ptrace);
		ptaskc->Assemble(cunit->cmd.code);
		break;
	}
	return 1;
}
int RouterStepStockImpl::Assemble(char*pln)
{
	if(!strncmp(pln,"[@XML]=",7))
		type=OP_RETN;
	else
		type=OP_CODE;
	if(!HasMacro(pln))
	{
		pstep=new RouterStepImpl(plib,rootAccum,ptrace);
		pstep->Assemble(pln);
	}
	else
	{
		char*st1,*st2,is_first=1;
		HTOKEN pMap;RETITEM ri;
		RouterStreamImpl rsi(ptrace);
		RouterStepImpl*rstep=pstep;
		st1=strchr(pln,'=');
		st2=RouterStringImpl::_RTrim(st1-1);
		if(*st2=='+')
		{
			is_first=0;
			*st2=0;
		}
		*st1++=0;
		pMap=MakeToken(st1,'+',0L);
		FIRSTITEM(ri);
		while(st2=GetNextToken(&ri,pMap))
		{
			//TR15
			rsi.Truncate();
			rsi.AppendText(pln);
			rsi.AppendText(is_first?"=":"+=");
			rsi.AppendText(st2);
			is_first=0;
			if(!pstep)
				rstep=pstep=new RouterStepImpl(plib,rootAccum,ptrace);
			else
				rstep=rstep->AddNewStep();
			try
			{
				rstep->Assemble(rsi.GetText());
			}
			catch(...)
			{
				DestroyToken(pMap);
				throw;
			}
		}
		DestroyToken(pMap);
	}
	return 1;
}
RouterStepStockImpl*RouterStepStockImpl::AddNewStepStock()
{
	if(nextStockStep)
		return nextStockStep->AddNewStepStock();
	nextStockStep=new RouterStepStockImpl(plib,rootAccum,ptrace);
	return nextStockStep;
}
RouterStepStockImpl*RouterStepStockImpl::Execute()
{
	switch(type)
	{
	case OP_CODE:case OP_RETN:case OP_DRCT:
		{
			RouterStepImpl*step=pstep;
			while(step)
			{
				if((step=step->Execute())==(RouterStepImpl*)-2)
					return(RouterStepStockImpl*)-2;
			}
		}
		if(type==OP_RETN)
			return(RouterStepStockImpl*)-3;
		break;
	case OP_LOOP:
		if(!ploop->Execute())
			return(RouterStepStockImpl*)-3;
		break;
	case OP_COND:
		if(!ptaskc->Execute())
			return(RouterStepStockImpl*)-3;
		break;
	}
	return nextStockStep;
}