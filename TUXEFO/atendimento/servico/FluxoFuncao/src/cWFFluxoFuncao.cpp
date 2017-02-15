/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:12 $
 **/ 


#include "../include/cWFFluxoFuncao.h"

extern bool proCIncluirWFFluxoFuncao(st_FluxoFuncao* dados, st_vlFluxoFuncao* status, XMLGen* saida);
extern bool proCAlterarWFFluxoFuncao(st_FluxoFuncao* dados, st_vlFluxoFuncao* status, XMLGen* saida);
extern bool proCExcluirWFFluxoFuncao(st_FluxoFuncao* dados, st_vlFluxoFuncao* status, XMLGen* saida);
extern bool proCConsultaWFFluxoFuncao(st_FluxoFuncao* dados, st_vlFluxoFuncao* status, char* order, XMLGen* saida);

cWFFluxoFuncao::cWFFluxoFuncao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFFluxoFuncao::incluir()
{
	return proCIncluirWFFluxoFuncao(&m_stDados, &m_vlDados, saida);
}

int cWFFluxoFuncao::alterar()
{
	if (m_vlDados.idFluxoFuncao == -1) 
	{
		return -1;
	}

	return proCAlterarWFFluxoFuncao(&m_stDados, &m_vlDados, saida);
}

int cWFFluxoFuncao::excluir()
{
	if (m_vlDados.idFluxoFuncao == -1) 
	{
		return -1;
	}

	return proCExcluirWFFluxoFuncao(&m_stDados, &m_vlDados, saida);
}

bool cWFFluxoFuncao::consultar(st_FluxoFuncao* dados,st_vlFluxoFuncao* status,char *order,XMLGen* saida)
{
	return proCConsultaWFFluxoFuncao(dados,status,order,saida);
}

bool cWFFluxoFuncao::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
        char *p;
	    if ( p=tx.walkTree( entrada, "order", 0 ),p )
        {
            sprintf(order,"%.*s",sizeof(order)-1,p);
            XMLString::release(&p);
        }
    }

	return proCConsultaWFFluxoFuncao(&m_stDados, &m_vlDados, order, saida);
}

void cWFFluxoFuncao::carregaDados()
{
   ULOG_START("cWFFluxoFuncao::carregaDados()");
   char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idFluxoFuncao", 0 ),p ) 
	{
		m_stDados.idFluxoFuncao = atoi(p);
		m_vlDados.idFluxoFuncao = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idFluxoFase", 0 ),p ) 
	{
		m_stDados.idFluxoFase = atoi(p);
		m_vlDados.idFluxoFase = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "numOrdem", 0 ),p ) 
	{
		m_stDados.numOrdem = atoi(p);
		m_vlDados.numOrdem = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idRouterScript", 0 ),p ) 
	{
		m_stDados.idRouterScript = atoi(p);
		m_vlDados.idRouterScript = 1;
        XMLString::release(&p);
	}
	ULOG_END("cWFFluxoFuncao::carregaDados()");
}

