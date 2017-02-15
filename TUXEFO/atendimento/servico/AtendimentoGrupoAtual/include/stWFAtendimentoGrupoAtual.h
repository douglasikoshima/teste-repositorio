/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 
 
#ifndef STWFATENDIMENTOGRUPOATUAL
	#define STWFATENDIMENTOGRUPOATUAL

struct st_AtendimentoGrupoAtual
{
	long idAtendimento;
	int  idGrupo;
	int  idSequencia;
	int  inAssociado;
	int  qtIntercambios;
	int  inCri;
	char dtEntradaBKO[256];
	char dtEntradaFila[256];
	char dtSaida[256];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoGrupoAtual
{
	short idAtendimento;
	short idGrupo;
	short inCri;
	short idSequencia;
	short inAssociado;
	short qtIntercambios;
	short dtEntradaBKO;
	short dtEntradaFila;
	short dtSaida;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

