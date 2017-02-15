/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/

#ifndef STWFAGRUPAMENTOESTADOATIVIDADE
	#define STWFAGRUPAMENTOESTADOATIVIDADE

struct st_AgrupamentoEstadoAtividade
{
	int  idAgrupamentoEstadoAtividade;
	int  idAtividade;
	int  idAgrupamentoEstado;
} ;

struct st_vlAgrupamentoEstadoAtividade
{
	short idAgrupamentoEstadoAtividade;
	short idAtividade;
	short idAgrupamentoEstado;
} ;

#endif

