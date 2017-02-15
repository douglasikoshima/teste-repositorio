/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#ifndef STWFCANCELAMENTOSOLICITADO
	#define STWFCANCELAMENTOSOLICITADO

struct st_CancelamentoSolicitado
{
	long idAtendimento;
	char dtSolicitacaoCancelamento[256];
	int  idPessoaUsuario;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlCancelamentoSolicitado
{
	short idAtendimento;
	short dtSolicitacaoCancelamento;
	short idPessoaUsuario;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

