/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/ 


#include "../include/cWFAtendimentoPesquisaURA.h"

extern bool proCIncluirWFAtendimentoPesquisaURA(st_AtendimentoPesquisaURA* dados, st_vlAtendimentoPesquisaURA* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoPesquisaURA(st_AtendimentoPesquisaURA* dados, st_vlAtendimentoPesquisaURA* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoPesquisaURA(st_AtendimentoPesquisaURA* dados, st_vlAtendimentoPesquisaURA* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoPesquisaURA(st_AtendimentoPesquisaURA* dados, st_vlAtendimentoPesquisaURA* status, char* order, XMLGen* saida);

cWFAtendimentoPesquisaURA::cWFAtendimentoPesquisaURA(DOMNode*dnode, XMLGen*xml_g) {
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoPesquisaURA::incluir() {
	return proCIncluirWFAtendimentoPesquisaURA(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaURA::alterar() {

	if (m_vlDados.idAtendimento == -1)  return -1;

	return proCAlterarWFAtendimentoPesquisaURA(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPesquisaURA::excluir() {

	if (m_vlDados.idAtendimento == -1)  return -1;

	return proCExcluirWFAtendimentoPesquisaURA(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoPesquisaURA::consultar() {
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), 0 )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoPesquisaURA(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoPesquisaURA::carregaDados() {
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) {
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

 	if (p = tx.walkTree( entrada, "vlNota", 0 ), p ) {
		m_stDados.vlNota = atoi( p );
		m_vlDados.vlNota = 1;
		XMLString::release(&p);
	}
}

