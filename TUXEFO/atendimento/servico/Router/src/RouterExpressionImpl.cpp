#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
	SI2Core/Client/Custom/ExpAnysr.cpp

Abstract:
	Implements expressions parse, evaluate and execute

Author:
    Ivan Mentone 2003-04-11

Environment:
    SI2 Core

Revision History:
	2003-04-11 - Convert RXIHANDLE struct (C) to RootExpressionImpl class (C++)
	2003-04-15 - Implement EXPRESSIONQL struct
	2003-06-23 - Implement TRACE handle
	2003-08-05 - Modify _Execute method
				 Add _rxiEvaluate method
				 Add _rxiParse method
				 Add _rxiAssemble method
	2004-07-17 - Deriv copy to Router service - RouterExpressionImpl class
				 Add Execute method - derived from _Execute
				 Add Assemble method - derived from _rxiAssemble
				 Add HasOperator and FindOperator methods - derived from _rxiParse method
	2004-07-18 - Drop _rxiEvaluate method
				 Deriv EXPRESSIONQ struct to Router service
				 Add AddItem method - derived from ExprQueue::Push method
				 Add GetItem method - derived from ExprQueue::Pop method
				 Modify to support use of RouterSentenceImpl class in EXPRESSIONQL struct

--*/

EXPRESSIONQL::EXPRESSIONQL():prsi(0L),ind(RXI_NONE)
{}
EXPRESSIONQL::~EXPRESSIONQL()
{if(prsi)delete(prsi);}
RouterExpressionImpl::RouterExpressionImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	rootAccum(rAccum),ptrace(pTrace),RouterHelper(rAccum,pTrace),plib(pLib),psent(0L),noSent(0),rxi(0L)
{}
RouterExpressionImpl::~RouterExpressionImpl()
{
	if(psent&&noSent)
	{
		int i;
		for(i=0;i<noSent;i++)
		{
			if(psent[i])
				delete psent[i];
		}
		delete[]psent;
	}
}
int RouterExpressionImpl::HasOperator(char*pv)
{return(strchr(pv,124)||strchr(pv,38));}
int RouterExpressionImpl::FindOperator(char*pv,char**ppv)
{
	for(;*pv;pv++)
	{
		if((*pv==RXI_OR&&*(pv+1)==RXI_OR)||
			(*pv==RXI_AND&&*(pv+1)==RXI_AND))
		{
			*ppv=pv;
			return*pv;
		}
	}
	*ppv=pv;
	return 0;
}
int RouterExpressionImpl::AddItem(char*pv,int ind)
{
	if(!rxi)
		rxi=lrxi=new EXPRESSIONQ;
	else
		lrxi=lrxi->next=new EXPRESSIONQ;
	lrxi->ind=ind;
	lrxi->pv=pv;
	lrxi->next=0L;
	return 1;
}
int RouterExpressionImpl::GetItem(char**ppv)
{
	int ind;

	if(!rxi)
		return 0;
	lrxi=rxi;
	rxi=rxi->next;
	*ppv=lrxi->pv;
	ind=lrxi->ind;
	delete lrxi;
	return ind;
}
int RouterExpressionImpl::Assemble(char*pln)
{
	char*pv,*lpv;
	int ind,i;

	if(!HasOperator(pln))
	{
		noSent=1;
		psent=new EXPRESSIONQL*;
		psent[0]=new EXPRESSIONQL;
		psent[0]->prsi=new RouterSentenceImpl(plib,rootAccum,ptrace);
		return psent[0]->prsi->Assemble(pln);
	}
	pv=pln;
	do
	{
		lpv=pv;
		ind=FindOperator(lpv,&pv);
		if(pv)
		{
			*pv=0;
			pv+=2;
		}
		AddItem(lpv,ind);
		noSent++;
	}while(ind);
	psent=new EXPRESSIONQL*[noSent];
	for(i=0;i<noSent;i++)
	{
		psent[i]=new EXPRESSIONQL;
		psent[i]->prsi=new RouterSentenceImpl(plib,rootAccum,ptrace);
		ind=GetItem(&pv);
		psent[i]->prsi->Assemble(pv);
		psent[i]->ind=ind;
	}
	return 0;
}
int RouterExpressionImpl::Execute()
{
	if(noSent==1)
		return psent[0]->prsi->Execute();
	{
		int i,opi=-1,rip,prv=0;
		for(i=0;i<noSent;i++)
		{
			rip=psent[i]->prsi->Execute();
			if(psent[i]->ind==RXI_AND&&!rip)
				return 0;
			if(prv)
			{
				if(prv==RXI_OR)
					opi=opi||rip;
				else
				{
					switch(prv)
					{
					case RXI_AND:
						opi=opi&&rip;
						break;
					case RXI_OR:
						opi=opi||rip;
					}
				}
			}
			else
				opi=rip;
			prv=psent[i]->ind;
		}
		return opi;
	}
}