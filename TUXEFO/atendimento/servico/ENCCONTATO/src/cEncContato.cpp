/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.13 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:14 $
 **/

/************************************************************************/
/* [A001] - Julho/2006                                                  */
/*          Inclusao do metodo de validacao dos dados de entrada        */
/*          evitando o rollback das operacoes, no caso                  */
/*          de dados incorretos.    @Marcelo                            */
/************************************************************************/

#include "../include/cEncContato.h"
#include "../../../commons/msgPadrao.h"
#include"../../AtendimentoUsuarioDevolucao/include/cWFAtendUsuarioDevolucao.h"
#include"../../AtendimentoGrupoDevolucao/include/cWFAtendimentoGrupoDevolucao.h"

#define PROC_CRI_ENC_INBOX 1
#define PROC_CRI_ENC_FILA  2


cEncContato::cEncContato( st_EncContato * origem,XMLDPR *xmldpr )
{
    dados = origem;
    xmlDpr = xmldpr;
    dataAndamento[0]=0;
}

cEncContato::cEncContato( st_EncContato * origem,st_CRI *pCri,XMLDPR *xmldpr )
{
    dados = origem;
    dadosCRI = pCri;
    xmlDpr = xmldpr;
    dataAndamento[0]=0;
}

cEncContato::cEncContato( st_EncContato * origem, st_CRI *pCri , st_EncContatoPreValidacao *pEncContatoValida,XMLDPR *xmldpr )
{
    dados = origem;
    dadosCRI = pCri;
    dadosValidacao = pEncContatoValida;
    xmlDpr = xmldpr;
    dataAndamento[0]=0;
}

