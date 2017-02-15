#ifndef ROUTERCLIENT
#define ROUTERCLIENT

/*++
Module Name:
    RouterClient.h

Abstract:
	Add support to Router SOs

Author:
    Ivan Mentone 2004-07-15

Environment:
    Router Core

Revision History:
	2004-07-28 - Ivan Mentone - Modify defines directive to use getCode and getMessage instead of eCode and pMsg
	2004-09-02 - Ivan Mentone - Update struct ACCUM to support UNIX64 environment

--*/

#include<tuxfw.h>

#define AC_NONE 0
#define AC_XMLGEN 1
#define AC_LIBFNC 2
#define AC_UNKNOWN 3
#define AC_CUSTTYPE 4
#define AC_DOMNODE 5
#define AC_STRING 6
#define AC_INTEGER 7

class AuxDOMImpl: public TuxHelper
{
public:
	AuxDOMImpl(char*);
	AuxDOMImpl(DOMNode*);
	~AuxDOMImpl();
	int Bad();
	DOMNode*RetrieveDOM();
	int AttachDOM(DOMNode*);
	int SetRoot(char*);
private:
	XercesDOMParser*xml_p;
	MemBufInputSource*mbis;
	DOMNode*pnode;
};

struct ACCUM
{
	ACCUM()
	{var.othr=0L;vartype=AC_NONE;};
	ACCUM&operator=(ACCUM*accum)
	{var.othr=accum->var.othr;vartype=accum->vartype;return*this;}
	ACCUM&operator=(ACCUM accum)
	{var.othr=accum.var.othr;vartype=accum.vartype;return*this;}
	union
	{
		XMLGen*xml;
		AuxDOMImpl*dom;
		char*pstr;
		long i32;
		void*othr;
	}var;
	int vartype;
};
struct PARAMS
{
	PARAMS():pcount(0),parmlist(0L){};
	int pcount;
	ACCUM**parmlist;
};
struct PARMLST
{
	int vartype;
	ACCUM*accum;
};
struct THROWIND
{
	char cid[28];
	char cmsg[1024];
	char id;
};

extern Validate(PARAMS*,PARMLST*,PARMLST*,ACCUM*,int);

#define BEGIN_DECLARE_ROUTER_PARM(FNC) struct PARMLST p##FNC[]={
#define ADD_ROUTER_FUNCTION_ITEM(TYPE) {TYPE,0L},
#define END_DECLARE_ROUTER_PARM {0L,0}};
#define DECLARE_RETURN_TYPE(FNC,RTP) struct PARMLST __RT##FNC[]={{RTP,0L},{AC_NONE,0L}};
#define DECLARE_NO_RETURN(FNC) struct PARMLST __RT##FNC[]={{AC_NONE,0L}};
#define BEGIN_DECLARE_ROUTER_INTERF(FNC,PLIST) extern"C"void FNC(PARAMS*parm,ACCUM*ac,THROWIND*tid){try{	\
	if(parm->pcount!=PLIST)throw new TuxBasicSvcException("00E9999","Invalid param list");	\
	if(!Validate(parm,p##FNC,__RT##FNC,ac,PLIST))throw new TuxBasicSvcException("00E9999","Invalid param list");
#define END_DECLARE_ROUTER_INTERF }catch(TuxBasicSvcException*e){	\
	sprintf(tid->cid,"%.27s",e->getCode());sprintf(tid->cmsg,"%.1023s",e->getMessage());tid->id=3;	\
	delete e;}catch(TuxBasicOraException o){sprintf(tid->cid,"%d",o.eCode);*tid->cmsg=0;	\
	tid->id=4;}catch(TuxBasicOraException*o){	\
	sprintf(tid->cid,"%d",o->eCode);sprintf(tid->cmsg,"%.1023s",o->pMsg);tid->id=2;	\
	delete o;}catch(...){tid->id=5;}};

#endif