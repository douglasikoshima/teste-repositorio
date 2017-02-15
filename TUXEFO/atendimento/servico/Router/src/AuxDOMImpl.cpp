#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    AuxDOMImpl.cpp

Abstract:
	Implements auxiliary class to manage DOMNodes

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-07 - Ivan Mentone - Add new features CleanAndDestroy and SetText

--*/

AuxDOMImpl::AuxDOMImpl(char*pxml):xml_p(0L),mbis(0L),pnode(0L)
{SetText(pxml);}
AuxDOMImpl::AuxDOMImpl(DOMNode*pdn):xml_p(0L),mbis(0L)
{pnode=pdn;}
AuxDOMImpl::~AuxDOMImpl()
{
	CleanAndDestroy();
}
int AuxDOMImpl::SetText(char*pxml)
{
	CleanAndDestroy();
	xml_p=new XercesDOMParser();
	if(!xml_p)
		return 0;
	try
	{
		mbis=new MemBufInputSource((const XMLByte*)pxml,strlen(pxml)+1,(const char*)prvni->VarName(0));
		xml_p->parse(*mbis);
		pnode=xml_p->getDocument();
	}
	catch(...)
	{CleanAndDestroy();}
	return 1;
}
int AuxDOMImpl::CleanAndDestroy()
{
	if(mbis){delete mbis;mbis=0L;}
	if(xml_p){delete xml_p;xml_p=0L;}
	return 1;
}
int AuxDOMImpl::Bad()
{
	if(!pnode)
		return 1;
	return 0;
}
int AuxDOMImpl::SetRoot(char*node)
{
	DOMNode*newNode;

	if(Bad())
		return 0;
	newNode=walkDOM(pnode,node);
	if(!newNode)
		return 0;
	pnode=newNode;
	return 1;
}
DOMNode*AuxDOMImpl::RetrieveDOM()
{return pnode;}
int AuxDOMImpl::AttachDOM(DOMNode*node)
{
	pnode=node;
	return 1;
}