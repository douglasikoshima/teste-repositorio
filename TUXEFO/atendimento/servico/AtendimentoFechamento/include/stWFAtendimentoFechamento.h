/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef STWFATENDIMENTOFECHAMENTO
	#define STWFATENDIMENTOFECHAMENTO

struct st_AtendimentoFechamento
{
	long idAtendimento;
	int  idResultadoFechamento;
	long  idAndamento;
	char dtFechamento[256];
	int  idPessoaUsuario;
} ;

struct st_vlAtendimentoFechamento
{
	short idAtendimento;
	short idResultadoFechamento;
	short idAndamento;
	short dtFechamento;
	short idPessoaUsuario;
} ;

#endif