/**
    Registra todos os dados do atendimento.
*/
bool cEncContato::registra()
{
    ULOG_START( "cEncContato::registra()" );

    char dataAtual[64];
    char dataEntrada[64];
    cWFAtendimento atd;

    int isCRI = 0;
    int incremento = 0;
    
    double horas = 24;

    /////////////////////////////////////////////////////////////////////////////////
    // Incidência 2491 - WR-RS
    //if (dados->idTipoReaberturaProcesso == 2)
    //    ecpc.proCDataEntrada(dados->idAtendimentoOrigem, dados->nrTelefone, dataEntrada);
    //else
    //    atd.diasUteis(dados->nrTelefone, horas, incremento, dataEntrada);
    /////////////////////////////////////////////////////////////////////////////////

    atd.diasUteis(dados->nrTelefone, horas, incremento, dataEntrada);

    ecpc.dataAtual(dataAtual);
    ecpc.dataAndamento(dataAndamento);

    // Se não encontrou a linha, não associa a CRI
    if ( dados->idPessoaLinhaHistorico > 0 )
    {
        unsigned long idGrupoCRIAssocUser;
        isCRI = ProcessaRelacionamentoCRI( dataAtual,dataEntrada,idGrupoCRIAssocUser );
    }

    long idAndamento = gravaAndamento(dataAndamento,dados->idAtividade,dadosValidacao->proximoAgrupamento,dadosCRI->idGrupoAbertura);
    gravaAndamentoMotivo(dataAtual, idAndamento);
    gravaAndamentoObservacao(dataAtual, idAndamento);

    // Se regra de fluxo de encaminhamento MeuCliente for MeuCLiente1 o processo
    // ja "nasce" com o próprio atendente que o abriu.
    if ( strcmp(dados->sgFluxoAtendimento,"MC1")==0 )
    {
        //gravaAtendimentoUsuarioAtual(dados->idAtendimento,dados->idUsuarioBKO,dataAtual);
        int idAgrupamentoEstadoTpProcFt = 
                      ecpc.proCConsultaProximoAgrupamento(ENCAMINHAR_E,dadosValidacao->proximoAgrupamento);
        long idAndamento2 = gravaAndamento(dataAndamento,ENCAMINHAR_E,idAgrupamentoEstadoTpProcFt,dadosCRI->idGrupoAbertura);
        gravaAndamentoMotivo(dataAtual,idAndamento2);
        gravaAtendimentoGrupoAtual(dataAtual,dataEntrada,dados->idAtendimento
                                  ,dadosCRI->idGrupoAbertura,dadosValidacao->idSequencia
                                  ,dados->idUsuarioBKO);
    }
    else if ( dados->isVolE )
    {
        bool gravouGrupoAtual=false;
        char szIdDestPossFormulario[11];
        szIdDestPossFormulario[0]=0;

        ULOG("idFormulario=%d",dados->idFormulario);

        if ( dados->idFormulario > 0 )
        {
            ecpc.proCDestinoPossivel(dados->idFormulario,dados->idContato
                                    ,dados->idTipoCarteira,dados->idSegmentacao
                                    ,dados->idUFOperadora,szIdDestPossFormulario);
        }

        if ( strcmp(szIdDestPossFormulario,"GC")==0 )
        { // Gerente de Contas ?
            int idGrupoAtual;
            int idPessoaUsuarioGerentePJ;
            if ( ecpc.proCObtemGerentePJ(dados->idPessoaDeParaCliente,&idPessoaUsuarioGerentePJ,&idGrupoAtual) )
            {
                gravaAtendimentoUsuarioAtual(dados->idAtendimento,idPessoaUsuarioGerentePJ,dataAtual);
                int idAgrupamentoEstadoTpProcFt =
                              ecpc.proCConsultaProximoAgrupamento(ENCAMINHAR_E,dadosValidacao->proximoAgrupamento);
                long idAndamento2 = gravaAndamento(dataAndamento,ENCAMINHAR_E,idAgrupamentoEstadoTpProcFt,idGrupoAtual);
                gravaAndamentoMotivo(dataAtual,idAndamento2);
                gravaAtendimentoGrupoAtual(dataAtual,dataEntrada,dados->idAtendimento
                                          ,idGrupoAtual,dadosValidacao->idSequencia
                                          ,dados->idUsuarioBKO);
                gravouGrupoAtual=true;
            }
            else
            { // se não encontrou um gerente, então envia para o consultor
                strcpy(szIdDestPossFormulario,"CR");
            }
        }

        if ( strcmp(szIdDestPossFormulario,"CR")==0 )
        { // Gestor de Contas ?
            int idGrupoAtual;
            int idPessoaUsuarioGestorPJ;
            if ( ecpc.proCObtemGestorPJ(dados->nrDocumento,&idPessoaUsuarioGestorPJ,&idGrupoAtual) )
            {
                gravaAtendimentoUsuarioAtual(dados->idAtendimento,idPessoaUsuarioGestorPJ,dataAtual);
                int idAgrupamentoEstadoTpProcFt =
                              ecpc.proCConsultaProximoAgrupamento(ENCAMINHAR_E,dadosValidacao->proximoAgrupamento);
                long idAndamento2 = gravaAndamento(dataAndamento,ENCAMINHAR_E,idAgrupamentoEstadoTpProcFt,idGrupoAtual);
                gravaAndamentoMotivo(dataAtual,idAndamento2);
                gravaAtendimentoGrupoAtual(dataAtual,dataEntrada,dados->idAtendimento
                                          ,idGrupoAtual,dadosValidacao->idSequencia
                                          ,dados->idUsuarioBKO);
                gravouGrupoAtual=true;
            }
        }

        if ( !gravouGrupoAtual )
        { // se não encaminhou para ninguém, então fica com o grupo de tratamento oficial
            gravaAtendimentoGrupoAtual(dataAtual,dataEntrada,dados->idAtendimento
                                      ,dadosValidacao->idProxGrupo
                                      ,dadosValidacao->idSequencia,dados->idUsuarioBKO);

            if ( 1 == dadosValidacao->inAssociado )
            {
                gravaAtendimentoEncIncorreto();
            }
        }
    }
    else if ( !isCRI ) // Nao houve tratamento CRI
    {
        gravaAtendimentoGrupoAtual(dataAtual,dataEntrada,dados->idAtendimento
                                  ,dadosValidacao->idProxGrupo
                                  ,dadosValidacao->idSequencia,dados->idUsuarioBKO);

        if ( 1 == dadosValidacao->inAssociado )
        {
            gravaAtendimentoEncIncorreto();
        }
    }

    // Se regra de fluxo de encaminhamento MeuCliente for MeuCLiente2 o processo irá para a 
    // fila BKO mas será encerrado por um analista 'Meu Cliente'
    //if ( strcmp(dados->sgFluxoAtendimento,"MC2")==0 )
    //{
    //    gravaAtendimentoGrpUsuDevolucao(dados->idAtendimento,dadosValidacao->idProxGrupo,dados->idUsuarioBKO);
    //}

    // A rotina proC que faz a inserção em andamento faz isso automaticamente - Out/2005 - Cassio
    // gravaAtendimentoAndamentoAtual(dataAtual, &idAndamento);

    // Grava atendimento nivel para fase ABERTURA
    if( dadosCRI == 0 )
    {
        gravaAtendimentoNivel(dataAtual,1,dados->idGrupoAbertura,dados->idAtividade);
    }
    else
    {
        gravaAtendimentoNivel(dataAtual,1,dadosCRI->idGrupoAbertura,1);
    }

    // Se atendimento é CRI e foi encaminhado para inbox de consultor CRI
    // deve informar na aba nivel sobre o encaminhamento para a fase
    // de TRATAMENTO.

    // ==========================================================================
    // ==> Incidência    3236
    // Esta parte do codigo foi retirada com alinha mento da funcional
    // Tatiana , informou ,no momento da aberturo o processo só deve 
    // exibir na aba nível   o grupo e nem uma ação associada ao grupo
    // foi identificado que a funcao  gravaAtendimentoNivel() apesar de
    // receber parâmetro os mesmos não são usados na função isto foi corrigido
    // <== Incidência 3288 
    // ==========================================================================
    // código comentado para a incidência  3288   
    // if ( isCRI == PROC_CRI_ENC_INBOX )
    // {
     //    gravaAtendimentoNivel(dataAtual,2,idGrupoCRIAssocUser,3);
    // }

    // ==========================================================================
    // ==> Incidência 3288 
    // Esta incidência especifica a abertura por conta para todos os tipos de
    // atendimento. Portanto a tabela ATENDIMENTOPORCONTACRI fica sem finalidade
    // a partir desta versão do código.

    // if(dadosCRI->atendimentoPorConta && isCRI )
    // {
    //  ecpc.proCInsereAtendimentoPorContaCRI(dados->idAtendimento);
    // }
    // <== Incidência 3288 
    // ==========================================================================

    // ==> Remodelagem Atendimento--Fev/2007--Cassio 
    // if (dados->idPessoaLinhaHistorico > 0)
    // {
    //     if ( ecpc.existeContatoLinha(dados->idContato, dados->idPessoaLinhaHistorico) == false )
    //     {
    //         ecpc.registraContatoLinha(dados->idAtendimento,dados->idContato
    //                                  ,dados->idPessoaLinhaHistorico,dados->idUsuarioBKO);
    //     }
    //     else
    //     {
    //         ecpc.atualizarContatoLinha(dados->idAtendimento,dados->idContato
    //                                   ,dados->idPessoaLinhaHistorico,dados->idUsuarioBKO);
    //     }
    // }
    
    ULOG_END( "cEncContato::registra()" );

    return false;
}

