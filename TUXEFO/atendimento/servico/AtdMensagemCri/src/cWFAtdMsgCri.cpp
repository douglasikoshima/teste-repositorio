/**
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Miguel Angel Benaventes
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/


#include "../include/cWFAtdMsgCri.h"
#include "../../../commons/msgPadrao.h"

extern bool proCIncluirWFAndamentoMensagem(st_AtdMsgCri* dados, st_vlAtdMsgCri* status);
extern bool proCConsultaWFMensagemUsuario(st_AtdMsgCri* dados, st_vlAtdMsgCri* status, XMLGen* saida);
extern bool proCConsultaWFMensagemLinha(st_AtdMsgCri* dados, st_vlAtdMsgCri* status, XMLGen* saida);

cWFAtdMsgCri::cWFAtdMsgCri()
{
    entrada=0;
    saida=0;

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

}

cWFAtdMsgCri::cWFAtdMsgCri(st_AtdMsgCri* dados, st_vlAtdMsgCri* status)
{
    memcpy(&m_stFila,dados,sizeof(m_stFila));
    memcpy(&m_vlFila,status,sizeof(m_vlFila));

    entrada = 0;
    saida   = 0;
}

cWFAtdMsgCri::cWFAtdMsgCri(st_AtdMsgCri* dados, st_vlAtdMsgCri* status, XMLGen*xml_g)
{
    memcpy(&m_stFila,dados,sizeof(m_stFila));
    memcpy(&m_vlFila,status,sizeof(m_vlFila));

    entrada = 0;
    saida   = xml_g;
}

cWFAtdMsgCri::cWFAtdMsgCri(DOMNode*dnode, XMLGen*xml_g)
{
    entrada = dnode;
    saida   = xml_g;
    
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    carregaDados();
}

bool cWFAtdMsgCri::incluir()
{
    return proCIncluirWFAndamentoMensagem(&m_stFila, &m_vlFila);
}

bool cWFAtdMsgCri::consultarMensagems( int idPessoaUsuario, long idAtendimento )
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
        return proCConsultaWFMensagemLinha(&m_stFila, &m_vlFila, saida);
    }

    return proCConsultaWFMensagemUsuario(&m_stFila, &m_vlFila, saida);
}

void cWFAtdMsgCri::carregaDados()
{
    // Inicializa as estruturas de dados para armazenar os valores.

    ULOG_START( "cWFAtdMsgCri::carregaDados()" );

    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

    int idAuxPrm = 0;
    char* p;

    if (p = tx.walkTree( entrada, "idAlerta", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.idAlerta = idAuxPrm;
                m_vlFila.idAlerta = 1;
                ULOG( "idAlerta [%i]", m_stFila.idAlerta);
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.idContato = idAuxPrm;
                m_vlFila.idContato = 1;
                ULOG( "idContato [%i]", m_stFila.idContato);
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.idEstado = idAuxPrm;
                m_vlFila.idEstado = 1;
                ULOG( "idEstado [%i]", m_stFila.idEstado);
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.idSubEstado = idAuxPrm;
                m_vlFila.idSubEstado = 1;
                ULOG( "idSubEstado [%i]", m_stFila.idSubEstado);
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
    {
        m_stFila.idPessoaUsuario = atoi(p);
        m_vlFila.idPessoaUsuario = 1;
        XMLString::release(&p);
        ULOG( "idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
    }

    if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
            m_vlFila.dtAberturaInicio= 1;
            ULOG( "dtAberturaInicio [%s]", m_stFila.dtAberturaInicio);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
            m_vlFila.dtAberturaFim= 1;
            ULOG("dtAberturaFim [%s]", m_stFila.dtAberturaFim);
        }       
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
            m_vlFila.dtFechamentoInicio= 1;
            ULOG( "dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
            m_vlFila.dtFechamentoFim= 1;
            ULOG( "dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "tipoDocumento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
            m_vlFila.tipoDocumento= 1;
            ULOG( "tipoDocumento [%s]", m_stFila.tipoDocumento);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "documento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.documento,"%.*s",sizeof(m_stFila.documento)-1, p );
            m_vlFila.documento= 1;
            ULOG( "documento [%s]", m_stFila.documento);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
            m_vlFila.pesquisa= 1;
            ULOG( "pesquisa [%s]", m_stFila.pesquisa);
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "inTipoPesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.inTipoPesquisa = idAuxPrm;
                m_vlFila.inTipoPesquisa = 1;
                ULOG( "inTipoPesquisa [%i]", m_stFila.inTipoPesquisa );
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "inAbaPesquisa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.inAbaPesquisa = idAuxPrm;
                m_vlFila.inAbaPesquisa = 1;
                ULOG( "inAbaPesquisa [%i]", m_stFila.inAbaPesquisa );
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
    {
        sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
        m_vlFila.textoContato = 1;
        XMLString::release(&p);
        ULOG( "textoContato [%s]", m_stFila.textoContato);
    }

    if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = strtoul(p,NULL,0);
            if ( idAuxPrm > 0 )
            {
                m_stFila.idAtendimento = idAuxPrm;
                m_vlFila.idAtendimento = 1;
                ULOG( "idAtendimento [%lu]", m_stFila.idAtendimento);
            }
        }
        XMLString::release(&p);
    }

    if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
            m_vlFila.nrLinha = 1;
            ULOG( "nrLinha [%s]", m_stFila.nrLinha);
        }
        XMLString::release(&p);
    }



    if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
    {
        if (strlen(p) > 0)
        {
            idAuxPrm = atoi(p);
            if ( idAuxPrm > 0 )
            {
                m_stFila.tbPausa = idAuxPrm;
                m_vlFila.tbPausa = 1;
                ULOG( "tbPausa [%i]", m_stFila.tbPausa );
            }
        }
        XMLString::release(&p);
    }

    ULOG_END( "cWFAtdMsgCri::carregaDados()" );
}
