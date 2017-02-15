/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:09:22 $
 **/

#ifndef STWFATDMSGCRI
	#define STWFATDMSGCRI

struct st_AtdMsgCri
{
	int  idGrupo;
	int  idContato;
	int  idEstado;
	int  idSubEstado;
	int  idPessoaUsuario;
	int  idPessoaUsuarioBko;
	int  idUsuarioGrupo;
	char dtAberturaInicio[11];
	char dtAberturaFim[11];
	char dtFechamentoInicio[11];
	char dtFechamentoFim[11];
	char dtMensagem[256];
	char tipoDocumento[256];
	char documento[256];
	int  inTipoPesquisa;
	int  idAlerta;
	char textoContato[256];
	char pesquisa[256];
	long idAtendimento;
	unsigned long idAndamento;
	char nrLinha[256];
	int  inAbaPesquisa;
	int  tbPausa;
	int  idUsuarioAlteracao;
    int inOrigem;
	char dtUltimaAlteracao[256];
} ;

struct st_vlAtdMsgCri
{
	short idGrupo;
	short idEstado;
	short idContato;
	short idSubEstado;
	short idPessoaUsuario;
	short idPessoaUsuarioBko;
	short idUsuarioGrupo;
	short dtAberturaInicio;
	short dtAberturaFim;
	short dtFechamentoInicio;
	short dtFechamentoFim;
	short dtMensagem;
	short tipoDocumento;
	short documento;
	short inTipoPesquisa;
	short idAlerta;
	short textoContato;
	short pesquisa;
	short idAtendimento;
	short idAndamento;
	short nrLinha;
	short inAbaPesquisa;
	short tbPausa;
	short idUsuarioAlteracao;
	short dtUltimaAlteracao;
    short inOrigem;
} ;

#endif
