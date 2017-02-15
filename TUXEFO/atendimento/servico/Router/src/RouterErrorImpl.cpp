#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif


/*++
Module Name:
    RouterErrorImpl.cpp

Abstract:
	Implements error control

Author:
    Ivan Mentone 2004-07-08

Environment:
    Router Core

Revision History:
	

--*/

ERRORQUEUE::ERRORQUEUE():pmsg(0L),ind(0),next(0L)
{}
ERRORQUEUE::~ERRORQUEUE()
{
	if(next)
		delete next;
	if(pmsg)
		free(pmsg);
}
ERRORQUEUE*ERRORQUEUE::AddNewERRORQUEUE()
{
	if(next)
		return next->AddNewERRORQUEUE();
	next=new ERRORQUEUE();
	return next;
}

RouterErrorImpl::RouterErrorImpl(RouterTraceImpl*pTrace):
	ptrace(pTrace),iec(0),iwc(0),icc(0),peq(0L),aclen(0),pbf(0L),scpt(0L)
{}
RouterErrorImpl::~RouterErrorImpl()
{
	if(peq)
		delete peq;
	if(scpt)
		free(scpt);
	if(pbf)
		free(pbf);
}
int RouterErrorImpl::HasCriticals()
{return icc;}
int RouterErrorImpl::HasErrors()
{return iec;}
int RouterErrorImpl::HasWarnings()
{return iwc;}
int RouterErrorImpl::AddMessage(char*psrc,char*pmsg,char*pfl,int ln,char ind,char _throw)
{
	char*cur;
	int len=100;
	ERRORQUEUE*eq;

	if(!ind)
		return 0;
	len+=strlen(pmsg);
	if(scpt)
		len+=strlen(scpt);
	if(psrc)
		len+=strlen(psrc);
	if(pfl)
		len+=strlen(pfl);
	if(!peq)
		eq=peq=new ERRORQUEUE();
	else
		eq=peq->AddNewERRORQUEUE();
	cur=eq->pmsg=(char*)malloc(len);
	aclen+=len;
	eq->ind=ind;
	if(scpt)
		cur+=sprintf(cur,"%s - ",scpt);
	if(psrc)
		cur+=sprintf(cur,"%s - ",psrc);
	switch(ind)
	{
	case 1:
		cur+=sprintf(cur,"WARN - ");
		iwc++;
		break;
	case 2:
		cur+=sprintf(cur,"ERR - ");
		iec++;
		break;
	case 3:
		cur+=sprintf(cur,"CRIT - ");
		icc++;
		break;
	case 4:
		cur+=sprintf(cur,"INFO - ");
		break;
	default:
		cur+=sprintf(cur,"UNK - ");
	}
	if(pfl)
		cur+=sprintf(cur,"%s - ",pfl);
	if(ln>-1)
		cur+=sprintf(cur,"%d - ",ln);
	sprintf(cur,"%s",pmsg);

	switch(_throw)
	{
	case 1:
		throw new TuxBasicSvcException("00E9999",eq->pmsg);
		break;
	case 2:
		Throw();
		break;
	}
	return 0;
}
int RouterErrorImpl::Throw()
{
	ERRORQUEUE*eq;
	char*cur;

	eq=peq;
	if(!aclen||!eq)
		return 0;
	if(!icc&&!iec&&!iwc)
		return 0;
	cur=pbf=(char*)malloc(aclen);
	*cur++=10;
	while(eq)
	{
		cur+=sprintf(cur,"%s\n",eq->pmsg);
		eq=eq->next;
	}
	if(strlen(pbf)>900)
	{
		pbf[900]=0;
		strcat(pbf,"!--Buffer Overflow--!");	//TR23
	}
	throw new TuxBasicSvcException("00E9999",pbf);
	return 1;
}
int RouterErrorImpl::SetScriptName(char*pscr)
{
	if(scpt)
		free(scpt);
	scpt=(char*)malloc(strlen(pscr)+1);
	strcpy(scpt,pscr);
	return 1;
}
