#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterStepImpl.cpp

Abstract:
	Implements steps in tasks

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-05 - Ivan Mentone - Implement new feature ParseFlowStep
	2004-07-06 - Ivan Mentone - Implement new Step type ST_FEXTRNCALL
	2004-07-10 - Ivan Mentone - Implement new Step type ST_COMMAND
	2004-07-14 - Ivan Mentone - Implement new Step type ST_RECORDSET
	2004-08-02 - Ivan Mentone - Correct if check to prevent access violation
	2004-08-04 - Ivan Mentone - Update flow control

--*/

RouterStepImpl::RouterStepImpl(RouterLibManImpl*pLib,RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	stype(0),nextStep(0L),paccum(0L),rootAccum(rAccum),plib(pLib),clrType(0),
	RouterHelper(rAccum,pTrace),pcur(0L),rac_ind(0),pfnc(0L),ptrace(pTrace),
	pextn(0L),prci(0L),sstype(0)
{}
RouterStepImpl::~RouterStepImpl()
{
	int i;

	if(nextStep)
		delete nextStep;
	if(clrType)
	{
		if(clrType==1&&paccum)
		{
			delete*paccum;
			delete paccum;
		}
		else if(clrType==2&&paccum)
		{
			for(i=0;i<rac_ind;i++)
			{
				if(paccum[i]&&paccum[i]->flags&AC_TYPE_DISCARDABLE)
					delete paccum[i];
			}
			delete[]paccum;
		}
	}
	if(pcur)
		delete pcur;
	if(pfnc)
		delete pfnc;
	if(pextn)
		delete pextn;
	if(prci)
		delete prci;
}
RouterStepImpl*RouterStepImpl::AddNewStep()
{
	if(nextStep)
		delete nextStep;
	nextStep=new RouterStepImpl(plib,rootAccum,ptrace);
	return nextStep;
}
int RouterStepImpl::ParseExecuteStep(char*pln)
{
	char*st1,*st2,*st0,*vrf;
	RouterAccumImpl*accum;

	if(clrType)
	{
		if(clrType==1&&paccum)
			delete paccum;
		else if(clrType==2&&paccum)
			delete[]paccum;
	}

	pfnc=new RouterFunctionCallImpl(ptrace);

	st1=strchr(pln,'(');
	vrf=strchr(pln,':');	//TR6
	if((vrf&&vrf<st1)||!st1)//TR37
	{
		char cmsg[1024];
		sprintf(cmsg,"Invalid function call - expected '.' after library identifier - %.800s",pln);
		REI_EPARSE(cmsg,REI_ERROR);
		return 0;
	}
	*st1++=0;
	st0=strchr(pln,'=');
	if(st0)
	{
		*st0++=0;
		if(*pln=='[')pln++;
		st2=strchr(pln,']');
		if(st2)
			*st2=0;
		pln=RouterStringImpl::_LTrim(pln);
		RouterStringImpl::_RTrim(pln);
		accum=rootAccum->RetrieveVar(pln);
		if(!accum)
		{
			char cmsg[1024];
			sprintf(cmsg,"'%.900s' : Undeclared identifier",pln);
			REI_EPARSE(cmsg,REI_ERROR);
			return 0;
		}
		pfnc->SetReturn(accum);
	}
	else
		st0=pln;

	clrType=1;
	paccum=new RouterAccumImpl*;
	*paccum=new RouterAccumImpl;

	st0=RouterStringImpl::_LTrim(st0);
	plib->GetInstance(st0,*paccum);
	pfnc->SetFunction(*paccum);
	accum=*paccum;
	if(*st1!=')')
	{
		st2=strchr(st1,')');
		*st2=0;
		while(st1)
		{
			st2=strchr(st1,',');
			if(st2)
				*st2++=0;
			accum=0L;
			st1=RouterStringImpl::_LTrim(st1);
			RouterStringImpl::_RTrim(st1);
			if(!strncmp(st1,"[XML]",5)||!strcmp(st1,"XML"))
			{
				char chmvs[]="[a_@XML]";
				MakeParam(chmvs,&accum);
			}
			else
				MakeParam(st1,&accum);
			pfnc->AddParam(accum);
			st1=st2;
		}
	}
	return 1;
}
int RouterStepImpl::ParseIncDecStep(char*pln)
{
	char*pc,oc[3]={43,61,0};

	pc=strchr(pln,43);
	if(!pc)
	{
		pc=strchr(pln,45);
		if(!pc)
		{
			REI_EPARSE("Invalid operation",REI_ERROR);
			return 0;
		}
		oc[0]=45;
	}
	*pc=0;
	{
		char*nst;
		nst=(char*)MMAllocator(strlen(pln)+10,"NST");
		sprintf(nst,"%s%s1",pln,oc);
		ParseTransformStep(nst);
		MMFree(nst);
	}
	return 1;
}
int RouterStepImpl::ParseTransformStep(char*pln)
{
	char*st1,*st2;
	
	st1=strchr(pln,61);
	if(!st1)
	{
		REI_EPARSE("Invalid transform",REI_ERROR);
		return 0;
	}
	*st1++=0;
	clrType=2;
	paccum=new RouterAccumImpl*[2];
	rac_ind=2;
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);
	st2=strchr(pln,']');
	if(!st2&&*pln=='[')
	{
		REI_EPARSE("Invalid script format",REI_ERROR);
		return 0;
	}
	else if(!st2)
	{
		st2=pln;
		while(*st2&&*st2!='+'&&*st2!='-'&&*st2!='/'&&*st2!='*')st2++;
	}
	st2=RouterStringImpl::_LTrim(st2);
	if(*st2=='+')
	{
		tranftype=OP_TR_ADD;
		*st2=0;
	}
	else if(*st2=='-')
	{
		tranftype=OP_TR_DFF;
		*st2=0;
	}
	else if(*st2=='*')
	{
		tranftype=OP_TR_MLT;
		*st2=0;
	}
	else if(*st2=='/')
	{
		tranftype=OP_TR_DIV;
		*st2=0;
	}
	else
		tranftype=OP_TR_RPL;
	RouterStringImpl::_RTrim(pln);
	pln=RouterStringImpl::_LTrim(pln);
	MakeParam(pln,&paccum[0]);
	st1=RouterStringImpl::_LTrim(st1);
	RouterStringImpl::_RTrim(st1);
	MakeParam(st1,&paccum[1]);
	return 1;
}
int RouterStepImpl::ParseSQLStep(char*pln)
{
	pcur=new RouterCursorImpl(rootAccum,ptrace);
	pcur->Assemble(pln+4);
	return 1;
}
int RouterStepImpl::ParseExceptionStep(char*pln)
{
	char*st1;

	clrType=2;
	rac_ind=2;
	paccum=new RouterAccumImpl*[2];
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);
	pln=strchr(pln,'(');
	if(!pln)
	{
		REI_EPARSE("Invalid Exception",REI_ERROR);
		return 0;
	}
	pln+=1;
	st1=strchr(pln,')');
	if(!st1)
	{
		REI_EPARSE("Invalid Exception Syntax",REI_ERROR);
		return 0;
	}
	*st1=0;
	st1=strchr(pln,',');
	if(!st1)
	{
		paccum[1]=0L;
		MakeParam(pln,&paccum[0]);
	}
	else
	{
		*st1++=0;
		MakeParam(pln,&paccum[0]);
		MakeParam(st1,&paccum[1]);
	}
	return 1;
}
int RouterStepImpl::ParsePrintStep(char*pln)
{
	clrType=2;
	rac_ind=1;
	paccum=new RouterAccumImpl*;
	paccum[0]=0L;

	pln+=5;
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&paccum[0]);
	return 1;
}
int RouterStepImpl::ParseSolveStep(char*pln)
{
	rac_ind=3;
	clrType=2;
	tranftype=(char)ParseSolveW(pln,&paccum,ptrace);
	return 1;
}
int RouterStepImpl::ParseConditionalSolveStep(char*pln)
{
	char st[1024],*st1,*stt,*stf;
	RouterTaskCondImpl*rtci;
	CodeUnitCond*cuc;

	st1=strchr(pln,61);
	*st1++=0;

	stt=strchr(st1,63);
	*stt++=0;
	stf=strchr(stt,58);
	*stf++=0;

	cuc=new CodeUnitCond();
	cuc->clause=st1;

	sprintf(st,"%s=%s",pln,stt);
	cuc->pucondT=new CodeUnitCond();
	cuc->pucondT->pcunit=new CodeUnit();
	cuc->pucondT->pcunit->cmd.pcmd=derivStr(st);
	cuc->pucondT->pcunit->optype=OP_CODE;
	cuc->pucondF=new CodeUnitCond();
	cuc->pucondF->pcunit=new CodeUnit();
	sprintf(st,"%s=%s",pln,stf);
	cuc->pucondF->pcunit->cmd.pcmd=derivStr(st);
	cuc->pucondF->pcunit->optype=OP_CODE;

	rtci=new RouterTaskCondImpl(plib,rootAccum,ptrace);
	rtci->Assemble(cuc);

	delete cuc;

	clrType=2;
	rac_ind=1;
	paccum=new RouterAccumImpl*;
	paccum[0]=new RouterAccumImpl();
	paccum[0]->MakeVar(0L,AC_ROUTERTASKCOND);
	paccum[0]->flags=AC_TYPE_DISCARDABLE;
	paccum[0]->descriptor.var.othr=rtci;

	return 1;
}
int RouterStepImpl::ParseFlowStep(char*pln,char ind)
{
	char*st1,*st2,*st3,och;
	int ID;

	st1=strchr(pln,61);
	if(!st1)
	{
		REI_EPARSE("Cannot find '=' before command line",REI_ERROR);
		return 0;
	}
	och=*(st1-1);
	if(och=='+')
		*(st1-1)=0;
	*st1++=0;
	st1=strchr(st1,'(');
	if(!st1)
	{
		REI_EPARSE("Cannot find '(' before parameters list",REI_ERROR);
		return 0;
	}
	*st1++=0;
	st2=strchr(st1,',');
	if(!st2)
	{
		REI_EPARSE("Cannot find ')' after parameters list",REI_ERROR);
		return 0;
	}
	*st2++=0;
	st3=strchr(st2,')');
	if(!st3)
	{
		REI_EPARSE("Cannot find ')' after parameters list",REI_ERROR);
		return 0;
	}
	*st3=0;
	pextn=new RouterExternCallImpl(rootAccum,ptrace);
	if(och=='+')
		pextn->SetInd();
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	st1=RouterStringImpl::_LTrim(st1);
	RouterStringImpl::_RTrim(st1);
	st2=RouterStringImpl::_LTrim(st2);
	RouterStringImpl::_RTrim(st2);
	ID=RouterStringImpl::_IsNumeric(st1);
	if(!pextn->SetVars(pln,st2))
		return 0;
	if(!ID&&!ind)
	{
		REI_EPARSE("Need ID to query correct flow",REI_ERROR);
		return 0;
	}
	else if(!ID&&ind)
	{
		char*st0;
		if(*st1!=34)
		{
			paccum=new RouterAccumImpl*[1];
			*paccum=0L;
			MakeParam(st1,&paccum[0]);
			if(!*paccum)
			{
				char cmsg[1024];
				sprintf("%.900s : undeclared identifier",st1);
				REI_EPARSE(cmsg,REI_ERROR);
				return 0;
			}
			if((*paccum)->descriptor.vartype!=AC_STRING&&(*paccum)->descriptor.vartype!=AC_STREAM&&
				(*paccum)->descriptor.vartype!=AC_INTEGER)
			{
				REI_EPARSE("Invalid datatype - FLOW",REI_ERROR);
				return 0;
			}
			clrType=2;
			return 1;
		}
		else
		{
			st0=RouterStringImpl::_GetStringLimitAdj(st1);
			if(!st0)
			{
				REI_EPARSE("Invalid string format",REI_ERROR);
				return 0;
			}
			st1++;
			*st0=0;
			ID=QueryScriptIDByName(st1);
		}
		switch(ID)
		{
		case -1:
			{
				char cmsg[1024];
				sprintf(cmsg,"Script named \"%.900s\" cannot be found",st1);
				REI_EPARSE(cmsg,REI_ERROR);
				return 0;
			}
		case -2:
			REI_EPARSE("Unknown error during retrieving script from name",REI_CRITICAL);
			return 0;
		}
	}
	else if(ID&&ind)
		ID=atoi(st1);

	if(!ind)
	{
		if((ID=QueryScriptFlowByID(atoi(st1)))==-1)
		{
			REI_EPARSE("Invalid flow ID",REI_ERROR);
			return 0;
		}
	}
	if(!(st3=QueryExternCode(0L,ID)))
	{
		char cmsg[1024];
		sprintf(cmsg,"Script ID \"%d\" cannot be found.",ID);
		REI_EPARSE(cmsg,REI_ERROR);
		return 0;
	}
	pextn->Assemble(st3);
	return 1;
}
int RouterStepImpl::ParseExternCallStep(char*pln)
{
	char*st1,*st2,*st3;

	st1=strchr(pln,'=');
	if(!st1)
	{
		REI_EPARSE("Cannot find '=' before command line",REI_ERROR);
		return 0;
	}
	*st1++=0;
	st2=strchr(st1,'(');
	if(!st2)
	{
		REI_EPARSE("Cannot find '(' before parameters list",REI_ERROR);
		return 0;
	}
	*st2++=0;
	st3=strchr(st2,')');
	if(!st3)
	{
		REI_EPARSE("Cannot find ')' after parameters list",REI_ERROR);
		return 0;
	}
	*st3=0;
	pextn=new RouterExternCallImpl(rootAccum,ptrace);
	if(*(st1-2)=='+')
	{
		pextn->SetInd();
		*(st1-2)=0;
	}
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	st1=RouterStringImpl::_LTrim(st1);
	RouterStringImpl::_RTrim(st1);
	st2=RouterStringImpl::_LTrim(st2);
	RouterStringImpl::_RTrim(st2);
	if(!(st3=QueryExternCode(st1,0)))
	{
		char cmsg[1024];
		sprintf(cmsg,"Script named \"%s\" cannot be found.",pln);
		REI_EPARSE(cmsg,REI_ERROR);
	}
	pextn->Assemble(st3);
	pextn->SetVars(pln,st2);
	return 1;
}
int RouterStepImpl::ParseCommandStep(char*pln)
{
	if(prci)
		delete prci;

	prci=new RouterCommandImpl(rootAccum,ptrace);
	prci->Assemble(pln);
	return 1;
}
int RouterStepImpl::ParseRecordsetStep(char*pln)
{
	char*st1;

	clrType=2;
	rac_ind=4;
	paccum=new RouterAccumImpl*[4];
	memset(paccum,0,sizeof(RouterAccumImpl*)*4);

	tranftype=OP_TR_RPL;
	st1=strchr(pln,'=');
	if(st1)
	{
		switch(*(st1-1))
		{
		case'+':
			tranftype=OP_TR_ADD;
			*(st1-1)=0;
			break;
		case'-':
			tranftype=OP_TR_DFF;
			*(st1-1)=0;
			break;
		case'/':
			tranftype=OP_TR_DIV;
			*(st1-1)=0;
			break;
		case'*':
			tranftype=OP_TR_MLT;
			*(st1-1)=0;
			break;
		}
		*st1=0;
		MakeParam(pln,&paccum[0]);
		if(!paccum[0])
		{
			char cmsg[1024];
			sprintf(cmsg,"'%.900s' : undeclared identifier",pln);
			REI_EPARSE(cmsg,REI_ERROR);
			return 0;
		}
		if(paccum[0]->descriptor.vartype!=AC_STREAM&&
			paccum[0]->descriptor.vartype!=AC_INTEGER&&
			paccum[0]->descriptor.vartype!=AC_XMLGEN)
		{
			REI_EPARSE("Invalid conversion to this datatype",REI_ERROR);
			return 0;
		}
		pln=st1+1;
	}

	st1=strchr(pln,'.');
	*st1=0;
	MakeParam(pln,&paccum[2]);
	pln=st1+1;
	st1=strchr(pln,'(');
	if(!st1)
	{
		REI_EPARSE("Cannot find '(' before arguments",REI_ERROR);
		return 0;
	}
	*st1=0;
	RouterStringImpl::_RTrim(pln);
	if(!strcmp(pln,"GetValue"))
		sstype=ST_RS_GETVAL;
	else if(!strcmp(pln,"MoveNext"))
		sstype=ST_RS_MOVENEXT;
	else if(!strcmp(pln,"EOF"))
		sstype=ST_RS_EOF;
	else if(!strcmp(pln,"MoveFirst"))
		sstype=ST_RS_MOVEFIRS;
	else if(!strcmp(pln,"MoveLast"))
		sstype=ST_RS_MOVELAST;
	else if(!strcmp(pln,"MovePrevious"))
		sstype=ST_RS_MOVEPREV;
	else if(!strcmp(pln,"BOF"))
		sstype=ST_RS_BOF;
	else if(!strcmp(pln,"RowCount"))
		sstype=ST_RS_ROWCOUNT;
	else if(!strcmp(pln,"GetInd"))
		sstype=ST_RS_GETIND;
	else if(!strcmp(pln,"GetError"))
		sstype=ST_RS_GETERROR;
	else if(!strcmp(pln,"ColCount"))
		sstype=ST_RS_COLCOUNT;
	else
	{
		REI_EPARSE("Invalid method call",REI_ERROR);
		return 0;
	}
	pln=st1+1;
	st1=strchr(pln,')');
	if(!st1)
	{
		REI_EPARSE("Cannot find ')' after arguments",REI_ERROR);
		return 0;
	}
	*st1=0;
	if(*pln)
	{
		MakeParam(pln,&paccum[3]);
		if(paccum[3]->descriptor.vartype!=AC_INTEGER&&
			paccum[3]->descriptor.vartype!=AC_STRING&&
			paccum[3]->descriptor.vartype!=AC_STREAM)
		{
			REI_EPARSE("Invalid datatype to GetValue",REI_ERROR);
			return 0;
		}
	}
	paccum[1]=new RouterAccumImpl();
	paccum[1]->flags=AC_TYPE_DISCARDABLE;
	return 1;
}
int RouterStepImpl::Assemble(char*pln)
{
	ptrace->DumpText("Assembling Step\nLine Text:");
	ptrace->DumpText(pln);
	if(!strncmp(pln,"return",6))
	{
		strncpy(pln,"[@XML]=",7);
		retind=1;
	}
	else retind=0;
	if(!strncmp(pln,"SQL ",4))
	{
		stype=ST_SELECT;
		ParseSQLStep(pln);
	}
	else if(IsCommand(pln))
	{
		stype=ST_COMMAND;
		ParseCommandStep(pln);
	}
	else if(strstr(pln,"Flow")&&strchr(pln,'(')&&strchr(pln,')')&&strchr(pln,'=')&&!strchr(pln,'.'))
	{
		stype=ST_FEXTRNCALL;
		ParseFlowStep(pln,0);
	}
	else if(IsRecordset(pln))
	{
		stype=ST_RECORDSET;
		ParseRecordsetStep(pln);
	}
	else if(strstr(pln,"FncC")&&strchr(pln,'(')&&strchr(pln,')')&&!strchr(pln,'.'))
	{
		stype=ST_FEXTRNCALL;
		ParseFlowStep(pln,1);
	}
	else if(RouterStringImpl::_IsExternCall(pln))
	{
		stype=ST_EXTRNCALL;
		ParseExternCallStep(pln);
	}
	else if(!strncmp(pln,"print",5))
	{
		stype=ST_PRINT;
		ParsePrintStep(pln);
	}
	else if(!strncmp(pln,"throw(",6)||!strncmp(pln,"throw ",6))
	{
		stype=ST_EXCEPTION;
		ParseExceptionStep(pln);
	}
	else if(strchr(pln,'?')&&strchr(pln,'='))
	{
		stype=ST_CONDSOLVE;
		ParseConditionalSolveStep(pln);
	}
	else if(RouterHelper::IsSolve(pln))
	{
		stype=ST_SOLVE;
		ParseSolveStep(pln);
	}
	else if(RouterStringImpl::_IsTransform(pln))
	{
		stype=ST_TRANSFORM;
		ParseTransformStep(pln);
	}
	else if(RouterStringImpl::_HasIncDec(pln))
	{
		stype=ST_TRANSFORM;
		ParseIncDecStep(pln);
	}
	else
	{
		stype=ST_EXECUTE;
		ParseExecuteStep(pln);
	}
	return 1;
}
RouterStepImpl*RouterStepImpl::Execute()
{
	switch(stype)
	{
	case ST_EXECUTE:
		ptrace->DumpText("Execute Step - EXECUTE\nBegin function call");
		pfnc->Execute();
		ptrace->DumpText("Function Exit Success");
		break;
	case ST_TRANSFORM:
		{
			ptrace->DumpText("Execute Step - TRANSFORM\nBefore Execution");
			ptrace->DumpAccum(paccum[0]);
			ptrace->DumpAccum(paccum[1]);
			RouterTransformImpl rti(paccum[1]);
			if(paccum[0]->descriptor.vartype==AC_STREAM)
				rti.CastToStream(paccum[0],tranftype);
			else if(paccum[0]->descriptor.vartype==AC_DOMNODE&&
				paccum[1]->descriptor.vartype==AC_STREAM)
				rti.CastFromStreamToDOMNode(paccum[0]);
			else if((paccum[0]->descriptor.vartype==AC_XMLGEN||
				paccum[0]->descriptor.vartype==AC_DOMNODE)
				&&(paccum[1]->descriptor.vartype==AC_XMLGEN||
				paccum[1]->descriptor.vartype==AC_DOMNODE))
				rti.CopyFromXMLToXML(paccum[0],tranftype);
			else if(paccum[0]->descriptor.vartype==AC_INTEGER
				&&paccum[1]->descriptor.vartype==AC_INTEGER)
				rti.CastToInteger(paccum[0],tranftype);
			else if(paccum[0]->descriptor.vartype==AC_INTEGER
				&&(paccum[1]->descriptor.vartype==AC_STRING||
				paccum[1]->descriptor.vartype==AC_STREAM))
				rti.CastToInteger(paccum[0],tranftype);
			else if(paccum[1]->descriptor.vartype==AC_INTEGER
				&&paccum[0]->descriptor.vartype==AC_XMLGEN)
				rti.CastFromIntToXML(paccum[0]);
			else if(paccum[1]->descriptor.vartype==AC_STRING
				&&paccum[0]->descriptor.vartype==AC_XMLGEN)
				rti.CastFromCharToXML(paccum[0],tranftype);
			else if(paccum[1]->descriptor.vartype==AC_STREAM
				&&paccum[0]->descriptor.vartype==AC_XMLGEN)
				rti.CastFromStreamToXML(paccum[0],tranftype);
			else if(paccum[0]->descriptor.vartype==AC_DOMNODE&&
				paccum[1]->descriptor.vartype==AC_STRING)
				rti.CastFromStringToDOMNode(paccum[0]);
			else
				rti.CastToString(paccum[0],tranftype);
			ptrace->DumpText("After Execution");
			ptrace->DumpAccum(paccum[0]);
			ptrace->DumpAccum(paccum[1]);
		}
		break;
	case ST_SELECT:
		pcur->Execute();
		break;
	case ST_EXCEPTION:
		ptrace->DumpText("Raise Exception");
		ptrace->DumpAccum(paccum[0]);
		if(paccum[1])
		{
			ptrace->DumpAccum(paccum[1]);
			switch(paccum[1]->descriptor.vartype)
			{
			case AC_STRING:
				throw new TuxBasicSvcException(paccum[0]->descriptor.var.pstr,
					paccum[1]->descriptor.var.pstr);
			case AC_STREAM:
				throw new TuxBasicSvcException(paccum[0]->descriptor.var.pstr,
					((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText());
			default:
				throw new TuxBasicSvcException(paccum[0]->descriptor.var.pstr,
					"NoText");
			}
		}
		else
			throw new TuxBasicSvcException(paccum[0]->descriptor.var.pstr);
		break;
	case ST_SOLVE:
		{
			ptrace->DumpText("Execute Step - SOLVE\nBefore Execution");
			ptrace->DumpAccum(paccum[0]);
			ptrace->DumpAccum(paccum[1]);
			ptrace->DumpAccum(paccum[2]);
			RouterTransformImpl rti(paccum[1]);
			rti.CastFromDOMNODEToStringOrInteger(paccum[0],paccum[2]->descriptor.var.pstr,tranftype);
			ptrace->DumpText("After Execution");
			ptrace->DumpAccum(paccum[0]);
			ptrace->DumpAccum(paccum[1]);
			ptrace->DumpAccum(paccum[2]);
		}
		break;
	case ST_PRINT:
		ptrace->PrintAccum(paccum[0]);
		if(paccum[0]->descriptor.vartype==AC_XMLGEN)
		{
			char*p;int i;
			p=paccum[0]->descriptor.var.xml->retrieveXML(&i);
			ptrace->PrintText(p);
		}
		break;
	case ST_CONDSOLVE:
		{
			RouterTaskCondImpl*rtci;
			rtci=(RouterTaskCondImpl*)paccum[0]->descriptor.var.othr;
			rtci->Execute();
			delete rtci;
		}
		break;
	case ST_EXTRNCALL:case ST_FEXTRNCALL:
		if(clrType)
		{
			char*pnm=0L;
			int ID=-5;
			switch((*paccum)->descriptor.vartype)
			{
			case AC_STREAM:
				pnm=((RouterStreamImpl*)(*paccum)->descriptor.var.othr)->GetText();
			case AC_STRING:
				if(!pnm)
					pnm=(*paccum)->descriptor.var.pstr;
				ID=QueryScriptIDByName(pnm);
				switch(ID)
				{
				case -1:
					{
						char cmsg[1024];
						sprintf(cmsg,"Script named \"%.900s\" cannot be found",pnm);
						REI_ERUNTIME(cmsg,REI_ERROR);
						return 0;
					}
				case -2:
					REI_EPARSE("Unknown error during retrieving script from name",REI_CRITICAL);
					return 0;
				}
			case AC_INTEGER:
				if(ID==-5)
					ID=(*paccum)->descriptor.var.i32;
				if(!(pnm=QueryExternCode(0L,ID)))
				{
					char cmsg[1024];
					sprintf(cmsg,"Script ID \"%d\" cannot be found.",ID);
					REI_EPARSE(cmsg,REI_ERROR);
					return 0;
				}
				pextn->Assemble(pnm);
			}
		}
		pextn->Execute();
		break;
	case ST_COMMAND:
		prci->Execute();
		break;
	case ST_RECORDSET:
		switch(sstype)
		{
		case ST_RS_MOVENEXT:
			((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->MoveNext();
			break;
		case ST_RS_MOVEPREV:
			((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->MovePrevious();
			break;
		case ST_RS_MOVEFIRS:
			((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->MoveFirst();
			break;
		case ST_RS_MOVELAST:
			((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->MoveLast();
			break;
		case ST_RS_GETVAL:
			{
				RouterAccumImpl*accum;
				switch(paccum[3]->descriptor.vartype)
				{
				case AC_INTEGER:
					accum=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetValue(
						paccum[3]->descriptor.var.i32);
					break;
				case AC_STRING:
					accum=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetValue(
						paccum[3]->descriptor.var.pstr);
					break;
				case AC_STREAM:
					accum=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetValue(
						((RouterStreamImpl*)paccum[3]->descriptor.var.othr)->GetText());
					break;
				}
				if(accum)
				{
					paccum[1]->Attach(accum);
					stype=ST_TRANSFORM;
					Execute();
					stype=ST_RECORDSET;
				}
				paccum[1]->flags=AC_TYPE_DISCARDABLE;
			}
			break;
		case ST_RS_ROWCOUNT:
			{
				int i;
				i=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetRowCount();
				paccum[1]->MakeVar(0L,AC_INTEGER);
				paccum[1]->flags=AC_TYPE_DISCARDABLE;
				paccum[1]->descriptor.var.i32=i;
				stype=ST_TRANSFORM;
				Execute();
				stype=ST_RECORDSET;
			}
			break;
		case ST_RS_COLCOUNT:
			{
				int i;
				i=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetColCount();
				paccum[1]->MakeVar(0L,AC_INTEGER);
				paccum[1]->flags=AC_TYPE_DISCARDABLE;
				paccum[1]->descriptor.var.i32=i;
				stype=ST_TRANSFORM;
				Execute();
				stype=ST_RECORDSET;
			}
			break;
		case ST_RS_GETIND:case ST_RS_GETERROR:
			{
				int i;
				i=((RouterRecordsetImpl*)paccum[2]->descriptor.var.othr)->GetInd();
				if(sstype==ST_RS_GETERROR)
				{
					paccum[1]->MakeVar(0L,AC_INTEGER);
					paccum[1]->flags=AC_TYPE_DISCARDABLE;
					if(i<0)
						paccum[1]->descriptor.var.i32=i;
					else 
						paccum[1]->descriptor.var.i32=RS_NONE;
				}
				else
				{
					paccum[1]->MakeVar(0L,AC_INTEGER);
					paccum[1]->flags=AC_TYPE_DISCARDABLE;
					if(!i)
						paccum[1]->descriptor.var.i32=RS_NOTFOUND;
					else if(i<0)
						paccum[1]->descriptor.var.i32=RS_HAVEERRORS;
					else
						paccum[1]->descriptor.var.i32=RS_NONE;
				}
			}
			stype=ST_TRANSFORM;
			Execute();
			stype=ST_RECORDSET;
			break;
		default:
			REI_ERUNTIME("Invalid use of EOF and BOF",REI_ERROR);
			break;
		}
		break;
	}
	if(retind)
		return(RouterStepImpl*)-2;
	return nextStep;
}
