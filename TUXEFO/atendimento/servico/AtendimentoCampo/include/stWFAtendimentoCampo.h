/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATENDIMENTOCAMPO
	#define STWFATENDIMENTOCAMPO

struct st_AtendimentoCampo
{
	long idAtendimentoCampo;
	long idAtendimento;
	int  idCampo;
} ;

struct st_vlAtendimentoCampo
{
	short idAtendimentoCampo;
	short idAtendimento;
	short idCampo;
} ;

#endif

