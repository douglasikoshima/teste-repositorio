/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.8.6.5 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:12:21 $
 **/

#ifndef STWFATENDIMENTO
    #define STWFATENDIMENTO

struct st_Atendimento
{
    char operacao[64];
    char PsqNrProtocolo[64];
    long idAtendimento;
    char dtAbertura[64];
    int  idContato;
    char dtPrazoFinalInterno[64];
    int  qtInsistencia;
    int  inAlarme;
    int  idCanal;
    int  idProcedencia;
    int  idTipoCarteira;
    int  idSegmentacao;
    long  idPessoaUsuarioAbertura;
    int  idGrupoAbertura;
    char dtPrazoFinalAnatel[64];
    int  idUsuarioAlteracao;
    char dtUltimaAlteracao[64];
    int  iPrazoLimite;
    int  nrNivel;
    int  idFase;
    int  qtHorasPrazoAtendimento;
    char vlPesoAtendimento[512];
    int  idTipoRetornoContato;
    int  idSequencia;
    char dtPrazoFinalCPrevio[64];
    int idTipoAbertura;
    int cdAreaRegistro;
    char dtFechamento[64];
    int idAgrupamentoEstadoTpProc;
    long idAndamentoAtual;
    int idGrupoAtual;
    int idLinhaTelefonica;
    int idEstadoLinha;
    long idPessoaLinhaHistorico;
    long idPessoaUsuarioAtual;
    int idTipoLinha;
    int idUFOperadora;
    int idPessoaConta;
    char idAtendimentoProtocolo[64];
    char sgTipoPortabilidade[32];
    char sgOperadoraSolicitante[64];
    char nrProtocoloPortabilidade[32];
    char dtJanelaPortout[64];
    char dtUltimaOperacaoExcFila[64];
    char sgRegraEncaminhamento[8];
    char sgFluxoAtendimento[8];
    char idPerfilConsultorAtd[64];
    char idFornecedorConsultorAtd[64];
    char idSiteConsultorAtd[64];
    char nrOrdemVenda[32];
} ;

struct st_vlAtendimento
{
    short operacao;
    short PsqNrProtocolo;
    short idAtendimento;
    short dtAbertura;
    short idContato;
    short dtPrazoFinalInterno;
    short qtInsistencia;
    short inAlarme;
    short idCanal;
    short idProcedencia;
    short idTipoCarteira;
    short idSegmentacao;
    short idPessoaUsuarioAbertura;
    short idGrupoAbertura;
    short dtPrazoFinalAnatel;
    short idUsuarioAlteracao;
    short dtUltimaAlteracao;
    short iPrazoLimite;
    short nrNivel;
    short idFase;
    short qtHorasPrazoAtendimento;
    short vlPesoAtendimento;
    short idTipoRetornoContato;
    short idSequencia;
    short dtPrazoFinalCPrevio;
    short idTipoAbertura;
    short cdAreaRegistro;
    short dtFechamento;
    short idAgrupamentoEstadoTpProc;
    short idAndamentoAtual;
    short idGrupoAtual;
    short idLinhaTelefonica;
    short idEstadoLinha;
    short idPessoaLinhaHistorico;
    short idPessoaUsuarioAtual;
    short idTipoLinha;
    short idUFOperadora;
    short idPessoaConta;
    short idAtendimentoProtocolo;
    short sgTipoPortabilidade;
    short sgOperadoraSolicitante;
    short nrProtocoloPortabilidade;
    short dtJanelaPortout;
    short dtUltimaOperacaoExcFila;
    short sgRegraEncaminhamento;
    short sgFluxoAtendimento;
    short idPerfilConsultorAtd;
    short idFornecedorConsultorAtd;
    short idSiteConsultorAtd;
    short nrOrdemVenda;
} ;

struct DadosAtendimento
{
    char operacao[64];
    char PsqNrProtocolo[64];
    char dtAbertura[64];
    char dtPrazoFinalAnatel[64];
    char dtPrazoFinalInterno[64];
    char dtUltimaAlteracao[64];
    char dtPrazoFinalCPrevio[64];
    long idAtendimento;
    int idCanal;
    int idContato;
    //int idDiscador;
    int idFase;
    int idGrupoAbertura;
    long idPessoaUsuarioAbertura;
    int idProcedencia;
    int idSegmentacao;
    int idTipoCarteira;
    int idTipoRetornoContato;
    int idUsuarioAlteracao;
    int inAlarme;
    int nrNivel;
    int qtHorasPrazoAtendimento;
    int qtInsistencia;
    char vlPesoAtendimento[512];
    int idUFOperadora;
} ;

