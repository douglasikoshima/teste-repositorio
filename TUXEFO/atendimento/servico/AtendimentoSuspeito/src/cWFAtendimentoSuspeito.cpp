/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/


#include "../include/cWFAtendimentoSuspeito.h"

extern long  proCIncluirWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, char* order, XMLGen* saida);
extern int proCObterWFAtendimentoSuspeito(long sIdAtendimento);

cWFAtendimentoSuspeito::cWFAtendimentoSuspeito(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoSuspeito::cWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;

	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

long cWFAtendimentoSuspeito::incluir()
{
	return proCIncluirWFAtendimentoSuspeito(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoSuspeito::alterar()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoSuspeito(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoSuspeito::excluir()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoSuspeito(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoSuspeito::consultar()
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

	return proCConsultaWFAtendimentoSuspeito(&m_stDados, &m_vlDados, order, saida);
}

int cWFAtendimentoSuspeito::obterUsuarioSuspeito()
{
	return proCObterWFAtendimentoSuspeito(m_stDados.idAtendimento);
}

void cWFAtendimentoSuspeito::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if (p=tx.walkTree( entrada, "idAtendimentoSuspeito", 0 ),p) 
	{
		m_stDados.idAtendimentoSuspeito = atol( p );
		m_vlDados.idAtendimentoSuspeito = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtSuspeito", 0 ),p) 
	{
		strcpy(m_stDados.dtSuspeito, p);
		m_vlDados.dtSuspeito = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idPessoaUsuario", 0 ),p) 
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idMotivo", 0 ),p) 
	{
		m_stDados.idMotivo = atoi(p);
		m_vlDados.idMotivo = 1;
        XMLString::release(&p);
	}
}

