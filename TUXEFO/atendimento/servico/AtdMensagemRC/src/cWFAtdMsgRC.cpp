/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/


#include "../include/cWFAtdMsgRC.h"
#include "../../../commons/msgPadrao.h"

extern bool proCIncluirWFAndamentoMensagemRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status);
extern bool proCConsultaWFMensagemLinhaRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status, XMLGen* saida);
extern bool proCConsultaWFMensagemUsuarioRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status, XMLGen* saida);

cWFAtdMsgRC::cWFAtdMsgRC()
{
    entrada=0;
    saida=0;

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

}

cWFAtdMsgRC::cWFAtdMsgRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status, XMLGen*xml_g)
{
    memcpy(&m_stFila,dados,sizeof(m_stFila));
    memcpy(&m_vlFila,status,sizeof(m_vlFila));

    entrada = 0;
    saida   = xml_g;
}

cWFAtdMsgRC::cWFAtdMsgRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status)
{
    memcpy(&m_stFila,dados,sizeof(m_stFila));
    memcpy(&m_vlFila,status,sizeof(m_vlFila));

    entrada = 0;
    saida   = 0;
}

cWFAtdMsgRC::cWFAtdMsgRC(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    carregaDados();
}

long cWFAtdMsgRC::incluir()
{
    return proCIncluirWFAndamentoMensagemRC(&m_stFila, &m_vlFila);
}

bool cWFAtdMsgRC::consultarMensagems( int idPessoaUsuario, long idAtendimento )
{
    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;

    if ( idAtendimento > 0 )
    {
        m_stFila.idAtendimento = idAtendimento;
        m_vlFila.idAtendimento = 1;
    }

    if (m_vlFila.nrLinha != -1)
    {
        return proCConsultaWFMensagemLinhaRC(&m_stFila, &m_vlFila, saida);
    }

    return proCConsultaWFMensagemUsuarioRC(&m_stFila, &m_vlFila, saida);
}

void cWFAtdMsgRC::carregaDados()
{
    ULOG_START( "cWFAtdMsgRC::carregaDados()" );

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));
  
    if ( !entrada )
    {
        ULOGE( "Parametros Invalidos" );
        ULOG_END( "void cWFAtdMsgRC::carregaDados()" );
        return;
    }

    char* p;

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idContato = atoi(p);
            m_vlFila.idContato = 1;
        }
        XMLString::release(&p);
        ULOG( "idContato [%d]", m_stFila.idContato );
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
    {
        m_stFila.idPessoaUsuario = atoi(p);
        m_vlFila.idPessoaUsuario = 1;
        XMLString::release(&p);
        ULOG( "idPessoaUsuario [%d]",m_stFila.idPessoaUsuario );
    }
  
    if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
            m_vlFila.dtAberturaInicio= 1;
        }
        XMLString::release(&p);
        ULOG( "dtAberturaInicio [%s]",m_stFila.dtAberturaInicio );
    }
  
    if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
            m_vlFila.dtAberturaFim= 1;
        }       
        XMLString::release(&p);
        ULOG( "dtAberturaFim [%s]",m_stFila.dtAberturaFim );
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            m_stFila.idAtendimento = atol(p);
            m_vlFila.idAtendimento = 1;
        }
        XMLString::release(&p);
        ULOG( "idAtendimento [%lu]",m_stFila.idAtendimento );
    }
  
    if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
            m_vlFila.nrLinha = 1;
        }
        XMLString::release(&p);
        ULOG( "nrLinha [%s]",m_stFila.nrLinha );
    }

//  if (p = tx.walkTree( entrada, "idAlerta", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.idAlerta = atoi(p);
//          m_vlFila.idAlerta = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado idAlerta [%i]", m_stFila.idAlerta);
//  }
//
//  if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.idEstado = atoi(p);
//          m_vlFila.idEstado = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado idEstado [%i]", m_stFila.idEstado);
//  }
//
//  if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.idSubEstado = atoi(p);
//          m_vlFila.idSubEstado = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado idSubEstado [%i]", m_stFila.idSubEstado);
//  }
//
//  if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
//          m_vlFila.dtFechamentoInicio= 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
//  }
//
//  if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
//          m_vlFila.dtFechamentoFim= 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
//  }
//
//  if (p = tx.walkTree( entrada, "tipoDocumento", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
//          m_vlFila.tipoDocumento= 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado Tipo Documento [%s]", m_stFila.tipoDocumento);
//  }
//
//  if (p = tx.walkTree( entrada, "documento", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          sprintf(m_stFila.documento,"%.*s",sizeof(m_stFila.documento)-1, p );
//          m_vlFila.documento= 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado documento [%s]", m_stFila.documento);
//  }
//
//  if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
//          m_vlFila.pesquisa= 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado pesquisa [%s]", m_stFila.pesquisa);
//  }
//
//  if (p = tx.walkTree( entrada, "inTipoPesquisa", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.inTipoPesquisa = atoi(p);
//          m_vlFila.inTipoPesquisa = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado tpPesquisa [%i]", m_stFila.inTipoPesquisa);
//  }
//
//  if (p = tx.walkTree( entrada, "inAbaPesquisa", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.inAbaPesquisa = atoi(p);
//          m_vlFila.inAbaPesquisa = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado inAbaPesquisa [%i]", m_stFila.inAbaPesquisa);
//  }
//
//  if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
//  {
//      sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
//      m_vlFila.textoContato = 1;
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("cWFAtdMsgRC - Pesquisa Fila - Encontrado textoContato [%s]", m_stFila.textoContato);
//  }
//
//  if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
//  {
//      if (strlen(p) > 0)
//      {
//          m_stFila.tbPausa = atoi(p);
//          m_vlFila.tbPausa = 1;
//      }
//      XMLString::release(&p);
//      tuxfw_getlogger()->debug("WFAtendimento - Pesquisa Fila - Encontrado tbPausa [%i]", m_stFila.tbPausa);
//  }
//
    ULOG_END( "cWFAtdMsgRC::carregaDados()" );
}

////////////////////////////////////////////////////////////////////////////////////////
// As operações abaixo não estão sendo usadas por Resposta ao Cliente
////////////////////////////////////////////////////////////////////////////////////////
//
//int cWFAtdMsgRC::incluir()
//{
//  return proCIncluirWFAndamentoMensagem(&m_stFila, &m_vlFila);
//
//}
//
//bool cWFAtdMsgRC::consultarMensagems( int idPessoaUsuario,long idAtendimento )
//{
//  m_stFila.idPessoaUsuario = idPessoaUsuario;
//  m_vlFila.idPessoaUsuario = 1;
//
//  m_stFila.idAtendimento = idAtendimento;
//  m_vlFila.idAtendimento = 1;
//
//  if (m_vlFila.nrLinha != -1)
//      return proCConsultaWFMensagemLinha(&m_stFila, &m_vlFila, saida);
//  else
//      return proCConsultaWFMensagemUsuario(&m_stFila, &m_vlFila, saida);
//  
//}
//
