/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 


#include "../include/cWFAtendPesquisaSatisfa.h"

extern bool proCIncluirWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, char* order, XMLGen* saida);
extern bool proCExisteWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen* saida);

cWFAtendimentoPesquisaSatisfa::cWFAtendimentoPesquisaSatisfa(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoPesquisaSatisfa::cWFAtendimentoPesquisaSatisfa(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
    saida   = xml_g;
}

bool cWFAtendimentoPesquisaSatisfa::incluir(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status)
{
	return proCIncluirWFAtendimentoPesquisaSatisfa(dados,status,0);
}

bool cWFAtendimentoPesquisaSatisfa::incluir()
{
	return proCIncluirWFAtendimentoPesquisaSatisfa(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaSatisfa::alterar()
{

	if (m_vlDados.idAtendimentoPesquisaSatisfa == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoPesquisaSatisfa(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaSatisfa::excluir()
{

	if (m_vlDados.idAtendimentoPesquisaSatisfa == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoPesquisaSatisfa(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoPesquisaSatisfa::seExiste(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status)
{
	return proCExisteWFAtendimentoPesquisaSatisfa(dados, status, saida);
}

bool cWFAtendimentoPesquisaSatisfa::consultar(st_AtendimentoPesquisaSatisfa* dados, st_vlAtendimentoPesquisaSatisfa* status)
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoPesquisaSatisfa(dados, status, order, saida);
}

bool cWFAtendimentoPesquisaSatisfa::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoPesquisaSatisfa(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoPesquisaSatisfa::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoPesquisaSatisfa", 0 ), p ) 
	{
		m_stDados.idAtendimentoPesquisaSatisfa = atol( p );
		m_vlDados.idAtendimentoPesquisaSatisfa = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtPesquisa", 0 ), p ) 
	{
		strcpy(m_stDados.dtPesquisa, p );
		m_vlDados.dtPesquisa = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p ) 
	{
		m_stDados.idPessoaUsuario = atoi( p );
		m_vlDados.idPessoaUsuario = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "observacao", 0 ), p ) 
	{
		strcpy(m_stDados.observacao, p );
		m_vlDados.observacao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "vlNota", 0 ), p ) 
	{
		m_stDados.vlNota = atoi( p );
		m_vlDados.vlNota = 1;
		XMLString::release(&p);
	}
}

