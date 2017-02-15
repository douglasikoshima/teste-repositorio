/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 


#include "../include/cWFAtendimentoPesquisaAtual.h"

extern bool proCIncluirWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status);
extern bool proCExcluirWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status, char* order, XMLGen* saida);
extern bool proCObterPesquisaAtendimentoAtual(long  _idAtendimento, long  *_idAtendimentoPesquisaSatisfa);

cWFAtendimentoPesquisaAtual::cWFAtendimentoPesquisaAtual(DOMNode*dnode, XMLGen*xml_g,char *user)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();

    m_stDados.idPessoaUsuario = user ? atoi(user):0;
    m_vlDados.idPessoaUsuario = user ? 1:-1;
}

cWFAtendimentoPesquisaAtual::cWFAtendimentoPesquisaAtual(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
    saida   = xml_g;
}

bool cWFAtendimentoPesquisaAtual::incluir(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status)
{
    return proCIncluirWFAtendimentoPesquisaAtual(dados, status, saida);
}

bool cWFAtendimentoPesquisaAtual::incluir()
{
	return proCIncluirWFAtendimentoPesquisaAtual(&m_stDados, &m_vlDados, saida);
}

long  cWFAtendimentoPesquisaAtual::alterar(st_AtendimentoPesquisaAtual* dados, st_vlAtendimentoPesquisaAtual* status)
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoPesquisaAtual(dados, status);
}

long  cWFAtendimentoPesquisaAtual::alterar()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoPesquisaAtual(&m_stDados, &m_vlDados);
}

long  cWFAtendimentoPesquisaAtual::excluir()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoPesquisaAtual(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoPesquisaAtual::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoPesquisaAtual(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoPesquisaAtual::ObterPesquisaAtendimentoAtual(long idAtendimento,long  *idAtendimentoPesquisaSatisfa)
{
    return proCObterPesquisaAtendimentoAtual(idAtendimento,idAtendimentoPesquisaSatisfa);
}

void cWFAtendimentoPesquisaAtual::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoPesquisaSatisfa", 0 ), p ) 
	{
		m_stDados.idAtendimentoPesquisaSatisfa = atol(p);
		m_vlDados.idAtendimentoPesquisaSatisfa = 1;
		XMLString::release(&p);
	}

}

