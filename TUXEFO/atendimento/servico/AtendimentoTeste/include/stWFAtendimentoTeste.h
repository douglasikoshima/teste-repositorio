/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/ 

#ifndef STWFATENDIMENTOTESTE
	#define STWFATENDIMENTOTESTE

struct st_AtendimentoTeste
{
	long idAtendimentoTeste;
	long idAtendimento;
	int  idTesteResposta;
	int  idTeste;
	char dsTeste[501];
	char dsResposta[256];
	char dtTeste[256];
	char dtUltimaAlteracao[256];
	int  idPessoaUsuario;
} ;

struct st_vlAtendimentoTeste
{
	short idAtendimentoTeste;
	short idAtendimento;
	short idTesteResposta;
	short idTeste;
	short dsTeste;
	short dsResposta;
	short dtTeste;
	short dtUltimaAlteracao;
	short idPessoaUsuario;
} ;

#endif

