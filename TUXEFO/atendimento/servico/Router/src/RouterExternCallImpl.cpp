#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterExternCallImpl.cpp

Abstract:
	Implements extern script calls

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-08-04 - Ivan Mentone - Update SetVars method to handle 'undeclared identifier'

--*/

RouterExternCallImpl::RouterExternCallImpl(RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	rootAccum(rAccum),ptrace(pTrace),paccum(0L),pr(0L),pcode(0L),RouterHelper(rAccum,pTrace),
	padi(0L),ind(0),op(OP_TR_RPL),pxml(0L)
{}
RouterExternCallImpl::~RouterExternCallImpl()
{
	if(paccum)
	{
		if(paccum[0]&&paccum[0]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[0];
		if(paccum[1]&&paccum[1]->flags&AC_TYPE_DISCARDABLE)
			delete paccum[1];
		delete[]paccum;
	}
	if(pcode)
		free(pcode);
	if(ind)
		delete padi;
	if(pxml)
		delete pxml;
	if(pr)
		delete pr;
}
int RouterExternCallImpl::Assemble(char*psrc)
{
	pcode=psrc;
	return 1;
}
int RouterExternCallImpl::Execute()
{
	int sz;
	RouterAccumImpl*accum;

	switch(paccum[1]->descriptor.vartype)
	{
	case AC_XMLGEN:
		{
			char*xml;
			ind=1;
			xml=paccum[1]->descriptor.var.xml->retrieveXML(&sz);
			padi=new AuxDOMImpl(xml);
		}
		break;
	case AC_STREAM:
		ind=1;
		padi=new AuxDOMImpl(((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText());
		break;
	case AC_DOMNODE:
		padi=new AuxDOMImpl(paccum[1]->descriptor.var.dom->RetrieveDOM());
		ind=1;
	}
	pxml=new XMLGen();
	if(padi->Bad())
		REI_ERUNTIME("Invalid DOMNode",REI_ERROR);
	accum=rootAccum->RetrieveVar("#tuxThis#");
	if(!accum)
		REI_ERUNTIME("Internal fail",REI_CRITICAL);
	pr=new Router(padi->RetrieveDOM(),pxml,accum->descriptor.var.othr);
	pr->Assemble(pcode);
	pr->Execute();
	switch(paccum[0]->descriptor.vartype)
	{
	case AC_STREAM:
		if(op==OP_TR_ADD)
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->AppendText(pxml->retrieveXML(&sz));
		else
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->SetText(pxml->retrieveXML(&sz));
		break;
	case AC_XMLGEN:
		if(op==OP_TR_ADD)
			((RouterXMLImpl*)paccum[0]->descriptor.var.xml)->AttachText(pxml->retrieveXML(&sz));
		else
		{
			paccum[0]->descriptor.var.xml->clearAndDestroy();
			paccum[0]->descriptor.var.xml->aggregateXML(pxml->retrieveXML(&sz));
		}
		break;
	}
	return 1;
}
int RouterExternCallImpl::SetVars(char*pin,char*pout)
{
	pin=RouterStringImpl::_LTrim(pin);
	RouterStringImpl::_RTrim(pin);
	pout=RouterStringImpl::_LTrim(pout);
	RouterStringImpl::_RTrim(pout);

	paccum=new RouterAccumImpl*[2];
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);
	if(!MakeParam(pin,&paccum[0]))
		return 0;
	if(!strcmp(pout,"XML"))
	{
		char chmvs[]="[a_@XML]";
		MakeParam(chmvs,&paccum[1]);
	}
	else
	{if(!MakeParam(pout,&paccum[1]))return 0;}
	if(paccum[1]->descriptor.vartype!=AC_XMLGEN&&paccum[1]->descriptor.vartype!=AC_STREAM&&
		paccum[1]->descriptor.vartype!=AC_DOMNODE)
		REI_ERUNTIME("Invalid cast from this type to DOMNode",REI_ERROR);
	if(paccum[0]->descriptor.vartype!=AC_XMLGEN&&paccum[0]->descriptor.vartype!=AC_STREAM)
		REI_ERUNTIME("Invalid cast from this type to XMLGen",REI_ERROR);
	return 1;
}
int RouterExternCallImpl::SetInd()
{
	op=OP_TR_ADD;
	return 1;
}