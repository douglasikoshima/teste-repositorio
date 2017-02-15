/*++
Module Name:
    RouterXMLImpl.cpp

Abstract:
	Extends XMLGen class to support direct text attach

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	2004-07-29 - Ivan Mentone - change header file to new framework

--*/

#include<tuxfw.h>

class RouterXMLImpl:public XMLGen
{
public:
	int AddSpace();
	int AttachText(char*);
};

#include<stdio.h>
#include<stdlib.h>
#include<string>

using namespace std;

typedef string*pstring;

int RouterXMLImpl::AddSpace()
{
	return 1;
}
int RouterXMLImpl::AttachText(char*pstr)
{
	if(!pstr)
		return 0;
	if(t_op){append(">\n");t_op=0;}
	AddSpace();
	append(pstr);
	append("\n");
	return 1;
}
