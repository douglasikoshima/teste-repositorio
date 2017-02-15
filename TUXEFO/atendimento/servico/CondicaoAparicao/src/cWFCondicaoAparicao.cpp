/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/ 


#include "../include/cWFCondicaoAparicao.h"

extern bool proCIncluirWFCondicaoAparicao(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status, XMLGen* saida);
extern bool proCAlterarWFCondicaoAparicao(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status, XMLGen* saida);
extern bool proCExcluirWFCondicaoAparicao(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status, XMLGen* saida);
extern bool proCConsultaWFCondicaoAparicao(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status, char* order, XMLGen* saida);
extern bool proCObterWFCondicaoAparicao(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status);

extern bool proCObtemWFCndAcoes(int sIdFluxoFase, int sIdAtividade, int sIdAtividadeAt, XMLGen* saida);
extern int proCExecutaWFCondicaoAparicao(char* sDsQuery, unsigned long sIdParametro);

cWFCondicaoAparicao::cWFCondicaoAparicao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFCondicaoAparicao::incluir()
{
	return proCIncluirWFCondicaoAparicao(&m_stDados, &m_vlDados, saida);
}

int cWFCondicaoAparicao::alterar()
{

	if (m_vlDados.idCondicaoAparicao == -1) 
	{
		return -1;
	}

	return proCAlterarWFCondicaoAparicao(&m_stDados, &m_vlDados, saida);
}

int cWFCondicaoAparicao::excluir()
{

	if (m_vlDados.idCondicaoAparicao == -1) 
	{
		return -1;
	}

	return proCExcluirWFCondicaoAparicao(&m_stDados, &m_vlDados, saida);
}

bool cWFCondicaoAparicao::consultar()
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

	return proCConsultaWFCondicaoAparicao(&m_stDados, &m_vlDados, order, saida);
}

//====================================================================================================
// A partir desta versão a operação irá decidir ela mesma se a condição é satisfeita ou não, evitando
// com isso o tráfego excessivo de estruturas XML.
// Maio, 2006 - Cassio
//====================================================================================================
//
// bool cWFCondicaoAparicao::ObtemWFCndAcoes(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status,XMLGen *saida)
// {
//     char order[] = "";
// 
//     if ( status->idFluxoFase == -1 ) { return false; }
//     if ( status->idAtividade == -1 ) { return false; }
//     if ( status->idAtividadeAt == -1 ) { return false; }
// 
//     return proCConsultaWFCondicaoAparicao(dados, status, order, saida);
// }

bool cWFCondicaoAparicao::ObtemWFCndAcoes(st_CondicaoAparicao* dados, st_vlCondicaoAparicao* status)
{
    if ( status->idFluxoFase == -1 ) { return false; }

    return proCObterWFCondicaoAparicao(dados,status);
}

bool cWFCondicaoAparicao::ObtemWFCndAcoes()
{
    if (m_vlDados.idFluxoFase == -1) { return false; }

    if (m_stDados.idAtividade != m_stDados.idAtividadeAt)
    {
        return proCObterWFCondicaoAparicao(&m_stDados,&m_vlDados);
    }

    return true;
}

bool cWFCondicaoAparicao::ObtemWFCnd()
{
    if (m_vlDados.dsQuery == -1) { return false; }
    if (m_vlDados.idParametro == -1) { return false; }

    return proCExecutaWFCondicaoAparicao(m_stDados.dsQuery,m_stDados.idParametro)==0 ? false : true;
}

void cWFCondicaoAparicao::carregaDados()
{
   ULOG_START("cWFCondicaoAparicao::carregaDados()");
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( entrada )
    {
	    if ( p=tx.walkTree( entrada, "idCondicaoAparicao", 0 ),p ) 
	    {
		    m_stDados.idCondicaoAparicao = atoi(p);
		    m_vlDados.idCondicaoAparicao = 1;
            XMLString::release(&p);
	    }

	    if ( p=tx.walkTree( entrada, "idFluxoFase", 0 ),p ) 
	    {
		    m_stDados.idFluxoFase = atoi(p);
		    m_vlDados.idFluxoFase = 1;
            XMLString::release(&p);
	    }

	    if ( p=tx.walkTree( entrada, "dsQuery", 0 ),p ) 
	    {
		    strcpy(m_stDados.dsQuery, p);
		    m_vlDados.dsQuery = 1;
            XMLString::release(&p);
	    }

	    if ( p=tx.walkTree( entrada, "idAtividade", 0 ),p ) 
	    {
		    m_stDados.idAtividade = atoi(p);
		    m_vlDados.idAtividade = 1;
            XMLString::release(&p);
	    }

	    if ( p=tx.walkTree( entrada, "idAtividadeAt", 0 ),p ) 
	    {
		    m_stDados.idAtividadeAt = atoi(p);
		    m_vlDados.idAtividadeAt = 1;
            XMLString::release(&p);
	    }

	    if ( p=tx.walkTree( entrada, "idParametro", 0 ),p ) 
	    {
		    m_stDados.idParametro = strtoul(p, NULL,0);
		    m_vlDados.idParametro = 1;
            XMLString::release(&p);
	    }
    }
    ULOG_END("cWFCondicaoAparicao::carregaDados()");    
}

