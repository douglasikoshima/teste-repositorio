/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFATENDIMENTOUSUARIOATUAL
	#define STWFATENDIMENTOUSUARIOATUAL

struct st_AtendimentoUsuarioAtual
{
	long idAtendimento;
	int  idPessoaUsuario;
	long  inPausaAtendimento;
	int  idMotivoPausaAtendimento;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
	char dtFimPausaAtendimento[256];
} ;

struct st_vlAtendimentoUsuarioAtual
{
	short idAtendimento;
	short idPessoaUsuario;
	short inPausaAtendimento;
	short idMotivoPausaAtendimento;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
	short dtFimPausaAtendimento;
} ;

#endif

