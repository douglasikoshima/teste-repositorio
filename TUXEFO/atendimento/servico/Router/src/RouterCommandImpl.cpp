#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterCommandImpl.cpp

Abstract:
	Implements internal commands

Author:
    Ivan Mentone 2004-06-30

Environment:
    Router Core

Revision History:
	2004-07-13 - Ivan Mentone - Add new command OpenRecordset
	2004-07-26 - Ivan Mentone - Convert throw to RouterErrorImpl

--*/

RouterCommandImpl::RouterCommandImpl(RouterAccumImpl*rAccum,RouterTraceImpl*pTrace):
	rootAccum(rAccum),ptrace(pTrace),cmdind(OP_CMD_NONE),varInd(0),RouterHelper(rAccum,pTrace),
	op(OP_TR_RPL),pbtask(0L),patask(0L),paccum(0L)
{}
RouterCommandImpl::~RouterCommandImpl()
{
	int i;

	if(paccum)
	{
		for(i=0;i<varInd;i++)
		{
			if(paccum[i]&&paccum[i]->flags&AC_TYPE_DISCARDABLE)
				delete paccum[i];
		}
		delete[]paccum;
	}
	if(pbtask)
		delete pbtask;
	if(patask)
		delete patask;
}
int RouterCommandImpl::Validate(char*pln)
{
	if(*pln!='(')
	{
		REI_EPARSE("Invalid command format : expected '(' before param list",REI_ERROR);
		return 0;
	}
	pln=strchr(pln,')');
	if(!pln)
	{
		REI_EPARSE("Invalid command format : expected ')' after param list",REI_ERROR);
		return 0;
	}
	return 1;
}
int RouterCommandImpl::ParseGetHdrData(char*pln)
{
	char*st1;

	varInd=2;
	paccum=new RouterAccumImpl*[2];
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);

	st1=strchr(pln,'=');
	if(*(st1-1)=='+')
	{
		op=OP_TR_ADD;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='-')
	{
		op=OP_TR_DFF;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='*')
	{
		op=OP_TR_MLT;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='/')
	{
		op=OP_TR_DIV;
		*(st1-1)=0;
	}
	*st1++=0;
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&paccum[0]);
	if(paccum[0]->descriptor.vartype!=AC_STREAM&&paccum[0]->descriptor.vartype!=AC_XMLGEN&&
		paccum[0]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Invalid datatype to GetHdrData",REI_ERROR);
		return 0;
	}
	if((op==OP_TR_DFF||op==OP_TR_MLT||op==OP_TR_DIV)&&paccum[0]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Invalid operation for this datatype",REI_ERROR);
		return 0;
	}
	pln=st1;
	pln=RouterStringImpl::_LTrim(pln);
	pln+=10;
	pln=RouterStringImpl::_LTrim(pln);
	if(!Validate(pln))
		return 0;
	pln++;
	st1=strchr(pln,')');
	*st1=0;
	if(strchr(pln,':'))
	{
		RouterAccumImpl*accum;
		RouterStreamImpl rsi(ptrace);

		accum=rootAccum->AddNewAccum();
		accum->MakeVar(0L,AC_STREAM);
		rsi.Truncate();
		rsi.AppendText(accum->varNm);
		rsi.AppendText("=");
		rsi.AppendText(pln);
		pbtask=new RouterTaskImpl(0L,rootAccum,ptrace);
		pbtask->Assemble(rsi.GetText());
		MakeParam(accum->varNm,&paccum[1]);
	}
	else
	{
		MakeParam(pln,&paccum[1]);
		if(paccum[1]->descriptor.vartype!=AC_STREAM&&paccum[1]->descriptor.vartype!=AC_STRING)
		{
			REI_EPARSE("Invalid datatype to GetHdrData",REI_ERROR);
			return 0;
		}
	}

	return 1;
}
int RouterCommandImpl::ParseClean(char*pln)
{
	char*st1;

	varInd=1;
	paccum=new RouterAccumImpl*[1];
	memset(paccum,0,sizeof(RouterAccumImpl*));

	st1=strchr(pln,'(');
	if(!Validate(st1))
		return 0;
	pln=st1+1;
	st1=strchr(pln,')');
	*st1=0;
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&paccum[0]);
	return 1;
}
int RouterCommandImpl::ParseTrim(char*pln)
{
	char*st1,*st0;

	pln=RouterStringImpl::_LTrim(pln);
	switch(*pln)
	{
	case 'R':
		cmdind=OP_CMD_RTRIM;
		break;
	case 'L':
		cmdind=OP_CMD_LTRIM;
		break;
	default:
		cmdind=OP_CMD_TRIM;
	}
	st1=pln;
	pln=strchr(pln,'(');
	if(!Validate(pln))
		return 0;
	*pln++=0;
	pln=RouterStringImpl::_LTrim(pln);
	if(*pln==34||(*pln>47&&*pln<58))
	{
		REI_EPARSE("Cannot execute TRIM command in constants",REI_ERROR);
		return 0;
	}
	{
		char*st1;
		st1=strchr(pln,')');
		*st1=0;
	}
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	varInd=1;
	paccum=new RouterAccumImpl*[1];
	memset(paccum,0,sizeof(RouterAccumImpl*));
	MakeParam(pln,&paccum[0]);
	if(paccum[0]->descriptor.vartype!=AC_STRING&&paccum[0]->descriptor.vartype!=AC_STREAM)
	{
		REI_EPARSE("Cannot execute TRIM command in other types except STRING or CHAR",REI_ERROR);
		return 0;
	}
	if((st0=strchr(st1,'=')))
	{
		st0++;
		strcpy(st0,pln);
		patask=new RouterTaskImpl(0L,rootAccum,ptrace);
		patask->Assemble(st1);
	}
	return 1;
}
int RouterCommandImpl::ParseOpenRS(char*pln)
{
	char*st1;

	varInd=2;
	paccum=new RouterAccumImpl*[2];
	memset(paccum,0,sizeof(RouterAccumImpl*)*2);

	st1=strchr(pln,'=');
	*st1++=0;
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&paccum[0]);
	if(!paccum[0]||paccum[0]->descriptor.vartype!=AC_RECORDSET)
	{
		REI_EPARSE("Invalid datatype : expected RECORDSET",REI_ERROR);
		return 0;
	}
	paccum[0]->AddRef();
	pln=st1;
	pln=RouterStringImpl::_LTrim(pln);
	pln+=13;
	pln=RouterStringImpl::_LTrim(pln);
	if(!Validate(pln))
		return 0;
	st1=RouterStringImpl::_SeekRelativesLmtAdj(pln);
	if(!st1||*st1)
	{
		REI_EPARSE("Invalid 'OpenRecordset' command format",REI_ERROR);
		return 0;
	}
	pln++;
	pln=RouterStringImpl::_LTrim(pln);
	*(st1-1)=0;
	if(strchr(pln,':'))
	{
		RouterAccumImpl*accum;
		RouterStreamImpl rsi(ptrace);

		accum=rootAccum->AddNewAccum();
		accum->MakeVar(0L,AC_STREAM);
		rsi.Truncate();
		rsi.AppendText(accum->varNm);
		rsi.AppendText("=");
		rsi.AppendText(pln);
		pbtask=new RouterTaskImpl(0L,rootAccum,ptrace);
		pbtask->Assemble(rsi.GetText());
		MakeParam(accum->varNm,&paccum[1]);
	}
	else
	{
		MakeParam(pln,&paccum[1]);
		if(paccum[1]&&paccum[1]->descriptor.vartype!=AC_STREAM&&paccum[1]->descriptor.vartype!=AC_STRING)
		{
			REI_EPARSE("Invalid command format : expected string or char",REI_ERROR);
			return 0;
		}
	}

	return 1;
}
int RouterCommandImpl::ParsePrintln(char*pln)
{
	HTOKEN ht;
	int iht,ite=0;
	RETITEM ri;
	char*pc;

	if(!ptrace->HasDebug())
	{
		cmdind=OP_CMD_NONE;
		return 0;
	}
	pln=strchr(pln,40)+1;
	pc=RouterStringImpl::_FindFromCode(pln,")");
	if(!pc)
	{
		REI_EPARSE("Missing ')' before command call",REI_ERROR);
		return 0;
	}
	*pc=0;
	ht=MakeToken(pln,'+',&iht);
	varInd=iht;
	paccum=new RouterAccumImpl*[varInd];
	memset(paccum,0,sizeof(RouterAccumImpl*)*varInd);
	FIRSTITEM(ri);
	while(pc=GetNextToken(&ri,ht))
		MakeParam(pc,&paccum[ite++]);
	DestroyToken(ht);
	return 1;
}
int RouterCommandImpl::ParseMid(char*pln)
{
	char*st1;

	varInd=4;
	paccum=new RouterAccumImpl*[4];
	memset(paccum,0,sizeof(RouterAccumImpl*)*4);

	st1=RouterStringImpl::_FindFromCode(pln,"=");
	if(*(st1-1)=='+')
	{
		op=OP_TR_ADD;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='-')
	{
		op=OP_TR_DFF;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='*')
	{
		op=OP_TR_MLT;
		*(st1-1)=0;
	}
	else if(*(st1-1)=='/')
	{
		op=OP_TR_DIV;
		*(st1-1)=0;
	}
	*st1++=0;
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&paccum[0]);
	if(paccum[0]->descriptor.vartype!=AC_STREAM&&paccum[0]->descriptor.vartype!=AC_XMLGEN&&
		paccum[0]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Invalid datatype to Mid",REI_ERROR);
		return 0;
	}
	if((op==OP_TR_DFF||op==OP_TR_MLT||op==OP_TR_DIV)&&paccum[0]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Invalid operation for this datatype",REI_ERROR);
		return 0;
	}
	pln=st1;
	pln=RouterStringImpl::_LTrim(pln);
	pln+=3;
	pln=RouterStringImpl::_LTrim(pln);
	if(!Validate(pln))
		return 0;
	pln++;
	st1=RouterStringImpl::_FindFromCode(pln,",");
	if(!st1)
	{
		REI_EPARSE("Invalid Mid param list",REI_ERROR);
		return 0;
	}
	*st1++=0;
	if(RouterStringImpl::_FindFromCode(pln,":"))
	{
		RouterAccumImpl*accum;
		RouterStreamImpl rsi(ptrace);

		accum=rootAccum->AddNewAccum();
		accum->MakeVar(0L,AC_STREAM);
		rsi.Truncate();
		rsi.AppendText(accum->varNm);
		rsi.AppendText("=");
		rsi.AppendText(pln);
		pbtask=new RouterTaskImpl(0L,rootAccum,ptrace);
		pbtask->Assemble(rsi.GetText());
		MakeParam(accum->varNm,&paccum[1]);
	}
	else
	{
		MakeParam(pln,&paccum[1]);
		if(paccum[1]->descriptor.vartype!=AC_STREAM&&paccum[1]->descriptor.vartype!=AC_STRING)
		{
			REI_EPARSE("Invalid datatype to Mid",REI_ERROR);
			return 0;
		}
	}

	pln=st1;
	st1=RouterStringImpl::_FindFromCode(pln,",");
	if(!st1)
	{
		REI_EPARSE("Invalid Mid param list",REI_ERROR);
		return 0;
	}
	*st1++=0;
	MakeParam(pln,&paccum[2]);
	if(paccum[2]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Mid start chr must be integer value",REI_ERROR);
		return 0;
	}
	pln=st1;
	st1=RouterStringImpl::_FindFromCode(pln,")");
	if(!st1)
	{
		REI_EPARSE("Invalid Mid param list",REI_ERROR);
		return 0;
	}
	*st1=0;
	MakeParam(pln,&paccum[3]);
	if(paccum[3]->descriptor.vartype!=AC_INTEGER)
	{
		REI_EPARSE("Mid count chr must be integer value",REI_ERROR);
		return 0;
	}
	return 1;
}
int RouterCommandImpl::ParseLen(char*pln)
{
	return 1;
}

