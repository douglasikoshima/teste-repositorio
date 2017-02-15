#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterSvcMan.cpp

Abstract:
	RouterService manager

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterSvcMan::RouterSvcMan():src(0L)
{}
RouterSvcMan::~RouterSvcMan()
{
	if(src)
		MMFree(src);
}
int RouterSvcMan::Parse(DOMNode*dn)
{
	char*ptype;
	char*desc;

	ptype=walkAttr(dn,"script","type",0);
	if(!ptype)
		throw new TuxBasicSvcException("00E9999","Invalid script tag");
	switch(*ptype)
	{
	case 'F':
		{
			FILE*fo;
			int sz;
			
			desc=walkAttr(dn,"script","fileid",0);
			if(!desc)
				throw new TuxBasicSvcException("00E9999","Cannot find fileid attribute");
			fo=fopen(desc,"rb");
			if(!fo)
				throw new TuxBasicSvcException("00E9999","Invalid filename");
			fseek(fo,0,SEEK_END);
			sz=ftell(fo);
			fseek(fo,0,SEEK_SET);
			if(!sz)
				throw new TuxBasicSvcException("00E9999","Invalid script file (zero length)");
			src=(char*)MMAllocator(sz+1,"FILESRC");
			src[fread(src,1,sz,fo)]=0;
			fclose(fo);
		}
		break;
	case 'D':
		{
			desc=walkAttr(dn,"script","dbid",0);
			if(!desc)
				throw new TuxBasicSvcException("00E9999","Cannot find dbid attribute");
			if(!QueryDB(&src,atoi(desc)))
				throw new TuxBasicSvcException("00E9999","Invalid script ID");
		}
		break;
	case 'X':
		{
			desc=walkAttr(dn,"script","source",0);
			if(!desc)
				throw new TuxBasicSvcException("00E9999","Cannot find script source");
			src=(char*)MMAllocator(strlen(desc)+1,"XMLSRC");
			strcpy(src,desc);
		}
		break;
	case 'B':
		{
			desc=walkTree(dn,"script",0);
			if(!desc)
				throw new TuxBasicSvcException("00E9999","Cannot find script source");
			src=(char*)MMAllocator(strlen(desc)+1,"BODYSRC");
			strcpy(src,desc);
            XMLString::release(&desc);
		}
		break;
	default:
		throw new TuxBasicSvcException("00E9999","Invalid script type");
	}
	return 1;
}
char*RouterSvcMan::RetrieveSource()
{return src;}