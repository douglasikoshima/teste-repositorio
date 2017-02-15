/**
 * @modulo  Atendimento
 * @usecase 
 * @author  Roberto
 * @version $Revision: 1.1.2.17.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/ 

#ifndef __CWF_ACAO_PC_H__
#define __CWF_ACAO_PC_H__

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>

#include "../../../commons/TString.h"

#include <tuxfw.h>
#include "../../../commons/SmallString.h"

#include "../../../commons/Collection/include/Collection.h"
#include "../../../commons/msgPadrao.h"
#include "../../../commons/WalkTreeAux.h"
#include "../../Usuario/include/cWFUsuario.h"
#include "../../Andamento/include/cWFAndamento.h"
#include "../../AndamentoMotivo/include/cWFAndamentoMotivo.h"
#include "../../AndamentoObservacao/include/cWFAndamentoObservacao.h"
#include "../../AndamentoTransferencia/include/cWFAndamentoTransferencia.h"
#include "../../AtdEncaminhadoIncorreto/include/cWFAtdEncaminhadoIncorreto.h"
#include "../../AtdMensagemCri/include/cWFAtdMsgCri.h"
#include "../../AtdMensagemRC/include/cWFAtdMsgRC.h"
#include "../../AtdTratamentoCri/include/cWFAtdTratamentoCri.h"
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../AtendimentoAgendamento/include/cWFAtendimentoAgendamento.h"
#include "../../AtendimentoAndamentoAtual/include/cWFAtendimentoAndamentoAtual.h"
#include "../../AtendimentoBaixaAtual/include/cWFAtendimentoBaixaAtual.h"
#include "../../AtendimentoBaixaHistorico/include/cWFAtendimentoBaixaHistorico.h"
#include "../../AtendimentoContato/include/cWFAtendimentoContato.h"
#include "../../AtendimentoCPFila/include/cWFAtendimentoCPFila.h"
#include "../../AtendimentoFechamento/include/cWFAtendimentoFechamento.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include "../../AtendimentoFrmCampo/include/cWFAtendimentoFrmCampo.h"
#include "../../AtendimentoGerarXMLDPR/include/cWFAtendimentoGerarXMLDPR.h"
#include "../../AtendimentoGrupoAtual/include/cWFAtendimentoGrupoAtual.h"
#include "../../AtendimentoGrupoDevolucao/include/cWFAtendimentoGrupoDevolucao.h"
#include "../../AtendimentoLinha/include/cWFAtendimentoLinha.h"
#include "../../AtendimentoMensagem/include/cWFAtendimentoMensagem.h"
#include "../../AtendimentoNivel/include/cWFAtendimentoNivel.h"
#include "../../AtendimentoPausa/include/cWFAtendimentoPausa.h"
#include "../../AtendimentoPessoa/include/cWFAtendimentoPessoa.h"
#include "../../AtendimentoPriorizacao/include/cWFAtdPriorizacao.h"
#include "../../AtendimentoSuspeito/include/cWFAtendimentoSuspeito.h"
#include "../../AtendimentoTeste/include/cWFAtendimentoTeste.h"
#include "../../AtendimentoUsuarioAtual/include/cWFAtendimentoUsuarioAtual.h"
#include "../../AtendimentoUsuarioDevolucao/include/cWFAtendUsuarioDevolucao.h"
#include "../../AtendimentoFrm/include/cWFAtendimentoFrm.h"
#include "../../AtendimentoFrmCampo/include/cWFAtendimentoFrmCampo.h"
#include "../../CancelamentoSolicitado/include/cWFCancelamentoSolicitado.h"
#include "../../REGCONTATO/include/cApoio.h"
#include "../../ENCCONTATO/include/cEncContato.h"
#include "../../PPRelatorios/include/cWFPPRelatorios.h"
#include "stWF_Acao.h"
#include "../../../commons/emailUtil/include/cEmailUtil.h"
#include "../../../commons/smsUtil/include/cSMSUtil.h"
#include "../../WF_ATDALTERPROT/include/cWfAtdAlterProt.h"

#define DS_MOT_CAN_PEDIDO "Cancelado a pedido"
#define DS_MOT_CAN_INDEVIDO "Cancelado Indevido"
#define DS_MOT_CAN_INCORRETO "Cancelado Incorreto"

#define PROX_AGRUP_DIFERENTE_DO_ATUAL true
#define PROX_AGRUP_PODE_SER_IGUAL_ATUAL false

#define INORIGEM_ANDAMENTOMENSAGEM_BKO 0 // BKO
#define INORIGEM_ANDAMENTOMENSAGEM_RC  1 // CONTATO PRÉVIO
#define INORIGEM_ANDAMENTOMENSAGEM_CRI 2 // CONSULTOR DE RELACIONAMENTO

#define ACAO_FECHAMENTO     0
#define ACAO_AGENDAMENTO    1
#define ACAO_ENCERRAMENTO   2

#define SEM_RETORNO                 0
#define COM_RETORNO_GRUPO_BKO       1
#define COM_RETORNO_GRUPO_RETORNO   2

#define AGENDAR_A            5
#define AGENDAR_AR          16
#define FECHAR_F            10
#define FECHAR_DR           17
#define ENCERRAR_BKO_EB      8
#define ENCERRAR_BKO_EBR    13
#define ENCERRAR_BKO_EBS    14

#define ABERTURA    1
#define TRATAMENTO  2
#define RETORNO     3

//------------------------------------------------------------------------------        
// cWF_Acao 
//------------------------------------------------------------------------------        

extern int proCPesquisaGrupoFaseRetorno(st_VariaveisUsuario* _dados);
extern bool proCPesquisaGrupoFase(st_VariaveisUsuario* _dados,int &idGrupoDestino);
extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados,int &idGrupoDestino);
extern bool proCPesquisaGrupoFaseVariablesUsuario(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFaseUsuario(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoProxNivel(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCObtemWFNivelGrAt(long sIdAtendimento, Collection* _grupos);
//extern bool proCPesquisaGrupoFaseVariables(st_VariaveisUsuario* _dados, Collection** _grupos);
//extern bool proCPesquisaGrupoFase(st_VariaveisUsuario* _dados, Collection** _grupos);
extern int proCPesquisaGrupoRetornoGrupoRetorno(st_VariaveisUsuario* _dados,char *_nmGrupo,int *inAssociado);
extern bool proCPesquisaGrupoFaseVariablesCRI(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCPesquisaGrupoFaseCRI(st_VariaveisUsuario* _dados, Collection* _grupos);
extern bool proCGetINCRI(int _idGrupo);
extern bool proCExisteGrupoUsuario( unsigned long idGrupoCRIPrm , unsigned long idPessoaUsuarioPrm ); 

struct DadosRetCons
{
    TString idLinhaTelefonica;
    TString idTipoRelacionamento;
    TString nmPessoa;
    TString nrLinha;
    TString idSegmentacao;
    TString idProcedencia;
    TString idCanal;
    TString idPessoaDePara;
    TString idContato;
    TString dsObservacao;
};

class cWF_Acao : public TuxHelper 
{
    public:
        //------------------------------------------------------------------------------        
        cWF_Acao() {idGrupoCprevio=0;valorCampo=0;tamValorCampo=0;};
        ~cWF_Acao() {delete[] valorCampo;valorCampo=0;};
        virtual void Executar(); 
        //------------------------------------------------------------------------------        
    protected: 
        //------------------------------------------------------------------------------        
        XMLDPR xmlDpr; // SM324 - DPR
        Collection listaAtendimentoLinhas;
        TuxRemoteService rc;
        TuxMessage tmIn;

        int     ind; 
        int     getIdGrupo();
        void    SetNodesDef(DOMNode *, XMLGen * );
        bool    SetNode( char * );
        bool    SetNode( char *, char * );
        void    Next() { ind++; } 
        void    Back() { ind--; } 
        void    XMLException(char *); 
        void    SetMessage(char *, char *); 
        void    SetMessage(char *, char *, char *); 
        void    sql_error(sqlca*);
        bool    Init();
        bool    InitPesquisa();
        bool    getSysDate();
        void    getFormaRetorno(stBaixaHistorico* datos);
        void    inserirAtendimentoLinhas(long idAtendimento,Collection *atendimentoLinhas);
        // bool    getBaixaMensagem(stMensagemBaixa* datos);

        WalkTreeAux wta;
        char *valorCampo;
        int tamValorCampo;

        DadosOrdemVenda dadosOrdemVenda;
        Collection formularioDinamico;

        //------------------------------------------------------------------------------
        bool alterarTipoRetornoContato;

        long idAndamento;
        long idAndamentoTrans;
        long idAtendimentoTeste;
        long idAtendimentoCancelamento;
        int idAlerta;
        int idGrupoAtual;
        int idGrupoAtualBKO;
        int idUsuarioAtendimento;
        int idStatusUsuario;
        int inDisponivelWF;
        int idTipoLinha;
        int idTipoPessoa;
        int idTipoRelacionamento;
        int idTipoRetornoContato;
        int idCanal;
        int idProcedencia;
        int idTipoCarteira;
        int idSegmentacao;
        int idGrupoAbertura;
        int idUsuarioAbertura;
        int idContato;
        int nrNivelAtendimento;
        int idFaseAtendimento;
        int qtInsistenciaAtendimento;
        int inPausa;
        long idAtendimentoBaixaHistorico;
        int idBaixaMensagem;
        long idBaixaAtendimento;
        int idAgrEstadoTProcTestes;
        int inCri;
        int inCriAtd;
        int idUsuarioCri;
        int qtEnvioBko;
        long idPessoaLinhaHistorico;
        int inAssociado;
        int qtIntercambios;
        int inRC;
        int idUFOperadora;
        int idUsuarioAtualAtendimento;
        int idUsuarioAlteracao;

        double nrCriticidade;

        char nmCor[256];
        char* nrTelefone;
        char dtAbertura[64];
        char sgTipoPortabilidade[16];
        char szSgOperadoraSolicitante[31];
        char szNrProtocoloPortabilidade[39];
        char szDtJanelaPortout[32];
        char szIdTipoLinha[5];

        char dtInicioTransferencia[64];
        char dtPrazoFinalCPrevio[64];
        char idAtendimentoProtocolo[64];

        char szSgRegraEncaminhamento[4];
        char szSgFluxoAtendimento[4];
        char szIdPerfilConsultorAtd[11];
        char szIdFornecedorConsultorAtd[11];
        char szIdSiteConsultorAtd[11];

        //------------------------------------------------------------------------------        
        bool grupoIsCRI(int idGrupo);
        bool processoCriDentroPrazoRetInboxSimNao();
        bool existeAtendimentoUsuarioAtual();
        void atualizarPessoaUsuarioAbertura(long idPessoaUsuarioAbertura);
        bool DevolverBKO(bool &retornarParaFila);
        bool ReanalisarBKO(bool &retornarParaFila);
        bool IsUsuarioDestinoMc2();
        void getAtendimento(); 
        void getAtendimento(DetalheAtendimento *detalheatendimento);
        void getAtendimentoPessoa(); 
        void getAtendimentoLinha(); 
        void getAtendimentoLinhaCri(); 
        void getAtendimentoContato(); 
        void getStatusUsuario(); 
        void getStatusUsuario(int idPessoaUsuario);
        void getStatusDispUsuario();
        void getStatusDispUsuario(int idPessoaUsuario);
        bool getAtendimentoUsuarioAtual(); 
        void getAtendimentoGrupoAtual(); 
        void getAtendimentoGrupoBko(); 
        void getGrupoAtualReabertura(); 
        void getGrupoProximoNivel(); 
        // void getGrupoProximaFase(); 
        void getGrupoProximaFaseSemConsiderarRetorno();
        void getGrupoProximaFaseCRI();
        void getAgrupEstadoTpProcFt(bool proxAgrupDifAtual);
        void getAndamentoTransferencia(); 
        void getAtendimentoGrupoDevolucao();
        void getAtendimentoUsuarioDevolucao();
        void getCancelamentoSolicitado();
        void getAlertaAtendimento();
        void getAlertaDinamicaAtendimento();
        void getFormaRetornoAtendimento();
        void registrarAcaoDPR(int idUsuarioWeb,int idContato,const char *nomeServico);
        // bool getBaixaMensagem();
        void getAtendimentoBaixaAtual();
        int getMotivoCancelamento();
        int getUsuarioAtendimentoSuspeito();
        int obterTipoRetornoProcesso(long idAtendimento);
        bool existeAtendimentoFechamento();
        void Encerrar();
        bool Fechar();
        void incluirAtendimentoTeste();
        void atualizarDataDelayFila();
        void atualizarDataJanelaPortout();
        bool EncerrarMC2();
        bool proCAtualizarDadosStatusSAP(const char *idStatusSAP,const char *vlLogXMLIn,int idUsuarioAlteracao);
        bool proCAtualizarOrdemVendaDadosStatusSAP(long idAtendimento,const char *idStatusSAP,const char *nrOrdemVenda,int idUsuarioAlteracao);
        bool proCIsEstadoFechamento(int idAgrupEstadoTpProc);

        void SyncRemoteCall(const char *nomeServico,const char *xmlIn);
        const char *procCObterObservacaoAtendimento(long idAtendimento);
        void procCAtualizarObservacaoAtendimento(long idAtendimento,const char *dsObservacao);


        void inserirTratamentoGrupoCri();

        void removeAtendimentoFechamento(); 
        void removeAtendimentoBaixaAtual(); 
        void removeAtendimentoGrupoBko(); 
        void removeAtendimentoGrupoReceptor();
        void removeAtendimentoUsuarioReceptor();
        void removeAtendimentoUsuarioAtual();
        void removeAtendimentoGrupoAtual();
        void removeAtendimentoSuspeito();
        void removeTratamentoCri();
        void removeCancelamentoSolicitado();
        void alterarAtendimento();
        void concluirAtendimentoNivel(int idFaseNivel);
        void alterarAndamentoAtual();
        void alterarAndamentoTransferencia();
        void alterarAtendimentoUsuarioAtual();
        void atualizarAtendimentoUsuarioAtual();
        void atualizarAtendimentoUsuarioAtual(int idPessoaUsuario,bool atualizarPausa);
        void alterarAtendimentoUsuarioAtual(int idPessoaUsuario,bool atualizarPausa);
        void alterarAtendimentoGrupoAtual();
        void alterarAtendimentoGrupoAtual(int idGrupo);
        void alterarCancelamentoSolicitado();
        void alterarAtendimentoPriorizacao();
        void alterarAtendimentoTipoRetorno();
        void alterarAtendimentoBaixaAtual();
        void alterarAtendimentoGrupoBko();
        void inserirAtendimentoGrupoAtual(); 
        void inserirAtendimentoNivel(bool concluir=false);
        void inserirAtendimentoNivel(int idGrupo,bool concluir=false);
        void inserirAndamento();
        void inserirAndamento(int idGrupo);
        void inserirAndamentoObservacao();
        void inserirAndamentoMotivo();
        void inserirAndamentoTransferencia();
        void inserirAtendimentoUsuarioAtual(bool agendamento=false);
        void inserirAtendimentoFechamento();
        void inserirCancelamentoSolicitado();
        void inserirAtendimentoAlerta();
        void inserirAtendimentoBaixaHistorico();
        void inserirAtendimentoBaixaMensagem();
        void inserirAtendimentoBaixaAtual();
        void inserirFormulario();
        void inserirAtendimentoAgendamento();
        void atualizarAtendimentoGrupoReceptor(int idGrupo);
        void inserirAtendimentoGrupoReceptor();
        void inserirAtendimentoGrupoReceptor(int idGrupo);
        void inserirAtendimentoUsuarioReceptor();
        void inserirAtendimentoSuspeito();
        bool iniciarRetencaoAtendimentosAgrupados();
        bool devolverFilaAtendimentosAgrupados();
        void gravaAtendimentoEncIncorreto();
        void gravarPessoaPortabilidadeHist(const char *nomeServico);
        bool proCObterDadosStatusSAP(long idAtendimento);
        bool obterGrupoTratamentoRetencaoPout(int *idGrupo);
        bool getGrupoTratamentoRetencaoPout();
        void proCAtualizarPessoaUsuarioAbertura(long idAtendimento,long idPessoaUsuarioAbertura);
        void proCTrocarResponsavelMC2(long idAtendimento,int idPessoaUsuarioMC,int idGrupoMC);
        void trocarResponsavelMC2(int idPessoaUsuarioMC,int idGrupoMC);

        void getAtendimentoUsuarioCri();
        void getTratamentoUsuarioCri();
        void getAtendimentoGrupoAtualBko();
        void getAtendimentoGrupoCri();
        void alterarAtendimentoCri(bool atualizaDataAberturaSimNao=false);
        void removeAtendimentoCri();
        void incAtendimentoUsuarioCriQtEnvioBKO();
        void alterarTratamentoCri();
        void alterarUsuarioDevolucaoCri();
        //void alterarAtendimentoGrupoAtualCri();
        void alterarTratamentoGrupoCri();
        void alterarGrupoCri();
        void alterarGrupoDevolucaoCri();
        void inserirAndamentoMensagemRC(int idPessoaUsuarioOrigem);
        void inserirAndamentoMensagem();
        void inserirAndamentoMensagem(int idPessoaUsuarioDestino,int idPessoaUsuarioOrigem,int inOrigem=INORIGEM_ANDAMENTOMENSAGEM_BKO);
        void inserirAtendimentoCri();
        void inserirTratamentoCri();
        void inserirAtendimentoGrupoBko();
        void atualizaAtendimentoGrupoAtual();

        bool usuarioIsSupervisor(int idGrupo);
        bool podeCancelarProcesso();
        bool getGrupoRetorno();
        bool proCObterDadosAberturaProcesso(long idAtendimento,const char * idAtendimentoProtocolo,DadosRetCons *dadosRetCons);
        void proCEnfileirarEfetivacaoRetencaoNoLegado(TString &idAtendimento,TString &User);
        void proCCancelarEfetivacaoRetencaoPontos(TString &idAtendimento,TString &User);

        void alterarAtendimentoGrupoAtualRC(int idGrupo);
        
        void adquirirContatoPrevio();

        void atualizarAtendimentoPausa();
        void removeAtendimentoPausa();
        bool ProcessoEmPausaSimNao();

        bool preProcessarRelatorios(cWFPPRelatorios &cwfpprelatorios );
        bool preProcessarRelEncFech(int acao);
        bool ProximoEstadoRetornoSimNao();
        void atualizarDataPrazoCRI();
        void obterAtdAgrupadosEmTratFila(long idAtendimento,Collection *listaAtendimentosAgrupados);
        void obterAtdAgrupadosEmTratPout(long idAtendimento,Collection *listaAtendimentosAgrupados);
        void gravaAtendimentoFormularioDinamico(long  idAtendimento);
        void carregarDadosFormulario(DOMNode *dnode);

        // incidencia 2939
        bool ExisteGrupoUsuario( unsigned long idGrupoCRIPrm , unsigned long idPessoaUsuarioPrm );
        void DecrementarPendentes();
        void DecrementarPendentes(const char *idAtendimentoProtocolo);

        //------------------------------------------------------------------------------        
        TString GetXML ( char *, bool tpObrigatorio = false );
        TString sSysDate;
        TString UsuarioAtual;
        TString UsuarioAtualScript;
        TString idUsuAtual;
        TString idAtendimento;
        TString idAgrEstTPrFt;
        TString idAgrEstTPrAt;
        TString idAtividade;
        TString idFase;
        TString User;
        TString idGrupo;
        TString dsObservacao;
        TString dsObservacaoOV;
        TString idMotivo;
        TString urlDestino;
        TString dtContato;
        TString inSPN;
        TString hrContato;
        TString idBaixa;
        TString cdAreaRegistro;
        TString nrLinha;
        TString dsMotivo;
        TString dtJanelaPortout;
        TString sgOperadoraSolicitante;
        TString dsAcaoPortabilidade;
        TString idStatusSAP;
        TString idRetencao;
        TString idPessoaUsuarioMC;
        TString idGrupoMC;

        //------------------------------------------------------------------------------        
        DOMNode *dnode;
        DOMNode *bdnode;

        DOMNode *domFormulario;
        //------------------------------------------------------------------------------        
        XMLGen  *xml_g;
        XMLGen   oxml;
        //------------------------------------------------------------------------------        
        // Incidência 3805  esta variavel ira armazena o id  idGrupoCprevio em tratamento
        int idGrupoCprevio ;
};

#endif
