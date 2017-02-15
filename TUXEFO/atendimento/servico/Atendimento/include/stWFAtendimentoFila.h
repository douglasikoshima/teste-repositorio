/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef STWFATENDIMENTOFILA
	#define STWFATENDIMENTOFILA

#include <tuxfw.h>
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
	int  inPessoa;
	char dtAberturaInicio[32];
	char dtAberturaFim[32];
	char dtFechamentoInicio[32];
	char dtFechamentoFim[32];
	char dtSuspeitoInicio[32];
	char dtSuspeitoFim[32];
	char pesquisa[256];
	char sgTipoPesquisa[32];
	char nrDocumento[64];
	int  tpPesquisa;
	int  idPessoaDePara;
	int  tpRelacionamento;
	long  idPessoaLinhaHistorico;
	char textoContato[512];
	long idAtendimento;
	char nrLinha[512];
	char nmLoginUsuario[256];
	char dsAndamentoObservacao[256];
	DOMNode *pesquisaDinamica;
	Collection *gruposUsuario;
	int  inPrimeiraChamada;
	int  tbPausa;
    int inTipoPesquisa;
	int inFilaAbertos;
	int idUfOperadora;
	int inTotalRegistros;
	char idAtendimentoProtocolo[64];
    char nrProtocoloPortabilidade[64];
    char nrConta[101];
    int idAlerta;
    int idTipoCarteira;
    int idSegmentacao;
    char idClassificacaoTipoLinha[21];
    char dsStatusProtocolo[256];
    char nrPesoHierarquia[32];
    char idFornecedorConsultorAtd[32];
    char idPerfilConsultorAtd[32];
    int inPesquisaFullMC;
};

struct st_vlAtendimentoFila
{
	short idGrupo;
	short idEstado;
	short idContato;
	short idSubEstado;
	short idPessoaUsuario;
	short idUsuarioGrupo;
	short nrCampos;
	short inPessoa;
	short dtAberturaInicio;
	short dtAberturaFim;
	short dtFechamentoInicio;
	short dtFechamentoFim;
	short dtSuspeitoInicio;
	short dtSuspeitoFim;
	short pesquisa;
	short sgTipoPesquisa;
	short nrDocumento;
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
    short inTipoPesquisa;
	short inFilaAbertos;
	short idUfOperadora;
	short inTotalRegistros;
	short idAtendimentoProtocolo;
    short nrProtocoloPortabilidade;
    short nrConta;
    short idAlerta;
    short idTipoCarteira;
    short idSegmentacao;
    short idClassificacaoTipoLinha;
    short dsStatusProtocolo;
    short nrPesoHierarquia;
    short idFornecedorConsultorAtd;
    short idPerfilConsultorAtd;
    short inPesquisaFullMC;
};

#endif

