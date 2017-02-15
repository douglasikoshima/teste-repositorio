/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:26 $
 **/ 


#include "../include/cWFFase.h"

extern bool proCIncluirWFFase(st_Fase* dados, st_vlFase* status, XMLGen* saida);
extern bool proCAlterarWFFase(st_Fase* dados, st_vlFase* status, XMLGen* saida);
extern bool proCExcluirWFFase(st_Fase* dados, st_vlFase* status, XMLGen* saida);
extern bool proCConsultaWFFase(Collection &collectionFases,XMLGen* saida);
extern bool proCConsultaWFFase(st_Fase* dados, st_vlFase* status, char* order, Collection &ColFase);

cWFFase::cWFFase(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFFase::incluir()
{
	return proCIncluirWFFase(&m_stDados, &m_vlDados, saida);
}

int cWFFase::alterar()
{

	if (m_vlDados.idFase == -1) 
	{
		return -1;
	}

	return proCAlterarWFFase(&m_stDados, &m_vlDados, saida);
}

int cWFFase::excluir()
{

	if (m_vlDados.idFase == -1) 
	{
		return -1;
	}

	return proCExcluirWFFase(&m_stDados, &m_vlDados, saida);
}

bool cWFFase::obterFases(Collection &collectionFases)
{
    return proCConsultaWFFase(0,0,0,collectionFases);
}

bool cWFFase::consultar(st_Fase* dados, st_vlFase* status, XMLGen* saida)
{
    char ordem[2] = {0,0};

    Collection collectionFases;

    proCConsultaWFFase(dados,status,ordem,collectionFases);

    return proCConsultaWFFase(collectionFases,saida);
}

bool cWFFase::consultar()
{
    char ordem[256];

    ordem[0] = 0;

    if ( entrada )
    {
        char *p;
        if ( p=tx.walkTree( entrada, "ordem", 0 ),p )
        {
            sprintf(ordem,"%.*s",sizeof(ordem)-1,p);
            XMLString::release(&p);
        }
    }

    Collection collectionFases;

    proCConsultaWFFase(&m_stDados, &m_vlDados, ordem, collectionFases);

    return proCConsultaWFFase(collectionFases,saida);
}

void cWFFase::carregaDados()
{
   ULOG_START("cWFFase::carregaDados()");
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if ( p=tx.walkTree( entrada, "idFase", 0 ),p ) 
	{
		m_stDados.idFase = atoi(p);
		m_vlDados.idFase = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "sgFase", 0 ),p ) 
	{
		strcpy(m_stDados.sgFase, p);
		m_vlDados.sgFase = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dsFase", 0 ),p ) 
	{
		strcpy(m_stDados.dsFase, p);
		m_vlDados.dsFase = 1;
        XMLString::release(&p);
	}
	ULOG_END("cWFFase::carregaDados()");     
}

