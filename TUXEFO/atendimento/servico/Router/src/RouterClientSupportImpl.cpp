#ifdef CVS
#include<RouterClass.h>
#else
#include"../include/RouterClass.h"
#endif

/*++
Module Name:
    RouterClientSupportImpl.cpp

Abstract:
	Auxiliary client (so) routines

Author:
    Ivan Mentone 2004-06-29

Environment:
    Router Core

Revision History:
	

--*/

int Validate(PARAMS*parm,PARMLST*pl,PARMLST*pr,ACCUM*ac,int pls)
{
	int i;

	if(!ac&&pr[0].vartype!=AC_NONE)
		REI_ERUNTIME("This function have a return",REI_ERROR);
	if(ac&&ac->vartype!=pr[0].vartype)
		REI_ERUNTIME("Invalid return type",REI_ERROR);
	for(i=0;i<pls;i++)
	{
		if(!parm->parmlist[i]||parm->parmlist[i]->vartype!=pl[i].vartype)
			return 0;
		pl[i].accum=parm->parmlist[i];
	}
	return 1;
}