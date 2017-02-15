
#include <tuxfw.h>
#include "../include/cWFApoioProcedencia.h"

extern bool proCIncluirWFApoioProcedencia(st_ApoioProcedencia* dados, st_vlApoioProcedencia* status, XMLGen* saida);
extern bool proCAlterarWFApoioProcedencia(st_ApoioProcedencia* dados, st_vlApoioProcedencia* status, XMLGen* saida);
extern bool proCExcluirWFApoioProcedencia(st_ApoioProcedencia* dados, st_vlApoioProcedencia* status, XMLGen* saida);
extern bool proCConsultaWFApoioProcedencia(st_ApoioProcedencia* dados, st_vlApoioProcedencia* status, char* order, XMLGen* saida);

cWFApoioProcedencia::cWFApoioProcedencia(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFApoioProcedencia::incluir()
{
	return proCIncluirWFApoioProcedencia(&m_stDados, &m_vlDados, saida);
}

int cWFApoioProcedencia::alterar()
{

	if (m_vlDados.idProcedencia == -1) 
	{
		return -1;
	}

	return proCAlterarWFApoioProcedencia(&m_stDados, &m_vlDados, saida);
}

int cWFApoioProcedencia::excluir()
{

	if (m_vlDados.idProcedencia == -1) 
	{
		return -1;
	}

	return proCExcluirWFApoioProcedencia(&m_stDados, &m_vlDados, saida);
}

bool cWFApoioProcedencia::consultar()
{
	char order[256];

	order[0] = 0;

	char* p = tx.walkTree( entrada, "order", 0 );

	if ( p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFApoioProcedencia(&m_stDados, &m_vlDados, order, saida);
}

void cWFApoioProcedencia::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idProcedencia", 0 ), p ) 
	{
		m_stDados.idProcedencia = atoi( p );
		m_vlDados.idProcedencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "sgProcedencia", 0 ), p ) 
	{
		strcpy( m_stDados.sgProcedencia, p );
		m_vlDados.sgProcedencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsProcedencia", 0 ), p ) 
	{
		strcpy( m_stDados.dsProcedencia, p );
		m_vlDados.dsProcedencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "vlPeso", 0 ), p ) 
	{
		m_stDados.vlPeso = atoi( p );
		m_vlDados.vlPeso = 1;
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
		strcpy( m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

}

