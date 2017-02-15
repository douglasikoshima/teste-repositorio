#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterImpl.cpp

Abstract:
	Implements Router basic environment

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterVarNameImpl*prvni=0L;
RouterErrorImpl*prei=0L;
RouterPatrolImpl*prpi=0L;

#ifdef HASLOG
RouterLogImpl __rli;
#endif

RouterImpl::RouterImpl(void*pvi):
	ptask(0L),paccum(0L),ptrace(0),plib(0L),pcode(0L),RouterHelper(0L,0L),owner(0),pv(pvi)
{}
RouterImpl::~RouterImpl()
{
	if(pcode)
		delete pcode;
	if(ptask)
		delete ptask;
	if(paccum)
		delete paccum;
	if(prvni&&owner)
	{
		delete prvni;
		prvni=0L;
	}
	if(prei&&owner)
	{
		delete prei;
		prei=0L;
	}
	if(prpi&&owner)
	{
		delete prpi;
		prpi=0L;
	}
	else
		prpi->Pop();
	if(plib)
		delete plib;
	if(ptrace)
		delete ptrace;
}
int RouterImpl::SetupSession(DOMNode*pnode,XMLGen*pxml)
{
	RouterAccumImpl*accum;

	ptrace=new RouterTraceImpl();
	if(!prvni)
	{
		prvni=new RouterVarNameImpl(ptrace);
		prei=new RouterErrorImpl(ptrace);
		prpi=new RouterPatrolImpl();
		owner=1;
	}
	plib=new RouterLibManImpl(ptrace);
	paccum=new RouterAccumImpl();
	paccum->MakeVar("a_@XML",AC_DOMNODE);
	{
		DOMNode*dom;
		int i0=0;
		dom=RetrieveNode(pnode,"rsBody",&i0,0);
		if(!dom)
			dom=pnode;	//EXTCLS
		paccum->descriptor.var.dom=new AuxDOMImpl(dom);
	}
	paccum->AddRef();
	accum=paccum->AddNewAccum();
	accum->MakeVar("@XML",AC_XMLGEN);
	delete accum->descriptor.var.xml;	//TR ML 1
	accum->descriptor.var.xml=pxml;
	accum->flags=0;
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SSIGN",AC_STREAM);
	((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(cSignature);
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SVERSION",AC_STREAM);
	((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(cVersion);
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SSO",AC_STREAM);
	((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(cSO);
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("STYPE",AC_STREAM);
	((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(cType);
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SMAJOR",AC_INTEGER);
	accum->descriptor.var.i32=iMajor;
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SMINOR",AC_INTEGER);
	accum->descriptor.var.i32=iMinor;
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SBUILD",AC_INTEGER);
	accum->descriptor.var.i32=iBuild;
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("SQLIND",AC_INTEGER);
	accum->descriptor.var.i32=0;
	accum->AddRef();
	accum=accum->AddNewAccum();
	accum->MakeVar("#tuxThis#",AC_UNKNOWN);
	accum->descriptor.var.othr=pv;
	accum->AddRef();
	prpi->Push("RouterCore");
	return 1;
}
int RouterImpl::XChrBootleg()
{
	RouterAccumImpl*accum;

	accum=paccum->RetrieveVar("@XML");
	if(accum&&accum->descriptor.var.xml&&accum->descriptor.vartype==AC_XMLGEN)
		RouterStringImpl::_Encode(accum->descriptor.var.xml);
	return 1;
}
int RouterImpl::Assemble(char*pln)
{
	RouterAssembleImpl rai(paccum,ptrace);
	CodeUnit*cunit;

	if(!ptrace||!paccum||!plib)
		throw new TuxBasicSvcException("00E9999","Interface cannot be started");

	if(pcode)
		delete pcode;
	if(ptask)
		delete ptask;

	pcode=new RouterCodeImpl();
	pcode->Assemble(pln);
	cunit=pcode->pcunit;

	rai._Assemble(cunit,plib,&ptask);
	return 0;
}
int RouterImpl::Execute()
{
	RouterTaskImpl*task;

	if(!ptrace||!paccum||!plib)
		throw new TuxBasicSvcException("00E9999","Interface cannot be started");

	if(ptrace->HasCheck())
	{
		int envvar=0;
		ptrace->DumpText("\t\tInitialize script check");
		{
			RouterAccumImpl*accum;
			accum=paccum;

			ptrace->DumpText("Check use of variables");
			while(accum)
			{
				envvar++;
				if(!accum->GetRef()&&*accum->varNm!=-1)
				{
					char cmsg[1024];
					sprintf(cmsg,"%s : unreferenced local variable",accum->varNm);
					REI_EPARSE(cmsg,REI_ERROR);
				}
				accum=accum->RetrieveNextAccum();
			}
		}
		if(ptrace->HasTrace()&&ptrace->HasCheck())
		{
			char cmsg[1024];
			sprintf(cmsg,"Declared variables: %d",envvar);
			ptrace->DumpText(cmsg);
		}
		ptrace->DumpText("\t\tEnd script check");
	}
	if(prei->HasCriticals()||prei->HasErrors())
		prei->Throw();
	if(prei->HasWarnings()&&ptrace->HasCheck())
		prei->Throw();
	ptrace->DumpText("\t\tBegin Script");
	task=ptask;
	while(task&&(task!=(RouterTaskImpl*)-2))
		task=task->Execute();
	XChrBootleg();
	ptrace->DumpText("\t\tEnd Script");
	return 1;
}