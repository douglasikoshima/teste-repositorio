/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:27 $
 **/


#include "../include/cWFAtendimento.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFAvanzadaFilaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosLinhaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaFechadosUsuarioRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioLinhaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaLinhaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaUsuarioRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFFilaMassaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFilaRC(int tipo, st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechadosUsuarioLinhaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxFechadosUsuarioRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioLinhaRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);
extern bool proCConsultaWFInboxUsuarioRC(st_AtendimentoFila* dados, st_vlAtendimentoFila* status, XMLGen* saida);


bool cWFAtendimento::consultarFilaRC(Collection* _grupos, int idPessoaUsuario)
{
    carregaDadosConsultaFilaRC();

    ULOG("Obteniendo la fila para el usuario =  [%d]", idPessoaUsuario);

    m_stFila.idUsuarioGrupo = idPessoaUsuario;
    m_vlFila.idUsuarioGrupo = 1;

    ULOG("=== STATUS ===");
    ULOG("Filtro por data de Fechamento =  [%s]", m_stFila.dtFechamentoInicio);
    ULOG("Filtro por Campos dinamicos =  [%d]", m_stFila.nrCampos);
    ULOG("Filtro por numero de linha =  [%s]", m_stFila.nrLinha);
    ULOG("Filtro por usuario =  [%d]", m_stFila.idPessoaUsuario);
    ULOG("Filtro por grupo =  [%d]", m_stFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio =  [%s]", m_stFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim =  [%s]", m_stFila.dtAberturaFim);
    ULOG("Filtro por Atendimento =  [%d]", m_stFila.idAtendimento);
    ULOG("Filtro por Contato =  [%d]", m_stFila.idContato);
    ULOG("Filtro por Estado =  [%d]", m_stFila.idEstado);
    ULOG("Filtro por SubEstado =  [%d]", m_stFila.idSubEstado);
    ULOG("Filtro por UsuarioGrupo =  [%d]", m_stFila.idUsuarioGrupo);

    ULOG("=== VALORES ===");
    ULOG("Filtro por data de Fechamento =  [%d]", m_vlFila.dtFechamentoInicio);
    ULOG("Filtro por Campos dinamicos =  [%d]", m_vlFila.nrCampos);
    ULOG("Filtro por numero de linha =  [%d]", m_vlFila.nrLinha);
    ULOG("Filtro por usuario =  [%d]", m_vlFila.idPessoaUsuario);
    ULOG("Filtro por grupo =  [%d]", m_vlFila.idGrupo);
    ULOG("Filtro por data de Abertura Inicio =  [%d]", m_vlFila.dtAberturaInicio);
    ULOG("Filtro por data de Abertura Fim =  [%d]", m_vlFila.dtAberturaFim);
    ULOG("Filtro por Atendimento =  [%d]", m_vlFila.idAtendimento);
    ULOG("Filtro por Contato =  [%d]", m_vlFila.idContato);
    ULOG("Filtro por Estado =  [%d]", m_vlFila.idEstado);
    ULOG("Filtro por SubEstado =  [%d]", m_vlFila.idSubEstado);
    ULOG("Filtro por UsuarioGrupo =  [%d]", m_vlFila.idUsuarioGrupo);

    if (m_stFila.nrCampos != 0)
        return proCConsultaWFAvanzadaFilaRC(&m_stFila, &m_vlFila, saida);

    if (m_vlFila.dtFechamentoInicio != -1)
    {
        if (m_vlFila.nrLinha != -1)
            return proCConsultaWFFilaFechadosLinhaRC(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFFilaFechadosUsuarioRC(&m_stFila, &m_vlFila, saida);
    }
    if (m_vlFila.nrLinha != -1)
    {
        if (m_vlFila.idPessoaUsuario != -1)
            return proCConsultaWFFilaUsuarioLinhaRC(&m_stFila, &m_vlFila, saida);
        else
            return proCConsultaWFFilaLinhaRC(&m_stFila, &m_vlFila, saida);
    }
    if (m_vlFila.idPessoaUsuario != -1)
        return proCConsultaWFFilaUsuarioRC(&m_stFila, &m_vlFila, saida);
    else
        return proCConsultaWFFilaRC(&m_stFila, &m_vlFila, saida);
        
}


bool cWFAtendimento::consultarMassaRC()
{
    carregaDadosConsultaFilaRC();

    return proCConsultaWFFilaMassaRC(&m_stFila, &m_vlFila, saida);

}
void cWFAtendimento::carregaDadosConsultaFilaRC()
{
    // Inicializa as estruturas de dados para armazenar os valores.

    ULOG_START( "cWFAtendimento::carregaDadosConsultaFilaRC()" );

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    char* p;
    DOMNode* dom;

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idGrupo = atoi(p);
            m_vlFila.idGrupo = 1;
            ULOG("idGrupo [%i]", m_stFila.idGrupo);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idContato = atoi(p);
            m_vlFila.idContato = 1;
        }
        XMLString::release(&p);
        ULOG("idContato [%i]", m_stFila.idContato);
    }

    if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idEstado = atoi(p);
            m_vlFila.idEstado = 1;
        }
        XMLString::release(&p);
        ULOG("idEstado [%i]", m_stFila.idEstado);
    }

    if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idSubEstado = atoi(p);
            m_vlFila.idSubEstado = 1;
        }
        XMLString::release(&p);
        ULOG("idSubEstado [%i]", m_stFila.idSubEstado);
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaUsuario = atoi(p);
            m_vlFila.idPessoaUsuario = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
    }

    if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
            m_vlFila.dtAberturaInicio= 1;
        }
        XMLString::release(&p);
        ULOG("dtAberturaInicio [%s]", m_stFila.dtAberturaInicio);
    }

    if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
            m_vlFila.dtAberturaFim= 1;
        }       
        XMLString::release(&p);
        ULOG("dtAberturaFim [%s]", m_stFila.dtAberturaFim);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
            m_vlFila.dtFechamentoInicio= 1;
        }
        XMLString::release(&p);
        ULOG("dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
            m_vlFila.dtFechamentoFim= 1;
        }
        XMLString::release(&p);
        ULOG("dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
    }

    if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
    {
        sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
        m_vlFila.pesquisa= 1;
        XMLString::release(&p);
        ULOG("pesquisa [%s]", m_stFila.pesquisa);
    }

    if (p = tx.walkTree( entrada, "tpPesquisa", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.tpPesquisa = atoi(p);
            m_vlFila.tpPesquisa = 1;
        }
        XMLString::release(&p);
        ULOG("tpPesquisa [%i]", m_stFila.tpPesquisa);
    }

    if (p = tx.walkTree( entrada, "idPessoaDePara", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaDePara = atoi(p);
            m_vlFila.idPessoaDePara = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaDePara [%i]", m_stFila.idPessoaDePara);
    }

    if (p = tx.walkTree( entrada, "tpRelacionamento", 0 ), p) 
    {
        m_stFila.tpRelacionamento = atoi(p);
        m_vlFila.tpRelacionamento = 1;
        XMLString::release(&p);
        ULOG("tpRelacionamento [%i]", m_stFila.tpRelacionamento);
    }

    if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idPessoaLinhaHistorico = atol(p);
            m_vlFila.idPessoaLinhaHistorico = 1;
        }
        XMLString::release(&p);
        ULOG("idPessoaLinhaHistorico [%ld]", m_stFila.idPessoaLinhaHistorico);
    }

    if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
    {
        sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
        m_vlFila.textoContato = 1;
        XMLString::release(&p);
        ULOG("textoContato [%s]", m_stFila.textoContato);
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.idAtendimento = atol(p);
            m_vlFila.idAtendimento = 1;
        }
        XMLString::release(&p);
        ULOG("idAtendimento [%i]", m_stFila.idAtendimento);
    }

    if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
            m_vlFila.nrLinha = 1;
            ULOG("nrLinha [%s]", m_stFila.nrLinha);
        }
        XMLString::release(&p);
    }


    if (dom = tx.walkDOM( entrada, "WFPesquisaAvancadaVO", 0 ), dom) 
    {
        m_stFila.pesquisaDinamica = dom;
        m_vlFila.pesquisaDinamica = 1;
        ULOG("WFPesquisaAvancadaVO");
        if (p = tx.walkTree( dom, "nrCampos", 0 ), p) 
            {
            m_stFila.nrCampos = atoi(p);
            m_vlFila.nrCampos = 1;
            XMLString::release(&p);
            }
    }

    if (p = tx.walkTree( entrada, "dtSuspeitoInicio", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtSuspeitoInicio,"%.*s",sizeof(m_stFila.dtSuspeitoInicio)-1, p );
            m_vlFila.dtSuspeitoInicio = 1;
            ULOG("dtSuspeitoInicio [%s]", m_stFila.dtSuspeitoInicio);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtSuspeitoFim", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.dtSuspeitoFim,"%.*s",sizeof(m_stFila.dtSuspeitoFim)-1, p );
            m_vlFila.dtSuspeitoFim = 1;
            ULOG("dtSuspeitoFim [%s]", m_stFila.dtSuspeitoFim);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "nmLoginUsuario", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            sprintf(m_stFila.nmLoginUsuario,"%.*s",sizeof(m_stFila.nmLoginUsuario)-1, p );
            m_vlFila.nmLoginUsuario = 1;
            ULOG("nmLoginUsuario [%s]", m_stFila.nmLoginUsuario);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dsAndamentoObservacao", 0 ), p) 
    {
        sprintf(m_stFila.dsAndamentoObservacao,"%.*s",sizeof(m_stFila.dsAndamentoObservacao)-1, p );
        m_vlFila.dsAndamentoObservacao = 1;
        XMLString::release(&p);
        ULOG("dsAndamentoObservacao [%s]", m_stFila.dsAndamentoObservacao);
    }

    if (dom = tx.walkDOM( entrada, "GruposDoUsuario", 0 ), dom) 
    {
        m_stFila.gruposUsuario = new Collection();
        char* grupo;
        char* x;
        int contador = 0;

        while (grupo = tx.walkTree(dom, "idGrupoFiltro", contador++) )
        {
            if ( x = new char[strlen(grupo)+1],x )
            {
                strcpy(x, grupo);
                m_stFila.gruposUsuario->AddItem((void*) x);

                ULOG("<idGrupoFiltro>=%s",grupo);
            }
            XMLString::release(&grupo);
        }

        m_vlFila.gruposUsuario = 1;
        ULOG("registro = [%i]", contador);
    }

    if (p = tx.walkTree( entrada, "inPrimeiraChamada", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.inPrimeiraChamada = atoi(p);
            m_vlFila.inPrimeiraChamada = 1;
        }
        XMLString::release(&p);
        ULOG("inPrimeiraChamada [%i]", m_stFila.inPrimeiraChamada);
    }

    if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
    {
        if( STRLENNULL( p ) > 0 )
        {
            m_stFila.tbPausa = atoi(p);
            m_vlFila.tbPausa = 1;
        }
        XMLString::release(&p);
        ULOG("tbPausa [%i]	", m_stFila.tbPausa);
    }

    ULOG_END( "cWFAtendimento::carregaDadosConsultaFilaRC()" );
}

