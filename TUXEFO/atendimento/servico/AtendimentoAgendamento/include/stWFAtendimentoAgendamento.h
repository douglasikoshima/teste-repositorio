/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATENDIMENTOAGENDAMENTO
	#define STWFATENDIMENTOAGENDAMENTO

struct st_AtendimentoAgendamento
{
	long idAtendimentoAgendamento;
	long idAtendimento;
	long  idAndamento;
	int  idGrupo;
	char dtAtendimentoAgendamento[256];
} ;

struct st_vlAtendimentoAgendamento
{
	short idAtendimentoAgendamento;
	short idAtendimento;
	short idAndamento;
	short idGrupo;
	short dtAtendimentoAgendamento;
} ;

#endif

