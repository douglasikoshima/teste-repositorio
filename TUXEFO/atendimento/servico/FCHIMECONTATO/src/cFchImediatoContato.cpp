/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.5 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:15 $
 **/


#include "../include/cFchImediatoContato.h"

cFchImediatoContato::cFchImediatoContato(st_FchImediatoContato* origem,XMLDPR *xmldpr)
{
    dados = origem;
    xmlDpr = xmldpr;
}

/**
    Registra todos os dados do atendimento.
*/
bool cFchImediatoContato::registra()
{
    char dataAndamento[64];

    cFchImediatoContatoPC fic(dados);

    fic.dataAndamento(dataAndamento);

    long idAndamento = gravaAndamento( dataAndamento );
    if (dados->idBaixa > 0)
    {
        int idBaixaHistorico = gravaAtendimentoBaixaHistorico(dataAndamento, idAndamento);
        gravaBaixaAtual(dataAndamento, &idBaixaHistorico);
        if (dados->idBaixaMensagem > 0)
        {
            gravaMensagemBaixa(dataAndamento, &idBaixaHistorico);
        }
    }

    if (dados->observacaoFechamento)
    {
        gravaAndamentoObservacao(dataAndamento, idAndamento);
    }

    gravaAtendimentoGrupoAtual(dataAndamento,dataAndamento,dados->idAtendimento
                              ,dados->idGrupoAbertura,0,dados->idUsuarioBKO);

    // se habilitar este método as palitagens serão exibidas nas filas BKO.
    //alterarAtendimento(dados->idAtendimento,dados->idUsuarioBKO);

    gravaAtendimentoFechamento(dataAndamento, idAndamento);

    // A rotina proC que faz a inserção em andamento faz isso automaticamente - Out/2005 - Cassio
    // gravaAtendimentoAndamentoAtual(dataAndamento, idAndamento);
    gravaAtendimentoNivel(dataAndamento);

    return false;
}

void cFchImediatoContato::alterarAtendimento(long  _idAtendimento,int _idUsuarioBKO)
{
    ULOG_START("cFchImediatoContato::alterarAtendimento()");

    struct st_Atendimento dados;
    struct st_vlAtendimento status;
    XMLGen xmlObtemAtendimento;

    memset( &dados, 0, sizeof(st_Atendimento)); 
    memset( &status,-1,sizeof(st_vlAtendimento));

    dados.idAtendimento = _idAtendimento;
    dados.idPessoaUsuarioAtual = _idUsuarioBKO;

    status.idAtendimento = 1;
    status.idPessoaUsuarioAtual = 1;

    cWFAtendimento cwfAtendimento(&dados,&status,&xmlObtemAtendimento);
    cwfAtendimento.alterar(xmlDpr);

    ULOG_END("cFchImediatoContato::alterarAtendimento()");
}

/**
    Registra o grupo atual do processo
*/
void cFchImediatoContato::gravaAtendimentoGrupoAtual(const char* _dataAtual,const char *_dataEntrada
                                            ,long  _idAtendimento,int _idGrupo
                                            ,int _idSequencia,int _idUsuarioBKO) 
{
    ULOG_START( "cFchImediatoContato::gravaAtendimentoGrupoAtual()" );

    ULOG( "idAtendimento = %lu",_idAtendimento);

    st_AtendimentoGrupoAtual    m_stDados;
    st_vlAtendimentoGrupoAtual  m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento         = _idAtendimento;
    m_stDados.idGrupo               = _idGrupo;
    m_stDados.idSequencia           = _idSequencia;
    m_stDados.idUsuarioAlteracao    = _idUsuarioBKO;

    strcpy(m_stDados.dtEntradaBKO     , _dataEntrada);
    strcpy(m_stDados.dtEntradaFila    , _dataAtual);
    strcpy(m_stDados.dtUltimaAlteracao, _dataAtual);

    m_vlDados.idAtendimento             = 1;
    m_vlDados.idGrupo                   = 1;
    m_vlDados.idSequencia               = 1;
    m_vlDados.dtEntradaBKO              = 1;
    m_vlDados.dtEntradaFila             = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoGrupoAtual atga(&m_stDados, &m_vlDados, &retorno);

    atga.incluir(xmlDpr);
    //atga.incluirBko();

    ULOG_END( "cFchImediatoContato::gravaAtendimentoGrupoAtual()" );
}

