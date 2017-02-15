/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Charles Santos
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/

#include "../../../commons/msgPadrao.h"

#include "../include/cWFAtendimentoPausa.h"

extern bool proCExistePausa( long _idAtendimento );
extern bool proCObterWFRegistroPausa(long sIdAtendimento,st_AtendimentoPausa* dados);
extern void proCIncluirWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status, XMLGen* saida);
extern void proCAlterarWFAtendimentoPausa(st_AtendimentoPausa* dados,st_vlAtendimentoPausa* status);
extern void proCExcluirWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status, XMLGen* saida);
extern void proCConsultaWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status, char* order, XMLGen* saida);
extern bool proCProcessoEmPausaSimNao(long _idAtendimento);


cWFAtendimentoPausa::cWFAtendimentoPausa()
{
    entrada = 0;
    saida   = 0;
    alocado = false;

    m_stDados = 0;
    m_vlDados = 0;
}


cWFAtendimentoPausa::cWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status)
{
    entrada = 0;
    saida   = 0;
    alocado = false;

    m_stDados = dados;
    m_vlDados = status;
}


cWFAtendimentoPausa::cWFAtendimentoPausa(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;
    alocado = true;

    m_stDados = new st_AtendimentoPausa;
    m_vlDados = new st_vlAtendimentoPausa;

    carregaDados();
}


cWFAtendimentoPausa::cWFAtendimentoPausa(st_AtendimentoPausa* dados, st_vlAtendimentoPausa* status, XMLGen*xml_g)
{
    entrada = 0;
    saida   = xml_g;
    alocado = false;

    m_stDados = dados;
    m_vlDados = status;
}


cWFAtendimentoPausa::~cWFAtendimentoPausa()
{
    if ( alocado )
    {
        delete m_stDados;
        delete m_vlDados;
    }
}


// true: pausa do processo ainda não expirou
// false: pausa do processo já expirou ou processo nem se encontra em pausa
bool cWFAtendimentoPausa::processoEmPausaSimNao(long idAtendimento)
{
    return proCProcessoEmPausaSimNao(idAtendimento);
}

void cWFAtendimentoPausa::incluir()
{
    if (m_vlDados->idAtendimento == 1)
    {
        proCIncluirWFAtendimentoPausa(m_stDados,m_vlDados, saida);
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::incluir(st_AtendimentoPausa *dados,st_vlAtendimentoPausa *status)
{
    if (status->idAtendimento == 1)
    {
        proCIncluirWFAtendimentoPausa(dados, status, saida);
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::alterar()
{
    if (m_vlDados->idAtendimento == 1)
    {
		m_vlDados->dtFimPausaAtendimento = 1;
		m_vlDados->idUsuarioAlteracao = 1;
        proCAlterarWFAtendimentoPausa(m_stDados, m_vlDados);
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::alterar(st_AtendimentoPausa *dados,st_vlAtendimentoPausa *status)
{
    if (status->idAtendimento == 1)
    {
        proCAlterarWFAtendimentoPausa(dados,status);
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::atualizar()
{
    if (m_vlDados->idAtendimento == 1)
    {
        //if ( existePausa(m_stDados->idAtendimento) )
        //{
        //    proCAlterarWFAtendimentoPausa(m_stDados,m_vlDados);
        //}
        //else
        //{
            proCIncluirWFAtendimentoPausa(m_stDados,m_vlDados,saida);
        //}
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::excluir()
{
    if (m_vlDados->idAtendimento == 1)
    {
        proCExcluirWFAtendimentoPausa(m_stDados,m_vlDados,saida);
    }
    else
    {
        ULOGE(mensagemSimples("idAtendimento invalido"));
    }
}


void cWFAtendimentoPausa::consultar()
{
    char order[256];

    order[0] = 0;

    if ( entrada )
    {
        char *p;
        if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

    if ( saida )
    {
        proCConsultaWFAtendimentoPausa(m_stDados, m_vlDados, order, saida);
    }
}


bool cWFAtendimentoPausa::obterRegistroPausa(long sIdAtendimento,st_AtendimentoPausa *dadosSaida)
{
    return proCObterWFRegistroPausa(sIdAtendimento,dadosSaida);
}


bool cWFAtendimentoPausa::existePausa(long _idAtendimento)
{
    return proCExistePausa( _idAtendimento );
}


void cWFAtendimentoPausa::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.
    if ( m_stDados ) memset(m_stDados,0,sizeof(m_stDados));
    if ( m_vlDados ) memset(m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

    char *p;

    if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
    {
        m_stDados->idAtendimento = atol(p);
        m_vlDados->idAtendimento = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtFimPausaAtendimento", 0 ),p) 
    {
        strcpy(m_stDados->dtFimPausaAtendimento, p);
        m_vlDados->dtFimPausaAtendimento = 1;
        XMLString::release(&p);
    }
    
    if (p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p)  
    {
        m_stDados->idUsuarioAlteracao = atoi(p);
        m_vlDados->idUsuarioAlteracao = 1;
        XMLString::release(&p);
    }

    if (p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p) 
    {
        strcpy(m_stDados->dtUltimaAlteracao, p);
        m_vlDados->dtUltimaAlteracao = 1;
        XMLString::release(&p);
    }
}