/**
    Registra os dados de andamento do atendimento.
*/
void cEncContato::gravaAtendimentoGrupoAtual(const char* _dataAtual,const char *_dataEntrada
                                            ,long  _idAtendimento,int _idGrupo
                                            ,int _idSequencia,int _idUsuarioBKO) 
{
    ULOG_START( "cEncContato::gravaAtendimentoGrupoAtual()" );

    //cWFAtendimento atd;
    //char dataEntrada[64];
    //double horas = 24;
    //int incremento = 0;
    //int idUFOperadoraNaoCliente = 0;
    
    ULOG( "idAtendimento = %d",_idAtendimento);

    /////////////////////////////////////////////////////////////
    // Incidência 2491 - WR-RS
    //if (dados->idTipoReaberturaProcesso == 2)
    //    ecpc.proCDataEntrada(dados->idAtendimentoOrigem, dados->nrTelefone, dataEntrada);
    //else
    //    atd.diasUteis(dados->nrTelefone, horas, incremento, dataEntrada);

    st_AtendimentoGrupoAtual    m_stDados;
    st_vlAtendimentoGrupoAtual  m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento         = _idAtendimento;
    m_stDados.idGrupo               = _idGrupo;
    //m_stDados.inAssociado           = _inAssociado;
    m_stDados.idSequencia           = _idSequencia;
    m_stDados.idUsuarioAlteracao    = _idUsuarioBKO;

    strcpy(m_stDados.dtEntradaBKO     , _dataEntrada);
    strcpy(m_stDados.dtEntradaFila    , _dataAtual);
    strcpy(m_stDados.dtUltimaAlteracao, _dataAtual);

    m_vlDados.idAtendimento             = 1;
    m_vlDados.idGrupo                   = 1;
    //m_vlDados.inAssociado               = 1;
    m_vlDados.idSequencia               = 1;
    m_vlDados.dtEntradaBKO              = 1;
    m_vlDados.dtEntradaFila             = 1;
    m_vlDados.idUsuarioAlteracao        = 1;
    m_vlDados.dtUltimaAlteracao         = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen retorno;
    cWFAtendimentoGrupoAtual atga(&m_stDados, &m_vlDados, &retorno);

    if ( false == dados->isPortout )
    { // processos de portout não são registrados em grupo bko
        //atga.incluirBko();
        atga.atualizarBko();
    }

    atga.incluir(xmlDpr);

    ULOG_END( "cEncContato::gravaAtendimentoGrupoAtual()" );
}

