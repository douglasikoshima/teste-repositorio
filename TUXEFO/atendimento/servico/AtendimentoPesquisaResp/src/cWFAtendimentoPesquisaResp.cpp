/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 


#include "../include/cWFAtendimentoPesquisaResp.h"

extern bool proCIncluirWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, char* order, XMLGen* saida);


cWFAtendimentoPesquisaResp::cWFAtendimentoPesquisaResp(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status, XMLGen*xml_g)
{
    if ( dados )
    {
        memcpy(&m_stDados,dados,sizeof(m_stDados));
    }

    if ( status )
    {
        memcpy(&m_vlDados,status,sizeof(m_vlDados));
    }

    entrada = 0;
    saida   = xml_g;
}

cWFAtendimentoPesquisaResp::cWFAtendimentoPesquisaResp(DOMNode*dnode, XMLGen*xml_g,char *user)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();

    m_stDados.idPessoaUsuario = user ? atoi(user):0;
    m_vlDados.idPessoaUsuario = user ? 1:-1;
}

bool cWFAtendimentoPesquisaResp::incluir(st_AtendimentoPesquisaResp* dados, st_vlAtendimentoPesquisaResp* status)
{
    return proCIncluirWFAtendimentoPesquisaResp(dados, status, saida);
}

bool cWFAtendimentoPesquisaResp::incluir()
{
    return proCIncluirWFAtendimentoPesquisaResp(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaResp::alterar()
{

    if (m_vlDados.idAtendimentoPesquisaResp == -1) 
    {
        return -1;
    }

    return proCAlterarWFAtendimentoPesquisaResp(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaResp::excluir()
{

    if (m_vlDados.idAtendimentoPesquisaResp == -1) 
    {
        return -1;
    }

    return proCExcluirWFAtendimentoPesquisaResp(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoPesquisaResp::consultar()
{
    char order[256];

    order[0] = 0;

    char* p;

    if (p = tx.walkTree( entrada, "order", 0 ), p)
    {
        strcpy( order, p );
        XMLString::release(&p);
    }

    return proCConsultaWFAtendimentoPesquisaResp(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoPesquisaResp::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    char* p;

    if (p = tx.walkTree( entrada, "idAtendimentoPesquisaResp", 0 ), p ) 
    {
        m_stDados.idAtendimentoPesquisaResp = atol( p );
        m_vlDados.idAtendimentoPesquisaResp = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idAtendimentoPesquisaSatisfa", 0 ), p ) 
    {
        m_stDados.idAtendimentoPesquisaSatisfa = atol( p );
        m_vlDados.idAtendimentoPesquisaSatisfa = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idPergunta", 0 ), p ) 
    {
        m_stDados.idPergunta = atoi( p );
        m_vlDados.idPergunta = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "vlResposta", 0 ), p ) 
    {
        sprintf(m_stDados.vlResposta,"%.*s"
               ,sizeof(m_stDados.vlResposta)-1
               ,p);

        m_vlDados.vlResposta = 1;
        XMLString::release(&p);
    }
}

