/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:14 $
 **/


#include "../include/cWFMotivo.h"
#include "../../../commons/msgPadrao.h"

extern int proCIncluirWFMotivo(st_Motivo* dados, st_vlMotivo* status, XMLGen* saida);
extern int proCAlterarWFMotivo(st_Motivo* dados, st_vlMotivo* status, XMLGen* saida);
extern int proCExcluirWFMotivo(st_Motivo* dados, st_vlMotivo* status, XMLGen* saida);
extern int proCConsultaWFMotivo(st_Motivo* dados,st_vlMotivo* status,char* order
                               ,XMLGen* saida,Collection *collection);
extern bool proCObterIdMotivo(char *_dsMotivo,int &_idMotivo);

cWFMotivo::cWFMotivo()
{
    entrada = 0;
    saida   = 0;

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));
}

cWFMotivo::cWFMotivo(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;

    carregaDados();
}

cWFMotivo::cWFMotivo(st_Motivo* dados, st_vlMotivo* status, XMLGen*xml_g)
{
    entrada = 0;
    saida   = xml_g;

    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

int cWFMotivo::incluir()
{
    ULOG_START("incluir()");

    int retorno = proCIncluirWFMotivo(&m_stDados, &m_vlDados, saida);

    ULOG_END("incluir()");

    return retorno;
}

int cWFMotivo::alterar()
{
    ULOG_START("alterar()");

    int retorno = -1;

    if ( 1 == m_vlDados.idMotivo ) 
    {
        retorno = proCAlterarWFMotivo(&m_stDados, &m_vlDados, saida);
    }
    else
    {
        ULOG("nao alterou()");
    }

    ULOG_END("alterar()");

    return retorno;
}

int cWFMotivo::excluir()
{
    ULOG_START("excluir()");

    int retorno = -1;

    if ( 1 == m_vlDados.idMotivo ) 
    {
        retorno = proCExcluirWFMotivo(&m_stDados, &m_vlDados, saida);
    }
    else
    {
        ULOG("nao alterou()");
    }

    ULOG_END("excluir()");

    return retorno;
}

int cWFMotivo::consultar()
{
    ULOG_START("consultar()");

    char order[256];
    char* p;

    order[0] = 0;

    if (p = tx.walkTree( entrada, "order", 0 ), p )
    {
        sprintf(order, "%.*s",sizeof(order)-1,p);
        XMLString::release(&p);
    }

    int retorno = proCConsultaWFMotivo(&m_stDados,&m_vlDados,order,saida,0);

    ULOG_END("consultar()");

    return retorno;
}

int cWFMotivo::consultar(Collection *registrosMotivo)
{
    ULOG_START("consultar (2)()");

    char order[256];
    char* p;

    order[0] = 0;

    if (p = tx.walkTree( entrada, "order", 0 ), p )
    {
        sprintf(order, "%.*s",sizeof(order)-1,p);
        XMLString::release(&p);
    }

    int retorno = proCConsultaWFMotivo(&m_stDados,&m_vlDados,order,0,registrosMotivo);

    ULOG_END("consultar (2)()");

    return retorno;
}

bool cWFMotivo::ObterMotivoEncAutomatico(int &idMotivo)
{
    ULOG_START("ObterMotivoEncAutomatico()");

    bool retorno = obterIdMotivo("Encaminhamento Automatico",idMotivo);

    ULOG_END("ObterMotivoEncAutomatico()");

    return retorno;
}

bool cWFMotivo::obterIdMotivo(char *dsMotivo,int &idMotivo)
{
    ULOG_START("obterIdMotivo()");

    bool retorno = 
            proCObterIdMotivo(dsMotivo,idMotivo);

    ULOG_END("obterIdMotivo()");

    return retorno;
}

void cWFMotivo::carregaDados()
{
    ULOG_START("carregaDados()");

    memset(&m_stDados,0,sizeof(m_stDados));
    memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( entrada )
    {
        char *p;

        if (p = tx.walkTree(entrada,"idMotivo", 0),p) 
        {
            setarIdMotivo(p);
            XMLString::release(&p);
        }

        //if (p = tx.walkTree(entrada,"idTabelaMotivo", 0),p) 
        //{
        //    setarIdTabelaMotivo(p);
        //    XMLString::release(&p);
        //}
        
        if (p = tx.walkTree(entrada,"idUsuarioAlteracao", 0),p) 
        {
            setarIdUsuarioAlteracao(p);
            XMLString::release(&p);
        }
        
        if (p = tx.walkTree(entrada,"dsMotivo", 0),p) 
        {
            setarDsMotivo(p);
            XMLString::release(&p);
        }
        
        if (p = tx.walkTree(entrada,"dtUltimaAlteracao", 0),p) 
        {
            setarDtUltimaAlteracao(p);
            XMLString::release(&p);
        }
    }

    ULOG_END("carregaDados()");
}

void cWFMotivo::setarIdMotivo(int valor)
{
    m_stDados.idMotivo=valor;
    m_vlDados.idMotivo = 1;

    ULOG("idMotivo=%d",m_stDados.idMotivo);
}


void cWFMotivo::setarIdUsuarioAlteracao(int valor)
{
    m_stDados.idUsuarioAlteracao=valor;
    m_vlDados.idUsuarioAlteracao = 1;

    ULOG("idUsuarioAlteracao=%d",m_stDados.idUsuarioAlteracao);
}

void cWFMotivo::setarDsMotivo(char *valor)
{
    if (valor)
    {
        sprintf(m_stDados.dsMotivo,"%.*s",sizeof(m_stDados.dsMotivo)-1,valor);
        m_vlDados.dsMotivo = 1;

        ULOG("dsMotivo=%s",m_stDados.dsMotivo);
    }
    else
    {
        ULOG("dsMotivo nao fornecido()");
    }
}

void cWFMotivo::setarDtUltimaAlteracao(char *valor)
{
    if (valor) 
    {
        sprintf(m_stDados.dtUltimaAlteracao,"%.*s",sizeof(m_stDados.dtUltimaAlteracao)-1,valor);
        m_vlDados.dtUltimaAlteracao = 1;

        ULOG("dtUltimaAlteracao=%s",m_stDados.dtUltimaAlteracao);
    }
    else
    {
        ULOG("dtUltimaAlteracao nao fornecido()");
    }
}
