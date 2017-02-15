#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif
#include<time.h>
#include<stdarg.h>

#include "../../../commons/msgPadrao.h"

/*++
Module Name:
    RouterTraceImpl.cpp

Abstract:
	Implements trace

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterTraceImpl::RouterTraceImpl():ind(0),chk(1),dbg(0)
{}
int RouterTraceImpl::PrintHeader()
{
	time_t tmt;
	tm*_tm;
	if(!ind)
		return 0;

	time(&tmt);
	_tm=localtime(&tmt);

	ULOG("%04d-%02d-%02d %02d:%02d:%02d\n",
		_tm->tm_year+1900,_tm->tm_mon+1,_tm->tm_mday,
		_tm->tm_hour,_tm->tm_min,_tm->tm_sec);
	return 1;
}
extern char*cbVarType[];
int RouterTraceImpl::PrintAccum(RouterAccumImpl*accum)
{
	ULOG("VarName: %s\n\tVarAddr: 0x%08x\n\tVarSize: %d\n\tVarType: %s\n\t",
		*accum->varNm?accum->varNm:"NoName",accum,accum->descriptor.varsize,
		cbVarType[accum->descriptor.vartype]);
	switch(accum->descriptor.vartype)
	{
	case AC_STRING:
		ULOG("VarString: %s\n",accum->descriptor.var.pstr);
		break;
	case AC_INTEGER:
		ULOG("VarInteger: %d\n",accum->descriptor.var.i32);
		break;
	case AC_XMLGEN:
		{
			char*pxml;
			int xmls;
			pxml=accum->descriptor.var.xml->retrieveXML(&xmls);
			ULOG("VarXML:\n%s\n",pxml);
		}
		break;
	case AC_DOMNODE:
		ULOG("VarDOMNODE: <NOPRINT>\n");
		break;
	case AC_NONE:
		ULOG("NoValues\n");
		break;
	case AC_LIBFNC:
        ULOG("VarLibfnc: 0x%08x\n",
            accum->descriptor.var.othr);
		break;
	case AC_CUSTTYPE:
        ULOG("VarCustomType: 0x%08x\n",
            accum->descriptor.var.othr);
		break;
	case AC_STREAM:
        ULOG("VarStream: %s\n",
            ((RouterStreamImpl*)accum->descriptor.var.othr)->GetText());
		break;
	case AC_RECORDSET:
        ULOG("VarRS: <NOPRINT>\n");
		break;
	default:
        ULOG("Unknown datatype\n");
	}
	return 1;
}
int RouterTraceImpl::DumpAccum(RouterAccumImpl*accum)
{
	if(!ind)
		return 0;

	PrintHeader();
    ULOG("Dump accumulator:\n\t");
	PrintAccum(accum);
	return 1;
}




int RouterTraceImpl::PrintText(char*pstr)
{
    ULOG("%s\n",pstr);
    return 1;
}



int RouterTraceImpl::DumpText(char*pstr)
{
	if(!ind)
		return 0;
	PrintHeader();
	PrintText(pstr);
	return 1;
}
int RouterTraceImpl::AdjSize(char*pfm)
{
	int sz;
	char*vp,*vpi,n[10],*vn;

	sz=strlen(pfm)+10;

	vp=pfm;
	while(*vp)
	{
		if(*vp=='%')
		{
			vp++;
			if(*vp=='.')vp++;
			vpi=vp;
			if(*vpi>47&&*vpi<58)
			{
				vn=n;
				while(*vpi>47&&*vpi<58)
					*vn++=*vpi++;
				*vn=0;
			}
			if(*vpi=='s')
				sz+=atoi(n);
			else if(*vpi=='d')
				sz+=15;
			else if(*vp=='x')
				sz+=10;
			else if(*vp=='f')
				sz+=25;
			vp=vpi+1;
		}
		else
			vp++;
	}

	return sz;
}
int RouterTraceImpl::DumpVText(char*pfm,...)
{
	va_list vl;

	if(!ind)
		return 0;
	va_start(vl,pfm);
	{
		char*bf;int sz;
		sz=AdjSize(pfm);
		bf=(char*)MMAllocator(sz,"RTI_DVT");
		PrintHeader();
		vsprintf(bf,pfm,vl);
		PrintText(bf);
		MMFree(bf);
	}
	va_end(vl);
	return 1;
}
