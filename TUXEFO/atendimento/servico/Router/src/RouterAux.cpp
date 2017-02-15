#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterAux.cpp

Abstract:
	Auxiliary routines

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-05 - Ivan Mentone - Add static _IsNumeric function to RouterStringImpl

--*/

RouterHelper::RouterHelper(RouterAccumImpl*pAccum,RouterTraceImpl*pTrace):
	rootAccum(pAccum),ptrace(pTrace)
{}
int RouterHelper::ParseSolveW(char*pln,RouterAccumImpl***paccum,RouterTraceImpl*ptrace)
{
	char*st1,*st2,och=0,retType=OP_TR_RPL;

	st1=strchr(pln,'+');
	if(st1)
	{
		retType=OP_TR_ADD;
		*st1++=0;
		st1=RouterStringImpl::_LTrim(st1);
	}
	else
		st1=strchr(pln,'=');
	if(!st1||*st1!='=')
	{
		REI_EPARSE("Invalid command",REI_ERROR);
		return 0;
	}
	*st1++=0;
	pln=RouterStringImpl::_LTrim(pln);
	if(strchr(pln,':')||*pln=='"')
	{
		ptrace->DumpText("l-value cannot be set");
		ptrace->DumpText(pln);
		REI_EPARSE("l-value cannot be set",REI_ERROR);
		return 0;
	}
	st2=strchr(st1,':');
	*st2++=0;
	st2=RouterStringImpl::_LTrim(st2);
	if(!*st2||*st2==']')
	{
		REI_EPARSE("Need tag to solve this",REI_ERROR);
		return 0;
	}
	(*paccum)=new RouterAccumImpl*[3];
	memset(*paccum,0,sizeof(RouterAccumImpl*)*3);
	st1=RouterStringImpl::_LTrim(st1);
	RouterStringImpl::_RTrim(st1);
	if(*st1=='[')
	{
		st2--;
		*st2=']';
		st2++;
		och=*st2;
		*st2=0;
	}
	pln=RouterStringImpl::_LTrim(pln);
	RouterStringImpl::_RTrim(pln);
	MakeParam(pln,&(*paccum)[0]);
	if(!strcmp(st1,"[XML]")||!strcmp(st1,"XML"))
	{
		char cvar[15];
		strcpy(cvar,"[a_@XML]");
		MakeParam(cvar,&(*paccum)[1]);
	}
	else
	{
		MakeParam(st1,&(*paccum)[1]);
		if(!(*paccum)[1])
		{
			char cmsg[1024];
			sprintf(cmsg,"'%.900s' : Undeclared identifier",st1);
			REI_EPARSE(cmsg,REI_ERROR);
			return 0;
		}
		if((*paccum)[1]->descriptor.vartype!=AC_DOMNODE&&
			(*paccum)[1]->descriptor.vartype!=AC_XMLGEN&&
			(*paccum)[1]->descriptor.vartype!=AC_STREAM)
		{
			REI_EPARSE("Incompatible types",REI_ERROR);
			return 0;
		}
	}
	if(och)
		*st2=och;
	st1=strchr(st2,']');
	if(st1)
		*st1=0;
	(*paccum)[2]=new RouterAccumImpl();
	(*paccum)[2]->MakeVar(0L,AC_STRING);
	RouterStringImpl::_RTrim(st2);
	st2=RouterStringImpl::_LTrim(st2);
	(*paccum)[2]->descriptor.var.pstr=derivStr(st2);
	(*paccum)[2]->flags=AC_TYPE_AUTOMOUNT;				//TR ML 2
	return retType;
}
char*RouterHelper::RetrieveNodeText(DOMNode*node,char*ptag)
{
	DOMNode*dnode,*child;
	int _0=0;

	dnode=RetrieveNode(node,ptag,&_0,0);
	if(!dnode)
		return 0L;
	child=dnode->getFirstChild();
	if(!child)
		return"";
	return XMLString::transcode(child->getNodeValue());
}
DOMNode*RouterHelper::RetrieveNode(DOMNode*node,char*ptag,int*item,int itemno)
{
	DOMNodeList*child;
	char*aux,cmp;
	DOMNode*ret;
	int len;

	if(!node)
		return 0L;
	switch(node->getNodeType())
	{
	case DOMNode::DOCUMENT_NODE:
		return RetrieveNode(node->getFirstChild(),ptag,item,itemno);
	case DOMNode::ELEMENT_NODE:
		aux=XMLString::transcode(node->getNodeName());
		if(!(cmp=strcmp(aux,ptag))&&*item==itemno)
			return node;
		else if(!cmp)
			*item+=1;
		else
		{
			child=node->getChildNodes();
			len=(int)child->getLength();
			for(int i=0;i<len;i++)
			{
				ret=RetrieveNode(child->item(i),ptag,item,itemno);
				if(ret&&*item==itemno)
					return ret;
				else if(ret)
					*item+=1;
			}
		};
		break;
	default:
		break;

	};
	return 0L;
}
int RouterHelper::MakeParam(char*param,RouterAccumImpl**accum)
{
	char*st1,*st2,ind=0;

	param=RouterStringImpl::_LTrim(param);
	RouterStringImpl::_RTrim(param);
	if(strchr(param,'"'))
	{
		st1=strchr(param,34);
		st2=strchr(st1+1,34);
		while(st2&&*(st2-1)==92)
			st2=strchr(st2+1,34);
		if(!st2)
		{
			REI_EPARSE("Invalid string - missing \" after constant",REI_ERROR);
			return 0;
		}
		st1++;
		*st2--=0;
		if(!*accum)
		{
			*accum=new RouterAccumImpl;
			ind=1;
		}
		(*accum)->MakeVar(0L,AC_STRING);
		if(ind)(*accum)->flags=AC_TYPE_AUTOMOUNT;		//TR ML 2
		(*accum)->descriptor.var.pstr=derivStr(st1);
		(*accum)->sz=strlen(st1);
		(*accum)->descriptor.varsize=(*accum)->sz;
		if(RouterTransformImpl::HasEscapeSequence(*accum))
		{
			RouterTransformImpl rti(*accum);
			rti.ReplaceEscapeSequence();
		}
	}
	else if(*param=='['||!((*param>47&&*param<58)||*param==45))
	{
		st1=param;
		if(*st1=='[')
			st1++;
		st2=strchr(st1,']');
		if(!st2&&*param=='[')
		{
			REI_EPARSE("Missing ']' before var or XML tag",REI_ERROR);
			return 0;
		}
		if(st2)*st2--=0;
		if(!strncmp(st1,"XML:",4))
		{
			AuxDOMImpl*adi;
			RouterAccumImpl*laccum;
			laccum=rootAccum->RetrieveVar("a_@XML");
			adi=new AuxDOMImpl(laccum->descriptor.var.dom->RetrieveDOM());
			if(adi->Bad())
			{
				REI_EPARSE("Badly formation of string XML",REI_ERROR);
				return 0;
			}
			st1+=4 ;
			adi->SetRoot(st1);
			if(!*accum)
			{
				*accum=new RouterAccumImpl;
				ind=1;
			}
			(*accum)->MakeVar(0L,AC_DOMNODE);
			if(ind)(*accum)->flags=AC_TYPE_DISCARDABLE;
			(*accum)->descriptor.var.dom=adi;
		}
		else
		{
			RouterAccumImpl*laccum;
			if(!strcmp(st1,"XML"))
			{
				char cvar[15];
				strcpy(cvar,"a_@XML");
				laccum=rootAccum->RetrieveVar(cvar);
			}
			else
				laccum=rootAccum->RetrieveVar(st1);
			if(!laccum)
			{
				char cmsg[1024];
				sprintf(cmsg,"'%.900s' : Undeclared identifier",st1);
				REI_EPARSE(cmsg,REI_ERROR);
				return 0;
			}
			else
			{
				laccum->AddRef();
				if(*accum)
					delete *accum;
				*accum=laccum;
			}
		}
	}
	else if((*param>47&&*param<58)||*param==45)
	{
		if(!*accum)
		{
			*accum=new RouterAccumImpl;
			ind=1;
		}
		(*accum)->MakeVar(0L,AC_INTEGER);
		if(ind)(*accum)->flags=AC_TYPE_DISCARDABLE;
		(*accum)->descriptor.var.i32=atoi(param);
	}
	else
	{
		REI_EPARSE("Unknown Type",REI_ERROR);
		return 0;
	}
	return 1;
}
int RouterHelper::DeclareVar(char*pln)
{
	char*st3;
	RouterAccumImpl*accum;
	int ind;

	ptrace->DumpText("Data Segment");
	ptrace->DumpText(pln);
	st3=pln;
	while(*st3!=32&&*st3!=10&&*st3!=13&&*st3!=9&&*st3)st3++;
	if(!*st3){ind=-5;goto __except_cascade;}
	*st3++=0;
	st3=RouterStringImpl::_LTrim(st3);
	RouterStringImpl::_RTrim(st3);
	RouterStringImpl::_RTrim(pln);

	if(*st3>47&&*st3<58){ind=-6;goto __except_cascade;}
	if(!strcmp(st3,"XML")){ind=-3;goto __except_cascade;}
	if(!strcmp(pln,"char"))
	{
		char*sst1,*sst2;
		int sz;

		sst1=strchr(st3,'[');
		if(!sst1){ind=-7;goto __except_cascade;}
		*sst1++=0;
		sst2=strchr(sst1,']');
		if(!sst2){ind=-8;goto __except_cascade;}
		*sst2=0;
		sz=atoi(sst1);
		if(sz<1){ind=-9;goto __except_cascade;}
		if(ind=IsDistinct(st3))goto __except_cascade;
		accum=rootAccum->AddNewAccum();
		accum->MakeVar(st3,AC_STRING);
		{
			RouterStringImpl rsi;
			rsi.MakeString(accum,sz);
		}
	}
	else
	{
		if(!*st3){ind=-4;goto __except_cascade;}
		if(ind=IsDistinct(st3))goto __except_cascade;
		accum=rootAccum->AddNewAccum();
		if(!accum->MakeVar(st3,pln)){ind=-5;goto __except_cascade;}
	}
	ptrace->DumpAccum(accum);
	return 1;
__except_cascade:
	{
		char cmsg[1024];
		switch(ind)
		{
		case -1:
			sprintf(cmsg,"Var name exceed limit - %.900s",st3);
			REI_EPARSE(cmsg,REI_ERROR);
			break;
		case -2:
			sprintf(cmsg,"Redefinition - %.900s",st3);
			REI_EPARSE(cmsg,REI_ERROR);
			break;
		case -3:
			sprintf(cmsg,"Reserved var name - %.900s",st3);
			REI_EPARSE(cmsg,REI_ERROR);
			break;
		case -4:
			REI_EPARSE("Empty var name",REI_ERROR);
			break;
		case -5:
			sprintf(cmsg,"Undefined datatype - %.900s",pln);
			REI_EPARSE(cmsg,REI_ERROR);
			break;
		case -6:
			sprintf(cmsg,"'%.900s' : bad suffix on number",st3);
			REI_EPARSE(cmsg,REI_ERROR);
			break;
		case -7:
			REI_EPARSE("CHAR datatype requires size",REI_ERROR);
			break;
		case -8:
			REI_EPARSE("Missing ']' after CHAR size",REI_ERROR);
			break;
		case -9:
			REI_EPARSE("Invalid size for CHAR",REI_ERROR);
			break;
		}
	}
	return 0;
}
int RouterHelper::IsDistinct(char*pvn)
{
	RouterAccumImpl*accum;
	if(strlen(pvn)>14)return -1;
	accum=rootAccum->RetrieveVar(pvn);
	if(accum)
		return -2;
	return 0;
}
int RouterHelper::IsSolve(char*pstr)
{
	char*st1,*st2,*st3;
	if(strchr(pstr,'('))
		return 0;
	if(!strchr(pstr,'='))
		return 0;
	st1=RouterStringImpl::_GetStringLimitAdj(pstr,':');	//TR9
	if(!st1)
		return 0;
	st2=strchr(pstr,'"');
	if(!st2)
		return 1;
	st3=strchr(st2+1,'"');
	if(!st3)
	{
		REI_EPARSE("Invalid string - missing \" after constant",REI_ERROR);
		return 0;
	}
	if(st2<st1&&st3>st1)
		return 0;
	return 1;
}
int RouterHelper::IsCommand(char*pstr)
{
	char*st1;

	st1=RouterStringImpl::_FindFromCode(pstr,"=");
	if(!st1)
		st1=pstr;
	else
		st1++;
	st1=RouterStringImpl::_LTrim(st1);

	if(!strncmp(st1,"GetHdrData",10))
		goto __check;
	else if(!strncmp(st1,"Clean",5))
		goto __check;
	else if(!strncmp(st1+1,"Trim",4))
		goto __check;
	else if(!strncmp(st1,"Trim",4))
		goto __check;
	else if(!strncmp(st1,"OpenRecordset",13))
		goto __check;
	else if(!strncmp(st1,"Mid",3))
		goto __check;
	else if(!strncmp(st1,"Len",3))
		goto __check;
	else if(!strncmp(st1,"println",7))
		goto __check;
	return 0;
__check:
	if(strchr(st1,'('))
		return 1;
	return 0;
}
int RouterHelper::IsRecordset(char*pstr)
{
	RouterAccumImpl*accum;
	char*st1;
	int ret=0;

	st1=strchr(pstr,'=');
	if(st1)
		pstr=RouterStringImpl::_LTrim(st1+1);
	st1=strchr(pstr,'.');
	if(!st1)
		return 0;
	*st1=0;
	accum=rootAccum->RetrieveVar(pstr);
	if(accum&&accum->descriptor.vartype==AC_RECORDSET)
		ret=1;
	*st1='.';
	return ret;
}
char*RouterTuxFWReader::readHdrAttr(void*pv,char*pattr)
{return((RouterTuxFWReader*)pv)->getHdrAttr(pattr);};
char*RouterStringImpl::RTrim(char*psrc)
{
	int sz;

	sz=strlen(psrc)-1;
	while(psrc[sz]==32)sz--;
	psrc[sz+1]=0;
	return psrc;
}
char*RouterStringImpl::_RTrim(char*psrc)
{
	int sz;

	sz=strlen(psrc)-1;
	while(psrc[sz]==32||psrc[sz]==9||psrc[sz]==10||psrc[sz]==13)sz--;
	psrc[sz+1]=0;
	return psrc;
}
char*RouterStringImpl::_LTrim(char*psrc)
{
	char*pcr;

	pcr=psrc;
	while(*pcr==10||*pcr==13||*pcr==9||*pcr==32)pcr++;
	return pcr;
}
int RouterStringImpl::_IsNumeric(char*psrc)
{
	while(*psrc&&(*psrc)>47&&(*psrc)<58)psrc++;
	if(!*psrc)
		return 1;
	return 0;
}
int RouterStringImpl::_HasIncDec(char*psrc)
{
	char*pc;

	pc=strchr(psrc,'+');
	if(pc&&*(pc+1)=='+')
		return 1;
	pc=strchr(psrc,'-');
	if(pc&&*(pc+1)=='-')
		return 1;
	return 0;
}
int RouterStringImpl::MakeString(RouterAccumImpl*accum,int sz)
{
	if(accum->descriptor.vartype!=AC_STRING)
	{
		REI_EPARSE("Invalid datatype",REI_ERROR);
		return 0;
	}
	if(accum->descriptor.var.pstr)
	{
		if(accum->descriptor.varsize)
			delete[]accum->descriptor.var.pstr;
		else
			delete accum->descriptor.var.pstr;
		accum->descriptor.var.pstr=0L;
	}
	if(sz)
	{
		accum->descriptor.var.pstr=new char[sz];
		*accum->descriptor.var.pstr=0;
		accum->descriptor.varsize=sz;
		accum->sz=0;
	}
	return sz;	
}
int RouterStringImpl::MakeString(RouterAccumImpl*accum,char*pstr)
{
	int sz;

	if(!(sz=strlen(pstr)))
		return 0;
	if(accum->descriptor.varsize<sz+1)
	{REI_EPARSE("Size great than need",REI_ERROR);return 0;}
	strcpy(accum->descriptor.var.pstr,pstr);
	accum->sz=sz;
	return sz;
}
int RouterStringImpl::AppendString(RouterAccumImpl*accum,char*pstr)
{
	int sz;

	if(!(sz=strlen(pstr)))
		return 0;
	if((accum->sz+sz)+1>accum->descriptor.varsize)
	{REI_EPARSE("Size great than need",REI_ERROR);return 0;}
	strcat(accum->descriptor.var.pstr,pstr);
	accum->sz+=sz;
	return accum->sz;
}
int RouterStringImpl::AdjustSize(RouterAccumImpl*accum)
{
	int sz;

	if(accum->descriptor.vartype!=AC_STRING)
		return 0;
	sz=strlen(accum->descriptor.var.pstr);
	accum->sz=sz;
	return sz;
}
char*RouterStringImpl::_GetStringLimitAdj(char*pstr,char ind)
{
	int bc=0;

	while(*pstr)
	{
		if(*pstr==34&&*(pstr-1)!=92)
			bc++;
		else if(*pstr==ind&&!(bc%2))
			break;
		pstr++;
	}
	if(!*pstr)
	{
		if(bc%2)REI_EPARSE("Missing \" in constant",REI_ERROR);
		return 0L;
	}
	return pstr;
}
extern char*cbTOC[];
int RouterStringImpl::_Encode(XMLGen*pxml)
{
	char*pv,*cur;
	unsigned char chr;
	int len,mod=0;
	RouterStreamImpl rsi(0L);

	pv=cur=pxml->retrieveXML(&len);
	while(*cur)
	{
		if((unsigned char)*cur/160)
		{
			chr=*cur;
			*cur++=0;
			if(*pv)						//TR37
				rsi.AppendText(pv);
			rsi.AppendText(cbTOC[chr%160]);
			pv=cur;
			mod++;
		}
		else
			cur++;
	}
	if(mod)
	{
		if(*pv)
			rsi.AppendText(pv);
		pxml->clearAndDestroy();
		((RouterXMLImpl*)pxml)->AttachText(rsi.GetText());
	}
	return 1;
}
char*RouterStringImpl::_GetStringLimitAdj(char*pstr)
{
	int bc=0;

	if(*pstr!=34)
		return 0L;
	while(*pstr)
	{
		if(*pstr==34&&*(pstr-1)!=92)
			bc++;
		if(!(bc%2))
			break;
		pstr++;
	}
	if(!*pstr)
	{
		if(bc%2)REI_EPARSE("Missing \" in constant",REI_ERROR);
		return 0L;
	}
	return pstr;
}
char*RouterStringImpl::_FindFromLimitAdj(char*pstr)
{
	int bc=0;
	while(*pstr)
	{
		if(*pstr==34&&*(pstr-1)!=92)
			bc++;
		else if((*pstr==32||*pstr==9||*pstr==10||*pstr==13||*pstr==61)&&!(bc%2))
			break;
		pstr++;
	}
	if(!*pstr)
		return 0L;
	return pstr;
}
extern"C"char*FindFromCodeC(char*,char*);
char*RouterStringImpl::_FindFromCode(char*pstr,char*pfnd)
{return FindFromCodeC(pstr,pfnd);}
char*RouterStringImpl::_SeekRelativesLmtAdj(char*pc)
{
	char*rl;
	int rlc=0;

	rl=strchr(pc,'(');
	if(!rl)
		return pc;
	while(*rl)
	{
		if(*rl=='(')
			rlc++;
		else if(*rl==')')
			rlc--;
		if(!rlc)
			break;
		rl++;
	}
	if(!*rl)
		return 0L;
	return rl+1;
}
char*RouterStringImpl::_TRIfBlockAdj(char*pc)
{
	pc=RouterStringImpl::_LTrim(pc);
	if(*pc=='i'&&*(pc+1)=='f')
	{
		pc--;
		if(*pc==9||*pc==13||*pc==9||*pc==10||*pc==32||!*pc)
			*pc=';';
	}
	return pc;
}
int RouterStringImpl::_IsTransform(char*pc)
{
	char*st1;
	int bc=0;

	st1=strchr(pc,'=');
	if(!st1)
		return 0;
	while(*st1)
	{
		if(*st1==34&&*(st1-1)!=92)
			bc++;
		else if(*st1=='.'&&!(bc%2))
			break;
		st1++;
	}
	if(!*st1)
		return 1;
	return 0;
}
int RouterStringImpl::_IsExternCall(char*pc)
{
	char*st1;
	int bc=0,i1=0,i2=0,i3=0;

	st1=strchr(pc,'=');
	if(!st1)
		return 0;
	while(*st1)
	{
		if(*st1==34&&*(st1-1)!=92)
			bc++;
		else if(*st1=='.'&&!(bc%2))
			i1++;
		else if(*st1=='('&&!(bc%2))
			i2++;
		else if(*st1==')'&&!(bc%2))
			i3++;
		st1++;
	}
	if(!i1&&i2&&i3)
		return 1;
	return 0;
}
