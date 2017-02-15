#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterCodeImpl.cpp

Abstract:
	Code parser

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-07 - Ivan Mentone - Add new function GetCommentLimitAdj
	2004-07-07 - Ivan Mentone - Adjust code to correct interpretation of comment in the first line

--*/

CodeUnitCond::CodeUnitCond():clause(0L),pucondT(0L),pucondF(0L),pcunit(0L)
{}
CodeUnitCond::~CodeUnitCond()
{
	if(pucondT)
		delete pucondT;
	if(pucondF)
		delete pucondF;
	if(pcunit)
		delete pcunit;
}
CodeUnit::CodeUnit():nextUnit(0L)
{
	cmd.code=0L;
	optype=0;
}
CodeUnit::~CodeUnit()
{
	if(nextUnit)
		delete nextUnit;
	if(optype==OP_COND||optype==OP_LOOP)
		delete cmd.code;
}
CodeUnit*CodeUnit::AddNewUnit()
{
	nextUnit=new CodeUnit();
	return nextUnit;
}

RouterCodeImpl::RouterCodeImpl():pcunit(0L)
{}
RouterCodeImpl::~RouterCodeImpl()
{
	if(pcunit)
		delete pcunit;
}
int RouterCodeImpl::GetBlockLimitAdj(char*src)
{
	if(!src)return 0;
	if(*src!='i'&&*(src+1)!='f')
		return 1;
	src-=2;
	while(*src==10||*src==13||*src==32||*src==9)src--;
	if(*src=='e'&&*(src-1)=='s'&&*(src-2)=='l'&&*(src-3)=='e')
		return 0;
	return 1;
}
char*RouterCodeImpl::GetBlockLimit(char*src)
{
	char*pa,*pb,*sti,*stf,*sa,*sb;
	int adj;

	switch(*src)
	{
	case 'i':
		pa="if";
		pb="fi;";
		adj=2;
		break;
	case 'w':
		pa="while";
		pb="loop;";
		adj=4;
		break;
	}

	sa=sb=src;
scan:
	stf=RouterStringImpl::_FindFromCode(sb,pb);
	sti=RouterStringImpl::_FindFromCode(sa+1,pa);
	while(sti&&!GetBlockLimitAdj(sti))
		sti=strstr(sti+1,pa);
	if(!sti||sti>stf)
		return stf+adj;
	sa=sti;
	sb=stf+1;
	goto scan;
}
int RouterCodeImpl::Assemble(char*code)
{
	psrc=code;
	return Assemble();
}
char*RouterCodeImpl::GetCommentLimitAdj(char*psrc)
{
	char*pf;

	pf=strchr(psrc,10);
	return pf;
}
int RouterCodeImpl::Assemble()
{
	CodeUnit*cunit;
	char*st1,*st2;

	st1=psrc;
	if(!st1)
		return 0;
	st1=RouterStringImpl::_LTrim(st1);
	if(!strncmp(st1,"//",2))
		st2=GetCommentLimitAdj(st1);
	else
		st2=RouterStringImpl::_GetStringLimitAdj(st1,59);
	if(!st2)
		return 0;

	while(st2)
	{
		st1=RouterStringImpl::_LTrim(st1);
		if(*st1=='i'&&*(st1+1)=='f')
		{
			st2=GetBlockLimit(st1);
			if(st2==(char*)2)
			{
				REI_EPARSE("Invalid \"if\" block",REI_ERROR);
				return 0;
			}
			*st2=0;
		}
		else if(*st1=='w'&&*(st1+1)=='h'&&*(st1+2)=='i'&&*(st1+3)=='l'&&*(st1+4)=='e')
		{
			st2=GetBlockLimit(st1);
			*st2=0;
		}
		else
			*st2=0;

		if(strncmp(st1,"//",2)&&*st1)
		{
			if(!pcunit)
				pcunit=cunit=new CodeUnit();
			else
				cunit=cunit->AddNewUnit();
			st1=RouterStringImpl::_LTrim(st1);
			if(*st1!='#')
				cunit->optype=OP_CODE;
			else
				cunit->optype=OP_DRCT;
			cunit->cmd.pcmd=st1;

			st1=st2+1;
			st1=RouterStringImpl::_LTrim(st1);
			if(!strncmp(st1,"//",2))
				st2=GetCommentLimitAdj(st1);
			else
				st2=RouterStringImpl::_GetStringLimitAdj(st1,59);
		}
		else
		{
			st1=st2+1;
			st1=RouterStringImpl::_LTrim(st1);
			if(!strncmp(st1,"//",2))
				st2=GetCommentLimitAdj(st1);
			else
				st2=RouterStringImpl::_GetStringLimitAdj(st1,59);
		}
	}

	cunit=pcunit;
	while(cunit)
	{
		if(*(cunit->cmd.pcmd)==105&&
			*(cunit->cmd.pcmd+1)==102)
			ProcessIFDirective(cunit);
		else if(*(cunit->cmd.pcmd)==119&&
			*(cunit->cmd.pcmd+1)==104&&
			*(cunit->cmd.pcmd+2)==105&&
			*(cunit->cmd.pcmd+3)==108&&
			*(cunit->cmd.pcmd+4)==101)
			ProcessWHILEDirective(cunit);
		cunit=cunit->nextUnit;
	}
	return 1;
}
int RouterCodeImpl::ProcessWHILEDirective(CodeUnit*cunit)
{
	RouterCodeImpl*rci;
	CodeUnitCond*ccond;
	char*st1,*st2,*cur,*vtb;

	cur=cunit->cmd.pcmd;
	st1=strchr(cur,'(');
	st2=RouterStringImpl::_SeekRelativesLmtAdj(cur);
	st2--;
	st1++;
	*st2++=0;

	ccond=new CodeUnitCond();
	ccond->clause=st1;

	st1=st2;
	vtb=RouterStringImpl::_FindFromCode(st1,"loop");
	while(vtb)
	{
		st2=vtb;
		vtb=RouterStringImpl::_FindFromCode(vtb+1,"loop");
	}
	*st2=0;

	rci=new RouterCodeImpl();
	try
	{
		rci->Assemble(st1);
	}
	catch(...)
	{
		delete rci;
		throw;
	}
	ccond->pcunit=rci->pcunit;
	rci->pcunit=0L;
	delete rci;

	cunit->optype=OP_LOOP;
	cunit->cmd.code=ccond;
	return 1;
}
char*RouterCodeImpl::GetLocalBlockLimitAdj(char*src,char*pfiind)
{
	char*cur;
	int tf=0,rc=0;

	src=RouterStringImpl::_LTrim(src);
	if(!strncmp(src,"else",4))
		src+=4;
	src=RouterStringImpl::_LTrim(src);
	if(!strncmp(src,"if",2))
		src+=2;
	src=RouterStringImpl::_LTrim(src);
	if(!strncmp(src,"fi",2))
		return 0;
	cur=src;
	while(*cur)
	{
		cur=RouterStringImpl::_LTrim(cur);
		if(*cur==34&&*(cur-1)!=92)
			rc++;
		else if(!(rc%2))
		{
			if(!strncmp(cur,"if",2))
			{
				tf++;
				cur++;
			}
			if(!strncmp(cur,"else",4))
			{
				if(tf)
				{
					cur+=4;
					cur=RouterStringImpl::_LTrim(cur);
					cur++;
				}
				else
					break;
			}
			if(!strncmp(cur,"fi",2))
			{
				tf--;
				cur++;
			}
		}
		if(tf<0)
			break;
		cur++;
	}
	if(!*cur)
		return 0L;
	return cur-1;
}
int RouterCodeImpl::ProcessIFDirective(CodeUnit*cunit)
{
	RouterCodeImpl*rci;
	CodeUnitCond*ccond,*ccur;
	char*st1,*st2,*cur,och,scp,*tc1;

	cur=cunit->cmd.pcmd;
	st1=strchr(cur,'(');
	st2=strchr(cur,')');

	st1++;
	*st2++=0;
	cur=st2;

	ccond=new CodeUnitCond();
	ccond->clause=st1;

	cur=RouterStringImpl::_TRIfBlockAdj(cur);
	st2=GetLocalBlockLimitAdj(cur,&scp);
	if(!st2)
	{
		REI_EPARSE("Invalid \"if\" startment - empty true part",REI_ERROR);
		delete ccond;
		cunit->optype=OP_COND;
		cunit->cmd.code=0L;
		return 0;
	}
	och=*st2;
	*st2=0;
	rci=new RouterCodeImpl();
	try
	{
		rci->Assemble(cur);
	}
	catch(...)
	{
		delete rci;
		throw;
	}
	ccond->pucondT=new CodeUnitCond();
	ccond->pucondT->pcunit=rci->pcunit;
	rci->pcunit=0L;
	delete rci;
	*st2=och;

	ccur=ccond;
	while(st2)
	{
		st1=st2;
		st1=RouterStringImpl::_TRIfBlockAdj(st1);
		st2=GetLocalBlockLimitAdj(st1,&scp);
		if(st2)
		{
			och=*st2;
			*st2=0;
		}
		if(st1&&!(*st1=='f'&&*(st1+1)=='i'&&*(st1+2)==0))
		{
			st1=RouterStringImpl::_LTrim(st1);
			st1+=4;
			while(*st1==32||*st1==9||*st1==10||*st1==13)st1++;
			if(!strncmp(st1,"if",2))
			{
				st1=strchr(st1,'(');
				tc1=strchr(st1,')');
				st1++;
				*tc1++=0;
				ccur->pucondF=new CodeUnitCond();
				ccur=ccur->pucondF;
				ccur->clause=st1;
				rci=new RouterCodeImpl();
				try
				{
					rci->Assemble(tc1);
				}
				catch(...)
				{
					delete rci;
					throw;
				}
				ccur->pucondT=new CodeUnitCond();
				ccur->pucondT->pcunit=rci->pcunit;
				rci->pcunit=0L;
				delete rci;
			}
			else
			{
				rci=new RouterCodeImpl();
				try
				{
					rci->Assemble(st1);
				}
				catch(...)
				{
					delete rci;
					throw;
				}
				ccur->pucondF=new CodeUnitCond();
				ccur->pucondF->pcunit=rci->pcunit;
				rci->pcunit=0L;
				delete rci;
			}
		}
		if(st2)
			*st2=och;
	}
	cunit->optype=OP_COND;
	cunit->cmd.code=ccond;

	return 1;
}