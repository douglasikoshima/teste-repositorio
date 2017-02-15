/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:35 $
 **/

#include <tuxfw.h>
#include "../include/cWFAtendimentoDocTecnicoTipo.h"

extern bool proCIncluirWFAtendimentoDocTecnicoTipo(st_AtendimentoDocTecnicoTipo* dados, st_vlAtendimentoDocTecnicoTipo* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoDocTecnicoTipo(st_AtendimentoDocTecnicoTipo* dados, st_vlAtendimentoDocTecnicoTipo* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoDocTecnicoTipo(st_AtendimentoDocTecnicoTipo* dados, st_vlAtendimentoDocTecnicoTipo* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoDocTecnicoTipo(st_AtendimentoDocTecnicoTipo* dados, st_vlAtendimentoDocTecnicoTipo* status, char* order, XMLGen* saida);

cWFAtendimentoDocTecnicoTipo::cWFAtendimentoDocTecnicoTipo(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoDocTecnicoTipo::incluir()
{
	return proCIncluirWFAtendimentoDocTecnicoTipo(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocTecnicoTipo::alterar()
{

	if (m_vlDados.idDocumentoTecnicoTipo == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoDocTecnicoTipo(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoDocTecnicoTipo::excluir()
{

	if (m_vlDados.idDocumentoTecnicoTipo == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoDocTecnicoTipo(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoDocTecnicoTipo::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoDocTecnicoTipo(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoDocTecnicoTipo::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idDocumentoTecnicoTipo", 0 ), p ) 
	{
		m_stDados.idDocumentoTecnicoTipo = atoi( p );
		m_vlDados.idDocumentoTecnicoTipo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsDocumentoTecnicoTipo", 0 ), p ) 
	{
		strcpy( m_stDados.dsDocumentoTecnicoTipo, p );
		m_vlDados.dsDocumentoTecnicoTipo = 1;
		XMLString::release(&p);
	}

}