/**
    Registra o encaminhamento incorreto
*/
void cEncContato::gravaAtendimentoEncIncorreto() 
{
    ULOG_START( "cEncContato::gravaAtendimentoEncIncorreto()" );

    st_AtdEncaminhadoIncorreto dadosLoc;
    st_vlAtdEncaminhadoIncorreto statusLoc;

    memset(&dadosLoc,0,sizeof(dadosLoc));
    memset(&statusLoc,-1,sizeof(statusLoc));

    dadosLoc.idCanal = dados->idCanal;
    dadosLoc.idContato = dados->idContato;
    dadosLoc.idGrupo = dados->idGrupo;
    dadosLoc.idGrupoAbertura = dados->idGrupoAbertura;
    dadosLoc.idProcedencia = dados->idProcedencia;
    dadosLoc.idSegmentacao = dados->idSegmentacao;
    dadosLoc.idTipoCarteira = dados->idTipoCarteira;
    dadosLoc.idTipoLinha = dados->idTipoLinha;
    dadosLoc.idTipoPessoa = dados->idTipoPessoa;
    dadosLoc.idTipoRelacionamento = dados->idTipoRelacionamento;
    dadosLoc.idUsuarioAlteracao = dados->idUsuarioBKO;
    dadosLoc.dtAbertura = dados->dtAbertura;

    statusLoc.idCanal = 1;
    statusLoc.idContato = 1;
    statusLoc.idGrupo = 1;
    statusLoc.idGrupoAbertura = 1;
    statusLoc.idProcedencia = 1;
    statusLoc.idSegmentacao = 1;
    statusLoc.idTipoCarteira = 1;
    statusLoc.idTipoLinha = 1;
    statusLoc.idTipoPessoa = 1;
    statusLoc.idTipoRelacionamento = 1;
    statusLoc.idUsuarioAlteracao = 1;
    statusLoc.dtAbertura = 1;

    // Executa a chamada de inclusão do registro.
    cWFAtdEncaminhadoIncorreto cwfatdencaminhadoincorreto;

    cwfatdencaminhadoincorreto.incluir(&dadosLoc,&statusLoc);

    ULOG_END( "cEncContato::gravaAtendimentoEncIncorreto()" );
}

