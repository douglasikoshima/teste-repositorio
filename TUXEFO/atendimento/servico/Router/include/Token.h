#ifndef TOKEN
#define TOKEN

#ifdef __cplusplus
extern"C"{
#endif

#include<stdlib.h>
#include<stdio.h>

typedef char**HTOKEN;
typedef struct tagRETITEM
{
	char*pstr;
	int ind;
}RETITEM,*PRETITEM;
#define FIRSTITEM(VAR) {VAR.pstr=0L;VAR.ind=0;}

HTOKEN MakeToken(char*,char,int*);
char*GetNextToken(PRETITEM,HTOKEN);
int DestroyToken(HTOKEN);

#ifdef __cplusplus
}
#endif

#endif
