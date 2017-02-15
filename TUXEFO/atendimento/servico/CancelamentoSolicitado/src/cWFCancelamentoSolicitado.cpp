/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 

#include <tuxfw.h>
#include "../include/cWFCancelamentoSolicitado.h"

extern bool proCIncluirWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, XMLGen* saida);
extern bool proCAlterarWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, XMLGen* saida);
extern bool proCExcluirWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, XMLGen* saida);
extern bool proCConsultaWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, char* order, XMLGen* saida);

cWFCancelamentoSolicitado::cWFCancelamentoSolicitado(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFCancelamentoSolicitado::cWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;

	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));
}


bool cWFCancelamentoSolicitado::incluir()
{
	return proCIncluirWFCancelamentoSolicitado(&m_stDados, &m_vlDados, saida);
}

int cWFCancelamentoSolicitado::alterar()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFCancelamentoSolicitado(&m_stDados, &m_vlDados, saida);
}

int cWFCancelamentoSolicitado::excluir()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFCancelamentoSolicitado(&m_stDados, &m_vlDados, saida);
}

bool cWFCancelamentoSolicitado::consultar()
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

	return proCConsultaWFCancelamentoSolicitado(&m_stDados, &m_vlDados, order, saida);
}

void cWFCancelamentoSolicitado::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtSolicitacaoCancelamento", 0 ),p) 
	{
		strcpy(m_stDados.dtSolicitacaoCancelamento, p);
		m_vlDados.dtSolicitacaoCancelamento = 1;
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
}

