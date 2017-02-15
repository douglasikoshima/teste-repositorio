/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author   Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#include "../include/cWFAtdInBoxRC.h"
#include "../../../commons/msgPadrao.h"

extern bool proCConsultaWFAdq(st_AtdInBoxRC* dados, st_vlAtdInBoxRC* status, XMLGen* saida);
extern bool proCConsultaWFAdqLinha(st_AtdInBoxRC* dados, st_vlAtdInBoxRC* status, XMLGen* saida);
extern bool proCConsultaWFAdqDocumento(st_AtdInBoxRC* dados, st_vlAtdInBoxRC* status, XMLGen* saida);
extern bool proCConsultaWFAdqConta(st_AtdInBoxRC* dados, st_vlAtdInBoxRC* status, XMLGen* saida);
extern bool proCConsultaWFAdqNome(st_AtdInBoxRC* dados, st_vlAtdInBoxRC* status, XMLGen* saida);

cWFAtdInBoxRC::cWFAtdInBoxRC()
{
    entrada=0;
    saida=0;

    memset( &m_stFila,  0,sizeof(m_stFila) );
    memset( &m_vlFila, -1,sizeof(m_vlFila) );

}

cWFAtdInBoxRC::~cWFAtdInBoxRC()
{
}

cWFAtdInBoxRC::cWFAtdInBoxRC(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;
	
    memset(&m_stFila,0,sizeof(m_stFila));
    memset(&m_vlFila,-1,sizeof(m_vlFila));

	carregaDados();
}
/* INICIO  */
                    
bool cWFAtdInBoxRC::consultarBoxAdq(int idPessoaUsuario)
{
    
	m_stFila.idPessoaUsuario = idPessoaUsuario;
	m_vlFila.idPessoaUsuario = 1;

    ULOG( ">>> Iniciando cWFAtdInBoxRC::consultarBoxAdq..." );

    if (m_vlFila.inTipoPesquisa == -1)
		return proCConsultaWFAdq(&m_stFila, &m_vlFila, saida);
	else if (m_stFila.inTipoPesquisa == 1)
		return proCConsultaWFAdqLinha(&m_stFila, &m_vlFila, saida);
	else if (m_stFila.inTipoPesquisa == 2)
		return proCConsultaWFAdqConta(&m_stFila, &m_vlFila, saida);
	else if (m_stFila.inTipoPesquisa == 3)
		return proCConsultaWFAdqNome(&m_stFila, &m_vlFila, saida);
	else if (m_stFila.inTipoPesquisa == 4)
		return proCConsultaWFAdqDocumento(&m_stFila, &m_vlFila, saida);
	else
		return true;
	
}

bool cWFAtdInBoxRC::AtualizaProcessos( int idPessoaUsuario )
{
    m_stFila.idPessoaUsuario = idPessoaUsuario;
    m_vlFila.idPessoaUsuario = 1;
    
    ULOG( ">>> Iniciando cWFAtdInBoxRC::AtualizaProcessos..." );
    
   return proCConsultaWFAdq(&m_stFila, &m_vlFila, saida);
    
}




