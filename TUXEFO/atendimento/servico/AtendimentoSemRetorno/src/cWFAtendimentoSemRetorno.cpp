/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/ 


#include "../include/cWFAtendimentoSemRetorno.h"

extern bool proCIncluirWFAtendimentoSemRetorno(st_AtendimentoSemRetorno* dados, st_vlAtendimentoSemRetorno* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoSemRetorno(st_AtendimentoSemRetorno* dados, st_vlAtendimentoSemRetorno* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoSemRetorno(st_AtendimentoSemRetorno* dados, st_vlAtendimentoSemRetorno* status, char* order, XMLGen* saida);

cWFAtendimentoSemRetorno::cWFAtendimentoSemRetorno(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

bool cWFAtendimentoSemRetorno::incluir()
{
	return proCIncluirWFAtendimentoSemRetorno(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoSemRetorno::excluir()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoSemRetorno(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoSemRetorno::consultar()
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

	return proCConsultaWFAtendimentoSemRetorno(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoSemRetorno::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
	}
}

