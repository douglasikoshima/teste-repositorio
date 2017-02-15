/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/


#include "../include/cWFAgrupEstadoAtividade.h"

extern bool proCIncluirWFAgrupamentoEstadoAtividade(st_AgrupamentoEstadoAtividade* dados, st_vlAgrupamentoEstadoAtividade* status, XMLGen* saida);
extern bool proCAlterarWFAgrupamentoEstadoAtividade(st_AgrupamentoEstadoAtividade* dados, st_vlAgrupamentoEstadoAtividade* status, XMLGen* saida);
extern bool proCExcluirWFAgrupamentoEstadoAtividade(st_AgrupamentoEstadoAtividade* dados, st_vlAgrupamentoEstadoAtividade* status, XMLGen* saida);
extern bool proCConsultaWFAgrupamentoEstadoAtividade(st_AgrupamentoEstadoAtividade* dados, st_vlAgrupamentoEstadoAtividade* status, char* order, XMLGen* saida);

cWFAgrupamentoEstadoAtividade::cWFAgrupamentoEstadoAtividade(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAgrupamentoEstadoAtividade::incluir()
{
	return proCIncluirWFAgrupamentoEstadoAtividade(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstadoAtividade::alterar()
{

	if (m_vlDados.idAgrupamentoEstadoAtividade == -1) 
	{
		return -1;
	}

	return proCAlterarWFAgrupamentoEstadoAtividade(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstadoAtividade::excluir()
{

	if (m_vlDados.idAgrupamentoEstadoAtividade == -1) 
	{
		return -1;
	}

	return proCExcluirWFAgrupamentoEstadoAtividade(&m_stDados, &m_vlDados, saida);
}

bool cWFAgrupamentoEstadoAtividade::consultar()
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

	return proCConsultaWFAgrupamentoEstadoAtividade(&m_stDados, &m_vlDados, order, saida);
}

void cWFAgrupamentoEstadoAtividade::carregaDados()
{
   
    ULOG_START("cWFAgrupamentoEstadoAtividade::carregaDados()");
    
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idAgrupamentoEstadoAtividade", 0 ),p )
	{
		m_stDados.idAgrupamentoEstadoAtividade = atoi(p);
		m_vlDados.idAgrupamentoEstadoAtividade = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idAtividade", 0 ),p )
	{
		m_stDados.idAtividade = atoi(p);
		m_vlDados.idAtividade = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idAgrupamentoEstado", 0 ),p )
	{
		m_stDados.idAgrupamentoEstado = atoi(p);
		m_vlDados.idAgrupamentoEstado = 1;
        XMLString::release(&p);
	}
	
	ULOG_END("cWFAgrupamentoEstadoAtividade::carregaDados()");
}

