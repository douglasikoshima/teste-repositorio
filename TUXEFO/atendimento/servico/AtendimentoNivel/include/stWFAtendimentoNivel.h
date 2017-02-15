/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFATENDIMENTONIVEL
	#define STWFATENDIMENTONIVEL

struct st_AtendimentoNivel
{
	long idAtendimentoNivel;
	long idAtendimento;
	int  idGrupo;
	int  idFase;
	int  idAtividade;
	int  idContato;
	int  idSequencia;
    int  inConcluido;
	int  nrNivel;
	char dtNivel[20];
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoNivel
{
	short  idAtendimentoNivel;
	short  idAtendimento;
	short  idGrupo;
	short  idFase;
	short  idAtividade;
	short  idContato;
	short  idSequencia;
    short  inConcluido;
	short  nrNivel;
	short  dtNivel;
	short  idUsuarioAlteracao;
	short  dtUltimaAlteracao;
} ;

#endif

