/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFAtendimentoFila(int tipo, st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFilaQtd(int tipo, st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPorAtendimento(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPorAtendimentoQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaPorProtocolo(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaPorProtocoloQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaAbertosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaAbertosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosPorLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosPorLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFAvanzadaFila(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAvanzadaFilaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaFechadosUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaUsuarioLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaLinha(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaLinhaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFilaUsuario(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

extern bool proCConsultaWFFila(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaQtd(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);

bool cWFAtendimento::consultarBox()
{
    carregaDadosConsultaFila();
    return proCConsultaWFAtendimentoFila(2, &m_stFila, &m_vlFila, saida);
}

bool cWFAtendimento::consultarFila()
{
    return consultarFila(NULL);
}

bool cWFAtendimento::consultarFila(Collection* _grupos)
{
    ULOG_START( "cWFAtendimento::consultarFila(Collection*)" );

    bool retorno;

    carregaDadosConsultaFila();

    if ( _grupos )
    {
        m_stFila.gruposUsuario = _grupos;
        m_vlFila.gruposUsuario = 1;
    }

    // Tem idPessoaUsuario ?
    if( m_vlFila.idPessoaUsuario != -1 )
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
    	if( m_vlFila.inTotalRegistros == -1 )
        	retorno = proCConsultaWFAtendimentoFila(1, &m_stFila, &m_vlFila, saida);
        else
        	retorno = proCConsultaWFAtendimentoFilaQtd(1, &m_stFila, &m_vlFila, saida);
        ULOG_END( "cWFAtendimento::consultarFila(Collection*)" );
        return retorno;
    }
    else
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
    	if( m_vlFila.inTotalRegistros == -1 )
        	retorno = proCConsultaWFAtendimentoFila(1, &m_stFila, &m_vlFila, saida);
        else
        	retorno = proCConsultaWFAtendimentoFilaQtd(1, &m_stFila, &m_vlFila, saida);
        ULOG_END( "cWFAtendimento::consultarFila(Collection*)" );
        return retorno;
    }
}

bool cWFAtendimento::consultarFila(Collection* _grupos, int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarFila(Collection*,int)" );

    carregaDadosConsultaFila();

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    ULOG("=== VALORES ===");
    ULOG("Filtro por data de fechamento = [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Filtro por campos dinamicos = [%d]", m_stFila.nrCampos);
    ULOG("Filtro por numero de linha = [%s]", m_stFila.nrLinha);
    ULOG("Filtro por usuario = [%d]", m_stFila.idPessoaUsuario);
    ULOG("Filtro por grupo = [%d]", m_stFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio = [%s]", m_stFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim = [%s]", m_stFila.dtAberturaFim);
    ULOG("Filtro por atendimento = [%lu]", m_stFila.idAtendimento);
    ULOG("Filtro por contato = [%d]", m_stFila.idContato);
    ULOG("Filtro por estado = [%d]", m_stFila.idEstado);
    ULOG("Filtro por subEstado = [%d]", m_stFila.idSubEstado);
    ULOG("Filtro por usuarioGrupo = [%d]", m_stFila.idUsuarioGrupo);
    ULOG("Filtro por idAtendimento); = [%lu]", m_stFila.idAtendimento);
    ULOG("Filtro por idAtendimentoProtocolo = [%d]", m_stFila.idAtendimentoProtocolo);

    ULOG("=== STATUS ===");
    ULOG("Filtro por data de fechamento = [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Filtro por campos dinamicos = [%d]", m_vlFila.nrCampos);
    ULOG("Filtro por numero de linha = [%d]", m_vlFila.nrLinha);
    ULOG("Filtro por usuario = [%d]", m_vlFila.idPessoaUsuario);
    ULOG("Filtro por grupo = [%d]", m_vlFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio = [%d]", m_vlFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim = [%d]", m_vlFila.dtAberturaFim);
    ULOG("Filtro por atendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("Filtro por contato = [%d]", m_vlFila.idContato);
    ULOG("Filtro por estado = [%d]", m_vlFila.idEstado);
    ULOG("Filtro por subEstado = [%d]", m_vlFila.idSubEstado);
    ULOG("Filtro por usuarioGrupo = [%d]", m_vlFila.idUsuarioGrupo);
    ULOG("Filtro por idAtendimento = [%d]", m_vlFila.idAtendimento);
    ULOG("Filtro por idAtendimentoProtocolo = [%d]", m_vlFila.idAtendimentoProtocolo);
    //
    // Quando o idatendimento for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    //
    if (m_vlFila.idAtendimento != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
    	if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        	return proCConsultaWFFilaPorAtendimento(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        return proCConsultaWFFilaPorAtendimentoQtd(&m_stFila, &m_vlFila, saida);
    }
    //
    // Quando o idAtendimentoProtocolo for preenchido, realizamos a consulta ignorando os demais parâmetros
    // Alteração para atender as incidências de WR 2984 e 3038, conforme reunião realizada com a VIVO
    // Adaptação para OS 661-SMP -- Jan/2008 -- Cassio
    //
    if (m_vlFila.idAtendimentoProtocolo != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
    	if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        	return proCConsultaWFFilaPorProtocolo(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        return proCConsultaWFFilaPorProtocoloQtd(&m_stFila, &m_vlFila, saida);
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
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaAbertosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaAbertosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaFechadosPorLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaFechadosPorLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_stFila.nrCampos != 0)
    {
        // Tem idPessoaUsuario ?
        if( m_vlFila.idPessoaUsuario != -1 )
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFAvanzadaFila(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFAvanzadaFilaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
	            return proCConsultaWFAvanzadaFila(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
	        return proCConsultaWFAvanzadaFilaQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.dtFechamentoInicio != -1 && m_vlFila.dtFechamentoFim != -1)
    {
        if (m_vlFila.nrLinha != -1)
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaFechadosLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaFechadosLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaFechadosUsuario(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaFechadosUsuarioQtd(&m_stFila, &m_vlFila, saida);
        }
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaUsuarioLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaUsuarioLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
        else
        {
///////////////////////////////////////////////////Contator/////////////////////////////////
    		if( m_vlFila.inTotalRegistros == -1 )
            {
                ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            	return proCConsultaWFFilaLinha(&m_stFila, &m_vlFila, saida);
            }
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
            return proCConsultaWFFilaLinhaQtd(&m_stFila, &m_vlFila, saida);
        }
    }

    if (m_vlFila.idPessoaUsuario != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        	return proCConsultaWFFilaUsuario(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        return proCConsultaWFFilaUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        {
            ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        	return proCConsultaWFFila(&m_stFila, &m_vlFila, saida);
        }
        ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
        return proCConsultaWFFilaQtd(&m_stFila, &m_vlFila, saida);
    }
        
    ULOG_END( "cWFAtendimento::consultarFila(Collection*,int)" );
}

bool cWFAtendimento::consultarFila(int idPessoaUsuario)
{
    ULOG_START( "consultarFila(int)" );
    bool ret;

    carregaDadosConsultaFila();
    ULOG("consultarFila(%d)", idPessoaUsuario);

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    if (m_vlFila.dtFechamentoInicio != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	ret = proCConsultaWFAtendimentoFila(1, &m_stFila, &m_vlFila, saida);
        else
        	ret = proCConsultaWFAtendimentoFilaQtd(1, &m_stFila, &m_vlFila, saida);
    }
	else if (m_vlFila.nrLinha != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	ret = proCConsultaWFFilaLinha(&m_stFila, &m_vlFila, saida);
        else
        	ret = proCConsultaWFFilaLinha(&m_stFila, &m_vlFila, saida);
    }
    else if (m_vlFila.idPessoaUsuario != -1)
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
        	ret = proCConsultaWFFilaUsuario(&m_stFila, &m_vlFila, saida);
        else
        	ret = proCConsultaWFFilaUsuarioQtd(&m_stFila, &m_vlFila, saida);
    }
    else
    {
///////////////////////////////////////////////////Contator/////////////////////////////////
   		if( m_vlFila.inTotalRegistros == -1 )
    		ret = proCConsultaWFFila(&m_stFila, &m_vlFila, saida);
    	else
    		ret = proCConsultaWFFilaQtd(&m_stFila, &m_vlFila, saida);
   	}

    ULOG_START( "consultarFila(int)" );

    return ret;
}
