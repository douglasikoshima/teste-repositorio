/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/


#include "../include/cWFAtendimentoFrmCampoValor.h"

extern bool proCIncluirWFAtendimentoFrmCampoValor(st_AtendimentoFrmCampoValor* dados, st_vlAtendimentoFrmCampoValor* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoFrmCampoValor(st_AtendimentoFrmCampoValor* dados, st_vlAtendimentoFrmCampoValor* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoFrmCampoValor(st_AtendimentoFrmCampoValor* dados, st_vlAtendimentoFrmCampoValor* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFrmCampoValor(st_AtendimentoFrmCampoValor* dados, st_vlAtendimentoFrmCampoValor* status, char* order, XMLGen* saida);
extern bool proCObtemWFAtendimentoCamposValor(long sIdAtendimento, XMLGen* saida);
extern bool proCObtemWFAtendimentoCamposValorEx(long sIdAtendimento, XMLGen* saida);

cWFAtendimentoFrmCampoValor::cWFAtendimentoFrmCampoValor(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoFrmCampoValor::incluir()
{
	return proCIncluirWFAtendimentoFrmCampoValor(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoFrmCampoValor::alterar()
{

	if (m_vlDados.idAtendimentoFrmCampoValor == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoFrmCampoValor(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoFrmCampoValor::excluir()
{

	if (m_vlDados.idAtendimentoFrmCampoValor == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoFrmCampoValor(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoFrmCampoValor::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, tx.walkTree( entrada, "order", 0 ) );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoFrmCampoValor(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoFrmCampoValor::ObtemAtdFrmCpVl()
{
	if (m_vlDados.idAtendimento == -1)
		return false;
	return proCObtemWFAtendimentoCamposValor( m_stDados.idAtendimento  , saida );
}

bool cWFAtendimentoFrmCampoValor::ObtemAtdFrmCpVlEx()
{
	if (m_vlDados.idAtendimento == -1)
		return false;
	return proCObtemWFAtendimentoCamposValorEx( m_stDados.idAtendimento  , saida );
}

void cWFAtendimentoFrmCampoValor::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol( p );
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoFrmCampoValor", 0 ), p ) 
	{
		m_stDados.idAtendimentoFrmCampoValor = atol( p );
		m_vlDados.idAtendimentoFrmCampoValor = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoFrmCampo", 0 ), p ) 
	{
		m_stDados.idAtendimentoFrmCampo = atol( p );
		m_vlDados.idAtendimentoFrmCampo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "valor", 0 ), p ) 
	{
		strcpy(m_stDados.valor, p );
		m_vlDados.valor = 1;
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
		strcpy(m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}

