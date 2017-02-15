/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#ifndef STWFATENDIMENTOFRMCAMPOVALOR
	#define STWFATENDIMENTOFRMCAMPOVALOR

struct st_AtendimentoFrmCampoValor
{
	long idAtendimento;
	long idAtendimentoFrmCampoValor;
	long idAtendimentoFrmCampo;
	char valor[256];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoFrmCampoValor
{
	short idAtendimento;
	short idAtendimentoFrmCampoValor;
	short idAtendimentoFrmCampo;
	short valor;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

