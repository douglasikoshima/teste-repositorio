/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:29:42 $
 **/

#ifndef STWFATENDIMENTOANDAMENTOATUAL
	#define STWFATENDIMENTOANDAMENTOATUAL

struct st_AtendimentoAndamentoAtual
{
	long idAtendimento;
	long  idAndamento;
	int  idUsuarioAlteracao;
    int  idAgrupamentoEstadoTpProc;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoAndamentoAtual
{
	short idAtendimento;
	short idAndamento;
	short idUsuarioAlteracao;
    short idAgrupamentoEstadoTpProc;
	short dtUltimaAlteracao;
} ;

#endif