int RouterCommandImpl::Assemble(char*pln)
{
	if(strstr(pln,"GetHdrData"))
	{
		cmdind=OP_CMD_GETHDRDT;
		ParseGetHdrData(pln);
	}
	else if(strstr(pln,"Clean"))
	{
		cmdind=OP_CMD_CLEAN;
		ParseClean(pln);
	}
	else if(strstr(pln,"Trim"))
		ParseTrim(pln);
	else if(strstr(pln,"OpenRecordset"))
	{
		cmdind=OP_CMD_OPENRS;
		ParseOpenRS(pln);
	}
	else if(strstr(pln,"Mid"))
	{
		cmdind=OP_CMD_MID;
		ParseMid(pln);
	}
	else if(strstr(pln,"Len"))
	{
		cmdind=OP_CMD_LEN;
		ParseLen(pln);
	}
	else if(strstr(pln,"println"))
	{
		cmdind=OP_CMD_PRINTLN;
		ParsePrintln(pln);
	}
	return 1;
}
/*
extern"C"int RetrieveCommandType(char*);
int RouterCommandImpl::Assemble(char*pln)
{
	int id;

	id=RetrieveCommandType(pln);
	switch(id)
	{
		case OP_CMD_GETHDRDT:
			ParseGetHdrData(pln);
			break;
		case OP_CMD_CLEAN:
			ParseClean(pln);
			break;
		case OP_CMD_TRIM:
			ParseTrim(pln);
			break;
		case OP_CMD_OPENRS:
			ParseOpenRS(pln);
			break;
		case OP_CMD_MID:
			ParseMid(pln);
			break;
		case OP_CMD_LEN:
			ParseLen(pln);
			break;
		case OP_CMD_PRINTLN:
			ParsePrintln(pln);
			break;
	}
	return 1;
}
*/
int RouterCommandImpl::BeforeExecute()
{
	RouterTaskImpl*task;

	task=pbtask;
	while(task)
		task=task->Execute();
	return 1;
}
int RouterCommandImpl::AfterExecute()
{
	RouterTaskImpl*task;

	task=patask;
	while(task)
		task=task->Execute();
	return 1;
}
int RouterCommandImpl::Execute()
{
	switch(cmdind)
	{
	case OP_CMD_GETHDRDT:
		{
			RouterAccumImpl*accum;
			char*ret;
			BeforeExecute();
			accum=rootAccum->RetrieveVar("#tuxThis#");
			if(!accum)
				REI_ERUNTIME("Internal fail",REI_CRITICAL);
			switch(paccum[1]->descriptor.vartype)
			{
			case AC_STRING:
				ret=paccum[1]->descriptor.var.pstr;
				break;
			case AC_STREAM:
				ret=((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText();
			}
			ret=((RouterTuxFWReader*)accum->descriptor.var.othr)->readHdrAttr(
				accum->descriptor.var.othr,ret);
			switch(paccum[0]->descriptor.vartype)
			{
			case AC_STREAM:
				if(op==OP_TR_ADD)
				{
					if(ret)
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->AppendText(ret);
				}
				else
				{
					if(ret)
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->SetText(ret);
					else
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->Truncate();
				}
				break;
			case AC_XMLGEN:
				if(op==OP_TR_ADD)
				{
					if(ret)
						((RouterXMLImpl*)paccum[0]->descriptor.var.xml)->AttachText(ret);
				}
				else
				{
					paccum[0]->descriptor.var.xml->clearAndDestroy();
					if(ret)
						paccum[0]->descriptor.var.xml->aggregateXML(ret);
				}
			case AC_INTEGER:
				{
					int i=0;
					if(ret)
						i=atoi(ret);
					if(op==OP_TR_ADD)
						paccum[0]->descriptor.var.i32+=i;
					else if(op==OP_TR_DFF)
						paccum[0]->descriptor.var.i32-=i;
					else if(op==OP_TR_MLT)
						paccum[0]->descriptor.var.i32*=i;
					else if(op==OP_TR_DIV)
						paccum[0]->descriptor.var.i32/=i;
					else if(op==OP_TR_RPL)
						paccum[0]->descriptor.var.i32=i;
				}
				break;
			}
		}
		break;
	case OP_CMD_CLEAN:
		switch(paccum[0]->descriptor.vartype)
		{
		case AC_STREAM:
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->Truncate();
			break;
		case AC_STRING:
			paccum[0]->sz=0;
			*paccum[0]->descriptor.var.pstr=0;
			break;
		case AC_INTEGER:
			paccum[0]->descriptor.var.i32=0;
			break;
		case AC_DOMNODE:
			delete paccum[0]->descriptor.var.dom;
			paccum[0]->descriptor.var.othr=0L;
			break;
		case AC_XMLGEN:
			paccum[0]->descriptor.var.xml->clearAndDestroy();
			break;
		}
		break;
	case OP_CMD_LTRIM:
		switch(paccum[0]->descriptor.vartype)
		{
		case AC_STREAM:
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->LTrim();
			break;
		case AC_STRING:
			{
				char*pstr;
				int sz;
				if(paccum[0]->sz)
				{
					pstr=paccum[0]->descriptor.var.pstr;
					pstr=RouterStringImpl::_LTrim(pstr);
					sz=strlen(pstr);
					memcpy(paccum[0]->descriptor.var.othr,pstr,sz+1);
					paccum[0]->sz=sz;
				}
			}
			break;
		}
		break;
	case OP_CMD_RTRIM:
		switch(paccum[0]->descriptor.vartype)
		{
		case AC_STREAM:
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->LTrim();
			break;
		case AC_STRING:
			{
				char*pstr;
				if(paccum[0]->sz)
				{
					pstr=paccum[0]->descriptor.var.pstr;
					RouterStringImpl::_RTrim(pstr);
					paccum[0]->sz=strlen(pstr);
				}
			}
			break;
		}
		break;
		break;
	case OP_CMD_TRIM:
		switch(paccum[0]->descriptor.vartype)
		{
		case AC_STREAM:
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->LTrim();
			((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->RTrim();
			break;
		case AC_STRING:
			cmdind=OP_CMD_RTRIM;
			Execute();
			cmdind=OP_CMD_LTRIM;
			Execute();
			break;
		}
		break;
	case OP_CMD_OPENRS:
		{
			RouterCursorImpl*prci;
			char*psql;

			BeforeExecute();
			try
			{
				prci=new RouterCursorImpl(rootAccum,ptrace);
				switch(paccum[1]->descriptor.vartype)
				{
				case AC_STRING:
					psql=paccum[1]->descriptor.var.pstr;
					break;
				case AC_STREAM:
					psql=((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText();
					break;
				}
				prci->Assemble(psql,0);
				if(prei->HasCriticals()||prei->HasErrors()||
					(prei->HasWarnings()&&ptrace->HasCheck()))
					prei->Throw();
				if(paccum[0]->descriptor.var.othr)
					delete(RouterRecordsetImpl*)paccum[0]->descriptor.var.othr;
				paccum[0]->descriptor.var.othr=new RouterRecordsetImpl(ptrace);
				prci->Execute((RouterRecordsetImpl*)paccum[0]->descriptor.var.othr);
			}
			catch(...)
			{
				delete prci;
				throw;
			}
			delete prci;
		}
		break;
	case OP_CMD_PRINTLN:
		{
			RouterStreamImpl rsi(ptrace);
			char*aux,nbr[20];int ind,i;
			for(i=0;i<varInd;i++)
			{
				switch(paccum[i]->descriptor.vartype)
				{
				case AC_XMLGEN:
					aux=paccum[i]->descriptor.var.xml->retrieveXML(&ind);
					if(aux&&ind)
						rsi.AppendText(aux);
					break;
				case AC_STREAM:
					rsi.AppendText(((RouterStreamImpl*)paccum[i]->descriptor.var.othr)->GetText());
					break;
				case AC_STRING:
					rsi.AppendText(paccum[i]->descriptor.var.pstr);
					break;
				case AC_INTEGER:
					sprintf(nbr,"%d",paccum[i]->descriptor.var.i32);
					rsi.AppendText(nbr);
					break;
				case AC_DOMNODE:
					rsi.AppendText("DOMNODE");
					break;
				default:
					rsi.AppendText("Unknown type");
				}
			}
			ptrace->PrintText(rsi.GetText());
		}
		break;
	case OP_CMD_MID:
		{
			char*ret,clrInd=0;
			BeforeExecute();
			switch(paccum[1]->descriptor.vartype)
			{
			case AC_STRING:
				ret=paccum[1]->descriptor.var.pstr;
				break;
			case AC_STREAM:
				ret=((RouterStreamImpl*)paccum[1]->descriptor.var.othr)->GetText();
			}
			{
				if((RTINT)strlen(ret)<paccum[2]->descriptor.var.i32||!paccum[3]->descriptor.var.i32)
					ret="";
				else
				{
					RouterStreamImpl rsi(ptrace);
					if(paccum[2]->descriptor.var.i32)
						ret+=paccum[2]->descriptor.var.i32;
					rsi.SetText(ret);
					rsi.SetLength(paccum[3]->descriptor.var.i32);
					ret=derivStr(rsi.GetText());
					clrInd=1;
				}
			}
			switch(paccum[0]->descriptor.vartype)
			{
			case AC_STREAM:
				if(op==OP_TR_ADD)
				{
					if(ret)
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->AppendText(ret);
				}
				else
				{
					if(ret)
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->SetText(ret);
					else
						((RouterStreamImpl*)paccum[0]->descriptor.var.othr)->Truncate();
				}
				break;
			case AC_XMLGEN:
				if(op==OP_TR_ADD)
				{
					if(ret)
						((RouterXMLImpl*)paccum[0]->descriptor.var.xml)->AttachText(ret);
				}
				else
				{
					paccum[0]->descriptor.var.xml->clearAndDestroy();
					if(ret)
						paccum[0]->descriptor.var.xml->aggregateXML(ret);
				}
			case AC_INTEGER:
				{
					int i=0;
					if(ret)
						i=atoi(ret);
					if(op==OP_TR_ADD)
						paccum[0]->descriptor.var.i32+=i;
					else if(op==OP_TR_DFF)
						paccum[0]->descriptor.var.i32-=i;
					else if(op==OP_TR_MLT)
						paccum[0]->descriptor.var.i32*=i;
					else if(op==OP_TR_DIV)
						paccum[0]->descriptor.var.i32/=i;
					else if(op==OP_TR_RPL)
						paccum[0]->descriptor.var.i32=i;
				}
				break;
			}
			if(ret&&clrInd)
				free(ret);
		}
		break;
	}
	AfterExecute();
	return 1;
}

