#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterTaskImpl.cpp

Abstract:
	Implements tasks

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-12 - Ivan Mentone - Add support to IncDec
	2004-08-02 - Ivan Mentone - Correct to retrieve correctly RETURN indicator (WHILE)

--*/

RouterTaskImpl::RouterTaskImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*rTrace):
	pstep(0L),ttype(0),nextTask(0L),rootAccum(rAccum),plib(pLib),pcond(0L),ptrace(rTrace),
	pdnli(0L)
{}
RouterTaskImpl::~RouterTaskImpl()
{
	if(nextTask)
		delete nextTask;
	if(pstep)
		delete pstep;
	if(pcond)
		delete pcond;
	if(pdnli)
		delete pdnli;
}
int RouterTaskImpl::Assemble(char*pln)
{
	if(pstep)
		delete pstep;
	if(strchr(pln,'(')&&strchr(pln,')')&&!strchr(pln,'='))
	{
		if(!strchr(pln,'('))
		{
			REI_EPARSE("Missing '(' before function call",REI_ERROR);
			return 0;
		}
		if(!strchr(pln,')'))
		{
			REI_EPARSE("Missing ')' in script",REI_ERROR);
			return 0;
		}
	}
	if(!strcmp(pln,"break"))
		ttype=TK_BREAK;
	else
	{
		pstep=new RouterStepStockImpl(plib,rootAccum,ptrace);
		pstep->Assemble(pln);
		ttype=TK_STEP;
	}

	return 1;
}
int RouterTaskImpl::Assemble(CodeUnitCond*cunit)
{
	if(pcond)
		delete pcond;

	if(!cunit)
	{
		REI_EPARSE("Invalid conditional",REI_ERROR);
		return 0;
	}
	if(!cunit->pucondF&&!cunit->pucondT)
	{
		pdnli=new RouterLoopImpl(plib,rootAccum,ptrace);
		pdnli->Assemble(cunit);
		ttype=TK_LOOP;
	}
	else
	{
		pcond=new RouterTaskCondImpl(plib,rootAccum,ptrace);
		pcond->Assemble(cunit);
		ttype=TK_COND;
	}
	return 1;
}
RouterTaskImpl*RouterTaskImpl::AddNewTask()
{
	if(nextTask)
		delete nextTask;
	nextTask=new RouterTaskImpl(plib,rootAccum,ptrace);
	return nextTask;
}
RouterTaskImpl*RouterTaskImpl::Execute()
{
	RouterStepStockImpl*step;

	if(ttype==TK_STEP)
	{
		step=pstep;
		while(step&&step!=(RouterStepStockImpl*)-3)
			step=step->Execute();
	}
	else if(ttype==TK_COND)
	{
		if(!pcond->Execute())
			return(RouterTaskImpl*)-2;
	}
	else if(ttype==TK_LOOP)
	{
		if(!pdnli->Execute())
			return(RouterTaskImpl*)-2;
	}
	else if(ttype==TK_BREAK)
		return(RouterTaskImpl*)-1;
	if(step==(RouterStepStockImpl*)-3)
		return(RouterTaskImpl*)-2;
	return nextTask;
}

/*************************************************************************************/

