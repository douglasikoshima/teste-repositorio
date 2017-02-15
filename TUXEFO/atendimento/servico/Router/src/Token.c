#ifdef CVS
#include<Token.h>
#else
#include"../include/Token.h"
#endif

/*TR2*/
extern char*strchr(const char*,int);
extern size_t strlen(const char*);
/*++
Module Name:
    Token.c

Abstract:
	Implements TOKEN

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

char*FindFromCodeC(char*pstr,char*pfnd)
{
	int bc=0;
	int i,ln,ind=0;

	ln=strlen(pfnd);
	while(*pstr)
	{
		if(*pstr==34&&*(pstr-1)!=92)
			bc++;
		else if(!(bc%2))
		{
			ind=1;
			for(i=0;i<ln;i++)
			{
				if(pstr[i]!=pfnd[i])
				{
					ind=0;
					break;
				}
			}
		}
		if(ind)
			break;
		pstr++;
	}
	if(!ind)
		return 0L;
	return pstr;
}
HTOKEN MakeToken(char*pstr,char sep,int*pi)
{
	int ind,sind;
	HTOKEN pMap;
	char*st1,sp[2]={sep,0};

	ind=0;
	st1=pstr;
	while(st1)
	{
		ind++;
		st1=FindFromCodeC(*st1==sep?st1+1:st1,sp);
	}
	pMap=(char**)malloc(sizeof(char*)*(ind+2));
	pMap[0]=pstr;

	sind=1;
	st1=pstr;
	while(st1)
	{
		pMap[sind++]=st1;
		st1=FindFromCodeC(*st1==sep?st1+1:st1,sp);
		if(st1)
			*st1++=0;
	}
	pMap[ind+1]=0L;
	if(pi)
		*pi=ind;
	return pMap;
}
char*GetNextToken(PRETITEM pri,HTOKEN pMap)
{
	pri->ind++;
	pri->pstr=pMap[pri->ind];
	return pri->pstr;
}
int DestroyToken(HTOKEN pmap)
{
	free(pmap);
	return 1;
}