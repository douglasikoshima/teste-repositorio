
#include "../include/cWFRouterAdmin.h"

extern bool proCIncluirScript(st_RouterAdmin* dados, st_vlRouterAdmin* status, XMLGen* saida);
extern bool proCAlterarScript(st_RouterAdmin* dados, st_vlRouterAdmin* status, XMLGen* saida);

cWFRouterAdmin::cWFRouterAdmin(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFRouterAdmin::incluir()
{
	return proCIncluirScript(&m_stDados, &m_vlDados, saida);
}

bool cWFRouterAdmin::alterar()
{
	return proCAlterarScript(&m_stDados, &m_vlDados, saida);
}

void cWFRouterAdmin::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if (p=tx.walkTree( entrada, "idRouterScript", 0 ),p) 
	{
		m_stDados.idRouterScript = atoi(p);
		m_vlDados.idRouterScript = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "nmScript", 0 ),p) 
	{
		strcpy(m_stDados.nmScript, p);
		m_vlDados.nmScript = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "vlScriptSource", 0 ),p) 
	{
		m_stDados.vlScriptSource = p;
		m_vlDados.vlScriptSource = 1;
	}

	if (p=tx.walkTree( entrada, "inactive", 0 ),p) 
	{
		strcpy(m_stDados.inactive, p);
		m_vlDados.inactive = 1;
        XMLString::release(&p);
	}
}
