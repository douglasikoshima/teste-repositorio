/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:33:00 $
 **/

#ifndef STWFATENDIMENTOBAIXAHISTORICO
#define STWFATENDIMENTOBAIXAHISTORICO

struct st_AtendimentoBaixaHistorico
{
	long idAtendimentoBaixaHistorico;
	long idAtendimento;
	int  idBaixa;
	int  idFase;
	long  idAndamento;
	int  idBaixaMensagem;
	char dtBaixa[256];
	int  idPessoaUsuario;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoBaixaHistorico
{
	short idAtendimentoBaixaHistorico;
	short idAtendimento;
	short idBaixa;
	short idFase;
	short idAndamento;
	short idBaixaMensagem;
	short dtBaixa;
	short idPessoaUsuario;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

