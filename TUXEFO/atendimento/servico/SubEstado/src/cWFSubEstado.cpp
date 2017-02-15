#include "../include/cWFSubEstado.h"

extern bool proCIncluirWFSubEstado(st_WFSubEstado* dados, st_vlWFSubEstado* status, XMLGen* saida);
extern bool proCAlterarWFSubEstado(st_WFSubEstado* dados, st_vlWFSubEstado* status, XMLGen* saida);
extern bool proCExcluirWFSubEstado(st_WFSubEstado* dados, st_vlWFSubEstado* status, XMLGen* saida);
extern bool proCConsultaWFSubEstado(st_WFSubEstado* dados, st_vlWFSubEstado* status, char* order, XMLGen* saida);
extern bool proCConsultaWFSubEstadoEstado(int idEstado, XMLGen* saida);

cWFSubEstado::cWFSubEstado(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFSubEstado::incluir()
{
	return proCIncluirWFSubEstado(&m_stDados, &m_vlDados, saida);
}

int cWFSubEstado::alterar()
{

	if (m_vlDados.idSubEstado == -1)
	{
		return -1;
	}

	return proCAlterarWFSubEstado(&m_stDados, &m_vlDados, saida);
}

int cWFSubEstado::excluir()
{
	if (m_vlDados.idSubEstado == -1)
	{
		return -1;
	}

	return proCExcluirWFSubEstado(&m_stDados, &m_vlDados, saida);
}

bool cWFSubEstado::consultar()
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

	return proCConsultaWFSubEstado(&m_stDados, &m_vlDados, order, saida);
}

int cWFSubEstado::consultarEstado()
{
	// ?? int estado = 0;

	if (m_vlDados.idEstado == -1)
	{
		return -2;
	}

	return proCConsultaWFSubEstadoEstado(m_stDados.idEstado, saida);
}

void cWFSubEstado::carregaDados()
{
   
   ULOG_START("cWFSubEstado::carregaDados()");
   
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idSubEstado", 0 ),p ) 
	{
		m_stDados.idSubEstado = atoi(p);
		m_vlDados.idSubEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "sgSubEstado", 0 ),p ) 
	{
		strcpy(m_stDados.sgSubEstado, p);
		m_vlDados.sgSubEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dsSubEstado", 0 ),p ) 
	{
		strcpy(m_stDados.dsSubEstado, p);
		m_vlDados.dsSubEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idEstado", 0 ),p ) 
	{
		m_stDados.idEstado = atoi(p);
		m_vlDados.idEstado = 1;
        XMLString::release(&p);
	}
	
	ULOG_END("cWFSubEstado::carregaDados()");
}

