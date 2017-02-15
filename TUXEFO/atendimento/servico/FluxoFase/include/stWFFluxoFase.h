/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/ 

#ifndef STWFFLUXOFASE
    #define STWFFLUXOFASE

#define ANALISTA_BKO_SEM_CRI   0
#define SUPERVISOR_CRI         1
#define CRI_RESPONSAVEL        2
#define CRI_NAO_RESPONSAVEL    3
#define ANALISTA_BKO_COM_CRI   4
#define ANALISTA_RESP_CLI      5
#define SUPERVISOR_BKO         6
#define SUPERVISOR_BKO_COM_CRI 7
#define ANALISTA_DE_RETENCAO   8

#define PROCESSO_BKO 0
#define PROCESSO_CRI 1
#define PROCESSO_RC  2 // PROCESSO DE CONTATO PRÉVIO

struct st_FluxoFase
{
    long idAtendimento;
    int inCri;
    int idTipoRelacionamentoBko;
    int idUsuarioCri;
    int idFluxoFase;
    int idFase;
    int idFluxo;
    int idGrupo;
    int idContato;
    int idPessoaUsuario;
    int idAgrupamentoEstadoTpProc;
    int idAgrupamentoEstadoTpProcFt;
    int idAtividade;
    int idTipoLinha;
    int idSegmentacao;
    int idTipoRelacionamento;
    int idTipoCarteira;
    int inRC;
    int inSupervisor;
    char inTipoPessoa[256];
    char nmURLDestino[256];
    char nmFuncValidacao[256];
    int inPortout;
};

struct st_vlFluxoFase
{
    short idAtendimento;
    short inCri;
    short idTipoRelacionamentoBko;
    short idUsuarioCri;
    short idFluxoFase;
    short idFase;
    short idFluxo;
    short idGrupo;
    short idContato;
    short idPessoaUsuario;
    short idAgrupamentoEstadoTpProc;
    short idAgrupamentoEstadoTpProcFt;
    short idAtividade;
    short idTipoLinha;
    short idSegmentacao;
    short idTipoRelacionamento;
    short idTipoCarteira;
    short inRC;
    short inSupervisor;
    short inTipoPessoa;
    short nmURLDestino;
    short nmFuncValidacao;
    short inPortout;
};

#endif

