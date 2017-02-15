/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATENDIMENTOCAMPOVALORLIVRE
	#define STWFATENDIMENTOCAMPOVALORLIVRE

struct st_AtendimentoCampoValorLivre
{
	long idAtendimentoCampo;
	char dsValor[256];
} ;

struct st_vlAtendimentoCampoValorLivre
{
	short idAtendimentoCampo;
	short dsValor;
} ;

#endif