RouterTaskCondImpl::RouterTaskCondImpl(RouterLibManImpl*pLib,RouterAccumImpl*pAccum,RouterTraceImpl*rTrace):
	pstepT(0L),pstepF(0L),ind(0),plib(pLib),rootAccum(pAccum),pexp(0L),
	ptrace(rTrace),ptci(0L)
{}
RouterTaskCondImpl::~RouterTaskCondImpl()
{
	if(pstepT)
		delete pstepT;
	if(pstepF)
		delete pstepF;
	if(pexp)
		delete pexp;
	if(ptci)
		delete ptci;
}
int RouterTaskCondImpl::Execute()
{
	ptrace->DumpText("Executing Condition");
	ind=pexp->Execute();
	return RunNode();
}
int RouterTaskCondImpl::RunNode()
{
	RouterStepStockImpl*step;

	ptrace->DumpText("Taking action");
	if(ind>0)
	{
		ptrace->DumpText("True Result");
		step=pstepT;
	}
	else
	{
		ptrace->DumpText("False Result");
		step=pstepF;
	}

	if(!step&&ptci)
	{
		ptrace->DumpText("Calling Execution Condition - having next condition");
		ptci->Execute();
	}

	if(step)
		ptrace->DumpText("Run Steps");

	while(step&&(step!=(RouterStepStockImpl*)-2)&&(step!=(RouterStepStockImpl*)-3))
		step=step->Execute();

	ptrace->DumpText("Exiting IF block");
	if(step==(RouterStepStockImpl*)-2||step==(RouterStepStockImpl*)-3)
		return 0;
	return 1;
}
int RouterTaskCondImpl::Assemble(CodeUnitCond*pcunit)
{
	CodeUnit*cunit;
	RouterStepStockImpl*step;

	pexp=new RouterExpressionImpl(plib,rootAccum,ptrace);
	if(!pcunit->clause)
	{
		REI_EPARSE("Invalid \"if\" startment - cannot find sentence",REI_ERROR);
		return 0;
	}
	pexp->Assemble(pcunit->clause);
	if(pcunit->pucondT)
	{
		if(pstepT)
			delete pstepT;
		cunit=pcunit->pucondT->pcunit;
		while(cunit)
		{
scan0:
			if(cunit->optype==OP_CODE&&
				!strncmp(cunit->cmd.pcmd,"return ",7))
			{
				strncpy(cunit->cmd.pcmd,"[@XML]=",7);
				goto scan0;
			}
			else if(cunit->optype==OP_CODE&&
				!strchr(cunit->cmd.pcmd,')')&&
				!strchr(cunit->cmd.pcmd,'(')&&
				!strchr(cunit->cmd.pcmd,'=')&&
				strncmp(cunit->cmd.pcmd,"SQL ",4)&&
				!RouterStringImpl::_HasIncDec(cunit->cmd.pcmd)
				)
			{
				RouterHelper rh(rootAccum,ptrace);
				rh.DeclareVar(cunit->cmd.pcmd);
			}
			else
			{
				if(!pstepT)
					pstepT=step=new RouterStepStockImpl(plib,rootAccum,ptrace);
				else
					step=step->AddNewStepStock();
				step->Assemble(cunit);
			}
			cunit=cunit->nextUnit;
		}
	}

	if(pcunit->pucondF)
	{
		if(pstepF)
			delete pstepF;
		if(pcunit->pucondF->pcunit)
		{
			cunit=pcunit->pucondF->pcunit;
			while(cunit)
			{
scan1:
				if(cunit->optype==OP_CODE&&
					!strncmp(cunit->cmd.pcmd,"return ",7))
				{
					strncpy(cunit->cmd.pcmd,"[@XML]=",7);
					goto scan1;
				}
				else if(cunit->optype==OP_CODE&&
					!strchr(cunit->cmd.pcmd,')')&&
					!strchr(cunit->cmd.pcmd,'(')&&
					!strchr(cunit->cmd.pcmd,'=')&&
					strncmp(cunit->cmd.pcmd,"SQL ",4)&&
					!RouterStringImpl::_HasIncDec(cunit->cmd.pcmd)
					)
				{
					RouterHelper rh(rootAccum,ptrace);
					rh.DeclareVar(cunit->cmd.pcmd);
				}
				else
				{
					if(!pstepF)
						pstepF=step=new RouterStepStockImpl(plib,rootAccum,ptrace);
					else
						step=step->AddNewStepStock();
					step->Assemble(cunit);
				}
				cunit=cunit->nextUnit;
			}
		}
		else
		{
			ptci=new RouterTaskCondImpl(plib,rootAccum,ptrace);
			ptci->Assemble(pcunit->pucondF);
		}
	}

	return 1;
}

/*************************************************************************************/

