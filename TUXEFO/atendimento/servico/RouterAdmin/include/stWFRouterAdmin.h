/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:18 $
 **/ 

#ifndef STWFROUTERADMIN
	#define STWFROUTERADMIN

struct st_RouterAdmin
{
	int   idRouterScript;
	char  nmScript[256];
	char* vlScriptSource;
	char  inactive[2];
} ;

struct st_vlRouterAdmin
{
	short idRouterScript;
	short nmScript;
	short vlScriptSource;
	short inactive;
} ;

#endif

