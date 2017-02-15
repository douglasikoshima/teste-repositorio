#include <tuxfw.h>
#include "../include/cWFAgrupamentoEstado.h"

extern bool proCIncluirWFAgrupamentoEstado(st_WFAgrupamentoEstado* dados, st_vlWFAgrupamentoEstado* status, XMLGen* saida);
extern bool proCAlterarWFAgrupamentoEstado(st_WFAgrupamentoEstado* dados, st_vlWFAgrupamentoEstado* status, XMLGen* saida);
extern bool proCExcluirWFAgrupamentoEstado(st_WFAgrupamentoEstado* dados, st_vlWFAgrupamentoEstado* status, XMLGen* saida);
extern bool proCConsultaWFAgrupamentoEstado(st_WFAgrupamentoEstado* dados, st_vlWFAgrupamentoEstado* status, char* order, XMLGen* saida);
extern bool proCConsultaWFAgrupamentoEstadoDetalhado(st_WFAgrupamentoEstado* dados, st_vlWFAgrupamentoEstado* status, char* order, XMLGen* saida);

cWFAgrupamentoEstado::cWFAgrupamentoEstado(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAgrupamentoEstado::incluir()
{
	return proCIncluirWFAgrupamentoEstado(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstado::alterar()
{

	if (m_vlDados.idAgrupamentoEstado == -1) 
	{
		return -1;
	}

	return proCAlterarWFAgrupamentoEstado(&m_stDados, &m_vlDados, saida);
}

int cWFAgrupamentoEstado::excluir()
{

	if (m_vlDados.idAgrupamentoEstado == -1) 
	{
		return -1;
	}

	return proCExcluirWFAgrupamentoEstado(&m_stDados, &m_vlDados, saida);
}

bool cWFAgrupamentoEstado::consultar()
{
	char order[256];
    char *p;

	order[0] = 0;

    if ( entrada )
    {
	    if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

	return proCConsultaWFAgrupamentoEstado(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAgrupamentoEstado::consultarDetalhado()
{
	char order[256];
    char *p;

	order[0] = 0;

    if ( entrada )
    {
	    if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

	return proCConsultaWFAgrupamentoEstadoDetalhado(&m_stDados, &m_vlDados, order, saida);
}

void cWFAgrupamentoEstado::carregaDados()
{
   ULOG_START("cWFAgrupamentoEstado::carregaDados()");
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idAgrupamentoEstado", 0 ),p )
	{
		m_stDados.idAgrupamentoEstado = atoi(p);
		m_vlDados.idAgrupamentoEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idEstado", 0 ),p )
	{
		m_stDados.idEstado = atoi(p);
		m_vlDados.idEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idSubEstado", 0 ),p )
	{
		m_stDados.idSubEstado = atoi(p);
		m_vlDados.idSubEstado = 1;
        XMLString::release(&p);
	}
   ULOG_END("cWFAgrupamentoEstado::carregaDados()");	
}

