#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif
#include<signal.h>

/*++
Module Name:
    RouterPatrolImpl.cpp

Abstract:
	Implements Unix signal patrol routines

Author:
    Ivan Mentone 2004-07-27

Environment:
    Router Core

Revision History:
	

--*/

ITEM::ITEM():pnm(0L),next(0L)
{}
ITEM::~ITEM()
{
	if(next)
		delete next;
	if(pnm)
		free(pnm);
}

void sgAcRtU(int si)
{
	char cmsg[2048],*cur;
	char ccs[1024];

	if(prpi)
		prpi->RetrieveCallStack(ccs,1024);
	else
		sprintf(ccs,"[OutofRouterSegment]");
	cur=cmsg;
	cur+=sprintf(cur,"RunTime - CRIT - %.1023s - ",ccs);
	switch(si)
	{
	case SIGSEGV:
		sprintf(cur,"Segmentation fault");
		break;
	case SIGABRT:
		sprintf(cur,"Abnormal termination");
		break;
	case SIGINT:
		sprintf(cur,"Interrupt signal");
		break;
	case SIGTERM:
		sprintf(cur,"Termination request");
		break;
	case SIGILL:
		sprintf(cur,"Illegal instruction");
		break;
	}
	{
		char cxml[4096],*ret;
		int len;
		len=sprintf(cxml,"<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?><msg><FAILED>%.3050s</FAILED></msg>",cmsg);
#ifdef WIN32
		ret=(char*)malloc(4096);
#else
		ret=tpalloc("STRING",0L,4096);
#endif
		strcpy(ret,cxml);
#ifdef WIN32
		exit(1);
#else
		tpreturn(TPEXIT,0L,ret,len,0);
#endif
	}
}

RouterPatrolImpl::RouterPatrolImpl():pitem(0L)
{
	signal(SIGSEGV,sgAcRtU);
	signal(SIGABRT,sgAcRtU);
	signal(SIGINT,sgAcRtU);
	signal(SIGTERM,sgAcRtU);
	signal(SIGILL,sgAcRtU);
}
RouterPatrolImpl::~RouterPatrolImpl()
{
	if(pitem)
		delete pitem;
}
int RouterPatrolImpl::Push(char*ptsk)
{
	ITEM*item;

	item=new ITEM();
	item->pnm=derivStr(ptsk);
	if(!pitem)
		pitem=item;
	else
	{
		item->next=pitem;
		pitem=item;
	}
	return 1;
}
int RouterPatrolImpl::Pop()
{
	ITEM*item;

	item=pitem;
	if(item)
	{
		pitem=item->next;
		item->next=0L;
		delete item;
	}
	return 1;
}
int RouterPatrolImpl::RetrieveCallStack(char*bf,int sz)
{
	ITEM*item;
	char*cur=bf+1;
	int ic=0;

	*bf='[';
	for(item=pitem;item;item=item->next)
	{
		if(ic)
			cur+=sprintf(cur," <- ");
		if(((cur-bf)+strlen(item->pnm)+10)>(unsigned int)sz)
		{
			cur+=sprintf(cur,"...");
			break;
		}
		cur+=sprintf(cur,"%s",item->pnm);
		ic++;
	}
	sprintf(cur,"]");
	return 1;
}