/**
    Registra os dados de historico da baixa do atendimento
*/
int cFchImediatoContato::gravaAtendimentoBaixaHistorico(char* dataAtual, long idAndamento) 
{
    ULOG_START("cFchImediatoContato::gravaAtendimentoBaixaHistorico()");
    
    st_AtendimentoBaixaHistorico    m_stDados;
    st_vlAtendimentoBaixaHistorico  m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtendimento         = dados->idAtendimento;
    m_stDados.idBaixa               = dados->idBaixa;
    m_stDados.idFase                = 1;
    m_stDados.idAndamento           = idAndamento;
    strcpy(m_stDados.dtBaixa,       dataAtual);
    m_stDados.idPessoaUsuario       = dados->idUsuarioBKO;
    m_stDados.idUsuarioAlteracao    = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao, dataAtual);

    m_vlDados.idAtendimento             = 1;
    m_vlDados.idBaixa                   = 1;
    m_vlDados.idFase                    = 1;
    m_vlDados.idAndamento               = 1;
    m_vlDados.dtBaixa                   = 1;
    m_vlDados.idPessoaUsuario           = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;
    
    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoBaixaHistorico bh(&m_stDados, &m_vlDados, &retorno);

    int idBaixaHistorico = bh.incluir(xmlDpr);

    ULOG_END("cFchImediatoContato::gravaAtendimentoBaixaHistorico()");

    return idBaixaHistorico;
}

/**
    Registra os dados de mensagem de baixa do atendimento
*/
void cFchImediatoContato::gravaMensagemBaixa(char* dataAtual, int* idBaixaHistorico) 
{
    ULOG_START("cFchImediatoContato::gravaMensagemBaixa()");

    st_AtendimentoBaixaHistorico    m_stDados;
    st_vlAtendimentoBaixaHistorico  m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimentoBaixaHistorico   = *idBaixaHistorico;
    m_stDados.idBaixaMensagem               = dados->idBaixaMensagem;
    m_stDados.idUsuarioAlteracao            = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,     dataAtual);

    m_vlDados.idAtendimentoBaixaHistorico   = 1;
    m_vlDados.idBaixaMensagem               = 1;
    m_vlDados.idUsuarioAlteracao            = 1;
    m_vlDados.dtUltimaAlteracao             = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoBaixaHistorico bh(&m_stDados, &m_vlDados, &retorno);

    bh.incluirBaixaMensagem();

    ULOG_END("cFchImediatoContato::gravaMensagemBaixa()");
}

/**
    Registra os dados de baixa atual do atendimento
*/
void cFchImediatoContato::gravaBaixaAtual(char* dataAtual, int* idBaixaHistorico) 
{

    ULOG_START("cFchImediatoContato::gravaBaixaAtual()");

    cFchImediatoContatoPC fic(dados);

    int registros = fic.verificaBaixas(&dados->idAtendimento);

    st_AtendimentoBaixaAtual    m_stDados;
    st_vlAtendimentoBaixaAtual  m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento                 = dados->idAtendimento;
    m_stDados.idAtendimentoBaixaHistorico   = *idBaixaHistorico;
    m_stDados.idUsuarioAlteracao            = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,     dataAtual);

    m_vlDados.idAtendimento                 = 1;
    m_vlDados.idAtendimentoBaixaHistorico   = 1;
    m_vlDados.idUsuarioAlteracao            = 1;
    m_vlDados.dtUltimaAlteracao             = 1;
    
    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoBaixaAtual ba(&m_stDados, &m_vlDados, &retorno);

    if (registros == 0)
        ba.incluir();
    else
        ba.alterar();

    ULOG_END("cFchImediatoContato::gravaBaixaAtual()");
}


