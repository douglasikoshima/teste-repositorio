/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:39:27 $
 **/


#include "../include/cWFAtendimentoDocumento.h"

extern bool proCIncluirWFAtendimentoDocumento(st_AtendimentoDocumento* dados, st_vlAtendimentoDocumento* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoDocumento(st_AtendimentoDocumento* dados, st_vlAtendimentoDocumento* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoDocumento(st_AtendimentoDocumento* dados, st_vlAtendimentoDocumento* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoDocumento(st_AtendimentoDocumento* dados, st_vlAtendimentoDocumento* status, char* order, XMLGen* saida);

cWFAtendimentoDocumento::cWFAtendimentoDocumento(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoDocumento::cWFAtendimentoDocumento()
{
	entrada = 0;
	saida   = 0;
}

bool cWFAtendimentoDocumento::incluir(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,XMLGen *xml_g)
{
	return proCIncluirWFAtendimentoDocumento(dados,status,xml_g);
}

bool cWFAtendimentoDocumento::incluir()
{
	return proCIncluirWFAtendimentoDocumento(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocumento::alterar()
{

	if (m_vlDados.idAtendimentoDocumento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoDocumento(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocumento::excluir()
{

	if (m_vlDados.idAtendimentoDocumento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoDocumento(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoDocumento::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoDocumento(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoDocumento::carregaDados()
{
  
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoDocumento", 0 ), p) 
	{
		m_stDados.idAtendimentoDocumento = atol( p );
		m_vlDados.idAtendimentoDocumento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idDocumentoTecnico", 0 ), p) 
	{
		m_stDados.idDocumentoTecnico = atoi( p );
		m_vlDados.idDocumentoTecnico = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p) 
	{
		m_stDados.idUsuarioAlteracao = atoi( p );
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

}