/**
    Grupo e Usuário de Retorno
*/
void cEncContato::gravaAtendimentoGrpUsuDevolucao(long  _idAtendimento,int _idGrupoAtual,int _idPessoaUsuario)
{
    ULOG_START( "cEncContato::gravaAtendimentoGrpUsuDevolucao()" );

	// Marca o grupo de devolução do processo para garantir encerramento pelo analista que o abriu
	st_AtendimentoGrupoDevolucao m_stDadosGrupoDevolucao;
	st_vlAtendimentoGrupoDevolucao m_vlDadosGrupoDevolucao;
	XMLGen AtdGrpRecep;

	memset(&m_stDadosGrupoDevolucao, 0, sizeof(m_stDadosGrupoDevolucao));
	memset(&m_vlDadosGrupoDevolucao,-1, sizeof(m_vlDadosGrupoDevolucao));

	m_stDadosGrupoDevolucao.idAtendimento=_idAtendimento;
	m_vlDadosGrupoDevolucao.idAtendimento=1;
	m_stDadosGrupoDevolucao.idGrupo=_idGrupoAtual;
	m_vlDadosGrupoDevolucao.idGrupo=1;

	cWFAtendimentoGrupoDevolucao AtendimentoGrupoDevolucao(&m_stDadosGrupoDevolucao, &m_vlDadosGrupoDevolucao, &AtdGrpRecep);

	AtendimentoGrupoDevolucao.incluir();

	// Marca o usuário de devolução do processo para garantir encerramento pelo analista que o abriu
	st_AtendimentoUsuarioDevolucao m_stDadosUsuarioDevolucao;
	st_vlAtendimentoUsuarioDevolucao m_vlDadosUsuarioDevolucao;
	XMLGen AtdUsrRecep;

	memset(&m_stDadosUsuarioDevolucao, 0, sizeof(m_stDadosUsuarioDevolucao));
	memset(&m_vlDadosUsuarioDevolucao,-1, sizeof(m_vlDadosUsuarioDevolucao));

	m_stDadosUsuarioDevolucao.idAtendimento=_idAtendimento;
	m_vlDadosUsuarioDevolucao.idAtendimento=1;
	m_stDadosUsuarioDevolucao.idPessoaUsuario=_idPessoaUsuario;
	m_vlDadosUsuarioDevolucao.idPessoaUsuario=1;

	cWFAtendimentoUsuarioDevolucao AtendimentoUsuarioDevolucao(&m_stDadosUsuarioDevolucao, &m_vlDadosUsuarioDevolucao, &AtdUsrRecep);

	AtendimentoUsuarioDevolucao.incluir();

    ULOG_END( "cEncContato::gravaAtendimentoGrpUsuDevolucao()" );
}

/**
    Inbox de usuário
*/
void cEncContato::gravaAtendimentoUsuarioAtual(long  _idAtendimento,int _idPessoaUsuario,const char *_dataAtual) 
{
    ULOG_START( "cEncContato::gravaAtendimentoUsuarioAtual()" );

	st_AtendimentoUsuarioAtual m_stDados;
	st_vlAtendimentoUsuarioAtual m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento         = _idAtendimento;
    m_stDados.idPessoaUsuario       = _idPessoaUsuario;
    m_stDados.idUsuarioAlteracao    = _idPessoaUsuario;
    strcpy(m_stDados.dtUltimaAlteracao, _dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idPessoaUsuario       = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    cWFAtendimentoUsuarioAtual atua(&m_stDados, &m_vlDados);

    atua.incluir(xmlDpr);

    ULOG_END( "cEncContato::gravaAtendimentoUsuarioAtual()" );
}

/**
    Altera o tipo de retorno do processo
*/
void cEncContato::AlterarTipoRetornoContatoCRI(long  _idAtendimento,int _idPessoaUsuario,const char *_dataAtual)
{
    ULOG_START( "cEncContato::AlterarTipoRetornoContatoCRI()" );

	st_Atendimento m_stDados;
	st_vlAtendimento m_vlDados;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento         = _idAtendimento;
    m_stDados.idTipoRetornoContato  = 4;
    m_stDados.idUsuarioAlteracao    = _idPessoaUsuario;

    strcpy(m_stDados.dtUltimaAlteracao, _dataAtual);

    m_vlDados.idAtendimento         = 1;
    m_vlDados.idTipoRetornoContato  = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    XMLGen xml_g;
    cWFAtendimento cwfatendimento(&m_stDados,&m_vlDados, &xml_g);

    cwfatendimento.alterar(xmlDpr);

    ULOG_END( "cEncContato::AlterarTipoRetornoContatoCRI()" );
}

/**
    Registra os dados de andamento do atendimento.
*/
long cEncContato::gravaAndamento(char* dataAtual,int idAtividade,int idAgrupamentoEstadoTpProcFt,int idGrupoAtual) 
{
    ULOG_START( "cEncContato::gravaAndamento()" );

    st_Andamento m_stDados;
    st_vlAndamento m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAtividade               = idAtividade;
    m_stDados.idAgrupamentoEstadoTpProc = idAgrupamentoEstadoTpProcFt;
    m_stDados.idAtendimento             = dados->idAtendimento;
    m_stDados.idPessoaUsuario           = dados->idUsuarioBKO;
    m_stDados.idUsuarioAlteracao        = dados->idUsuarioBKO;
    m_stDados.idGrupo                   = idGrupoAtual;
    strcpy(m_stDados.dtAndamento        , dataAtual);
    strcpy(m_stDados.dtUltimaAlteracao  , dataAtual);

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
    cWFAndamento andx( &m_stDados, &m_vlDados, &retorno );

    long idAndamento = andx.incluir(xmlDpr);

    ULOG_END( "cEncContato::gravaAndamento()" );

    return idAndamento; 
}

/**
    Registra os dados de andamento observacao.
*/
void cEncContato::gravaAndamentoObservacao(char* dataAtual, long idAndamento) 
{
    ULOG_START( "cEncContato::gravaAndamentoObservacao()" );

    st_AndamentoObservacao      m_stDados;
    st_vlAndamentoObservacao    m_vlDados;
    
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
    
    m_stDados.idAndamento                   = idAndamento;
    m_stDados.pdsAndamentoObservacao        = "Encaminhamento Automático";
    m_stDados.idUsuarioAlteracao            = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,     dataAtual);

    m_vlDados.idAndamento           = 1;
    m_vlDados.dsAndamentoObservacao = 1;
    m_vlDados.idUsuarioAlteracao    = 1;
    m_vlDados.dtUltimaAlteracao     = 1;

    // Executa a chamada de inclusão do registro.
    cWFAndamentoObservacao ando(&m_stDados, &m_vlDados, 0);

    ando.incluir(xmlDpr);

    ULOG_END( "cEncContato::gravaAndamentoObservacao()" );
}

