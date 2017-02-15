#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterCursorImpl.cpp

Abstract:
	Implements class to work with Oracle

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-06 - Ivan Mentone - Add functions SeekCommaAdj and SeekRelativesLmtAdj
	2004-07-06 - Ivan Mentone - Corrections in engine to generate VarNm
	2004-07-13 - Ivan Mentone - Execute SQL command from var
	2004-07-14 - Ivan Mentone - Add support to RouterRecordsetImpl
	2004-07-20 - Ivan Mentone - TR ML 3
	2004-07-20 - Ivan Mentone - Corrections in AdjustWHEREAdj for retrieve integer values correctly

--*/

COLUMN::COLUMN():cname(0L),paccum(0L),nextColumn(0L),clrType(0)
{}
COLUMN::~COLUMN()
{
	if(nextColumn)
		delete nextColumn;
	if(cname)
		free(cname);
	if(clrType&AC_TYPE_DISCARDABLE&&paccum)
		delete paccum;
}
COLUMN*COLUMN::AddNewColumn()
{
	if(nextColumn)
		return nextColumn->AddNewColumn();
	nextColumn=new COLUMN;
	return nextColumn;
}
RouterCursorImpl::RouterCursorImpl(RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	sqlclause(0L),pcol(0L),rootAccum(rAccum),ptrace(pTrace),wclause(0L),cc(0),psqlInd(0L),
	RouterHelper(rAccum,pTrace),sqlcd(0),adind(1)
{}
RouterCursorImpl::~RouterCursorImpl()
{
	if(sqlclause)
		free(sqlclause);
	if(pcol)
		delete pcol;
	if(wclause)
		delete wclause;
}
char*RouterCursorImpl::SeekFROMAdj(char*pc)
{
	char*sv,*cur,ff;

	ff=102;
	sv=pc;
scan:
	while(pc)
	{
		cur=strchr(pc,ff);
		if(cur)
		{
			if((*(cur+1)!=114&&*(cur+1)!=82)||(*(cur+2)!=111&&*(cur+2)!=79)||
				(*(cur+3)!=109&&*(cur+3)!=77))
				pc=cur+1;
			else
				break;
		}
		else
			pc=0L;
	}
	if(!cur&&ff==102)
	{
		ff=70;
		pc=sv;
		goto scan;
	}
	return cur;
}
char*RouterCursorImpl::SeekCommaAdj(char*pc)
{
	char*cr,*cm,*fr;
	int rl=0;

	fr=SeekFROMAdj(pc);
	cm=strchr(pc,',');
	if(!cm)
		return 0L;
	if(fr&&cm>fr)
		return 0L;
	cr=strchr(pc,'(');
	if(cr>cm)
		return cm;
	cm=RouterStringImpl::_SeekRelativesLmtAdj(pc);
	if(cm)
		cm=strchr(cm,',');
	return cm;
}
int RouterCursorImpl::ReadSelectListAdj(char*pln)
{
	char*st2,*st3,och;

	st2=pln+6;
	och=0;
	while(st2)
	{
		st3=SeekCommaAdj(st2);	// TR 20
		if(!st3)
		{
			st3=SeekFROMAdj(st2);
			if(!st3)
			{
				REI_EPARSE("Invalid SQL startment - cannot find 'FROM' clause",REI_ERROR);
				return 0;
			}
			och=*st3;
		}
		*st3=0;
		if(strchr(st2,'('))
			st2=RouterStringImpl::_SeekRelativesLmtAdj(st2);	// TR 21
		else
		{
			char*st11;
			if(st11=strchr(st2,'\''))
			{
				while(st11)
				{
					st2=st11+1;
					st11=strchr(st2,'\'');
				}
			}
		}
		st2=RouterStringImpl::_LTrim(st2);
		AddColumnItem(st2);
		if(och)
		{
			*st3=och;
			st2=0L;
		}
		else
			st2=st3+1;
	}
	return 1;
}
int RouterCursorImpl::Assemble(char*pv)
{return Assemble(pv,1);}
int RouterCursorImpl::Assemble(char*pln,int id)
{
	pln=RouterStringImpl::_LTrim(pln);
	if((*pln!=115&&*pln!=83)||(*(pln+1)!=101&&*(pln+1)!=69)||
		(*(pln+2)!=108&&*(pln+2)!=76)||(*(pln+3)!=101&&*(pln+3)!=69)||
		(*(pln+4)!=99&&*(pln+4)!=67)||(*(pln+5)!=116&&*(pln+5)!=84))
	{
		REI_EPARSE("Script supports only SELECT clause",REI_ERROR);
		return 0;
	}

	{
		char*bf;
		bf=derivStr(pln);
		if(sqlclause)
			free(sqlclause);
		sqlclause=derivStr(pln);

		ReadSelectListAdj(bf);
		free(bf);
	}
	MakeInd();
	if(id)AssembleVars();

	return SetWHEREAdj(sqlclause);
}
int RouterCursorImpl::AddColumnItem(char*pit)
{
	COLUMN*col;
	char*pcc;

	pit=RouterStringImpl::_LTrim(pit);
	RouterStringImpl::_RTrim(pit);

	if(pcc=strchr(pit,39))
		pit=pcc+1;

	if(pcc=strchr(pit,32))
		pit=pcc+1;

	if(!pcol)
		pcol=col=new COLUMN;
	else
		col=pcol->AddNewColumn();

	col->cname=derivStr(pit);
	RouterStringImpl::_RTrim(col->cname);
	return 1;
}
char*RouterCursorImpl::SeekWHEREAdj(char*pln)
{
	char*st1;char tf=119;
	st1=pln;
scan:
	st1=strchr(st1,tf);
	if(!st1)
	{
		if(tf!=87)
		{
			tf=87;
			st1=pln;
			goto scan;
		}
		return 0L;
	}
	if((*(st1+1)!=104&&*(st1+1)!=72)||(*(st1+2)!=101&&*(st1+2)!=69)||
		(*(st1+3)!=114&&*(st1+3)!=82)||(*(st1+4)!=101&&*(st1+4)!=69))
	{
		st1=st1+1;
		goto scan;
	}
	return st1;
}
int RouterCursorImpl::CommitSQL(char*pv1,char*pv2,char**ppv1,char**ppv2,char ind,int*pbnd)
{
	int pvsz;

	pvsz=pv1-*ppv1;
	if(ind==39)
		pvsz--;
	strncpy(*ppv2,*ppv1,pvsz);
	*ppv2+=pvsz;
	*ppv2+=sprintf(*ppv2,":b%d ",(*pbnd)++);
	*ppv1=pv2;
	if(pv2&&*pv2)
		(*ppv1)++;
	return 1;
}
int RouterCursorImpl::SetWHEREAdj(char*pln)
{
	char*st1,*st2,*st3,*sp,och,cch;
	COLUMN*col;
	char*pv,*ppv,*wptr;
	int pvsz,ind;

	if(!(st1=SeekWHEREAdj(pln)))
		return 0;
	st1+=6;

	ppv=pv=(char*)malloc(strlen(pln)*2);
	wptr=st1;
	sp=st1;
	ind=0;

	while(st1)
	{
		st2=RouterStringImpl::_FindFromLimitAdj(st1);
		if(st2)
		{
			st2++;
			st2=RouterStringImpl::_LTrim(st2);
			if(*st2==39||(*st2>47&&*st2<58))
			{
				if(*st2==39)
				{
					cch=39;
					st2++;
				}
				else 
					cch=32;
				st3=strchr(st2,cch);
				if(!st3)
				{
					if(cch==32)
					{
						st3=st2;
						while(*st3>47&&*st3<58)st3++;
					}
					else
					{
						REI_EPARSE("Invalid WHERE clause",REI_ERROR);
						return 0;
					}
				}
				CommitSQL(st2,st3,&sp,&ppv,cch,&ind);
				och=*st3;
				*st3=0;
				if(!wclause)
					wclause=col=new COLUMN();
				else
					col=col->AddNewColumn();
				col->cname=derivStr(st2);
				if(cch==39)
					st2--;
				*st3=och;
				st2=st3;
			}
			else
			{
				if(!IsSQL(st2)&&*st2==':')
				{
					char*st3,och=0;
					RouterAccumImpl*accum;
					st2++;
					st3=RouterStringImpl::_FindFromLimitAdj(st2);
					if(st3)
					{
						och=*st3;
						*st3=0;
					}
					accum=rootAccum->RetrieveVar(st2);
					if(accum)
					{
						if(!wclause)
							wclause=col=new COLUMN();
						else
							col=col->AddNewColumn();
						col->cname=derivStr(st2);
						col->clrType=AC_TYPE_MOUNT;
						CommitSQL(st2-1,st3,&sp,&ppv,0,&ind);
					}
					else
					{
						char cmsg[1024];
						sprintf(cmsg,"'%.900s' : undeclared identifier",st2);
						REI_EPARSE(cmsg,REI_ERROR);
					}
					if(och)
						*st3=och;
				}
			}
		}
		st1=st2;
	}
	if(sp&&*sp)
	{
		pvsz=strlen(sp);
		strncpy(ppv,sp,pvsz);
		ppv[pvsz]=0;
	}
	pvsz=strlen(pv);
	if((int)strlen(wptr)<pvsz)
	{
		AdjustSize(pvsz);
		wptr=SeekWHEREAdj(sqlclause)+6;
	}
	strcpy(wptr,pv);
	free(pv);
	return 1;
}
int RouterCursorImpl::AdjustSize(int sz)
{
	int nsz;
	char*np;

	nsz=strlen(sqlclause);
	np=(char*)malloc(sz+nsz);
	strcpy(np,sqlclause);
	free(sqlclause);
	sqlclause=np;
	return 1;
}
int RouterCursorImpl::CountWhere()
{
	COLUMN*wcc=wclause;
	int sz=0;

	for(;wcc;wcc=wcc->nextColumn)
		sz++;
	return sz;
}
int RouterCursorImpl::AssembleVars()
{
	COLUMN*pcc;
	RouterAccumImpl*accum;

	for(pcc=pcol;pcc;pcc=pcc->nextColumn)
	{
		accum=rootAccum->AddNewAccum();
		accum->MakeVar(pcc->cname,AC_UNKNOWN);
		accum->AddRef();
	}
	return 1;
}
int RouterCursorImpl::AssembleRecordsetVar(RouterRecordsetImpl*prrs)
{
	COLUMN*pcc;
	int icl=0;

	for(pcc=pcol;pcc;pcc=pcc->nextColumn)
		icl++;
	prrs->MakeColumns(icl);
	icl=0;
	for(pcc=pcol;pcc;pcc=pcc->nextColumn)
		prrs->SetColumnName(pcc->cname,icl++);
	return 1;
}
int RouterCursorImpl::AdjustWHEREAdj()
{
	COLUMN*pcc;
	RouterAccumImpl*accum;

	for(pcc=wclause;pcc;pcc=pcc->nextColumn)
	{
		if(pcc->clrType&AC_TYPE_MOUNT)
		{
			accum=rootAccum->RetrieveVar(pcc->cname);
			switch(accum->descriptor.vartype)
			{
			case AC_STRING:
				{
					pcc->paccum=new RouterAccumImpl();
					pcc->paccum->MakeVar(0L,AC_STRING);
					pcc->paccum->flags=AC_TYPE_AUTOMOUNT;
					pcc->paccum->descriptor.var.pstr=derivStr(accum->descriptor.var.pstr);
					pcc->clrType=AC_TYPE_DISCARDABLE;
					pcc->paccum->descriptor.varsize=accum->descriptor.varsize;
					pcc->paccum->sz=accum->sz;
				}
				break;
			case AC_STREAM:
				{
					char*pv;
					pv=((RouterStreamImpl*)accum->descriptor.var.othr)->GetText();
					pcc->paccum=new RouterAccumImpl();
					pcc->paccum->MakeVar(0L,AC_STRING);
					pcc->paccum->flags=AC_TYPE_AUTOMOUNT;
					pcc->paccum->descriptor.var.pstr=derivStr(pv);
					pcc->clrType=AC_TYPE_DISCARDABLE;
					pcc->paccum->sz=strlen(pv);
					pcc->paccum->descriptor.varsize=pcc->paccum->sz;
				}
				break;
			case AC_INTEGER:
				{
					char pv[20];
					sprintf(pv,"%d",accum->descriptor.var.i32);
					pcc->paccum=new RouterAccumImpl();
					pcc->paccum->MakeVar(0L,AC_STRING);
					pcc->paccum->flags=AC_TYPE_AUTOMOUNT;
					pcc->paccum->descriptor.var.pstr=derivStr(pv);
					pcc->clrType=AC_TYPE_DISCARDABLE;
					pcc->paccum->sz=strlen(pv);
					pcc->paccum->descriptor.varsize=pcc->paccum->sz;
				}
				break;
			default:
				REI_EPARSE("Invalid datatype",REI_ERROR);
			}
		}
	}
	return 0;
}
int RouterCursorImpl::CountSelList()
{
	COLUMN*pcc=pcol;
	int sz=0;

	for(;pcc;pcc=pcc->nextColumn)
		sz++;
	return sz;
}
int RouterCursorImpl::NVL(char*pst)
{
	if(strlen(pst)>4)
	{
		if((*(pst)!=110&&*(pst)!=78)||(*(pst+1)!=117&&*(pst+1)!=85)||
			(*(pst+2)!=108&&*(pst+2)!=76)||(*(pst+3)!=108&&*(pst+3)!=76))
			return 0;
		else
			return -1;
	}
	return 0;
}
int RouterCursorImpl::MakeInd()
{
	char cInd[20];
	RouterAccumImpl*accum;

	accum=rootAccum->RetrieveVar("SQLIND");
	sprintf(cInd,"SQL%d",accum->descriptor.var.i32++);
	psqlInd=rootAccum->AddNewAccum();
	psqlInd->MakeVar(cInd,AC_SQLIND);
	psqlInd->AddRef();
	if(ptrace->HasTrace())
	{
		char cmsg[1024];
		sprintf(cmsg,"Make SQL Indicator named: %s",cInd);
		ptrace->DumpText(cmsg);
	}
	return 1;
}
int RouterCursorImpl::Execute(RouterRecordsetImpl*prrs)
{
	void*SQLDASelData=0L;
	void*SQLDAWhereClause=0L;
	short*fetchInd=0L,*whereInd=0L;
	int ind;

	prrs->SetInd(psqlInd);
	AdjustWHEREAdj();
	AssembleRecordsetVar(prrs);
	ptrace->DumpText("Prepare SQL startment");
	Prepare();
	ptrace->DumpText("Assemble WHERE clause");
	AssembleWhere(&SQLDAWhereClause,&whereInd);
	ptrace->DumpText("Assemble columns");
	AssembleColsAdj(SQLDAWhereClause,&SQLDASelData,&fetchInd,prrs);
	ptrace->DumpText("Executing SQL startment");
	ind=ORAExecuteAdj(SQLDASelData,SQLDAWhereClause,prrs);
	psqlInd->descriptor.var.i32=sqlcd;
	if(whereInd)free(whereInd);
	if(fetchInd)free(fetchInd);
	SQLDAFree(SQLDASelData);
	SQLDAFree(SQLDAWhereClause);
	return 1;
}
int RouterCursorImpl::Execute()
{
	void*SQLDASelData=0L;
	void*SQLDAWhereClause=0L;
	short*fetchInd=0L,*whereInd=0L;
	RouterAccumImpl*accum;
	RouterStringImpl rsi;
	COLUMN*col;
	int ind;

	AdjustWHEREAdj();
	ptrace->DumpText("Prepare SQL startment");
	Prepare();
	ptrace->DumpText("Assemble WHERE clause");
	AssembleWhere(&SQLDAWhereClause,&whereInd);
	ptrace->DumpText("Assemble columns");
	AssembleCols(SQLDAWhereClause,&SQLDASelData,&fetchInd);
	ptrace->DumpText("Executing SQL startment");
	ind=ORAExecute(SQLDASelData,SQLDAWhereClause);
	if(ind!=2&&ind)
		psqlInd->descriptor.var.i32=1;

	if(adind)
	{
		for(col=pcol;col;col=col->nextColumn)
		{
			accum=rootAccum->RetrieveVar(col->cname);
			if(accum->descriptor.vartype==AC_STRING)
			{
				rsi.RTrim(accum->descriptor.var.pstr);
				rsi.AdjustSize(accum);
			}
		}
	}
	else
		ptrace->DumpText("Execution failed");

	if(whereInd)free(whereInd);
	if(fetchInd)free(fetchInd);
	SQLDAFree(SQLDASelData);
	SQLDAFree(SQLDAWhereClause);
	return 1;
}
int RouterCursorImpl::IsSQL(char*pst)
{
	if(((*(pst)!=108&&*(pst)!=76)||(*(pst+1)!=105&&*(pst+1)!=73)||
		(*(pst+2)!=107&&*(pst+2)!=75)||(*(pst+3)!=101&&*(pst+3)!=69))&&!IsSQLSep(pst))
		return 0;
	return 1;
}
int RouterCursorImpl::IsSQLSep(char*pst)
{
	if((((*(pst)!=97&&*(pst)!=65)||(*(pst+1)!=110&&*(pst+1)!=78)||(*(pst+2)!=100&&*(pst+2)!=68))&&
	   ((*(pst)!=111&&*(pst)!=79)||(*(pst+1)!=114&&*(pst+1)!=82))))
		return 0;
	return 1;

}