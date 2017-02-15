/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 16:59:41 $
 **/

#ifndef STWFAGRUPAMANTOESTADOTPPROC
	#define STWFAGRUPAMANTOESTADOTPPROC

struct st_AgrupamentoEstadoTpProc
{
	int  idAgrupamentoEstadoTpProc;
	int  idTipoProcesso;
	int  idAgrupamentoEstado;
	int  inEstadoInicial;
	int  nmURLDados;
	int  idAtividade;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[20];
} ;

struct st_vlAgrupamentoEstadoTpProc
{
	short idAgrupamentoEstadoTpProc;
	short idTipoProcesso;
	short idAgrupamentoEstado;
	short inEstadoInicial;
	short nmURLDados;
	short idAtividade;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

