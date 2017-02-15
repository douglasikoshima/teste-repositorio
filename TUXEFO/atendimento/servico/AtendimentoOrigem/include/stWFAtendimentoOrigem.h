/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFATENDIMENTOORIGEM
	#define STWFATENDIMENTOORIGEM

struct st_AtendimentoOrigem
{
	long idAtendimento;
	long idAtendimentoOrigem;
	int  idTipoReaberturaProcesso;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoOrigem
{
	short idAtendimento;
	short idAtendimentoOrigem;
	short idTipoReaberturaProcesso;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

