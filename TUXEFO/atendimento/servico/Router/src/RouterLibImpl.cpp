#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterLibImpl.cpp

Abstract:
	Implements library loader

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

#ifndef WIN32
#include<dlfcn.h>
#include<dirent.h>
#else
#include<unixemu10.h>
#endif

int Solve(char*pfn,char*buff,RouterTraceImpl*ptrace)
{
	char*penvr,*envrtok,envrdpl[1024];
	HTOKEN pMap;RETITEM ri;
	DIR*dir;
	struct dirent*dent;

	penvr=getenv("LD_LIBRARY_PATH");
	ptrace->DumpText(penvr);
	ptrace->DumpText(pfn);
	strcpy(envrdpl,penvr);
#ifdef WIN32
	pMap=MakeToken(envrdpl,';',0L);
#else
	pMap=MakeToken(envrdpl,':',0L);
#endif
	FIRSTITEM(ri);
	while(envrtok=GetNextToken(&ri,pMap))
	{
		ptrace->DumpText(envrtok);
		dir=opendir(envrtok);
		for(dent=readdir(dir);dent!=0L;dent=readdir(dir))
		{
			if(!strcmp(dent->d_name,pfn))
			{
				DestroyToken(pMap);					//ML 10
				closedir(dir);
				sprintf(buff,"%s/%s",envrtok,pfn);
				return 1;
			}
		}
		closedir(dir);
	}
	DestroyToken(pMap);
	return 0;
}
HLIB LoadExternComponent(char*pln,RouterTraceImpl*ptrace)
{
	char cpfn[128],buff[1024];
#ifdef WIN32
	char*lbx=".dll";
#else
	char*lbx=".so";
#endif
	HLIB hlib;

	sprintf(cpfn,"%s%s",pln,lbx);
	if(!Solve(cpfn,buff,ptrace))
	{
		sprintf(cpfn,"lib%s%s",pln,lbx);
		if(!Solve(cpfn,buff,ptrace))
			return 0L;
	}
	hlib=dlopen(buff,RTLD_LAZY);
	return hlib;
}
int UnloadExternComponent(HLIB hlib)
{return dlclose(hlib);}
HFUNC GetProc(HLIB hlib,char*pf)
{return dlsym(hlib,pf);}
#define HASUNLOADED(A) A

RouterLibImpl::RouterLibImpl(char*plibname,RouterTraceImpl*pTrace):
	hLib(0L),nextLib(0L),pLib(0L),paccum(0L),ptrace(pTrace)
{
	hLib=LoadExternComponent(plibname,ptrace);
	if(!hLib)
	{
		char cmsg[1024];
		sprintf(cmsg,"Library \"%.900s\" cannot be loaded",plibname);
		prei->AddMessage("Parse",cmsg,0L,-1,REI_ERROR,THROW_ADD_ONLY);
	}
	pLib=derivStr(plibname);
}
RouterLibImpl::~RouterLibImpl()
{
	if(nextLib)
		delete nextLib;
	if(pLib)
		free(pLib);
	if(hLib&&HASUNLOADED(UnloadExternComponent(hLib)))
		throw new TuxBasicSvcException("00E9999","Library cannot be unloaded.");
	if(paccum)
	{
		RouterAccumImpl*accum;

		accum=paccum;
		while(accum)
		{
			if(((LIBACCUM*)accum->descriptor.var.othr)->pfncName)
			free(((LIBACCUM*)accum->descriptor.var.othr)->pfncName);
			delete(LIBACCUM*)accum->descriptor.var.othr;
			accum=accum->RetrieveNextAccum();
		}
		delete paccum;
	}
}
int RouterLibImpl::LoadFunction(char*pfnc)
{
	RouterAccumImpl*accum;
	LIBACCUM*lbaccum;

	if(!paccum)
		accum=paccum=new RouterAccumImpl();
	else
		accum=paccum->AddNewAccum();

	lbaccum=new LIBACCUM;
	accum->descriptor.vartype=AC_CUSTTYPE;
	accum->descriptor.var.othr=lbaccum;

	lbaccum->fnc=(_fncCall)GetProc(hLib,pfnc);

	if(!lbaccum->fnc)
	{
		char cmsg[1024];
		sprintf(cmsg,"Function \"%.900s\" cannot be found - FuncLoader",pfnc);
		prei->AddMessage("Parse",cmsg,0L,-1,REI_ERROR,THROW_ADD_ONLY);
	}
	else
		lbaccum->pfncName=derivStr(pfnc);
	return 1;
}
void*RouterLibImpl::GetInstance(char*pfnc)
{
	RouterAccumImpl*accum;
	LIBACCUM*lbaccum;

	if(!paccum)
		return 0L;

	pfnc=RouterStringImpl::_LTrim(pfnc);
	RouterStringImpl::_RTrim(pfnc);
	accum=paccum;
	while(accum)
	{
		lbaccum=(LIBACCUM*)accum->descriptor.var.othr;
		if(lbaccum->pfncName&&!strcmp(lbaccum->pfncName,pfnc))
			return(void*)lbaccum;
		accum=accum->RetrieveNextAccum();
	}
	{
		char cmsg[1024];
		sprintf(cmsg,"Function \"%.900s\" cannot be found",pfnc);
		prei->AddMessage("Parse",cmsg,0L,-1,REI_ERROR,THROW_ADD_ONLY);
	}
	return 0L;
}
RouterLibImpl*RouterLibImpl::RetrieveLibInstance(char*plib)
{
	if(!strcmp(plib,pLib))
		return this;
	if(nextLib)
		return nextLib->RetrieveLibInstance(plib);
	return 0L;
}
RouterLibImpl*RouterLibImpl::AddNewLib(char*plib)
{
	if(!nextLib)
	{
		nextLib=new RouterLibImpl(plib,ptrace);
		return nextLib;
	}
	return nextLib->AddNewLib(plib);
}