RouterSentenceImpl::RouterSentenceImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	paccum(0L),RouterHelper(rAccum,pTrace),ptrace(pTrace),rootAccum(rAccum),plib(pLib),psstep(0L)
{}
RouterSentenceImpl::~RouterSentenceImpl()
{
	if((op==TC_OPNEXISTS||op==TC_OPEXISTS)&&paccum[1])
	{
		RouterStepImpl*prsi;
		prsi=(RouterStepImpl*)paccum[1]->descriptor.var.othr;
		if(prsi)
			delete prsi;
	}
	if(paccum)
	{
		if(paccum[0]&&paccum[0]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[0];
		if(paccum[1]&&paccum[1]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[1];
		delete[]paccum;
	}
	if(psstep)
		delete psstep;
}
int RouterSentenceImpl::Assemble(char*pcmd)	//TR ML 2
{
	char*st1,*st2;

	if(paccum)
		delete[]paccum;

	pcmd=RouterStringImpl::_LTrim(pcmd);
	if((st1=strchr(pcmd,'!'))&&*pcmd!='!')
		op=TC_OPDIFF;
	else if((st1=strchr(pcmd,'<')))
		op=TC_OPLESS;
	else if((st1=strchr(pcmd,'>')))
		op=TC_OPGREAT;
	else if((st1=strchr(pcmd,'=')))
	{
		if(!strchr(st1+1,'='))
		{
			REI_EPARSE("Cannot set value on IF clause",REI_ERROR);
			return 0;
		}
		op=TC_OPEQUALS;
	}
	else
	{
		if(*pcmd=='!')
		{
			op=TC_OPNEXISTS;
			pcmd++;
		}
		else
			op=TC_OPEXISTS;
	}

	paccum=new RouterAccumImpl*[2];
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);

	if(st1)
	{
		*st1=0;
		switch(op)
		{
		case TC_OPGREAT:case TC_OPLESS:
			st1+=1;
			break;
		default:
			st1+=2;
		}
		MakeParam(st1,&paccum[1]);
		pcmd=RouterStringImpl::_LTrim(pcmd);
		RouterStringImpl::_RTrim(pcmd);
		MakeParam(pcmd,&paccum[0]);
	}
	else
	{
		if(st2=strchr(pcmd,':'))
		{
			char sent[1024];
			RouterStepImpl*prsi;
			RouterAccumImpl*accum;
			accum=rootAccum->AddNewAccum();
			accum->MakeVar(0L,AC_DOMNODE);
			sprintf(sent,"[%s]=%s",accum->varNm,pcmd);
			prsi=new RouterStepImpl(plib,rootAccum,ptrace);
			prsi->Assemble(sent);
			paccum[1]=new RouterAccumImpl();
			paccum[1]->MakeVar(0L,AC_ROUTERSTEP);
			paccum[1]->flags=AC_TYPE_DISCARDABLE;
			paccum[1]->descriptor.var.othr=prsi;
			sprintf(sent,"%s",accum->varNm);
			MakeParam(sent,&paccum[0]);
		}
		else
		{
			pcmd=RouterStringImpl::_LTrim(pcmd);
			RouterStringImpl::_RTrim(pcmd);
			MakeParam(pcmd,&paccum[0]);
		}
	}
	return 0;
}
int RouterSentenceImpl::Execute()
{
	switch(op)
	{
	case TC_OPGREAT:
		if(paccum[0]->OperatorIsGreat(paccum[1]))
			return 1;
		break;
	case TC_OPLESS:
		if(paccum[0]->OperatorIsLess(paccum[1]))
			return 1;
		break;
	case TC_OPEQUALS:
		if(paccum[0]->OperatorIsEquals(paccum[1]))
			return 1;
		break;
	case TC_OPDIFF:
		if(paccum[0]->OperatorIsNotEquals(paccum[1]))
			return 1;
		break;
	case TC_OPNEXISTS:case TC_OPEXISTS:
		if(paccum[1])
		{
			RouterStepImpl*prsi;
			prsi=(RouterStepImpl*)paccum[1]->descriptor.var.othr;
			prsi->Execute();
		}
		switch(paccum[0]->descriptor.vartype)
		{
		case AC_SQLIND:
			return paccum[0]->descriptor.var.i32;
		case AC_DOMNODE:
			if(paccum[0]->descriptor.var.dom->RetrieveDOM()&&op==TC_OPEXISTS)
				return 1;
			else if(!paccum[0]->descriptor.var.dom->RetrieveDOM()&&op==TC_OPNEXISTS)
				return 1;
			break;
		case AC_STRING:
			if(*paccum[0]->descriptor.var.pstr&&op==TC_OPEXISTS)
				return 1;
			else if(!(*paccum[0]->descriptor.var.pstr)&&op==TC_OPNEXISTS)
				return 1;
			break;
		case AC_STREAM:
			if(*(((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->GetText())&&op==TC_OPEXISTS)
				return 1;
			else if(!*(((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->GetText())&&op==TC_OPNEXISTS)
				return 1;
			break;
		case AC_INTEGER:
			if(paccum[0]->descriptor.var.i32&&op==TC_OPEXISTS)
				return 1;
			else if(!paccum[0]->descriptor.var.i32&&op==TC_OPNEXISTS)
				return 1;
			break;
		case AC_XMLGEN:
			{
				char*xml;int len;
				xml=paccum[0]->descriptor.var.xml->retrieveXML(&len);
				if(len&&op==TC_OPEXISTS)
					return 1;
				else if(!len&&op==TC_OPNEXISTS)
					return 1;
			}
			break;
		}
	}
	return 0;
}