/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendimentoCampoDominio.h"

extern bool proCIncluirWFAtendimentoCampoDominio(st_AtendimentoCampoDominio* dados, st_vlAtendimentoCampoDominio* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoCampoDominio(st_AtendimentoCampoDominio* dados, st_vlAtendimentoCampoDominio* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoCampoDominio(st_AtendimentoCampoDominio* dados, st_vlAtendimentoCampoDominio* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoCampoDominio(st_AtendimentoCampoDominio* dados, st_vlAtendimentoCampoDominio* status, char* order, XMLGen* saida);

cWFAtendimentoCampoDominio::cWFAtendimentoCampoDominio(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoCampoDominio::incluir()
{
	return proCIncluirWFAtendimentoCampoDominio(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampoDominio::alterar()
{

	if (m_vlDados.idAtendimentoCampo == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoCampoDominio(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampoDominio::excluir()
{

	if (m_vlDados.idAtendimentoCampo == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoCampoDominio(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoCampoDominio::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if ( p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoCampoDominio(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoCampoDominio::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoCampo", 0 ), p ) 
	{
		m_stDados.idAtendimentoCampo = atol(p);
		m_vlDados.idAtendimentoCampo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idDominio", 0 ), p ) 
	{
		m_stDados.idDominio = atoi( p );
		m_vlDados.idDominio = 1;
		XMLString::release(&p);
	}

}

