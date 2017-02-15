/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.11.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#ifndef STREGCONTATO
    #define STREGCONTATO

#include "../../../commons/Collection/include/Collection.h"

#define ABERTURA_NAO_CLASSIFICADA   0
#define ABERTURA_POR_CONTA          1
#define ABERTURA_POR_LINHA          2

#define PALITAGEM 1

#define PROCESSO_PORTIN  "PORTIN"
#define PROCESSO_PORTOUT "PORTOUT"
#define PROCESSO_FRAUDE  "FRAUDE"
#define PROCESSO_WINBACK "WINBACK"

struct st_RegContato
{
    bool atendimentoPorConta;
    bool forceUsoProtocoloNaoAberto;
    bool gravarLinhaTelefonica;
    bool naoEnvProtocoloSMS;
    bool naoValidarDados;
    bool isVolE;

    char dataAtual[64];
    //char dataAtualSMS[32];
    char cdAreaRegistroSZ[16];
    char dsAcaoPortabilidade[512];
    char dtJanelaPortout[64];
    char inTipoPessoa[16];
    char msgSMS[1024];
    char nmContato[512];
    char nmFalandoCom[512];
    char idAtendimentoProtocolo[64];
    char idSistemaOrigemProtocolo[64];
    char idTipoAberturaProtocolo[8];
    char nrProtocoloPortabilidade[32];
    char nrTelefone[16];
    char observacao[1001];
    char observacaoFechamento[1001];
    char peso[256];
    char sgOperadoraSolicitante[48];
    char sgTipoPortabilidade[32];
    char cdConta[512];
    char cdDigitoConta[512];
    char dataANATEL[64];
    char dataCPrevio[64];
    char dataFechamento[64];
    char dddSMSProtocolo[8];
    char nrLinhaSMSProtocolo[16];
    char idUsuarioBKOSZ[64];
    char dsClassificacaoSMS[512];
    char sgRegraEncaminhamento[16];
    char sgFluxoAtendimento[16];
    char inConsultor[8];
    char nrDocumento[64];
    char nrOrdemVenda[64];

    double horas;
    double horas2;
    double horasRC;

    int cdAreaRegistro;
    long idAndamento;
    long idAtendimento;
    long idAtendimentoOrigem;
    int idBaixa;
    int idBaixaMensagem;
    int idMensagemBaixa;
    int idCanal;
    int idChamadaTelefonica;
    int idConta;
    int idContato;
    int idEstadoLinha;
    int idGrupoAbertura;
    int idGrupoAtual;
    int idLinhaAtendimento;
    int idLinhaTelefonica;
    int idPessoaConta;
    int idPessoaDePara;
    int idPessoaDeParaCliente;
    int idPessoaDeParaUsuario;
    long idPessoaLinhaHistorico;
    int idProcedencia;
    int idSegmentacao;
    int idTerminal;
    int idTipoCarteira;
    int idTipoLinha;
    int idTipoPessoa;
    int idTipoReaberturaProcesso;
    int idTipoRelacionamento;
    int idTipoRetorno;
    int idUFOperadora;
    int idUsuarioBKO;
    int tpOperacao;
    int idAgrupamentoEstadoTpProc;
    int idAgrupamentoEstadoTpProcFt;
    int idGrupoTratamento;
    int inSMS;
    int idContatoFolhaCampo;


    Collection contatoComunic;
    Collection formularioDinamico;
    Collection atendimentoLinhas;

    st_RegContato()
    {
        atendimentoPorConta = false;
        forceUsoProtocoloNaoAberto = false;
        gravarLinhaTelefonica = false;
        naoEnvProtocoloSMS = false;
        naoValidarDados = false;

        dataAtual[0] = 0;
        cdAreaRegistroSZ[0] = 0;
        dsAcaoPortabilidade[0] = 0;
        dtJanelaPortout[0] = 0;
        inTipoPessoa[0] = 0;
        msgSMS[0] = 0;
        nmContato[0] = 0;
        nmFalandoCom[0] = 0;
        idAtendimentoProtocolo[0] = 0;
        idSistemaOrigemProtocolo[0] = 0;
        idTipoAberturaProtocolo[0] = 0;
        nrProtocoloPortabilidade[0] = 0;
        nrTelefone[0] = 0;
        observacao[0] = 0;
        observacaoFechamento[0] = 0;
        peso[0] = 0;
        sgOperadoraSolicitante[0] = 0;
        sgTipoPortabilidade[0] = 0;
        cdConta[0] = 0;
        cdDigitoConta[0] = 0;
        dataANATEL[0] = 0;
        dataCPrevio[0] = 0;
        dataFechamento[0] = 0;
        dddSMSProtocolo[0] = 0;
        nrLinhaSMSProtocolo[0] = 0;
        idUsuarioBKOSZ[0] = 0;
        sgRegraEncaminhamento[0] = 0;
        sgFluxoAtendimento[0] = 0;
        nrOrdemVenda[0] = 0;

        horas = 0.0;
        horas2 = 0.0;
        horasRC = 0.0;

        cdAreaRegistro = 0;
        idAndamento = 0;
        idAtendimento = 0;
        idAtendimentoOrigem = 0;
        idBaixa = 0;
        idBaixaMensagem = 0;
        idMensagemBaixa = 0;
        idCanal = 0;
        idChamadaTelefonica = 0;
        idConta = 0;
        idContato = 0;
        idEstadoLinha = 0;
        idGrupoAbertura = 0;
        idGrupoAtual = 0;
        idLinhaAtendimento = 0;
        idLinhaTelefonica = 0;
        idPessoaConta = 0;
        idPessoaDePara = 0;
        idPessoaDeParaCliente = 0;
        idPessoaDeParaUsuario = 0;
        idPessoaLinhaHistorico = 0;
        idProcedencia = 0;
        idSegmentacao = 0;
        idTerminal = 0;
        idTipoCarteira = 0;
        idTipoLinha = 0;
        idTipoPessoa = 0;
        idTipoReaberturaProcesso = 0;
        idTipoRelacionamento = 0;
        idTipoRetorno = 0;
        idUFOperadora = 0;
        idUsuarioBKO = 0;
        tpOperacao = 0;
        idAgrupamentoEstadoTpProc = 0;
        idAgrupamentoEstadoTpProcFt = 0;
        idGrupoTratamento = 0;
        idContatoFolhaCampo = 0;
    }

    ~st_RegContato() {}
};

struct st_RegContatoErro
{
    char sCode[32];
    char mCode[512];
};

struct st_DadosEstadoProtocolo
{
    char idTipoAberturaProtocolo[8];
    char dsTipoAberturaProtocolo[512];
    char idStatusProtocolo[8];
    char dsStatusProtocolo[64];
};

#endif

