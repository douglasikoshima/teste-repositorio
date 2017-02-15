/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:25 $
 **/

#include "../include/cWFAtendimento.h"
#include "../../../commons/msgPadrao.h"
#include "../include/stWFAtendimentoPOPC.h"
//
//=================================================================================
// IMPLEMENTAÇÃO
//
bool cWFAtendimento::consultarFilaPortOut(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaPortOut(Collection*,int)" );

    carregaDadosConsultaFila();

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    ULOG("=== VALORES ===");
    ULOG("Filtro por data de Fechamento = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Filtro por Campos dinamicos = [%d]", m_stFila.nrCampos);
    ULOG("Filtro por numero de linha = [%s]", m_stFila.nrLinha);
    ULOG("Filtro por usuario = [%d]", m_stFila.idPessoaUsuario);
    ULOG("Filtro por grupo = [%d]", m_stFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio = [%s]", m_stFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim = [%s]", m_stFila.dtAberturaFim);
    ULOG("Filtro por data de Fechamento Inicio = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Filtro por data de Fechamento Fim = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Filtro por Atendimento = [%lu]", m_stFila.idAtendimento);
    ULOG("Filtro por Contato = [%d]", m_stFila.idContato);
    ULOG("Filtro por Estado = [%d]", m_stFila.idEstado);
    ULOG("Filtro por SubEstado = [%d]", m_stFila.idSubEstado);
    ULOG("Filtro por UsuarioGrupo = [%d]", m_stFila.idUsuarioGrupo);
    ULOG("Filtro por idAtendimento = [%lu]", m_stFila.idAtendimento);
    ULOG("Filtro por idAlerta = [%d]", m_stFila.idAlerta);
    ULOG("Filtro por idTipoCarteira = [%d]", m_stFila.idTipoCarteira);
    ULOG("Filtro por idSegmentacao = [%d]", m_stFila.idSegmentacao);
    ULOG("Filtro por idAtendimentoProtocolo = [%s]", m_stFila.idAtendimentoProtocolo);
    ULOG("Filtro por nrProtocoloPortabilidade = [%s]", m_stFila.nrProtocoloPortabilidade);
    ULOG("Filtro por nrConta = [%s]", m_stFila.nrConta);
    ULOG("Filtro por idClassificacaoTipoLinha = [%s]", m_stFila.idClassificacaoTipoLinha);
    //
    ULOG("=== STATUS ===");
    ULOG("Filtro por data de Fechamento = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Filtro por Campos dinamicos = [%d]", m_vlFila.nrCampos);
    ULOG("Filtro por numero de linha = [%d]", m_vlFila.nrLinha);
    ULOG("Filtro por usuario = [%d]", m_vlFila.idPessoaUsuario);
    ULOG("Filtro por grupo = [%d]", m_vlFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio = [%d]", m_vlFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim = [%d]", m_vlFila.dtAberturaFim);
    ULOG("Filtro por data de Fechamento Inicio = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Filtro por data de Fechamento Fim = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Filtro por Atendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("Filtro por idAlerta = [%d]", m_vlFila.idAlerta);
    ULOG("Filtro por idTipoCarteira = [%d]", m_vlFila.idTipoCarteira);
    ULOG("Filtro por idSegmentacao = [%d]", m_vlFila.idSegmentacao);
    ULOG("Filtro por Contato = [%d]", m_vlFila.idContato);
    ULOG("Filtro por Estado = [%d]", m_vlFila.idEstado);
    ULOG("Filtro por SubEstado = [%d]", m_vlFila.idSubEstado);
    ULOG("Filtro por UsuarioGrupo = [%d]", m_vlFila.idUsuarioGrupo);
    ULOG("Filtro por idAtendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("Filtro por idAtendimentoProtocolo = [%d]", m_vlFila.idAtendimentoProtocolo);
    ULOG("Filtro por nrProtocoloPortabilidade = [%d]", m_vlFila.nrProtocoloPortabilidade);
    ULOG("Filtro por nrConta = [%d]", m_vlFila.nrConta);
    ULOG("Filtro por idClassificacaoTipoLinha = [%d]", m_vlFila.idClassificacaoTipoLinha);

    bool retorno;

    // Se o usuário é um consultor de relacionamento então este verá 
    // somente processos associados ao mesmo.
    if ( proCConsultorRelacionamentoSimNao(idPessoaUsuario) == true )
    {
        retorno = consultarFilaPortOutConsRelacionamento(_grupos,idPessoaUsuario);
    }
    else
    {
        retorno = consultarFilaPortOutSemConsRelacionamento(_grupos,idPessoaUsuario);
    }

    ULOG_END( "cWFAtendimento::consultarFilaPortOut(Collection*,int)" );

    return retorno;
}
//
//=================================================================================
///\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
// ESTAS PESQUISAS RETORNAM RESULTADOS NÃO ATRELADOS A CONSULTOR DE RELACIONAMENTO
//\\//\\/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/\\/\\//\\//\\//
//
bool cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );

    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOPorAtendimento(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOPorAtendimentoQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // OS de Portabilidade
    // Pesquisa pelo numero do protocolo de portabilidade
    //
    if (m_vlFila.nrProtocoloPortabilidade != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        	return proCConsultaWFFilaPOPorProtocoloPO(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOPorProtocoloPOQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // Quando o idAtendimentoProtocolo for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    // Adaptação para OS 661-SMP -- Jan/2008 -- Cassio
    //
    if (m_vlFila.idAtendimentoProtocolo != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOPorProtocolo(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOPorProtocoloQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // Quando o número da linha for preenchido, realizamos a consulta ignorando os demais parâmetros
    // considerando apenas se foi indicado se a consulta é em processos abertos ou fechados
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    // Para esta consulta específica os campos de data estarão vazios
    if ( (m_vlFila.nrLinha != -1) && ( STRLENNULL(m_stFila.dtAberturaInicio)==0 ) && ( STRLENNULL(m_stFila.dtFechamentoInicio)==0 ) )
    {
        // Procura por fechados ou Abertos? 
        if(m_vlFila.inFilaAbertos != -1) 
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOAbertosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOAbertosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    //
    // Quando o número da linha for preenchido, realizamos a consulta ignorando os demais parâmetros
    // considerando apenas se foi indicado se a consulta é em processos abertos ou fechados
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    // APLIQUEI A MESMA REGRA À PESQUISA POR CONTA POR SER SIMILAR À PESQUISA POR LINHA
    // Para esta consulta específica os campos de data estarão vazios
    if ( (m_vlFila.nrConta != -1) && ( STRLENNULL(m_stFila.dtAberturaInicio)==0 ) && ( STRLENNULL(m_stFila.dtFechamentoInicio)==0 ) )
    {
        // Procura por fechados ou Abertos? 
        if(m_vlFila.inFilaAbertos != -1) 
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOAbertosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOAbertosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOFechadosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOFechadosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.nrCampos != 0)
    {
        // Tem idPessoaUsuario ?
        if( m_vlFila.idPessoaUsuario != -1 )
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaPO(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaPOQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaPO(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaPOQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOUsuarioLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOUsuarioLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.idPessoaUsuario != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOUsuario(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPO(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOQtd(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarFilaPortOutSemConsRelacionamento(Collection*,int)" );
}
//
//==============================================================================
///\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\
// PESQUISAS ATRELADAS A CONSULTOR DE RELACIONAMENTO
//\\//\\/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/\\/\\//\\//\
//
bool cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRPorAtendimento(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOCRPorAtendimentoQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // OS de Portabilidade
    // Pesquisa pelo numero do protocolo de portabilidade
    //
    if (m_vlFila.nrProtocoloPortabilidade != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        	return proCConsultaWFFilaPOCRPorProtocoloPO(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOCRPorProtocoloPOQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // Quando o idAtendimentoProtocolo for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    // Adaptação para OS 661-SMP -- Jan/2008 -- Cassio
    //
    if (m_vlFila.idAtendimentoProtocolo != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRPorProtocolo(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOCRPorProtocoloQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // Quando o número da linha for preenchido, realizamos a consulta ignorando os demais parâmetros
    // considerando apenas se foi indicado se a consulta é em processos abertos ou fechados
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    // Para esta consulta específica os campos de data estarão vazios
    if ( (m_vlFila.nrLinha != -1) && ( STRLENNULL(m_stFila.dtAberturaInicio)==0 ) && ( STRLENNULL(m_stFila.dtFechamentoInicio)==0 ) )
    {
        // Procura por fechados ou Abertos? 
        if(m_vlFila.inFilaAbertos != -1) 
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRAbertosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRAbertosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    //
    // Quando o número da linha for preenchido, realizamos a consulta ignorando os demais parâmetros
    // considerando apenas se foi indicado se a consulta é em processos abertos ou fechados
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    // APLIQUEI A MESMA REGRA À PESQUISA POR CONTA POR SER SIMILAR À PESQUISA POR LINHA
    // Para esta consulta específica os campos de data estarão vazios
    if ( (m_vlFila.nrConta != -1) && ( STRLENNULL(m_stFila.dtAberturaInicio)==0 ) && ( STRLENNULL(m_stFila.dtFechamentoInicio)==0 ) )
    {
        // Procura por fechados ou Abertos? 
        if(m_vlFila.inFilaAbertos != -1) 
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRAbertosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRAbertosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRFechadosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRFechadosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_stFila.nrCampos != 0)
    {
        // Tem idPessoaUsuario ?
        if( m_vlFila.idPessoaUsuario != -1 )
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaPOCR(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaPOCRQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaPOCR(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaPOCRQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRUsuarioLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRUsuarioLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaPOCRLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.idPessoaUsuario != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCRUsuario(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOCRUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaPOCR(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaPOCRQtd(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarFilaPortOutConsRelacionamento(Collection*,int)" );
}
