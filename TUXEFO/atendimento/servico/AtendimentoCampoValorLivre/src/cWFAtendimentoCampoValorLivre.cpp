/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendCampoValorLivre.h"

extern bool proCIncluirWFAtendimentoCampoValorLivre(st_AtendimentoCampoValorLivre* dados, st_vlAtendimentoCampoValorLivre* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoCampoValorLivre(st_AtendimentoCampoValorLivre* dados, st_vlAtendimentoCampoValorLivre* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoCampoValorLivre(st_AtendimentoCampoValorLivre* dados, st_vlAtendimentoCampoValorLivre* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoCampoValorLivre(st_AtendimentoCampoValorLivre* dados, st_vlAtendimentoCampoValorLivre* status, char* order, XMLGen* saida);

cWFAtendimentoCampoValorLivre::cWFAtendimentoCampoValorLivre(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoCampoValorLivre::incluir()
{
	return proCIncluirWFAtendimentoCampoValorLivre(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampoValorLivre::alterar()
{

	if (m_vlDados.idAtendimentoCampo == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoCampoValorLivre(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoCampoValorLivre::excluir()
{

	if (m_vlDados.idAtendimentoCampo == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoCampoValorLivre(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoCampoValorLivre::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if ( p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoCampoValorLivre(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoCampoValorLivre::carregaDados()
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

	if (p = tx.walkTree( entrada, "dsValor", 0 ), p ) 
	{
		strcpy(m_stDados.dsValor, p );
		m_vlDados.dsValor = 1;
		XMLString::release(&p);
	}
}

