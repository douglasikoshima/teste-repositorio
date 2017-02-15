/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:07:13 $
 **/


#ifndef STWFANDAMENTOTRANSFERENCIA
	#define STWFANDAMENTOTRANSFERENCIA

struct st_AndamentoTransferencia
{
	long idAtendimento;
	long idAndamento;
	int  idPessoaUsuario;
	int idMotivoEncaminhamento;
	char dtInicioTransferencia[256];
	char dtFinTransferencia[256];
	int idUsuarioAlteracao;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAndamentoTransferencia
{
	short  idAtendimento;
	short  idAndamento;
	short  idPessoaUsuario;
	short  idMotivoEncaminhamento;
	short dtInicioTransferencia;
	short dtFinTransferencia;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
} ;

#endif

