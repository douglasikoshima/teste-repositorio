/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:02 $
 **/ 

#ifndef STWFFLUXOFUNCAO
	#define STWFFLUXOFUNCAO

struct st_FluxoFuncao
{
	int  idFluxoFuncao;
	int  idFluxoFase;
	int  numOrdem;
	int  idRouterScript;
} ;

struct st_vlFluxoFuncao
{
	short idFluxoFuncao;
	short idFluxoFase;
	short numOrdem;
	short idRouterScript;
} ;

#endif

