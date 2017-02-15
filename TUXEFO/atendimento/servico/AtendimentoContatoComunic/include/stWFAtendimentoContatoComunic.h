/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:36:29 $
 **/

#ifndef STWFATENDIMENTOCONTATOCOMUNIC
	#define STWFATENDIMENTOCONTATOCOMUNIC

struct st_AtendimentoContatoComunic
{
	long idAtendimentoContatoComunic;
	long idAtendimento;
	char dsComunicacao[256];
	int  nrOrdemUtilizacao;
	int  idTipoComunicacao;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoContatoComunic
{
	short idAtendimentoContatoComunic;
	short idAtendimento;
	short dsComunicacao;
	short nrOrdemUtilizacao;
	short idTipoComunicacao;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

