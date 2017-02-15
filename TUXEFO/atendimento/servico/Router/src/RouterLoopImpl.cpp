#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterLoopImpl.cpp

Abstract:
	Implements WHILE

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-07 - Ivan Mentone - alter "if" to cast -1 instead of return pointer (bad conduction of xlC)
	2004-07-13 - Ivan Mentone - modify class to use base class
	2004-08-10 - Ivan Mentone - Correct check of valid input DOMNode
	2004-08-11 - Ivan Mentone - Adding support to other types (EXPRESSIONS)

--*/

RouterLoopImpl::RouterLoopImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	actItem(0),rootAccum(rAccum),plib(pLib),ptrace(pTrace),paccum(0L),RouterHelper(rAccum,pTrace),pdnd(0L),
	adi(0L),ind(0),RouterWhileImpl(rAccum,pTrace,pLib),sstype(0),prexi(0L)
{}
RouterLoopImpl::~RouterLoopImpl()
{
	if(paccum)
	{
		if(paccum[0]&&paccum[0]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[0];
		if(paccum[1]&&paccum[1]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[1];
		if(paccum[2]&&paccum[2]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[2];
		delete[]paccum;
	}
	if(prexi)
		delete prexi;
	if(ind)
		delete adi;
}
int RouterLoopImpl::Bad()
{
	if(paccum[1]->descriptor.vartype!=AC_DOMNODE)
		return 1;
	return paccum[1]->descriptor.var.dom->Bad();
}
DOMNode*RouterLoopImpl::GetFirst()
{
	actItem=0;
	return GetNext();
}
DOMNode*RouterLoopImpl::GetNext()
{
	int zr=0;
	return RetrieveNode(adi->RetrieveDOM(),
		paccum[2]->descriptor.var.pstr,&zr,actItem++);
}
int RouterLoopImpl::Execute()
{
	return _Execute();
}
int RouterLoopImpl::Assemble(CodeUnitCond*pcuc)
{
	char*st1;

	_Assemble(pcuc);
	if(st1=strchr(pcuc->clause,'.'))
	{
		char*st0;
		st0=pcuc->clause;
		st0=RouterStringImpl::_LTrim(st0);
		if(*st0=='!')
		{
			sstype=ST_RS_NEG;
			st0=RouterStringImpl::_LTrim(st0+1);
		}
		*st1=0;
		paccum=new RouterAccumImpl*[3];
		memset(paccum,0,sizeof(RouterAccumImpl*)*3);
		MakeParam(st0,&paccum[0]);
		if(paccum[0]->descriptor.vartype==AC_RECORDSET)
		{
			st1++;
			if(!strcmp(st1,"EOF()"))
				sstype|=ST_RS_EOF;
			else if(!strcmp(st1,"BOF()"))
				sstype|=ST_RS_BOF;
			else
			{
				REI_EPARSE("Invalid use of WHILE - supports only BOF and EOF",REI_ERROR);
				return 0;
			}
			
		}
		else
		{
			REI_EPARSE("Invalid use of WHILE",REI_ERROR);
			return 0;
		}
	}
	else if(RouterHelper::IsSolve(pcuc->clause))
	{
		ParseSolveW(pcuc->clause,&paccum,ptrace);
		sstype=RLI_SOLVE;
	}
	else
	{
		prexi=new RouterExpressionImpl(plib,rootAccum,ptrace);
		prexi->Assemble(pcuc->clause);
		sstype=RLI_EXPR;
	}
	return 1;
}
int RouterLoopImpl::_rbliBefore()
{
	if(sstype==RLI_SOLVE)
	{
		switch(paccum[1]->descriptor.vartype)
		{
		case AC_XMLGEN:
			{
				int sz;
				ind=1;
				adi=new AuxDOMImpl(paccum[1]->descriptor.var.xml->retrieveXML(&sz));
			}
			break;
		case AC_STREAM:
			ind=1;
			adi=new AuxDOMImpl(((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText());
			break;
		case AC_DOMNODE:
			adi=paccum[1]->descriptor.var.dom;
			break;
		}
		if(!paccum[0]->descriptor.var.dom)
			paccum[0]->descriptor.var.dom=new AuxDOMImpl((DOMNode*)0L);
		if(adi->Bad())
		{
			REI_ERUNTIME("Invalid DOMNode - Origin",REI_ERROR);
			return 0;
		}
		actItem=0;
	}
	return 1;
}
int RouterLoopImpl::_rbliGetInd()
{
	if(sstype==RLI_SOLVE)
	{
		pdnd=GetNext();
		if(pdnd)
		{
			paccum[0]->descriptor.var.dom->AttachDOM(pdnd);
			if(paccum[0]->descriptor.var.dom->Bad())
			{
				REI_ERUNTIME("Invalid DOMNode",REI_ERROR);
				return 0;
			}
			return 1;
		}
	}
	else if(sstype==RLI_EXPR)
		return prexi->Execute();
	else
	{
		int i;
		if(sstype&ST_RS_EOF)
			i=((RouterRecordsetImpl*)paccum[0]->descriptor.var.othr)->EOR();
		else if(sstype&ST_RS_BOF)
			i=((RouterRecordsetImpl*)paccum[0]->descriptor.var.othr)->BOR();
		if((sstype&ST_RS_NEG)&&!i)
			return 1;
		else if(i&&!(sstype&ST_RS_NEG))
			return 1;
	}
	return 0;
}
