/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendimentoAgendamento.h"

extern bool proCIncluirWFAtendimentoAgendamento(st_AtendimentoAgendamento* dados, st_vlAtendimentoAgendamento* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoAgendamento(st_AtendimentoAgendamento* dados, st_vlAtendimentoAgendamento* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoAgendamento(st_AtendimentoAgendamento* dados, st_vlAtendimentoAgendamento* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAgendamento(st_AtendimentoAgendamento* dados, st_vlAtendimentoAgendamento* status, char* order, XMLGen* saida);

cWFAtendimentoAgendamento::cWFAtendimentoAgendamento(st_AtendimentoAgendamento* dados, st_vlAtendimentoAgendamento* indicator, XMLGen*xml_g)
{
	memcpy( &m_stDados , dados, sizeof(st_AtendimentoAgendamento)); 
	memcpy( &m_vlDados , indicator, sizeof(st_vlAtendimentoAgendamento)); 
    entrada		= 0;
	saida		= xml_g;
}

cWFAtendimentoAgendamento::cWFAtendimentoAgendamento(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoAgendamento::incluir()
{
	return proCIncluirWFAtendimentoAgendamento(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoAgendamento::alterar()
{

	if (m_vlDados.idAtendimentoAgendamento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoAgendamento(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoAgendamento::excluir()
{

	if (m_vlDados.idAtendimentoAgendamento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoAgendamento(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoAgendamento::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if ( p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoAgendamento(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoAgendamento::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoAgendamento", 0 ), p ) 
	{
		m_stDados.idAtendimentoAgendamento = atol( p );
		m_vlDados.idAtendimentoAgendamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol( p );
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p ) 
	{
		m_stDados.idAndamento = atol( p );
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtAtendimentoAgendamento", 0 ), p ) 
	{
		strcpy(m_stDados.dtAtendimentoAgendamento, p );
		m_vlDados.dtAtendimentoAgendamento = 1;
		XMLString::release(&p);
	}

}

