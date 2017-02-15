#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterLogImpl.cpp

Abstract:
	Implements Router Core basic log

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

RouterLogImpl::RouterLogImpl()
{}
RouterLogImpl::~RouterLogImpl()
{}
int RouterLogImpl::WriteLog(char*pmsg)
{
	PrintHeader();
	PrintText(pmsg);
	return 1;
}
