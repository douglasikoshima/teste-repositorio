/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.118.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:38:43 $
 **/

#include <tuxfw.h>
#include "../include/cWFAtendimentoDocTecnicoEstado.h"

extern bool proCIncluirWFAtendimentoDocTecnicoEstado(st_AtendimentoDocTecnicoEstado* dados, st_vlAtendimentoDocTecnicoEstado* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoDocTecnicoEstado(st_AtendimentoDocTecnicoEstado* dados, st_vlAtendimentoDocTecnicoEstado* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoDocTecnicoEstado(st_AtendimentoDocTecnicoEstado* dados, st_vlAtendimentoDocTecnicoEstado* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoDocTecnicoEstado(st_AtendimentoDocTecnicoEstado* dados, st_vlAtendimentoDocTecnicoEstado* status, char* order, XMLGen* saida);

cWFAtendimentoDocTecnicoEstado::cWFAtendimentoDocTecnicoEstado(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoDocTecnicoEstado::incluir()
{
	return proCIncluirWFAtendimentoDocTecnicoEstado(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocTecnicoEstado::alterar()
{

	if (m_vlDados.idDocumentoTecnicoEstado == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoDocTecnicoEstado(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocTecnicoEstado::excluir()
{

	if (m_vlDados.idDocumentoTecnicoEstado == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoDocTecnicoEstado(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoDocTecnicoEstado::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if ( p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoDocTecnicoEstado(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoDocTecnicoEstado::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idDocumentoTecnicoEstado", 0 ), p ) 
	{
		m_stDados.idDocumentoTecnicoEstado = atoi( p );
		m_vlDados.idDocumentoTecnicoEstado = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsDocumentoTecnicoEstado", 0 ), p ) 
	{
		strcpy( m_stDados.dsDocumentoTecnicoEstado, p );
		m_vlDados.dsDocumentoTecnicoEstado = 1;
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

