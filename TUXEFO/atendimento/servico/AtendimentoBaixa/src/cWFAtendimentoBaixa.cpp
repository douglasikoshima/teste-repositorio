/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.114.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:31:30 $
 **/


#include <tuxfw.h>
#include "../include/cWFAtendimentoBaixa.h"

extern bool proCIncluirWFAtendimentoBaixa(st_AtendimentoBaixa* dados, st_vlAtendimentoBaixa* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoBaixa(st_AtendimentoBaixa* dados, st_vlAtendimentoBaixa* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoBaixa(st_AtendimentoBaixa* dados, st_vlAtendimentoBaixa* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoBaixa(st_AtendimentoBaixa* dados, st_vlAtendimentoBaixa* status, char* order, XMLGen* saida);
extern bool proCObtemWFBaixaContato(int sIdBaixa, int sIdContato, XMLGen* saida);
extern bool proCObtemWFBaixaLista(int sIdBaixa, XMLGen* saida);
extern bool proCObtemWFContatoFolha(int sIdContato, int sIdGrupo, int sIdUFOperadora, int sIdTipoLinha, int sIdTipoCarteira, int sIdSegmentacao, int sIdTipoRelacionamento, XMLGen* saida);
extern bool proCObtemWFContatoLista(int sIdContato, XMLGen* saida);
extern bool proCObtemWFContatoRaiz(int sIdContato, XMLGen* saida);
extern bool proCObtemWFBaixaAtendimento(int sIdBaixa, XMLGen* saida);
extern bool proCObtemWFContatoAtendimento(int sIdContato, XMLGen* saida);

cWFAtendimentoBaixa::cWFAtendimentoBaixa(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

bool cWFAtendimentoBaixa::incluir()
{
    return proCIncluirWFAtendimentoBaixa(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoBaixa::alterar()
{

    if (m_vlDados.idBaixa == -1) 
    {
        return -1;
    }

    return proCAlterarWFAtendimentoBaixa(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoBaixa::excluir()
{

    if (m_vlDados.idBaixa == -1) 
    {
        return -1;
    }

    return proCExcluirWFAtendimentoBaixa(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoBaixa::consultar()
{
    char order[256];

    order[0] = 0;

    char* p;

    if (p = tx.walkTree( entrada, "order", 0 ), p )
    {
        strcpy( order, p );
        XMLString::release(&p);
    }

    return proCConsultaWFAtendimentoBaixa(&m_stDados, &m_vlDados, order, saida);
}

int cWFAtendimentoBaixa::obtemBLista()
{

    if (m_vlDados.idBaixa == -1) 
    {
        return -1;
    }
    
    return proCObtemWFBaixaLista(m_stDados.idBaixa, saida );
}

int cWFAtendimentoBaixa::obtemBContato()
{

    if (m_vlDados.idBaixa == -1) 
    {
        return -1;
    }

    if (m_vlDados.idContato == -1) 
    {
        return -1;
    }

    return proCObtemWFBaixaContato(m_stDados.idBaixa, m_stDados.idContato, saida );
}

int cWFAtendimentoBaixa::obtemCLista()
{

    if (m_vlDados.idContato == -1) 
    {
        return -1;
    }
    
    return proCObtemWFContatoLista(m_stDados.idContato, saida );
}

int cWFAtendimentoBaixa::obtemCFolha()
{

    if (m_vlDados.idContato == -1) 
    {
        return -1;
    }

    if (m_vlDados.idGrupo == -1) 
    {
        return -1;
    }

    if (m_vlDados.idUFOperadora == -1) 
    {
        return -1;
    }

    return proCObtemWFContatoFolha(m_stDados.idContato, m_stDados.idGrupo, m_stDados.idUFOperadora, m_stDados.idTipoLinha, m_stDados.idTipoCarteira, m_stDados.idSegmentacao, m_stDados.idTipoRelacionamento, saida );
}

int cWFAtendimentoBaixa::obtemCRaiz()
{
    if (m_vlDados.idContato == -1) 
    {
        return -1;
    }
    return proCObtemWFContatoRaiz( m_stDados.idContato, saida );
}

int cWFAtendimentoBaixa::obtemAtdHrq(int idBaixa,XMLGen* saida)
{
    return proCObtemWFBaixaAtendimento( idBaixa, saida );
}

int cWFAtendimentoBaixa::obtemAtdHrq()
{
    if (m_vlDados.idBaixa == -1) 
    {
        return -1;
    }
    return proCObtemWFBaixaAtendimento( m_stDados.idBaixa, saida );
}

int cWFAtendimentoBaixa::obtemAtdCtHrq(int idContato,XMLGen* saida)
{
    return proCObtemWFContatoAtendimento( idContato, saida );
}

int cWFAtendimentoBaixa::obtemAtdCtHrq()
{
    if (m_vlDados.idContato == -1) 
    {
        return -1;
    }
    return proCObtemWFContatoAtendimento( m_stDados.idContato, saida );
}

void cWFAtendimentoBaixa::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    memset(&m_stDados, 0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    char* p;

    if (p = tx.walkTree( entrada, "idBaixa", 0 ), p ) 
    {
        m_stDados.idBaixa = atoi( p );
        m_vlDados.idBaixa = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p ) 
    {
        m_stDados.idContato = atoi( p );
        m_vlDados.idContato = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
    {
        m_stDados.idGrupo = atoi( p );
        m_vlDados.idGrupo = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idTipoRelacionamento", 0 ), p ) 
    {
        m_stDados.idTipoRelacionamento = atoi( p );
        m_vlDados.idTipoRelacionamento = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idUFOperadora", 0 ), p ) 
    {
        m_stDados.idUFOperadora = atoi( p );
        m_vlDados.idUFOperadora = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idTipoLinha", 0 ), p ) 
    {
        m_stDados.idTipoLinha = atoi( p );
        m_vlDados.idTipoLinha = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idTipoCarteira", 0 ), p ) 
    {
        m_stDados.idTipoCarteira = atoi( p );
        m_vlDados.idTipoCarteira = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idSegmentacao", 0 ), p ) 
    {
        m_stDados.idSegmentacao = atoi( p );
        m_vlDados.idSegmentacao = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idNomeBaixa", 0 ), p ) 
    {
        m_stDados.idNomeBaixa = atoi( p );
        m_vlDados.idNomeBaixa = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
    {
        m_stDados.idUsuarioAlteracao = atoi( p );
        m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
    {
        strcpy(m_stDados.dtUltimaAlteracao, p);
        m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
    }

}

