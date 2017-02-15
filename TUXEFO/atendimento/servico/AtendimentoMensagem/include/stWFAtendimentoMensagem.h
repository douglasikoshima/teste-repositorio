/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFATENDIMENTOMENSAGEM
	#define STWFATENDIMENTOMENSAGEM

struct st_AtendimentoMensagem
{
	long idAtendimentoMensagem;
	long idAtendimento;
	int  idAtividade;
	long  idMensagemAtendimento;
	char dtMensagem[256];
	
} ;

struct st_vlAtendimentoMensagem
{
	short idAtendimentoMensagem;
	short idAtendimento;
	short idAtividade;
	short idMensagemAtendimento;
	short dtMensagem;
} ;

#endif

