/**
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.4.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#ifndef STREGPALITAGEM
    #define STREGPALITAGEM

#include "../../../commons/Collection/include/Collection.h"

#define ABERTURA_NAO_CLASSIFICADA   0
#define ABERTURA_POR_CONTA          1
#define ABERTURA_POR_LINHA          2

#define PALITAGEM 1

#define PROCESSO_PORTIN  "PORTIN"
#define PROCESSO_PORTOUT "PORTOUT"
#define PROCESSO_FRAUDE  "FRAUDE"
#define PROCESSO_WINBACK "WINBACK"

struct st_DadosRegPalitagem
{
    bool AtendimentoPorConta;

    char *nmContato;
    char *nmFalandoCom;
    char *idAtendimentoProtocolo;
    char *nrTelefone;
    char observacao[1001];
    char cdConta[101];
    char cdDigitoConta[256];
    char dataAtual[32];
    char dataANATEL[32];
    char dataCPrevio[32];
    char dataFechamento[32];
    char dsAcaoPortabilidade[256];
    char dtJanelaPortout[32];
    char nrProtocoloPortabilidade[16];
    char peso[256];
    char sgOperadoraSolicitante[31];
    char sgTipoPortabilidade[16];

    double horas;

    int cdAreaRegistro;
    int idAgrupamentoEstadoTpProc;
    long idAndamento;
    long idAtendimento;
    long idAtendimentoOrigem;
    int idBaixa;
    int idBaixaMensagem;
    int idCanal;
    int idConta;
    int idContato;
    int idEstadoLinha;
    int idGrupoAbertura;
    int idGrupoAtual;
    int idLinhaTelefonica;
    int idPessoaDeParaCliente;
    int idPessoaConta;
    int idPessoaDePara;
    long idPessoaLinhaHistorico;
    int idPessoaDeParaUsuario;
    int idProcedencia;
    int idProximoGrupo;
    int idSegmentacao;
    int idTerminal;
    int idTipoCarteira;
    int idTipoLinha;
    int idTipoPessoa;
    int idTipoReaberturaProcesso;
    int idTipoRelacionamento;
    int idTipoComunicacao;
    int idTipoRetornoContato;
    int idUFOperadora;
    int idUfOperadora;
    int idUsuarioBKO;
    int idAgrupamentoEstadoTpProcFt;

    Collection atendimentoLinhas;

    st_DadosRegPalitagem()
    {
        memset(this,0,sizeof(st_DadosRegPalitagem));
    }

    ~st_DadosRegPalitagem()
    { 
        if ( nrTelefone ) XMLString::release(&nrTelefone);
        //if ( observacao ) XMLString::release(&observacao);
        if ( nmContato ) XMLString::release(&nmContato);
        if ( idAtendimentoProtocolo ) XMLString::release(&idAtendimentoProtocolo);
    }
};

struct st_StatusRegPalitagem
{
    short AtendimentoPorConta;
    short nmContato;
    short nmFalandoCom;
    short idAtendimentoProtocolo;
    short nrTelefone;
    short observacao;
    short cdConta;
    short cdDigitoConta;
    short dataAtual;
    short dataANATEL;
    short dataCPrevio;
    short dataFechamento;
    short dsAcaoPortabilidade;
    short dtJanelaPortout;
    short nrProtocoloPortabilidade;
    short peso;
    short sgOperadoraSolicitante;
    short sgTipoPortabilidade;
    short horas;
    short cdAreaRegistro;
    short idAgrupamentoEstadoTpProc;
    short idAndamento;
    short idAtendimento;
    short idAtendimentoOrigem;
    short idBaixa;
    short idBaixaMensagem;
    short idCanal;
    short idConta;
    short idContato;
    short idEstadoLinha;
    short idGrupoAbertura;
    short idGrupoAtual;
    short idLinhaTelefonica;
    short idPessoaDeParaCliente;
    short idPessoaConta;
    short idPessoaDePara;
    short idPessoaLinhaHistorico;
    short idPessoaDeParaUsuario;
    short idProcedencia;
    short idProximoGrupo;
    short idSegmentacao;
    short idTerminal;
    short idTipoCarteira;
    short idTipoLinha;
    short idTipoPessoa;
    short idTipoReaberturaProcesso;
    short idTipoRelacionamento;
    short idTipoComunicacao;
    short idTipoRetornoContato;
    short idUFOperadora;
    short idUfOperadora;
    short idUsuarioBKO;
    short idAgrupamentoEstadoTpProcFt;

    st_StatusRegPalitagem()
    {
        memset(this,-1,sizeof(st_StatusRegPalitagem));
    }
};

#endif

