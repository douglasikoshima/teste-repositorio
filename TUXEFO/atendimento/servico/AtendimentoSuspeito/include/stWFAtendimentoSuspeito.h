/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#ifndef STWFATENDIMENTOSUSPEITO
	#define STWFATENDIMENTOSUSPEITO

struct st_AtendimentoSuspeito
{
	long idAtendimentoSuspeito;
	char dtSuspeito[256];
	long idAtendimento;
	int  idPessoaUsuario;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
	int  idMotivo;
} ;

struct st_vlAtendimentoSuspeito
{
	short idAtendimentoSuspeito;
	short dtSuspeito;
	short idAtendimento;
	short idPessoaUsuario;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
	short idMotivo;
} ;

#endif

