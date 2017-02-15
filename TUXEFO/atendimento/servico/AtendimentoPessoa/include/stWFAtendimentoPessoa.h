/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFATENDIMENTOPESSOA
	#define STWFATENDIMENTOPESSOA

struct st_AtendimentoPessoa
{
	long idAtendimentoPessoa;
	long idAtendimento;
	int  idPessoaDePara;
	int  idTipoRelacionamento;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtendimentoPessoa
{
	short idAtendimentoPessoa;
	short idAtendimento;
	short idPessoaDePara;
	short idTipoRelacionamento;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

struct st_AtendimentoConsultaPessoa
{
	long  idPessoa;
} ;

struct st_vlAtendimentoConsultaPessoa
{
	short idPessoa;
} ;


struct AtendimentoPessoa
{
    long idPessoa;
    int idPessoaDePara;
    int inRspAbertura;
    int idTipoPessoa;
    char sgTipoPessoa[256];
    char nome[256];
    char dtCadastro[256];
} ;


#endif

