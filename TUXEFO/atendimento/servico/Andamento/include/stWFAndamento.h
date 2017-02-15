/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.4.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:02:23 $
 **/

#ifndef STWFANDAMENTO
	#define STWFANDAMENTO
 
struct st_Andamento
{
	long  idAndamento;
	int   idAtividade;
	int   idAgrupamentoEstadoTpProc;
	long idAtendimento;
	int   idEstado;
	int   idSubEstado;
	int   idPessoaUsuario;
	char  dtAndamento[64];
	int   idGrupo;
	int   idUsuarioAlteracao;
	char  dtUltimaAlteracao[64];
    int   inCRI;
} ;

struct st_AndamentoEx
{
	long  idAndamento;
	int  idAtividade;
	int  idAgrupamentoEstadoTpProc;
	long idAtendimento;
	char idEstado[256];
	char idSubEstado[256];
	int  idPessoaUsuario;
	char dtAndamento[64];
	int  idGrupo;
	int  idUsuarioAlteracao;
	char dtUltimaAlteracao[64];
    int inCRI;
} ;

struct st_vlAndamento
{
	short idAndamento;
	short idAtividade;
	short idAgrupamentoEstadoTpProc;
	short idAtendimento;
	short idEstado;
	short idSubEstado;
	short idPessoaUsuario;
	short dtAndamento;
	short idGrupo;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
    int inCRI;
} ;

#endif