//------------------------------------------------------------------------------------------------------------------    
// Inclusao do Andamento Motivo
//------------------------------------------------------------------------------------------------------------------    
void cEncContato::gravaAndamentoMotivo(char* dataAtual, long idAndamento) 
{
    ULOG_START( "cEncContato::gravaAndamentoMotivo()" );

    st_AndamentoMotivo   m_stDados;
    st_vlAndamentoMotivo m_vlDados; 
    XMLGen xmlObtemAndamento;

    memset( &m_stDados , 0, sizeof( st_AndamentoMotivo  )); 
    memset( &m_vlDados,-1, sizeof( st_vlAndamentoMotivo));
	
	ULOG( "*** idAndamento [%ld]\n", idAndamento );
	
    m_stDados.idAndamento = idAndamento;
    m_stDados.idMotivo = 100;
    m_stDados.idFase = dados->idFase;
    m_stDados.idUsuarioAlteracao = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,     dataAtual);

    m_vlDados.idAndamento = 1;
    m_vlDados.idMotivo = 1;
    m_vlDados.idFase = 1;
    m_vlDados.idUsuarioAlteracao = 1;
    m_vlDados.dtUltimaAlteracao = 1;

    cWFAndamentoMotivo cwfAndamentoMotivo(&m_stDados,&m_vlDados,&xmlObtemAndamento);
    cwfAndamentoMotivo.incluir();

    ULOG_END( "cEncContato::gravaAndamentoMotivo()" );
}

/**
    Registra os dados do atendimento andamento atual.
*/
void cEncContato::gravaAtendimentoAndamentoAtual(char* dataAtual, long idAndamento)
{
    ULOG_START( "cEncContato::gravaAtendimentoAndamentoAtual()" );

    st_AtendimentoAndamentoAtual    m_stDados;
    st_vlAtendimentoAndamentoAtual  m_vlDados;
    
    memset( &m_stDados, 0,sizeof(m_stDados) );
    memset( &m_vlDados,-1,sizeof(m_vlDados) );

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

    ULOG_END( "cEncContato::gravaAtendimentoAndamentoAtual()" );

}

