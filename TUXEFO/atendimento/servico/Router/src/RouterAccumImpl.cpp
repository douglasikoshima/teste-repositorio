#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterAccumImpl.cpp

Abstract:
	Implements accum class to manager variables in script

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterAccumImpl::RouterAccumImpl():
	nextAccum(0L),descriptor(),flags(AC_TYPE_DESTROY),sz(0),ptrace(0L),ref(0)
{}
RouterAccumImpl::RouterAccumImpl(RouterAccumImpl*accum):nextAccum(0L),flags(0),ref(0)
{
	descriptor.var=accum->descriptor.var;
	descriptor.vartype=accum->descriptor.vartype;
	sz=accum->sz;
	strcpy(varNm,accum->varNm);
	flags=0;
}
RouterAccumImpl::~RouterAccumImpl()
{
	if(nextAccum)
		delete nextAccum;
	CleanAndDestroy();
}
RouterAccumImpl*RouterAccumImpl::AddNewAccum()
{
	if(nextAccum)
		return nextAccum->AddNewAccum();
	nextAccum=new RouterAccumImpl();
	return nextAccum;
}
RouterAccumImpl*RouterAccumImpl::AddNewAccum(RouterAccumImpl*accum)
{
	if(nextAccum)
		return nextAccum->AddNewAccum(accum);
	nextAccum=new RouterAccumImpl(accum);
	return nextAccum;
}
RouterAccumImpl*RouterAccumImpl::RetrieveNextAccum()
{return nextAccum;}
RouterAccumImpl*RouterAccumImpl::RetrieveVar(char*vName)
{
	if(!strcmp(vName,varNm))
		return this;
	if(!nextAccum)
		return 0L;
	return nextAccum->RetrieveVar(vName);
}
RouterAccumImpl&RouterAccumImpl::operator=(RouterAccumImpl*accum)
{
	descriptor=accum->descriptor;
	strcpy(varNm,accum->varNm);
	sz=accum->sz;
	flags=0;
	return*this;
}
RouterAccumImpl&RouterAccumImpl::operator=(RouterAccumImpl&accum)
{
	descriptor=accum.descriptor;
	strcpy(varNm,accum.varNm);
	sz=accum.sz;
	flags=0;
	return*this;
}
int RouterAccumImpl::MakeVar(char*vName,int type)
{
	switch(type)
	{
	case AC_XMLGEN:case AC_XMLGENR:
		descriptor.var.xml=new XMLGen();
		goto __mk_var;
		break;
	case AC_INTEGER:case AC_STRING:case AC_ROUTERSTEP:case AC_ROUTERCOND:
	case AC_ROUTERTASKCOND:case AC_DOMNODE:case AC_UNKNOWN:case AC_SQLIND:
	case AC_RECORDSET:
		descriptor.var.othr=0L;
		goto __mk_var;
		break;
	case AC_STREAM:
		descriptor.var.othr=new RouterStreamImpl(ptrace);
		goto __mk_var;
		break;
	}
	*varNm=-1;
	return 0;

__mk_var:
	descriptor.vartype=type;
	if(vName)
	{
		if(strlen(vName)>14)
		{
			char cmsg[1024];
			sprintf(cmsg,"%.900s : var name exceed limit",vName);
			REI_EPARSE(cmsg,REI_ERROR);
			return 0;
		}
		strcpy(varNm,vName);
	}
	else
		strcpy(varNm,prvni->VarName(type));
	flags=AC_TYPE_DESTROY;
	switch(type)
	{
	case AC_XMLGEN:case AC_DOMNODE:case AC_ROUTERSTEP:case AC_ROUTERCOND:
	case AC_ROUTERTASKCOND:case AC_UNKNOWN:case AC_XMLGENR:
		descriptor.varsize=sizeof(char*);
		break;
	case AC_INTEGER:case AC_SQLIND:
		descriptor.varsize=sizeof(int);
		break;
	case AC_STRING:
		descriptor.varsize=0;
		break;
	case AC_STREAM:
		descriptor.varsize=sizeof(RouterStreamImpl*);
		break;
	case AC_RECORDSET:
		descriptor.varsize=sizeof(RouterRecordsetImpl*);
	}
	return 1;
}
int RouterAccumImpl::MakeVar(char*vName,char*typeName)
{
	int type=AC_NONE;

	typeName=RouterStringImpl::_LTrim(typeName);
	RouterStringImpl::_RTrim(typeName);
	vName=RouterStringImpl::_LTrim(vName);
	RouterStringImpl::_RTrim(vName);

	if(!strcmp(typeName,"XMLGen"))
		type=AC_XMLGEN;
	else if(!strcmp(typeName,"DOMNODE"))
		type=AC_DOMNODE;
	else if(!strcmp(typeName,"int"))
		type=AC_INTEGER;
	else if(!strcmp(typeName,"string"))
		type=AC_STREAM;
	else if(!strcmp(typeName,"RECORDSET"))
		type=AC_RECORDSET;
	return MakeVar(vName,type);
}
int RouterAccumImpl::Attach(RouterAccumImpl*accum)
{
	descriptor=accum->descriptor;
	strcpy(varNm,accum->varNm);
	sz=accum->sz;
	flags=0;
	return 1;
}
int RouterAccumImpl::CleanAndDestroy()
{
	if(flags&AC_TYPE_DESTROY)
	{
		switch(descriptor.vartype)
		{
		case AC_XMLGEN:case AC_XMLGENR:
			delete descriptor.var.xml;
			break;
		case AC_DOMNODE:
			if(descriptor.var.dom)
				delete descriptor.var.dom;
			break;
		case AC_STRING:
			if(descriptor.varsize)
				delete[]descriptor.var.pstr;
			else
				delete descriptor.var.pstr;
			break;
		case AC_STREAM:
			delete(RouterStreamImpl*)descriptor.var.othr;
			break;
		case AC_RECORDSET:
			delete(RouterRecordsetImpl*)descriptor.var.othr;
			break;
		}
	}
	return 1;
}
int RouterAccumImpl::OperatorIsEquals(RouterAccumImpl*accum)
{
	switch(descriptor.vartype)
	{
	case AC_NONE:case AC_XMLGEN:case AC_LIBFNC:case AC_UNKNOWN:
	case AC_CUSTTYPE:case AC_DOMNODE:case AC_RECORDSET:
		REI_ERUNTIME("Invalid compare type",REI_ERROR);
	case AC_INTEGER:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_INTEGER:
				return descriptor.var.i32==accum->descriptor.var.i32;
			case AC_STRING:
				return atoi(accum->descriptor.var.pstr)==descriptor.var.i32;
			case AC_STREAM:
				return atoi(((RouterStreamImpl*)accum->descriptor.var.othr)->GetText())==descriptor.var.i32;
			default:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			}
		}
		break;
	case AC_STRING:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_INTEGER:
				return atoi(descriptor.var.pstr)==accum->descriptor.var.i32;
			case AC_STRING:
				return!strcmp(descriptor.var.pstr,accum->descriptor.var.pstr);
			case AC_STREAM:
				return!strcmp(descriptor.var.pstr,((RouterStreamImpl*)accum->descriptor.var.pstr)->GetText());
			default:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			}
		}
		break;
	case AC_STREAM:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_INTEGER:
				return atoi(((RouterStreamImpl*)descriptor.var.othr)->GetText())==accum->descriptor.var.i32;
			case AC_STRING:
				return!strcmp(((RouterStreamImpl*)descriptor.var.othr)->GetText(),accum->descriptor.var.pstr);
			case AC_STREAM:
				return!strcmp(((RouterStreamImpl*)descriptor.var.othr)->GetText(),((RouterStreamImpl*)accum->descriptor.var.pstr)->GetText());
			default:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			}
		}
		break;
	}
	return 0;
}
int RouterAccumImpl::OperatorIsNotEquals(RouterAccumImpl*accum)
{
	return!(OperatorIsEquals(accum));
}
int RouterAccumImpl::OperatorIsLess(RouterAccumImpl*accum)
{
	switch(descriptor.vartype)
	{
	case AC_NONE:case AC_XMLGEN:case AC_LIBFNC:case AC_UNKNOWN:
	case AC_CUSTTYPE:case AC_DOMNODE:case AC_RECORDSET:
		REI_ERUNTIME("Invalid compare type",REI_ERROR);
	case AC_INTEGER:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_INTEGER:
				return descriptor.var.i32<accum->descriptor.var.i32;
			case AC_STRING:
				return atoi(accum->descriptor.var.pstr)>descriptor.var.i32;					
			default:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			}
		}
		break;
	case AC_STRING:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_INTEGER:
				return atoi(descriptor.var.pstr)<accum->descriptor.var.i32;
			case AC_STRING:
				return strcmp(descriptor.var.pstr,accum->descriptor.var.pstr)<0;
			default:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			}
		}
		break;
	}
	return 0;
}
int RouterAccumImpl::OperatorIsGreat(RouterAccumImpl*accum)
{
	switch(descriptor.vartype)
	{
	case AC_NONE:case AC_XMLGEN:case AC_LIBFNC:case AC_UNKNOWN:
	case AC_CUSTTYPE:case AC_DOMNODE:
		REI_ERUNTIME("Invalid compare type",REI_ERROR);
	case AC_INTEGER:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_NONE:case AC_XMLGEN:case AC_LIBFNC:case AC_UNKNOWN:
			case AC_CUSTTYPE:case AC_DOMNODE:case AC_RECORDSET:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			case AC_INTEGER:
				return descriptor.var.i32>accum->descriptor.var.i32;
			case AC_STRING:
				return atoi(accum->descriptor.var.pstr)<descriptor.var.i32;					
			}
		}
		break;
	case AC_STRING:
		{
			switch(accum->descriptor.vartype)
			{
			case AC_NONE:case AC_XMLGEN:case AC_LIBFNC:case AC_UNKNOWN:
			case AC_CUSTTYPE:case AC_DOMNODE:case AC_RECORDSET:
				REI_ERUNTIME("Invalid compare type",REI_ERROR);
			case AC_INTEGER:
				return atoi(descriptor.var.pstr)>accum->descriptor.var.i32;
			case AC_STRING:
				return strcmp(descriptor.var.pstr,accum->descriptor.var.pstr)>0;
			}
		}
		break;
	}
	return 0;
}
