/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:36 $
 **/

#ifndef STWFATENDIMENTOFILA
	#define STWFATENDIMENTOFILA

#include "../../../commons/Collection/include/Collection.h"

struct st_AtendimentoFila
{
	int  idGrupo;
	int  idContato;
	int  idEstado;
	int  idSubEstado;
	int  idPessoaUsuario;
	int  idUsuarioGrupo;
	int  nrCampos;
	char dtAberturaInicio[11];
	char dtAberturaFim[11];
	char dtFechamentoInicio[11];
	char dtFechamentoFim[11];
	char dtSuspeitoInicio[11];
	char dtSuspeitoFim[11];
	char pesquisa[256];
	int  tpPesquisa;
	int  idPessoaDePara;
	int  tpRelacionamento;
	long  idPessoaLinhaHistorico;
	char textoContato[256];
	long idAtendimento;
	char nrLinha[256];
	char nmLoginUsuario[256];
	char dsAndamentoObservacao[256];
	DOMNode*    pesquisaDinamica;
	Collection* gruposUsuario;
	int  inPrimeiraChamada;
	int  tbPausa;
} ;

struct st_vlAtendimentoFila
{
	short idGrupo;
	short idEstado;
	short idContato;
	short idSubEstado;
	short idPessoaUsuario;
	short idUsuarioGrupo;
	short nrCampos;
	short dtAberturaInicio;
	short dtAberturaFim;
	short dtFechamentoInicio;
	short dtFechamentoFim;
	short dtSuspeitoInicio;
	short dtSuspeitoFim;
	short pesquisa;
	short tpPesquisa;
	short idPessoaDePara;
	short tpRelacionamento;
	short idPessoaLinhaHistorico;
	short textoContato;
	short idAtendimento;
	short nrLinha;
	short nmLoginUsuario;
	short dsAndamentoObservacao;
	short pesquisaDinamica;
	short gruposUsuario;
	short inPrimeiraChamada;
	short tbPausa;
} ;

#endif