/**
    Registra os dados do atendimento andamento atual.
*/
void cEncContato::gravaAtendimentoNivel(char* dataAtual,int idFase,int idGrupo,int idAtividade)
{
    ULOG_START( "cEncContato::gravaAtendimentoNivel()" );

    st_AtendimentoNivel     m_stDados;
    st_vlAtendimentoNivel   m_vlDados;
    XMLGen retorno;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    m_stDados.idAtendimento = dados->idAtendimento;
    m_stDados.idFase = idFase;
    m_stDados.nrNivel = 0;
    m_stDados.idUsuarioAlteracao = dados->idUsuarioBKO;
    strcpy(m_stDados.dtUltimaAlteracao,dataAtual);
    strcpy(m_stDados.dtNivel,dataAtual);

    // NOVO CODIGO 3236
    m_stDados.idGrupo = idGrupo;
    m_stDados.idAtividade = idAtividade;


    m_vlDados.idAtendimento = 1;
    m_vlDados.idGrupo = 1;
    m_vlDados.idFase = 1;
    m_vlDados.idAtividade = 1;
    m_vlDados.nrNivel = 1;
    m_vlDados.idUsuarioAlteracao = 1;
    m_vlDados.dtUltimaAlteracao = 1;
    m_vlDados.dtNivel = 1;

    cWFAtendimentoNivel an(&m_stDados, &m_vlDados, &retorno);

    an.incluir();

    ULOG_END( "cEncContato::gravaAtendimentoNivel()" );
}


