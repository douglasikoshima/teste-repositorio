/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:02 $
 **/ 

#ifndef STWFFASE
	#define STWFFASE

struct st_Fase
{
	int  idFase;
	char sgFase[256];
	char dsFase[256];
} ;

struct st_vlFase
{
	short idFase;
	short sgFase;
	short dsFase;
} ;

#endif

