/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio Garcia
 * @version $Revision: 1.2.4.3.90.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:35:29 $
 **/

#include "../include/cWFAtendimento.h"
#include "../../../commons/msgPadrao.h"
#include "../include/stWFAtendimentoMCPC.h"
//
//=================================================================================
// IMPLEMENTAÇÃO
//
bool cWFAtendimento::consultarFilaMeuCliente(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaMeuCliente(Collection*,int)" );

    carregaDadosConsultaFila();

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    ULOG("=== VALORES ===");
    ULOG("data de Fechamento = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Campos dinamicos = [%d]", m_stFila.nrCampos);
    ULOG("numero de linha = [%s]", m_stFila.nrLinha);
    ULOG("usuario = [%d]", m_stFila.idPessoaUsuario);
    ULOG("grupo = [%d]", m_stFila.idGrupo);
    ULOG("data de Abertura Inicio = [%s]", m_stFila.dtAberturaInicio);
    ULOG("data de Abertura Fim = [%s]", m_stFila.dtAberturaFim);
    ULOG("data de Fechamento Inicio = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("data de Fechamento Fim = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Atendimento = [%d]", m_stFila.idAtendimento);
    ULOG("Contato = [%d]", m_stFila.idContato);
    ULOG("Estado = [%d]", m_stFila.idEstado);
    ULOG("SubEstado = [%d]", m_stFila.idSubEstado);
    ULOG("UsuarioGrupo = [%d]", m_stFila.idUsuarioGrupo);
    ULOG("idAtendimento = [%d]", m_stFila.idAtendimento);
    ULOG("idAlerta = [%d]", m_stFila.idAlerta);
    ULOG("idTipoCarteira = [%d]", m_stFila.idTipoCarteira);
    ULOG("idSegmentacao = [%d]", m_stFila.idSegmentacao);
    ULOG("idAtendimentoProtocolo = [%s]", m_stFila.idAtendimentoProtocolo);
    ULOG("nrProtocoloPortabilidade = [%s]", m_stFila.nrProtocoloPortabilidade);
    ULOG("nrConta = [%s]", m_stFila.nrConta);
    ULOG("idClassificacaoTipoLinha = [%s]", m_stFila.idClassificacaoTipoLinha);
    ULOG("inPesquisaFullMC = [%d]", m_stFila.inPesquisaFullMC);
    //
    ULOG("=== STATUS ===");
    ULOG("data de Fechamento = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Campos dinamicos = [%d]", m_vlFila.nrCampos);
    ULOG("numero de linha = [%d]", m_vlFila.nrLinha);
    ULOG("usuario = [%d]", m_vlFila.idPessoaUsuario);
    ULOG("grupo = [%d]", m_vlFila.idGrupo);
    ULOG("data de Abertura Inicio = [%d]", m_vlFila.dtAberturaInicio);
    ULOG("data de Abertura Fim = [%d]", m_vlFila.dtAberturaFim);
    ULOG("data de Fechamento Inicio = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("data de Fechamento Fim = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Atendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("idAlerta = [%d]", m_vlFila.idAlerta);
    ULOG("idTipoCarteira = [%d]", m_vlFila.idTipoCarteira);
    ULOG("idSegmentacao = [%d]", m_vlFila.idSegmentacao);
    ULOG("Contato = [%d]", m_vlFila.idContato);
    ULOG("Estado = [%d]", m_vlFila.idEstado);
    ULOG("SubEstado = [%d]", m_vlFila.idSubEstado);
    ULOG("UsuarioGrupo = [%d]", m_vlFila.idUsuarioGrupo);
    ULOG("idAtendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("idAtendimentoProtocolo = [%d]", m_vlFila.idAtendimentoProtocolo);
    ULOG("nrProtocoloPortabilidade = [%d]", m_vlFila.nrProtocoloPortabilidade);
    ULOG("nrConta = [%d]", m_vlFila.nrConta);
    ULOG("idClassificacaoTipoLinha = [%d]", m_vlFila.idClassificacaoTipoLinha);
    ULOG("inPesquisaFullMC = [%d]", m_vlFila.inPesquisaFullMC);

    bool retorno;

    // Se o usuário é um consultor de relacionamento então este verá 
    // somente processos associados ao mesmo.
    //if ( proCConsultorRelacionamentoSimNao(idPessoaUsuario) == true )
    //{
    //    retorno = consultarFilaMCConsRelacionamento(_grupos,idPessoaUsuario);
    //}
    //else
    //{
        if ( m_stFila.inPesquisaFullMC == 0 )
        {
            retorno = consultarFilaMCSemConsRelacionamento(_grupos,idPessoaUsuario);
        }
        else
        { // Check box "Meus Processos" esta acionado.
            retorno = consultarFilaMCSemConsRelacionamentoMP(_grupos,idPessoaUsuario);
        }
    //}

    ULOG_END( "cWFAtendimento::consultarFilaMeuCliente(Collection*,int)" );

    return retorno;
}
//
//=================================================================================
///\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
// ESTAS PESQUISAS RETORNAM RESULTADOS NÃO ATRELADOS A CONSULTOR DE RELACIONAMENTO
// E COM O CRITÉRIO 'MEUS PROCESSOS' DO MEU CLIENTE NÃO ACIONADO.
//\\//\\/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/\\/\\//\\//\\//
//
bool cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
    
    // Peso do perfil do atendente
    proCObterInfoConsultorAtd(&m_stFila,&m_vlFila);
    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCPorAtendimento(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCPorAtendimentoQtd(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        	return proCConsultaWFFilaMCPorProtocoloMC(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCPorProtocoloMCQtd(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCPorProtocolo(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCPorProtocoloQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCAbertosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCAbertosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCAbertosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCAbertosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosPorContaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                //return proCConsultaWFAvanzadaFilaMC(&m_stFila, &m_vlFila, saida);
                return true; // Meu Cliente não possui fila avançada
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            //return proCConsultaWFAvanzadaFilaMCQtd(&m_stFila, &m_vlFila, saida);
            return true; // Meu Cliente não possui fila avançada
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                //return proCConsultaWFAvanzadaFilaMC(&m_stFila, &m_vlFila, saida);
                return true; // Meu Cliente não possui fila avançada
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            //return proCConsultaWFAvanzadaFilaMCQtd(&m_stFila, &m_vlFila, saida);
            return true; // Meu Cliente não possui fila avançada
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCUsuarioLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCUsuarioLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.idPessoaUsuario != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCUsuario(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMC(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCQtd(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamento(Collection*,int)" );
}

//
//=================================================================================
///\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//
// ESTAS PESQUISAS RETORNAM RESULTADOS NÃO ATRELADOS A CONSULTOR DE RELACIONAMENTO
// E COM O CRITÉRIO 'MEUS PROCESSOS' DO MEU CLIENTE ACIONADO.
//\\//\\/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/\\/\\//\\//\\//
//
bool cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
    
    // Peso do perfil do atendente
    proCObterInfoConsultorAtd(&m_stFila,&m_vlFila);
    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCPorAtendimentoMP(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        return proCConsultaWFFilaMCPorAtendimentoQtdMP(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        	return proCConsultaWFFilaMCPorProtocoloMCMP(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        return proCConsultaWFFilaMCPorProtocoloMCQtdMP(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCPorProtocoloMP(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        return proCConsultaWFFilaMCPorProtocoloQtdMP(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCAbertosPorLinhaMP(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCAbertosPorLinhaQtdMP(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCAbertosPorContaMP(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCAbertosPorContaQtdMP(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosPorContaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                //return proCConsultaWFAvanzadaFilaMC(&m_stFila, &m_vlFila, saida);
                return true; // Meu Cliente não possui fila avançada
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            //return proCConsultaWFAvanzadaFilaMCQtd(&m_stFila, &m_vlFila, saida);
            return true; // Meu Cliente não possui fila avançada
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                //return proCConsultaWFAvanzadaFilaMC(&m_stFila, &m_vlFila, saida);
                return true; // Meu Cliente não possui fila avançada
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            //return proCConsultaWFAvanzadaFilaMCQtd(&m_stFila, &m_vlFila, saida);
            return true; // Meu Cliente não possui fila avançada
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCUsuarioLinhaMP(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCUsuarioLinhaQtdMP(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
                return proCConsultaWFFilaMCLinhaMP(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCLinhaQtdMP(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.idPessoaUsuario != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCUsuarioMP(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        return proCConsultaWFFilaMCUsuarioQtdMP(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
            return proCConsultaWFFilaMCMP(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
        return proCConsultaWFFilaMCQtdMP(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarFilaMCSemConsRelacionamentoMP(Collection*,int)" );
}

//
//==============================================================================
///\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\
// PESQUISAS ATRELADAS A CONSULTOR DE RELACIONAMENTO
//\\//\\/\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\//\\/\\/\\//\\//\
//
/*
bool cWFAtendimento::consultarFilaMCConsRelacionamento(Collection* _grupos, long idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );

    // Peso do perfil do atendente
    proCObterInfoConsultorAtd(&m_stFila, &m_vlFila);

    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRPorAtendimento(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCCRPorAtendimentoQtd(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        	return proCConsultaWFFilaMCCRPorProtocoloMC(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCCRPorProtocoloMCQtd(&m_stFila, &m_vlFila, saida);
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
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRPorProtocolo(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCCRPorProtocoloQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRAbertosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRAbertosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRAbertosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRAbertosPorContaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRFechadosPorConta(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRFechadosPorContaQtd(&m_stFila, &m_vlFila, saida);
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
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaMCCR(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaMCCRQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFAvanzadaFilaMCCR(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaMCCRQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRUsuarioLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRUsuarioLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
            ///////////////////////////////////////Contator/////////////////////////////////
            if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
                return proCConsultaWFFilaMCCRLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.idPessoaUsuario != -1)
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCRUsuario(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCCRUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ///////////////////////////////////////////Contator/////////////////////////////////
        if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
            return proCConsultaWFFilaMCCR(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
        return proCConsultaWFFilaMCCRQtd(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarFilaMCConsRelacionamento(Collection*,int)" );
}
*/
