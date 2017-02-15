/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/

#ifndef __STWFCORECONTATO_H_
#define __STWFCORECONTATO_H_

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <string>
using namespace std;

#define CD_OPERACAO_PORTIN              "1"
#define CD_OPERACAO_PORTOUT             "2"
#define CD_OPERACAO_FRAUDE              "3"
#define CD_OPERACAO_WINBACK             "4"

//#define CD_OPERA_TIM                    "1"
//#define CD_OPERA_CLARO                  "2"
//#define CD_OPERA_OI                     "3"
//#define CD_OPERA_NEXTEL                 "4"

//#define ESTADO_AGUARDANDO_AUTORIZACAO   "1"
//#define ESTADO_AGUARDANDO_JANELA        "2"
//#define ESTADO_PROCESSANDO_AUTORIZACAO  "3"
//#define ESTADO_EM_PROCESSO              "4"
//#define ESTADO_PROCESSANDO_ESTORNO      "5"
//#define ESTADO_AUTORIZACAO_NEGADA       "6"
//#define ESTADO_AGUARD_HAB_COM_AUTORIZA  "7"
//#define ESTADO_CANCELAMENTO_SOLICITADO  "8"
//#define ESTADO_DEVOLVIDO                "9"
//#define ESTADO_RETORNADO                "10"
//#define ESTADO_ESTORNADO                "11"
//#define ESTADO_REAGENDAMENTO_SOLICITADO "12"
//#define ESTADO_PENDENTE_FAVORITOS       "13"
//#define ESTADO_CONCLUIDO                "14"
//#define ESTADO_ESTORNO_NEGADO           "15"
//#define ESTADO_CANCELADO                "16"
//#define ESTADO_PENDENTE_RETORNO         "17"
//#define ESTADO_PENDENTE_DEVOL           "18"
//#define ESTADO_ANALISE_DE_FRAUDE        "19"
//#define ESTADO_AGUARDANDO_HABILITACAO   "20"
//#define ESTADO_ABRIR_PROC_DE_RETENCAO   "21"
//#define ESTADO_ERRO                     "99"

#define PROCEDENCIA_LOJA                "1"
#define PROCEDENCIA_DEALER              "2"
#define PROCEDENCIA_GERENTE_DE_CONTAS   "3"
#define PROCEDENCIA_CALL_CENTER         "4"
#define PROCEDENCIA_TIM                 "5"
#define PROCEDENCIA_CLARO               "6"
#define PROCEDENCIA_OI                  "7"
#define PROCEDENCIA_BRASIL_TELECOM      "8"

#define TPOPER_PALITAGEM                "1"

struct st_DadosEntrada
{
    char cdAreaRegistro[21];
    char nrLinha[32];
    char dtJanelaPortout[32];
    char cdOperacao[21];
    char dsOperacao[16];
    char estadoPortabilidade[21];
    char cdOperadoraSolicitante[21];
    char dsComentarioProcesso[1001];
    char nrProcesso[21];
    char nrBilhetePortabilidade[16];
    //char sgOperadoraSolicitante[31];
    char cdProcedencia[5];
    char dsProcedencia[256];
};

struct st_StatusEntrada
{
    short cdAreaRegistro;
    short nrLinha;
    short dtJanelaPortout;
    short cdOperacao;
    short estadoPortabilidade;
    short cdOperadoraSolicitante;
    short dsComentarioProcesso;
    short nrProcesso;
    short nrBilhetePortabilidade;
    short cdProcedencia;
    short dsProcedencia;
};

struct st_Parametros
{
    string cdFuncionalidade;
    string dsAcaoPortabilidade;
    string dsPathContato;
    string dsTipoAtividade;
    string dtUltimaAlteracao;
    string idAcaoPortabilidade;
    string idAgrupamentoEstadoTpProc;
    string idAgrupamentoEstadoTpProcFt;
    string idAtendimento;
    string idAtividade;
    string idCanal;
    string idConta;
    string idContato;
    string idFase;
    string idGrupoAbertura;
    string idGrupoTratamento;
    string idLinhaTelefonica;
    string idLinhaTelefonicaCliente;
    string idMotivo;
    string idPessoaCliente;
    string idPessoaDeParaCliente;
    string idPessoaLinhaHistorico;
    string idPessoaUsuario;
    string idProcedencia;
    string idSegmentacaoCliente;
    string idTipoCarteiraCliente;
    string idTipoRelacionamentoCliente;
    string idUsuarioAlteracao;
    string inEnviaSms;
    string nmPessoaCliente;
    string nmServico;
    string sgAtividade;
    string sgSubSistema;
    string sgTipoPortabilidade;
    string tipoOperacao;
    string sgOperadoraSolicitante;
    string dsTipoPessoa;
};

#endif //#ifndef __STWFCORECONTATO_H_
