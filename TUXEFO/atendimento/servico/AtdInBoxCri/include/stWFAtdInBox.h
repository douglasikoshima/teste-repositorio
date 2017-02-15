/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATDINBOX
	#define STWFATDINBOX

struct st_AtdInBox
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
	char tipoDocumento[256];
	char documento[256];
	int  inTipoPesquisa;
	int  idAlerta;
	char textoContato[256];
	char pesquisa[256];
	long idAtendimento;
	char nrLinha[256];
	int  inAbaPesquisa;
	int  tbPausa;
} ;

struct st_vlAtdInBox
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
	short tipoDocumento;
	short documento;
	short inTipoPesquisa;
	short idAlerta;
	short textoContato;
	short pesquisa;
	short idAtendimento;
	short nrLinha;
	short inAbaPesquisa;
	short tbPausa;
} ;


#endif