bool cWFAtendimento::consultarBoxRC()
{
    carregaDadosConsultaFilaRC();

    return proCConsultaWFAtendimentoFilaRC(2, &m_stFila, &m_vlFila, saida);
}


bool cWFAtendimento::consultarBoxRC(int idPessoaUsuario)
{
    ULOG_START( "cWFAtendimento::consultarBoxRC()" );

    bool retorno = false;

    carregaDadosConsultaFilaRC();

    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;

    ULOG("m_vlFila.dtFechamentoInicio [%d]  ",m_vlFila.dtFechamentoInicio);

    if (m_vlFila.dtFechamentoInicio != -1)
    {
        if (m_vlFila.nrLinha != -1)
            retorno= proCConsultaWFInboxFechadosUsuarioLinhaRC(&m_stFila, &m_vlFila, saida);
        else
            retorno= proCConsultaWFInboxFechadosUsuarioRC(&m_stFila, &m_vlFila, saida);
    }
    else
    {
        ULOG("m_vlFila.nrLinha [%d]  ",m_vlFila.nrLinha);

        if (m_vlFila.nrLinha != -1)
            retorno= proCConsultaWFInboxUsuarioLinhaRC(&m_stFila, &m_vlFila, saida);
        else
            retorno= proCConsultaWFInboxUsuarioRC(&m_stFila, &m_vlFila, saida);
    }

    ULOG_END( "cWFAtendimento::consultarBoxRC()" );

    return retorno;
}
