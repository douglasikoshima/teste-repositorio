#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterTransformImpl.cpp

Abstract:
	Implements casts

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-07 - Ivan Mentone - Implement cast from string and char to DOMNode

--*/

RouterTransformImpl::RouterTransformImpl(RouterAccumImpl*accum):
	RouterHelper(accum,0L),paccum(accum)
{}
int RouterTransformImpl::ReplaceEscapeSequence()
{
	char*st1,*cur;

	if(paccum->descriptor.vartype!=AC_STRING)
		return 0;
	cur=paccum->descriptor.var.pstr;
	st1=strchr(cur,92);
	while(st1)
	{
		switch(*(st1+1))
		{
		case 110:*st1=10;break;
		case 114:*st1=13;break;
		case 116:*st1=9;break;
		case 34:*st1=34;break;
		case 92:break;
		default:REI_ERUNTIME("Unknown escape sequence",REI_ERROR);
		}
		st1++;
		memcpy(st1,st1+1,strlen(st1+1)+1);
		st1=strchr(st1,92);
	}

	paccum->sz=strlen(paccum->descriptor.var.pstr);
	return 1;
}
int RouterTransformImpl::HasEscapeSequence(RouterAccumImpl*accum)
{
	char*st1;

	if(accum->descriptor.vartype!=AC_STRING)
		return 0;
	st1=strchr(accum->descriptor.var.pstr,92);
	while(st1)
	{
		st1++;
		if(*st1==110||*st1==114||*st1==116||*st1==34)
			return 1;
		st1=strchr(st1,92);
	}
	return 0;
}
int RouterTransformImpl::CastToInteger(RouterAccumImpl*accum,char op)
{
	int ival;

	switch(paccum->descriptor.vartype)
	{
	case AC_DOMNODE:case AC_LIBFNC:case AC_UNKNOWN:case AC_CUSTTYPE:case AC_NONE:
		case AC_XMLGEN:
		return 0;
	case AC_INTEGER:
		if(op==OP_TR_RPL)
			accum->descriptor.var.i32=paccum->descriptor.var.i32;
		else
			ival=paccum->descriptor.var.i32;
		break;
	case AC_STRING:
		ival=atoi(paccum->descriptor.var.pstr);
		if(op==OP_TR_RPL)
			accum->descriptor.var.i32=ival;
		break;
	case AC_STREAM:
		ival=atoi(((RouterStreamImpl*)paccum->descriptor.var.othr)->GetText());
		if(op==OP_TR_RPL)
			accum->descriptor.var.i32=ival;
		break;
	}

	switch(op)
	{
	case OP_TR_ADD:
		accum->descriptor.var.i32+=ival;
		break;
	case OP_TR_DFF:
		accum->descriptor.var.i32-=ival;
		break;
	case OP_TR_MLT:
		accum->descriptor.var.i32*=ival;
		break;
	case OP_TR_DIV:
		accum->descriptor.var.i32/=ival;
		break;
	}
	return 1;
}
int RouterTransformImpl::CastToString(RouterAccumImpl*accum,char op)
{
	char*dsc;

	switch(paccum->descriptor.vartype)
	{
	case AC_DOMNODE:case AC_LIBFNC:case AC_UNKNOWN:case AC_CUSTTYPE:case AC_NONE:
		return 0;
	case AC_INTEGER:
		{
			if(op==OP_TR_ADD)
			{
				if(accum->descriptor.varsize<accum->sz+20)
					goto __cascade_exception;
				dsc=accum->descriptor.var.pstr+accum->sz;
			}
			else
			{
				if(accum->descriptor.varsize<20)
					goto __cascade_exception;
				dsc=accum->descriptor.var.pstr;
				accum->sz=0;
			}
			sprintf(dsc,"%d",paccum->descriptor.var.i32);
			accum->sz=strlen(accum->descriptor.var.pstr);
		}
		break;
	case AC_STRING:
		{
			if(paccum->sz)
			{
				if(op==OP_TR_ADD)
				{
					if(accum->descriptor.varsize<(accum->sz+paccum->sz)+1)
						goto __cascade_exception;
					dsc=accum->descriptor.var.pstr+accum->sz;
				}
				else
				{
					if(accum->descriptor.varsize<(paccum->sz+1))
						goto __cascade_exception;
					dsc=accum->descriptor.var.pstr;
					accum->sz=0;
				}
				memcpy(dsc,paccum->descriptor.var.othr,paccum->sz+1);
				accum->sz+=paccum->sz;
			}
		}
		break;
	case AC_XMLGEN:
		{
			char*pxml;
			int sz;

			pxml=paccum->descriptor.var.xml->retrieveXML(&sz);
			if(op==OP_TR_ADD)
			{
				if(accum->descriptor.varsize<(sz+accum->sz)+1)
					goto __cascade_exception;
				dsc=accum->descriptor.var.pstr+accum->sz;
			}
			else
			{
				if(accum->descriptor.varsize<sz+1)
					goto __cascade_exception;
				dsc=accum->descriptor.var.pstr;
				accum->sz=0;
			}
			strcpy(dsc,pxml);
			accum->sz+=sz;
		}
		break;
	}
	accum->descriptor.vartype=AC_STRING;
	return 1;
__cascade_exception:
	REI_ERUNTIME("Size less than need",REI_ERROR);
	return 0;
}
int RouterTransformImpl::CastFromXMLGenToDOMNode(RouterAccumImpl*accum)
{
	int ixml;
	AuxDOMImpl*dnode;

	dnode=new AuxDOMImpl(paccum->descriptor.var.xml->retrieveXML(&ixml));
 	if(dnode->Bad())
		REI_ERUNTIME("Bad XML",REI_ERROR);
	if(accum->descriptor.var.dom)
		delete accum->descriptor.var.dom;
	accum->descriptor.var.dom=dnode;
	return 1;
}
int RouterTransformImpl::CastFromDOMNodeToXMLGen(RouterAccumImpl*accum,char op)
{
	AuxDOMImpl*padi=paccum->descriptor.var.dom;
	RouterDOMImpl prdi(padi);
	if(op==OP_TR_RPL)
		accum->descriptor.var.xml->clearAndDestroy();
	((RouterXMLImpl*)accum->descriptor.var.xml)->AttachText(prdi.GetText());
	return 0;
}
int RouterTransformImpl::CopyFromXMLToXML(RouterAccumImpl*accum,char op)
{
	char*pxml;
	int ixml;

	switch(paccum->descriptor.vartype)
	{
	case AC_XMLGEN:
		switch(accum->descriptor.vartype)
		{
		case AC_XMLGEN:
			pxml=paccum->descriptor.var.xml->retrieveXML(&ixml);
			accum->descriptor.var.xml->clearAndDestroy();
			accum->descriptor.var.xml->aggregateXML(pxml);
			break;
		case AC_DOMNODE:
			return CastFromXMLGenToDOMNode(accum);
			break;
		}
		break;
	case AC_DOMNODE:
		switch(accum->descriptor.vartype)
		{
		case AC_XMLGEN:
			return CastFromDOMNodeToXMLGen(accum,op);
			break;
		case AC_DOMNODE:
			accum->descriptor.var.dom->AttachDOM(paccum->descriptor.var.dom->RetrieveDOM());
			break;
		}
		break;
		break;
	default:
		REI_ERUNTIME("Bad type",REI_ERROR);
	}
	return 0;
}
int RouterTransformImpl::CastFromIntToXML(RouterAccumImpl*accum)
{
	if(paccum->descriptor.vartype!=AC_INTEGER||
		accum->descriptor.vartype!=AC_XMLGEN)
		REI_ERUNTIME("Invalid conversion from Integer to XML",REI_ERROR);
	accum->descriptor.var.xml->createTag("Value");
	accum->descriptor.var.xml->addItem("Int",paccum->descriptor.var.i32);
	accum->descriptor.var.xml->closeTag();
	return 1;
}
int RouterTransformImpl::CastFromCharToXML(RouterAccumImpl*accum,char op)
{
	if(paccum->descriptor.vartype!=AC_STRING||
		accum->descriptor.vartype!=AC_XMLGEN)
		REI_ERUNTIME("Invalid conversion from char to XML",REI_ERROR);
	if(op==OP_TR_RPL)
		accum->descriptor.var.xml->clearAndDestroy();
	((RouterXMLImpl*)accum->descriptor.var.xml)->AttachText(paccum->descriptor.var.pstr);
	return 1;
}
int RouterTransformImpl::CastFromStreamToXML(RouterAccumImpl*accum,char op)
{
	if(paccum->descriptor.vartype!=AC_STREAM||
		accum->descriptor.vartype!=AC_XMLGEN)
		REI_ERUNTIME("Invalid conversion from Stream to XML",REI_ERROR);
	if(op==OP_TR_RPL)
		accum->descriptor.var.xml->clearAndDestroy();
	((RouterXMLImpl*)accum->descriptor.var.xml)->AttachText(((RouterStreamImpl*)paccum->descriptor.var.othr)->GetText());
	return 1;
}
int RouterTransformImpl::CastFromDOMNODEToStringOrInteger(RouterAccumImpl*accum,char*tag,char op)
{
	AuxDOMImpl*adi,*adic;
	char crt=0;

	if(accum->descriptor.vartype!=AC_STRING&&
		accum->descriptor.vartype!=AC_INTEGER&&
		accum->descriptor.vartype!=AC_STREAM&&
		accum->descriptor.vartype!=AC_DOMNODE)
		REI_ERUNTIME("Invalid cast from DOMNODE",REI_ERROR);
	if(paccum->descriptor.vartype!=AC_DOMNODE&&
		paccum->descriptor.vartype!=AC_XMLGEN)
		REI_ERUNTIME("Invalid cast to this datatype",REI_ERROR);
	else if(paccum->descriptor.vartype==AC_XMLGEN)
	{
		char*xml;int len;
		RouterStringImpl::_Encode(paccum->descriptor.var.xml);
		xml=paccum->descriptor.var.xml->retrieveXML(&len);
		adic=new AuxDOMImpl(xml);
		crt=1;
	}
	else if(paccum->descriptor.vartype==AC_DOMNODE)
		adic=paccum->descriptor.var.dom;
	if(!adic)
		REI_ERUNTIME("Empty DOMNode",REI_ERROR);
	adi=new AuxDOMImpl(adic->RetrieveDOM());
	if(adi->Bad())
		REI_ERUNTIME("Invalid origin",REI_ERROR);
	if(!adi->SetRoot(tag))
		adi->AttachDOM(0L);
	if(accum->descriptor.vartype==AC_DOMNODE)
	{
		DOMNode*ca,*cb;
		if(accum->descriptor.var.dom)
			ca=accum->descriptor.var.dom->RetrieveDOM();
		else
			ca=0L;
		cb=adi->RetrieveDOM();
		if(paccum==accum&&ca!=cb)
		{
			accum->descriptor.var.dom->AttachDOM(adi->RetrieveDOM());
			adi->AttachDOM(0L);
			delete adi;
		}
		else if(!accum->descriptor.var.dom)
			accum->descriptor.var.dom=adi;
		else
		{
			delete accum->descriptor.var.dom;
			accum->descriptor.var.dom=adi;
		}
	}
	else
	{
		char*pt=RetrieveNodeText(adi->RetrieveDOM(),tag);
		if(!pt)
		{
			delete adi;
			return 1;
		}
		if(accum->descriptor.vartype==AC_STRING)
		{
			int sz;
			sz=strlen(pt);
			if(accum->descriptor.varsize<sz+1)
				REI_ERUNTIME("Size less than need",REI_ERROR);
			strcpy(accum->descriptor.var.pstr,pt);
			accum->sz=sz;
		}
		else if(accum->descriptor.vartype==AC_STREAM)
		{
			if(op==OP_TR_ADD)
				((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(pt);
			else
				((RouterStreamImpl*)accum->descriptor.var.othr)->SetText(pt);
		}
		else
		{
			if(!pt)
				accum->descriptor.var.i32=0;
			else
				accum->descriptor.var.i32=atoi(pt);
		}
		delete adi;
	}
	if(crt)
	{
		adic->AttachDOM(0L);
		delete adic;
	}
	return 1;
}
int RouterTransformImpl::CastToStream(RouterAccumImpl*accum,char op)
{
	char*src;
	char ind=0;

	switch(paccum->descriptor.vartype)
	{
	case AC_STRING:
		src=paccum->descriptor.var.pstr;
		break;
	case AC_INTEGER:
		ind=1;
		src=(char*)MMAllocator(20,"src");
		sprintf(src,"%d",paccum->descriptor.var.i32);
		break;
	case AC_XMLGEN:
		{
			int sz;
			src=paccum->descriptor.var.xml->retrieveXML(&sz);
		}
		break;
	case AC_STREAM:
		src=(char*)((RouterStreamImpl*)paccum->descriptor.var.othr)->GetText();
		break;
	case AC_DOMNODE:
		{
			AuxDOMImpl*padi=paccum->descriptor.var.dom;
			RouterDOMImpl prdi(padi);
			src=derivStr(prdi.GetText());
			ind=1;
		}
		break;
	default:
		REI_ERUNTIME("Invalid cast to this datatype",REI_ERROR);
	}
	if(op==OP_TR_ADD)
		((RouterStreamImpl*)accum->descriptor.var.othr)->AppendText(src);
	else
		((RouterStreamImpl*)accum->descriptor.var.othr)->SetText(src);
	if(ind)
		MMFree(src);
	return 1;
}
int RouterTransformImpl::CastFromStreamToDOMNode(RouterAccumImpl*accum)
{
	if(accum->descriptor.var.dom)
		accum->descriptor.var.dom->SetText(((RouterStreamImpl*)paccum->descriptor.var.othr)->GetText());
	else
		accum->descriptor.var.dom=new AuxDOMImpl(((RouterStreamImpl*)paccum->descriptor.var.othr)->GetText());
	if(accum->descriptor.var.dom->Bad())
		REI_ERUNTIME("Bad XML Data",REI_ERROR);
	return 1;
}
int RouterTransformImpl::CastFromStringToDOMNode(RouterAccumImpl*accum)
{
	XMLGen*pxml;
	char*pcxml;
	int len;

	pxml=new XMLGen;
	((RouterXMLImpl*)pxml)->AttachText(paccum->descriptor.var.pstr);
	RouterStringImpl::_Encode(pxml);
	pcxml=pxml->retrieveXML(&len);
	if(accum->descriptor.var.dom)
		accum->descriptor.var.dom->SetText(pcxml);
	else
		accum->descriptor.var.dom=new AuxDOMImpl(pcxml);
	delete pxml;
	if(accum->descriptor.var.dom->Bad())
		REI_ERUNTIME("Bad XML Data",REI_ERROR);
	return 1;
}