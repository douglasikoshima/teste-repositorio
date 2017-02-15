/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:32 $
 **/

#include "../include/cWFAtdPriorizacao.h"
#include "../../../commons/msgPadrao.h"

cWFAtendimentoPriorizacao::cWFAtendimentoPriorizacao()
{
    entrada = 0;
    saida = 0;

	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));
}

cWFAtendimentoPriorizacao::cWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status, XMLGen*xml_g)
{
	memcpy(&m_stDados, dados, sizeof(m_stDados));
	memcpy(&m_vlDados, status, sizeof(m_vlDados));
	
    entrada = 0;
	saida = xml_g;
}

cWFAtendimentoPriorizacao::cWFAtendimentoPriorizacao(st_AtendimentoPriorizacao* dados, st_vlAtendimentoPriorizacao* status)
{
	memcpy(&m_stDados, dados, sizeof(m_stDados));
	memcpy(&m_vlDados, status, sizeof(m_vlDados));
	
    entrada = 0;
	saida = 0;
}

cWFAtendimentoPriorizacao::cWFAtendimentoPriorizacao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida = xml_g;
	
	carregaDados();
}

long cWFAtendimentoPriorizacao::incluir()
{
	long idAtendimento = proCIncluirWFAtendimentoPriorizacao(&m_stDados, &m_vlDados);

	return idAtendimento;
}

long cWFAtendimentoPriorizacao::alterar()
{

    long retorno = -1;

	if ( 1 == m_vlDados.idAtendimento ) 
	{
	    retorno =  proCAlterarWFAtendimentoPriorizacao(&m_stDados, &m_vlDados);
	}

    return retorno;
}

long cWFAtendimentoPriorizacao::excluir()
{

    long retorno = -1;

	if (m_vlDados.idAtendimento == -1) 
	{
	    retorno = proCExcluirWFAtendimentoPriorizacao(&m_stDados, &m_vlDados);
	}

    return retorno;
}

long cWFAtendimentoPriorizacao::consultar()
{

    char order[256];

	order[0] = 0;

    if ( entrada )
	{
		char* p;
	    if ( p = tx.walkTree( entrada, "order", 0 ), p )
		{
		    sprintf(order, "%.*s",sizeof(order)-1,p );
			XMLString::release(&p);
		}
	}

	long retorno = 
        proCConsultarWFAtendimentoPriorizacao(&m_stDados, &m_vlDados, order, saida);

    return retorno;
}

void cWFAtendimentoPriorizacao::carregaDados()
{

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtAtualizacao", 0 ), p) 
	{
		strcpy(m_stDados.dtAtualizacao, p );
		m_vlDados.dtAtualizacao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "nmCor", 0 ), p) 
	{
		strcpy(m_stDados.nmCor, p );
		m_vlDados.nmCor = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "nrCriticidade", 0 ), p) 
	{
		strcpy(m_stDados.nrCriticidade, p);
		m_vlDados.nrCriticidade = 1;
		XMLString::release(&p);
	}

// charles resposta ao cliente
	if (p = tx.walkTree( entrada, "nrTentativas", 0 ), p) 
	{
        m_stDados.nrTentativas = atoi(p);
		m_vlDados.nrTentativas = 1;
		XMLString::release(&p);
	}


}

