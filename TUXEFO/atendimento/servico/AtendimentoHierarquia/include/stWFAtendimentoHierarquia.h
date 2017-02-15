/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 

#ifndef STWFATENDIMENTOHIERARQUIA
	#define STWFATENDIMENTOHIERARQUIA

struct st_AtendimentoHierarquia
{
	long idAtendimento;
	long idAtendimentoPai;
} ;

struct st_vlAtendimentoHierarquia
{
	short idAtendimento;
	short idAtendimentoPai;
} ;

#endif

