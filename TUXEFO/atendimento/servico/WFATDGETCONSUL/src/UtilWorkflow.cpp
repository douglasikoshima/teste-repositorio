// Util.cpp: implementation of the Util class.
//
//////////////////////////////////////////////////////////////////////

#include "../include/UtilWorkflow.h"

//////////////////////////////////////////////////////////////////////
// Construction/Destruction
//////////////////////////////////////////////////////////////////////

UtilWorkflow::UtilWorkflow()
{

}

UtilWorkflow::~UtilWorkflow()
{

}

char*UtilWorkflow::format(char*buf,char*def)
{
	if((buf == NULL || (buf!=NULL && !strcmp(buf,""))) && def!=NULL)
		strcpy(buf,def);
	return buf;
}

char*UtilWorkflow::strnullcpy(char*str,const char*str2)
{
	if(str2!=NULL)
		return strcpy(str,str2);
	else
		return NULL;
}
