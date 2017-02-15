#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterFunctionCallImpl.cpp

Abstract:
	Implements external Function calls

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-06 - Ivan Mentone - Add new control struct ACCUMSTOCK

--*/

ACCUMSTOCK::ACCUMSTOCK():accum(0L),next(0L)
{}
ACCUMSTOCK::ACCUMSTOCK(RouterAccumImpl*ac):accum(ac),next(0L)
{}
ACCUMSTOCK::~ACCUMSTOCK()
{
	if(next)
		delete next;
	if(accum&&accum->flags&AC_TYPE_DISCARDABLE)
		delete accum;
}
ACCUMSTOCK*ACCUMSTOCK::AddNewACCUMSTOCK()
{
	if(next)
		return next->AddNewACCUMSTOCK();
	next=new ACCUMSTOCK();
	return next;
}
ACCUMSTOCK*ACCUMSTOCK::AddNewACCUMSTOCK(RouterAccumImpl*ac)
{
	if(next)
		return next->AddNewACCUMSTOCK(ac);
	next=new ACCUMSTOCK(ac);
	return next;
}
RouterFunctionCallImpl::RouterFunctionCallImpl(RouterTraceImpl*pTrace)
	:paccum(0L),params(0L),fncCall(0L),raccum(0L),ptrace(pTrace)
{
	params=new PARAMS;
}
RouterFunctionCallImpl::~RouterFunctionCallImpl()
{
	if(params)
	{
		if(params->parmlist)
			delete params->parmlist;
		delete params;
	}
	if(paccum)
		delete paccum;
	if(fncCall)
		delete fncCall;
}
int RouterFunctionCallImpl::AddParam(RouterAccumImpl*accum)
{
	if(!accum)
	{
		REI_EPARSE("Invalid param for param list",REI_ERROR);
		return 0;
	}

	if(!paccum)
		paccum=new ACCUMSTOCK(accum);
	else
		paccum->AddNewACCUMSTOCK(accum);

	accum->AddRef();
	params->parmlist=0L;
	params->pcount++;
	return 1;
}
int RouterFunctionCallImpl::Execute()
{
	int i;
	ACCUMSTOCK*accum;
	ACCUM*Acc;
	THROWIND THRIND;

	if(params->parmlist)
		delete params->parmlist;

	if(params->pcount)
		params->parmlist=new ACCUM*[params->pcount];

	accum=paccum;
	for(i=0;i<params->pcount;i++)
	{
		if(accum->accum->descriptor.vartype==AC_DOMNODE&&!accum->accum->descriptor.var.dom)		//TR CR 38
			params->parmlist[i]->var.dom=new AuxDOMImpl((DOMNode*)0L);
		params->parmlist[i]=&accum->accum->descriptor;
		accum=accum->next;
	}

	if(raccum)
		Acc=&raccum->descriptor;
	else
		Acc=0L;

	*THRIND.cid=0;
	ptrace->DumpText("Before Function call");
	try
	{
		_fncCall fncCallp;
		fncCallp=((LIBACCUM*)fncCall->descriptor.var.othr)->fnc;
		prpi->Push(((LIBACCUM*)fncCall->descriptor.var.othr)->pfncName);
		THRIND.id=0;	//TR 40
		fncCallp(params,Acc,&THRIND);
		prpi->Pop();
	}
	catch(...)
	{
		ptrace->DumpText("Function calling exception");
		char cmsg[256];
		sprintf(cmsg,"%.100s - External calling failed",((LIBACCUM*)fncCall->descriptor.var.othr)->pfncName);
		REI_ERUNTIME(cmsg,REI_ERROR);
	}
	ptrace->DumpText("After function call");

	switch(THRIND.id)
	{
	case 1:
	case 2:
		ptrace->DumpText("Oracle Exception");
		{
			char cmsg[1024];
			sprintf(cmsg,"%.100s - %.923s",((LIBACCUM*)fncCall->descriptor.var.othr)->pfncName,THRIND.cmsg);
			throw new TuxBasicOraException(atoi(THRIND.cid),cmsg,strlen(cmsg));
		}
	case 3:
		ptrace->DumpText("TuxFW Exception");
		{
			char cmsg[1024];
			sprintf(cmsg,"%.100s - %.923s",((LIBACCUM*)fncCall->descriptor.var.othr)->pfncName,THRIND.cmsg);
			throw new TuxBasicSvcException(THRIND.cid,cmsg);
		}
	case 4:
		ptrace->DumpText("Oracle Exception - with no custom description");
		{
			char cmsg[1024];
			sprintf(cmsg,"%.1023s",((LIBACCUM*)fncCall->descriptor.var.othr)->pfncName);
			throw new TuxBasicOraException(atoi(THRIND.cid),cmsg,strlen(cmsg));
		}
	case 5:
		ptrace->DumpText("Unknown Exception");
		throw;
	}
	return 1;
}
int RouterFunctionCallImpl::SetReturn(RouterAccumImpl*accum)
{
	accum->AddRef();
	raccum=accum;
	return 1;
}
int RouterFunctionCallImpl::SetFunction(RouterAccumImpl*accum)
{
	if(fncCall)
		delete fncCall;
	fncCall=new RouterAccumImpl(accum);
	return 1;
}