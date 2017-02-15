/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendimentoCampo.h"

extern bool proCIncluirWFAtendimentoCampo(st_AtendimentoCampo* dados, st_vlAtendimentoCampo* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoCampo(st_AtendimentoCampo* dados, st_vlAtendimentoCampo* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoCampo(st_AtendimentoCampo* dados, st_vlAtendimentoCampo* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoCampo(st_AtendimentoCampo* dados, st_vlAtendimentoCampo* status, char* order, XMLGen* saida);

cWFAtendimentoCampo::cWFAtendimentoCampo(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoCampo::incluir()
{
	return proCIncluirWFAtendimentoCampo(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampo::alterar()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoCampo(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampo::excluir()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoCampo(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoCampo::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if ( p = tx.walkTree( entrada, "order", 0 ), p)
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoCampo(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoCampo::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoCampo", 0 ), p ) 
	{
		m_stDados.idAtendimentoCampo = atol( p );
		m_vlDados.idAtendimentoCampo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idCampo", 0 ), p ) 
	{
		m_stDados.idCampo = atoi( p );
		m_vlDados.idCampo = 1;
		XMLString::release(&p);
	}

}