void cWFAtdInBoxRC::carregaDados()
{
    ULOG_START("cWFAtdInBoxRC::carregaDados()");
	// Inicializa as estruturas de dados para armazenar os valores.

	ULOG("cWFAtdInBoxRC - Pesquisa Fila - Iniciando estrutura de dados");

	memset(&m_stFila,0,sizeof(m_stFila));
	memset(&m_vlFila,-1,sizeof(m_vlFila));

	char* p;

	if (p = tx.walkTree( entrada, "idAlerta", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idAlerta = atoi(p);
			m_vlFila.idAlerta = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idAlerta [%i]", m_stFila.idAlerta);
	}

	if (p = tx.walkTree( entrada, "idContato", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idContato = atoi(p);
			m_vlFila.idContato = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idContato [%i]", m_stFila.idContato);
	}

	if (p = tx.walkTree( entrada, "idEstado", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idEstado = atoi(p);
			m_vlFila.idEstado = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idEstado [%i]", m_stFila.idEstado);
	}

	if (p = tx.walkTree( entrada, "idSubEstado", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idSubEstado = atoi(p);
			m_vlFila.idSubEstado = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idSubEstado [%i]", m_stFila.idSubEstado);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
	{
		m_stFila.idPessoaUsuario = atoi(p);
		m_vlFila.idPessoaUsuario = 1;
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idPessoaUsuario [%i]", m_stFila.idPessoaUsuario);
	}

	if (p = tx.walkTree( entrada, "dtAberturaInicio", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.dtAberturaInicio,"%.*s",sizeof(m_stFila.dtAberturaInicio)-1, p );
			m_vlFila.dtAberturaInicio= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - dtAberturaInicio idAtendimento [%s]", m_stFila.dtAberturaInicio);
	}

	if (p = tx.walkTree( entrada, "dtAberturaFim", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.dtAberturaFim,"%.*s",sizeof(m_stFila.dtAberturaFim)-1, p );
			m_vlFila.dtAberturaFim= 1;
		}		
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado dtAberturaFim [%s]", m_stFila.dtAberturaFim);
	}

	if (p = tx.walkTree( entrada, "dtFechamentoInicio", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.dtFechamentoInicio,"%.*s",sizeof(m_stFila.dtFechamentoInicio)-1, p );
			m_vlFila.dtFechamentoInicio= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado dtFechamentoInicio [%s]", m_stFila.dtFechamentoInicio);
	}

	if (p = tx.walkTree( entrada, "dtFechamentoFim", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.dtFechamentoFim,"%.*s",sizeof(m_stFila.dtFechamentoFim)-1, p );
			m_vlFila.dtFechamentoFim= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado dtFechamentoFim [%s]", m_stFila.dtFechamentoFim);
	}

	if (p = tx.walkTree( entrada, "tipoDocumento", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
			m_vlFila.tipoDocumento= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado Tipo Documento [%s]", m_stFila.tipoDocumento);
	}

	if (p = tx.walkTree( entrada, "documento", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.documento,"%.*s",sizeof(m_stFila.documento)-1, p );
			m_vlFila.documento= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado documento [%s]", m_stFila.documento);
	}

	if (p = tx.walkTree( entrada, "pesquisa", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.pesquisa,"%.*s",sizeof(m_stFila.pesquisa)-1, p );
			m_vlFila.pesquisa= 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado pesquisa [%s]", m_stFila.pesquisa);
	}

	if (p = tx.walkTree( entrada, "inTipoPesquisa", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.inTipoPesquisa = atoi(p);
			m_vlFila.inTipoPesquisa = 1;
            if ( m_stFila.inTipoPesquisa == 0 )
            {
                sprintf(m_stFila.tipoDocumento,"%.*s",sizeof(m_stFila.tipoDocumento)-1, p );
                m_vlFila.tipoDocumento= 1;
                m_stFila.inTipoPesquisa = 4; // Documentos
            }
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado tpPesquisa [%i]", m_stFila.inTipoPesquisa);
	}

	if (p = tx.walkTree( entrada, "inAbaPesquisa", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.inAbaPesquisa = atoi(p);
			m_vlFila.inAbaPesquisa = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado inAbaPesquisa [%i]", m_stFila.inAbaPesquisa);
	}

	if (p = tx.walkTree( entrada, "textoContato", 0 ), p) 
	{
		sprintf(m_stFila.textoContato,"%.*s",sizeof(m_stFila.textoContato)-1, p );
		m_vlFila.textoContato = 1;
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado textoContato [%s]", m_stFila.textoContato);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.idAtendimento = atol(p);
			m_vlFila.idAtendimento = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado idAtendimento [%i]", m_stFila.idAtendimento);
	}

	if (p = tx.walkTree( entrada, "nrLinha", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			sprintf(m_stFila.nrLinha,"%.*s",sizeof(m_stFila.nrLinha)-1, p );
			m_vlFila.nrLinha = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado nrLinha [%s]", m_stFila.nrLinha);
	}



	if (p = tx.walkTree( entrada, "tabPausa", 0 ), p) 
	{
		if (strlen(p) > 0)
		{
			m_stFila.tbPausa = atoi(p);
			m_vlFila.tbPausa = 1;
		}
		XMLString::release(&p);
		ULOG("cWFAtdInBoxRC - Pesquisa Fila - Encontrado tbPausa [%i]", m_stFila.tbPausa);
	}

	ULOG_END("cWFAtdInBoxRC::carregaDados()");
}