struct st_DadosAtendimento
{
    short operacao;
    short PsqNrProtocolo;
    short dtAbertura;
    short dtPrazoFinalAnatel;
    short dtPrazoFinalInterno;
    short dtUltimaAlteracao;
    short dtPrazoFinalCPrevio;
    short idAtendimento;
    short idCanal;
    short idContato;
    //short idDiscador;
    short idFase;
    short idGrupoAbertura;
    short idPessoaUsuarioAbertura;
    short idProcedencia;
    short idSegmentacao;
    short idTipoCarteira;
    short idTipoRetornoContato;
    short idUsuarioAlteracao;
    short inAlarme;
    short nrNivel;
    short qtHorasPrazoAtendimento;
    short qtInsistencia;
    short vlPesoAtendimento;
    short idUFOperadora;
};

typedef struct
{
    char dataAbertura[256];
    int idContato;
    char dataPrazoFinalInterno[256];
    int idTipoRetornoContato;
    int idCanal;
    char nmCanal[256];
    int idProcedencia;
    char dsProcedencia[256];
    int idSegmentacao;
    char dsSegmentacao[256];
    //int idDiscador;
    int idFase;
    long idPessoaUsuarioAbertura;
    char nmLoginUsuario[256];
    int idGrupoAbertura;
    char nmGrupoAbertura[256];
    int idGrupoAtual;
    long idPessoaUsuarioAtual;
    int idTipoCarteira;
    char dsTipoCarteira[256];
    int nrNivel;
    int qtInsistencia;
    int idAgrEstadoTProc;
    int idEstado;
    char dsEstado[256];
    int idSubEstado;
    char dsSubEstado[256];
    char cdConta[256];
    char nrLinha[256];
    long idAtendimentoBaixaHistorico;
    long idAtendimentoOrigem;
    char dataFechamento[256];
    int idGrupoAndamento;
    int inCri;
    char dtPrazoFinalCPrevio[256];
    int idTipoAbertura;
    long idPessoa;
    int idTipoRelacionamento;
    int idUFOperadora;
    int idUsuarioAlteracao;
    char idAtendimentoProtocolo[64];
    long idPessoaLinhaHistorico;
    int idLinhaTelefonica;
    char sgOperadoraSolicitante[31];
    char nrProtocoloPortabilidade[64];
    char dtJanelaPortout[32];
    char sgTipoPortabilidade[16];
    char idTipoLinha[64];
    char sgRegraEncaminhamento[32];
    char sgFluxoAtendimento[32];
    char idPerfilConsultorAtd[64];
    char idFornecedorConsultorAtd[64];
    char idSiteConsultorAtd[64];
    int idPessoaUsuarioMC;
    int idGrupoMC;
} DetalheAtendimento;


typedef struct
{
    short i_dataAbertura;
    short i_idContato;
    short i_dataPrazoFinalInterno;
    short i_idTipoRetornoContato;
    short i_idCanal;
    short i_nmCanal;
    short i_idProcedencia;
    short i_dsProcedencia;
    short i_idSegmentacao;
    short i_dsSegmentacao;
    //short i_idDiscador;
    short i_idFase;
    short i_idPessoaUsuarioAbertura;
    short i_nmLoginUsuario;
    short i_idGrupoAbertura;
    short i_nmGrupoAbertura;
    short i_idGrupoAtual;
    short i_idPessoaUsuarioAtual;
    short i_idTipoCarteira;
    short i_dsTipoCarteira;
    short i_nrNivel;
    short i_qtInsistencia;
    short i_idAgrEstadoTProc;
    short i_idEstado;
    short i_dsEstado;
    short i_idSubEstado;
    short i_dsSubEstado;
    short i_cdConta;
    short i_nrLinha;
    short i_idAtendimentoBaixaHistorico;
    short i_idAtendimentoOrigem;
    short i_dataFechamento;
    short i_idGrupoAndamento;
    short i_inCri;
    short i_dtPrazoFinalCPrevio;
    short i_idTipoAbertura;
    short i_idPessoa;
    short i_idTipoRelacionamento;
    short i_idUFOperadora;
    short i_idUsuarioAlteracao;
    short i_idAtendimentoProtocolo;
    short i_idPessoaLinhaHistorico;
    short i_idLinhaTelefonica;
    short i_sgOperadoraSolicitante;
    short i_nrProtocoloPortabilidade;
    short i_dtJanelaPortout;
    short i_sgTipoPortabilidade;
    short i_idTipoLinha;
    short i_sgRegraEncaminhamento;
    short i_sgFluxoAtendimento;
    short i_idPerfilConsultorAtd;
    short i_idFornecedorConsultorAtd;
    short i_idSiteConsultorAtd;
    short i_idPessoaUsuarioMC;
    short i_idGrupoMC;
} StatusDetalheAtendimento;

// Estrutura com as variáveis da aba 'retorno' da árvore de contato
struct VariaveisRetorno
{
    int idCanal;
    int idContato;
    int idGrupo;
    int idProcedencia;
    int idSegmentacao;
    int idTipoCarteira;
    int idTipoLinha;
    int idTipoPessoa;
    int idTipoRelacionamento;
    int idUFOperadora;
};

struct DetReabertura
{
  char idTipoReaberturaProcesso[256];
  char nmTipo[256];
};

#endif