int cEncContato::ProcessaRelacionamentoCRI( const char * _dataAtual,const char * _dtEntrada,unsigned long &idGrupoCRIAssocUser)
{
    ULOG_START( "cEncContato::ProcessaRelacionamentoCRI()" );

    unsigned long idGrupoCRI = 0;
    unsigned long idGrupoCRIUser = 0;
    long idPessoaUsuario = 0;
    bool retorno;

    // Modificação de critério de acordo com a incidência 3274
    // Busca um grupo CRI que esteja associado a linha do processo sendo aberto
    retorno = ecpc.proCListaGrupoAssociadoCRI(dados->idPessoaLinhaHistorico
                                             ,&idGrupoCRI
                                             ,dadosCRI->idUFOperadora
                                             ,dadosCRI->idGrupoAbertura);

    if ( retorno == false )
    {
        // Se não existe linha associada diretamente a um grupo então tenta encontrar
        // o grupo/consultor, do último processo aberto para esta linha. Esta rotina
        // pode não trazer o id do consultor, caso o último processo gerado para a linha
        // tenha sido cancelado/fechado diretamente na fila (fora de um inbox de CRI).
        // idGrupoCRIUser = idGrupoCRI;
        ecpc.proCBuscaConsultorDentroPrazo(dados->idPessoaLinhaHistorico
                                          ,dados->idContato
                                          ,&idPessoaUsuario
                                          ,&idGrupoCRI);
    }

    //  Se não encontrou um grupo CRI para o processo, busca o grupo CRI associado ao perfil
    //  do processo sendo criado
    if ( idGrupoCRI == 0 )
    {
        ecpc.proCComprovaVariaveis( dados->idPessoaLinhaHistorico
                                  , dadosCRI
                                  , &idGrupoCRI );
    }

    if ( idGrupoCRI > 0 )
    {
        if ( idPessoaUsuario > 0 )
        {
            idGrupoCRIAssocUser = idGrupoCRI;

            // Se o grupo atual do usuário atende ao perfil do novo processo ...
            if ( idGrupoCRIAssocUser > 0 )
            {
                ULOG(">>> Grupo CRI original encontado=%d,grupo usuario=%d",idGrupoCRI,idGrupoCRIAssocUser);

                //  Insere em atendimento.grupoCRI
                ecpc.proCInsereAtendimentoGrupoCRI(dados->idAtendimento,idGrupoCRIAssocUser,dados->idPessoaLinhaHistorico);

                //  Insere em atendimento.tratamentoGrupoCRI
                ecpc.proCInsereAtdTratamentoGrupoCRI(dados->idAtendimento,idGrupoCRIAssocUser);
                // =========================================================
                // ==> SM324--DPR--DEZ/2006--Cassio
                // Esforço para uso de classes de acesso a dados comuns em todo o
                // workflow para atender ao negócio da SM minimizando esforço de
                // manutenção posterior.
                //
                //  Insere em atendimento.atendimentoGrupoAtual
                // ecpc.proCInsereAtendimentoGrupoAtual(dados->idAtendimento,idGrupoCRIAssocUser,_dtEntrada);
                gravaAtendimentoGrupoAtual(_dataAtual,_dtEntrada,dados->idAtendimento
                                          ,idGrupoCRIAssocUser,0
                                          ,dados->idUsuarioBKO);
                //
                //  Insere em atendimento.atendimentoUsuarioAtual
                // ecpc.proCInsereAtendimentoUsuarioAtual(dados->idAtendimento,idPessoaUsuario );
                gravaAtendimentoUsuarioAtual(dados->idAtendimento,idPessoaUsuario,_dataAtual);
                // <== SM324--DPR--DEZ/2006--Cassio
                // =========================================================
                //
                //  Insere em atendimento.atendimentoCRI
                ecpc.proCInsereAtendimentoCRI(dados->idAtendimento,idPessoaUsuario,dados->idPessoaLinhaHistorico );

                //  Insere em atendimento.tratamentoCRI
                ecpc.proCInsereTratamentoCRI( idPessoaUsuario,dados->idAtendimento );

                // =========================================================
                // ==> SM324--DPR--DEZ/2006--Cassio
                // Esforço para uso de classes de acesso a dados comuns em todo o
                // workflow para atender ao negócio da SM minimizando esforço de
                // manutenção posterior.
                //
                //  Altera o tipo de retorno do contato do processo para RETORNO POR GRUPO CRI
                // ecpc.proCAtualizarTipoRetornoContatoCRI(dados->idAtendimento);
                AlterarTipoRetornoContatoCRI(dados->idAtendimento,dados->idUsuarioBKO,_dataAtual);
                // <== SM324--DPR--DEZ/2006--Cassio
                // =========================================================

                ULOG("Grupo CRI registrado e processo encaminhado para inbox.");

                // Correcao para incidencia 3214
                // No historico do processo, aparecia o usuario CRI que iria tratar, e nao o usuario que 
                // realizou a acao.
                /* dados->idUsuarioBKO = idPessoaUsuario; */ // assume como BKO o usuário CRI para o qual o processo esta sendo encaminhado

                dados->idAgrupamentoEstadoTpProc++; // força passagem de TRATAMENTO FILA para TRATAMENTO BKO

                //(incidência 3175 CRI Out/2005) dados->idGrupoAbertura = idGrupoCRIAssocUser; // andamento grava o grupo CRI

                ULOG_END( "cEncContato::ProcessaRelacionamentoCRI()" );

                return PROC_CRI_ENC_INBOX; // Encontrou processo CRI similar em um inbox
            }
            else
            {
                ULOG(">>> Usuario %d encontrado nao esta associado a grupo CRI que se encaixe no perfil do contato",idPessoaUsuario);
            }
        }
        else
        {
            ULOG(">>> Nao foi encontrado o id do usuario que esta tratando o processo");
        }

        //  Insere em atendimento.grupoCRI
        ecpc.proCInsereAtendimentoGrupoCRI(dados->idAtendimento,idGrupoCRI,dados->idPessoaLinhaHistorico);

        //  Insere em atendimento.atendimentoGrupoAtual
        // ecpc.proCInsereAtendimentoGrupoAtual(dados->idAtendimento,idGrupoCRI,_dtEntrada);
        gravaAtendimentoGrupoAtual(_dataAtual,_dtEntrada,dados->idAtendimento
                                  ,idGrupoCRI,0
                                  ,dados->idUsuarioBKO);

        //  Altera o tipo de retorno do contato do processo para RETORNO POR GRUPO CRI
        ecpc.proCAtualizarTipoRetornoContatoCRI(dados->idAtendimento);

        dados->idGrupoAbertura = idGrupoCRI;

        ULOG(">>> Processo CRI não foi enviado para inbox");

        ULOG_END( "cEncContato::ProcessaRelacionamentoCRI()" );

        return PROC_CRI_ENC_FILA;  // Encaminhou para fila CRI
    }

    ULOG(">>> Atendimento nao se enquadrou para CRI");

    ULOG_END( "cEncContato::ProcessaRelacionamentoCRI()" );

    return 0;  // Deve encaminhar BKO
}
