#include "tuxfw.h"
#include "../include/cWFEstado.h"

extern bool proCIncluirWFEstado(st_Estado* dados, st_vlEstado* status, XMLGen* saida);
extern bool proCAlterarWFEstado(st_Estado* dados, st_vlEstado* status, XMLGen* saida);
extern bool proCExcluirWFEstado(st_Estado* dados, st_vlEstado* status, XMLGen* saida);
extern bool proCConsultaWFEstado(st_Estado* dados, st_vlEstado* status, char* order, XMLGen* saida);
extern bool proCPesquisaTodosWFEstado( char* order, XMLGen* saida);

cWFEstado::cWFEstado(st_Estado* dados, st_vlEstado* indicator, XMLGen*xml_g)
{

	memcpy(&m_stDados, dados, sizeof(m_stDados));
	memcpy(&m_vlDados, indicator, sizeof(m_vlDados));

	saida   = xml_g;
    entrada = 0;
}

cWFEstado::cWFEstado(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFEstado::incluir()
{
	return proCIncluirWFEstado(&m_stDados, &m_vlDados, saida);
}

int cWFEstado::alterar()
{

	if (m_vlDados.idEstado == -1) 
	{
		return -1;
	}

	return proCAlterarWFEstado(&m_stDados, &m_vlDados, saida);
}

int cWFEstado::excluir()
{

	if (m_vlDados.idEstado == -1) 
	{
		return -1;
	}

	return proCExcluirWFEstado(&m_stDados, &m_vlDados, saida);
}

bool cWFEstado::consultar()
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

	return proCConsultaWFEstado(&m_stDados, &m_vlDados, order, saida);
}

bool cWFEstado::PesquisaTodos()
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

	return proCPesquisaTodosWFEstado( order, saida );
}

void cWFEstado::carregaDados()
{
   ULOG_START("cWFEstado::carregaDados()");     
   char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idEstado", 0 ),p ) 
	{
		m_stDados.idEstado = atoi(p);
		m_vlDados.idEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "sgEstado", 0 ),p ) 
	{
		strcpy(m_stDados.sgEstado, p);
		m_vlDados.sgEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dsEstado", 0 ),p ) 
	{
		strcpy(m_stDados.dsEstado, p);
		m_vlDados.dsEstado = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "inFiltro", 0 ),p ) 
	{
		m_stDados.inFiltro = atoi(p);
		m_vlDados.inFiltro = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p ) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p ) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}
	
	ULOG_END("cWFEstado::carregaDados()");     
}

