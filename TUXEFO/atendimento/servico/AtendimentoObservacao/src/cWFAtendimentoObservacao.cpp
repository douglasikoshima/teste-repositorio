/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#include "../include/cWFAtendimentoObservacao.h"
#include "../../../commons/definesAtendimento.h"

extern void proCIncluirWFAtendimentoObservacao(st_AtendimentoObservacao *dados, st_vlAtendimentoObservacao *status, XMLDPR *xmlDpr);

cWFAtendimentoObservacao::cWFAtendimentoObservacao()
{
    locado = false;

    m_stDados = 0;
    m_vlDados = 0;

    entrada = 0;
    saida   = 0;
}

cWFAtendimentoObservacao::cWFAtendimentoObservacao(st_AtendimentoObservacao *dados, st_vlAtendimentoObservacao *status, XMLGen *xml_g)
{
    locado = false;

    m_stDados = dados;
    m_vlDados = status;

    entrada = 0;
    saida   = xml_g;
}

cWFAtendimentoObservacao::cWFAtendimentoObservacao(DOMNode*dnode, XMLGen*xml_g) 
{
    m_stDados = (st_AtendimentoObservacao*) malloc(sizeof(st_AtendimentoObservacao));
    m_vlDados = (st_vlAtendimentoObservacao*) malloc(sizeof(st_vlAtendimentoObservacao));

    locado = true;

    entrada = dnode;
    saida   = xml_g;

    carregarDados(entrada);
}

cWFAtendimentoObservacao::~cWFAtendimentoObservacao()
{
    if (locado)
    {
        free(m_stDados);
        free(m_vlDados);
    }
}

void cWFAtendimentoObservacao::incluir(st_AtendimentoObservacao *dados, st_vlAtendimentoObservacao *status,XMLDPR *xmlDpr)
{
    ULOG_START("cWFAtendimentoObservacao::incluir");
    
    proCIncluirWFAtendimentoObservacao(dados,status,xmlDpr);

    ULOG_END("cWFAtendimentoObservacao::incluir");
}

void cWFAtendimentoObservacao::carregarDados(DOMNode *entrada)
{
    memset(m_stDados,0,sizeof(st_AtendimentoObservacao));
    memset(m_vlDados,-1,sizeof(st_vlAtendimentoObservacao));

    if ( entrada )
    {
        char *p;

        if ( p=tx.walkTree( entrada, "idAtendimento", 0 ),p )
        {
            m_stDados->idAtendimento = atol(p);
            m_vlDados->idAtendimento = 1;
            XMLString::release(&p);
        }

        if ( p=tx.walkTree( entrada, "dsObservacao", 0 ),p )
        {
            SAFE_STRNCPY(m_stDados->dsObservacao,p);
            m_stDados->pdsObservacao = m_stDados->dsObservacao;
            m_vlDados->dsObservacao = 1;
            XMLString::release(&p);
        }

        if ( p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p )
        {
            m_stDados->idUsuarioAlteracao = atoi(p);
            m_vlDados->idUsuarioAlteracao = 1;
            XMLString::release(&p);
        }

        if ( p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p )
        {
            strcpy(m_stDados->dtUltimaAlteracao, p);
            m_vlDados->dtUltimaAlteracao = 1;
            XMLString::release(&p);
        }
    }
}