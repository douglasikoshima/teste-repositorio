/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:32:05 $
 **/

#ifndef STWFATENDIMENTOBAIXAATUAL
	#define STWFATENDIMENTOBAIXAATUAL

struct st_AtendimentoBaixaAtual
{
	long idAtendimento;
	long idAtendimentoBaixaHistorico;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoBaixaAtual
{
	short idAtendimento;
	short idAtendimentoBaixaHistorico;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