/**
    Registra os dados de andamento do atendimento.
*/
long cFchImediatoContato::gravaAndamento( char* dataAtual ) 
{
    ULOG_START("cFchImediatoContato::gravaAndamento()");

    st_Andamento            m_stDados;
    st_vlAndamento          m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtividade = dados->idAtividade;
    m_stDados.idAgrupamentoEstadoTpProc = proximoAgrupamento;
    m_stDados.idAtendimento = dados->idAtendimento;
    m_stDados.idPessoaUsuario = dados->idUsuarioBKO;
    m_stDados.idUsuarioAlteracao = dados->idUsuarioBKO;
    m_stDados.idGrupo = dados->idGrupoAbertura;
    strcpy(m_stDados.dtAndamento, dataAtual);
    strcpy(m_stDados.dtUltimaAlteracao, dataAtual);

    m_vlDados.idAtividade               = 1;
    m_vlDados.idAgrupamentoEstadoTpProc = 1;
    m_vlDados.idAtendimento             = 1;
    m_vlDados.idPessoaUsuario           = 1;
    m_vlDados.dtAndamento               = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;
    m_vlDados.idGrupo                   = 1;
    
    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAndamento andm(&m_stDados, &m_vlDados, &retorno);

    long idAndamento = andm.incluir(xmlDpr);

    ULOG_END("cFchImediatoContato::gravaAndamento()");

    return idAndamento; 
}

/**
    Registra os dados de andamento observacao.
*/
void cFchImediatoContato::gravaAndamentoObservacao(char* dataAtual, long idAndamento) 
{
    ULOG_START("cFchImediatoContato::gravaAndamentoObservacao()");

    st_AndamentoObservacao      m_stDados;
    st_vlAndamentoObservacao    m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAndamento                   = idAndamento;
    m_stDados.pdsAndamentoObservacao        = dados->observacaoFechamento;
    m_stDados.idUsuarioAlteracao            = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,     dataAtual);

    m_vlDados.idAndamento           = 1;
    m_vlDados.dsAndamentoObservacao = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    cWFAndamentoObservacao ando(&m_stDados, &m_vlDados, 0);

    ando.incluir(xmlDpr);

    ULOG_END("cFchImediatoContato::gravaAndamentoObservacao()");
}

/**
    Registra os dados do fechamento a atendimento.
*/
void cFchImediatoContato::gravaAtendimentoFechamento(char* dataAtual, long idAndamento)
{
    ULOG_START("cFchImediatoContato::gravaAtendimentoFechamento()");

    st_AtendimentoFechamento    m_stDados;
    st_vlAtendimentoFechamento  m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento             = dados->idAtendimento;
    m_stDados.idAndamento               = idAndamento;
    m_stDados.idPessoaUsuario           = dados->idUsuarioBKO;
    strcpy(m_stDados.dtFechamento,      dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idAndamento           = 1;
    m_vlDados.idPessoaUsuario       = 1;
    m_vlDados.dtFechamento          = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoFechamento af(&m_stDados, &m_vlDados, &retorno);

    af.incluir(xmlDpr);

    ULOG_END("cFchImediatoContato::gravaAtendimentoFechamento()");
}

/**
    Registra os dados do atendimento andamento atual.
*/
void cFchImediatoContato::gravaAtendimentoAndamentoAtual(char* dataAtual, long idAndamento)
{
    ULOG_START("cFchImediatoContato::gravaAtendimentoAndamentoAtual()");

    st_AtendimentoAndamentoAtual    m_stDados;
    st_vlAtendimentoAndamentoAtual  m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento             = dados->idAtendimento;
    m_stDados.idAndamento               = idAndamento;
    m_stDados.idUsuarioAlteracao        = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao, dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idAndamento           = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoAndamentoAtual aaa(&m_stDados, &m_vlDados, &retorno);

    aaa.incluir();

    ULOG_END("cFchImediatoContato::gravaAtendimentoAndamentoAtual()");
}

/**
    Registra os dados do nivel de atendimento
*/
void cFchImediatoContato::gravaAtendimentoNivel(char* dataAtual)
{
    ULOG_START("cFchImediatoContato::gravaAtendimentoNivel()");

    st_AtendimentoNivel     m_stDados;
    st_vlAtendimentoNivel   m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento             = dados->idAtendimento;
    m_stDados.idGrupo                   = dados->idGrupoAbertura;
    m_stDados.idFase                    = 1;
    m_stDados.idAtividade               = dados->idAtividade;
    m_stDados.nrNivel                   = 0;
    m_stDados.idUsuarioAlteracao        = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao, dataAtual);

    m_vlDados.idAtendimento             = 1;
    m_vlDados.idGrupo                   = 1;
    m_vlDados.idFase                    = 1;
    m_vlDados.idAtividade               = 1;
    m_vlDados.nrNivel                   = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoNivel an(&m_stDados, &m_vlDados, &retorno);

    an.incluir();

    ULOG_END("cFchImediatoContato::gravaAtendimentoNivel()");
}

