/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATENDIMENTOCAMPODOMINIO
	#define STWFATENDIMENTOCAMPODOMINIO

struct st_AtendimentoCampoDominio
{
	long idAtendimentoCampo;
	int  idDominio;
} ;

struct st_vlAtendimentoCampoDominio
{
	short idAtendimentoCampo;
	short idDominio;
} ;

#endif

