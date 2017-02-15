#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterStepImpl.cpp

Abstract:
	Implements stream in Router (string datatype)

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-16 - Ivan Mentone - Optimize allocator and deallocator

--*/

struct{int _rs1;int _rsp;}ssz[]={
{1024,10},{4096,12},{16384,14},{65536,16},{131072,17},{524288,19},{1048576,20},
{4194304,22},{16777216,24},{67108864,26},{268435456,28},{536870912,29}};

RouterStreamImpl::RouterStreamImpl(RouterTraceImpl*pTrace):
	ptrace(pTrace),pstr(0L),varsz(0),varusz(0),rsz(0)
{Allocator();}
RouterStreamImpl::~RouterStreamImpl()
{
	if(pstr)
		MMFree(pstr);
}
int RouterStreamImpl::Allocator()
{return Allocator(varsz+CUSTSIZE);}
int RouterStreamImpl::MoveCursor(int sz)
{
	if(!sz)return 0;
	if(sz>0&&sz>varsz)return 0;
	if(sz<0&&(sz*-1)>varusz)return 0;
	varusz+=sz;
	return varusz;
}
int RouterStreamImpl::GetNewSizeAdj(int sz)
{
	int nsz;
	int prt;

	prt=(rsz++)/MAXREQUEST;
	if(prt>MAXADJSIZE)
		prt=MAXADJSIZE;
	nsz=(sz>>ssz[prt]._rsp)*ssz[prt]._rs1;
	if(sz%ssz[prt]._rs1)
		nsz+=ssz[prt]._rs1;
	return nsz;
}
int RouterStreamImpl::Allocator(int sz,int bsz,int osz)
{
	int asz=bsz;

	sz+=osz;
	if(sz<bsz)
		Allocator();
	else
	{
		asz=GetNewSizeAdj(sz);
		Allocator(asz);
	}
	return 1;
}
int RouterStreamImpl::Allocator(int sz)
{
	void*pv;

	pv=MMAllocator(sz,"RSI_PVpstr");
	if(!pv)
		REI_ERUNTIME("Out of memory",REI_CRITICAL);
	if(varusz&&pstr&&sz<varusz)
		memcpy(pv,pstr,sz);
	else if(varusz&&pstr)
		memcpy(pv,pstr,varusz);
	if(pstr)
		MMFree(pstr);
	pstr=(char*)pv;
	varsz=sz;
	return 1;
}
int RouterStreamImpl::SetText(char*ptxt)
{varusz=0;return AttachText(ptxt,strlen(ptxt),0);}
int RouterStreamImpl::AppendText(char*ptxt)
{return AttachText(ptxt,strlen(ptxt),varusz);}
int RouterStreamImpl::Truncate()
{varusz=0;return 1;}
char*RouterStreamImpl::GetText()
{
	SetEOT();
	return pstr;
}
int RouterStreamImpl::GetLength()
{return varusz;}
int RouterStreamImpl::SetEOT()
{
	if(varsz==varusz)
		Allocator();
	pstr[varusz]=0;
	return 1;
}
int RouterStreamImpl::CheckSpace(int sz,int ps)
{
	if(varsz<(sz+ps))
		Allocator(sz,CUSTSIZE,ps);
	return 1;
}
int RouterStreamImpl::AttachText(char*ptxt,int sz,int ps)
{
	char*pv;
	CheckSpace(sz,ps);
	pv=pstr+ps;
	memcpy(pv,ptxt,sz);
	varusz+=sz;
	return 1;
}
int RouterStreamImpl::SetLength(int sz)
{
	if(sz>varusz||sz<0)
		return 0;
	varusz=sz;
	return 1;
}
int RouterStreamImpl::RTrim()
{
	int tr;

	tr=varusz-1;
	while(*(pstr+tr)==32||*(pstr+tr)==10||*(pstr+tr)==9||*(pstr+tr)==13)tr--;
	varusz=tr+1;
	return 1;
}
int RouterStreamImpl::LTrim()
{
	char*tr,*lmt;
	int trs;

	tr=pstr;
	lmt=pstr+varusz;
	trs=varusz;
	while((*tr==32||*tr==10||*tr==13||*tr==9)&&tr<=lmt){tr++;trs--;}
	if(tr==lmt)
		return 0;
	memcpy(pstr,tr,trs);
	varusz=trs;
	return 1;
}