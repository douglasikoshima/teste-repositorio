/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:05:25 $
 **/


#ifndef STWFANDAMENTOOBSERVACAO
	#define STWFANDAMENTOOBSERVACAO

struct st_AndamentoObservacao
{
	long idAndamento;
	char dsAndamentoObservacao[1001];
    const char *pdsAndamentoObservacao;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAndamentoObservacao
{
	short idAndamento;
	short dsAndamentoObservacao;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

