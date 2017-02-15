/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 
 
#ifndef STWFATDTRATAMENTOCRI
	#define STWFATDTRATAMENTOCRI

struct st_AtdTratamentoCri
{
	long idAtendimento;
	int  idGrupo;
	int  idPessoaUsuario;
	int  idGrupoAtual;
	long idPessoaUsuarioAtual;
	char dtAbertura[256];
	long  idPessoaLinhaHistorico;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtdTratamentoCri
{
	short idAtendimento;
	short idGrupo;
	short idPessoaUsuario;
	short idGrupoAtual;
	short idPessoaUsuarioAtual;
	short dtAbertura;
	short idPessoaLinhaHistorico;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

