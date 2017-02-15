#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterSQLImpl.cpp

Abstract:
	Implements RECORDSET datatype

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

char*_MMDerivStr(char*_x,char*_n)
{
	char*_X;
	int i;

	i=strlen(_x);
	_X=(char*)malloc(i+1);
	strcpy(_X,_x);
	return _X;
}
#define MMDerivStr(A) _MMDerivStr(A,#A)

CELL::CELL():vInd(AC_NONE)
{var.pstr=0L;}
CELL::~CELL()
{
	if(vInd==AC_STRING&&var.pstr)
		free(var.pstr);
}
ROW::ROW():cel(0L),next(0L),prev(0L)
{}
ROW::~ROW()
{
	if(next)
		delete next;
	if(cel)
		delete[]cel;
}
ROW*ROW::AddNewROW()
{
	if(next)
		return next->AddNewROW();
	next=new ROW;
	return next;
}
COL::COL():pstr(0L),ind(0)
{}
COL::~COL()
{
	if(pstr&&ind==AC_STRING)
		free(pstr);
}
RS::RS():pstr(0L),row(0L),col(0L),cind(0)
{}
RS::~RS()
{
	if(pstr)
		free(pstr);
	if(row)
		delete row;
	if(col)
		delete[]col;
}

RouterRecordsetImpl::RouterRecordsetImpl(RouterTraceImpl*pTrace):
	ptrace(pTrace),rs(0L),ic(0),rw(0L),ri(0L),bf(0L),caccum(0L),
	iaccum(0L),ir(0),ieof(0),ibof(0),sqlind(0L)
{}
RouterRecordsetImpl::~RouterRecordsetImpl()
{
	if(rs)
		delete rs;
	if(bf)
		delete[]bf;
	if(iaccum)
		delete iaccum;
	if(caccum)
		delete caccum;
}
int RouterRecordsetImpl::MakeColumns(int _ic)
{
	if(!rs)
		rs=new RS();
	rs->col=new COL[_ic];
	bf=new COL[_ic];
	ic=_ic;
	return 1;
}
int RouterRecordsetImpl::SetColumnName(char*pnm,int ID)
{
	if(ID>ic||ID<0)
	{
		REI_EPARSE("Column ID out of bound",REI_ERROR);
		return 0;
	}
	rs->col[ID].pstr=MMDerivStr(pnm);
	rs->col[ID].ind=AC_STRING;
	return 1;
}
int RouterRecordsetImpl::FindFromColumnID(char*pnm)
{
	int i;

	for(i=0;i<ic;i++)
	{
		if(!strcmp(rs->col[i].pstr,pnm))
			return i;
	}
	{
		char cmsg[1024];
		sprintf(cmsg,"Invalid column name - \"%s\"",pnm);
		REI_ERUNTIME(cmsg,REI_ERROR);
	}
	return -1;
}
char RouterRecordsetImpl::GetValue(int ID,void**ppv)
{
	if(ID>ic||ID<0)
	{
		REI_ERUNTIME("Column ID out of bound",REI_ERROR);
		return 0;
	}
	if(!rw)
		rw=rs->row;
	if(!rw)
		return -1;
	*ppv=rw->cel[ID].var.othr;
	return rw->cel[ID].vInd;
}
char RouterRecordsetImpl::GetValue(char*pnm,void**ppv)
{return GetValue(FindFromColumnID(pnm),ppv);}
RouterAccumImpl*RouterRecordsetImpl::GetValue(int ID)
{
	if(ID>ic||ID<0)
	{
		REI_ERUNTIME("Column ID out of bound",REI_ERROR);
		return 0;
	}
	if(!rw)
		rw=rs->row;
	if(!rw)
		return 0L;
	switch(rw->cel[ID].vInd)
	{
	case AC_INTEGER:
		if(!iaccum)
		{
			iaccum=new RouterAccumImpl();
			iaccum->MakeVar(0L,AC_INTEGER);
		}
		iaccum->descriptor.var.i32=rw->cel[ID].var.i32;
		return iaccum;
	case AC_STRING:
		if(!caccum)
		{
			caccum=new RouterAccumImpl();
			caccum->MakeVar(0L,AC_STREAM);
		}
		((RouterStreamImpl*)caccum->descriptor.var.othr)->SetText(RouterStringImpl::_LTrim(rw->cel[ID].var.pstr));
		return caccum;
	}
	return 0L;
}
RouterAccumImpl*RouterRecordsetImpl::GetValue(char*pnm)
{return GetValue(FindFromColumnID(pnm));}
int RouterRecordsetImpl::MoveFirst()
{
	rw=0L;
	ibof=1;
	return 1;
}
int RouterRecordsetImpl::MoveLast()
{
	if(!rw)
		rw=rs->row;
	while(rw&&rw->next)
		rw=rw->next;
	ieof=1;
	return 1;
}
int RouterRecordsetImpl::MoveNext()
{
	if(!rw)
		rw=rs->row;
	else
	{
		if(rw->next)
		{
			rw=rw->next;
			ieof=0;
		}
		else
			ieof=1;
	}
	return 1;
}
int RouterRecordsetImpl::MovePrevious()
{
	if(rw)
		if(rw->prev)
		{
			rw=rw->prev;
			ibof=0;
		}
		else
			ibof=1;
	return 1;
}
int RouterRecordsetImpl::SetInd(RouterAccumImpl*accum)
{
	sqlind=accum;
	return 1;
}
int RouterRecordsetImpl::GetInd()
{
	if(sqlind)
		return sqlind->descriptor.var.i32;
	return 0;
}
int RouterRecordsetImpl::BOR()
{if(!rs->row)return 1;return ibof;}
int RouterRecordsetImpl::EOR()
{if(!rs->row)return 1;return ieof;}
int RouterRecordsetImpl::AddNewRow()
{
	if(!rs)
		return 0;
	if(!ri)
	{
		rs->row=ri=new ROW();
	}
	else
	{
		ri->next=new ROW();
		ri->next->prev=ri;
		ri=ri->next;
	}
	ri->cel=new CELL[ic];
	ir++;
	return 1;
}
char*RouterRecordsetImpl::GetColBuffInd(int sz,int ID,char ind)
{
	if(ID>ic||ID<0)
	{
		REI_ERUNTIME("Column ID out of bound",REI_ERROR);
		return 0;
	}
	switch(ind)
	{
	case AC_STRING:
		bf[ID].pstr=(char*)malloc(sz);
		bf[ID].pstr[sz-1]=0;
		bf[ID].ind=AC_STRING;
		return bf[ID].pstr;
		break;
	case AC_INTEGER:
		bf[ID].ind=AC_INTEGER;
		return(char*)&bf[ID].pstr;
		break;
	}
	return 0L;
}
int RouterRecordsetImpl::GetColCount()
{return ic;}
int RouterRecordsetImpl::GetRowCount()
{return ir;}
int RouterRecordsetImpl::SetValues()
{
	int i;

	AddNewRow();
	for(i=0;i<ic;i++)
	{
		switch(bf[i].ind)
		{
		case AC_STRING:
			RouterStringImpl::_RTrim(bf[i].pstr);
			ri->cel[i].var.pstr=MMDerivStr(bf[i].pstr);
			ri->cel[i].vInd=AC_STRING;
			break;
		case AC_INTEGER:
			ri->cel[i].var.i32=(((RTINT)bf[i].pstr));
			ri->cel[i].vInd=AC_INTEGER;
			break;
		}
	}
	return 1;
}
