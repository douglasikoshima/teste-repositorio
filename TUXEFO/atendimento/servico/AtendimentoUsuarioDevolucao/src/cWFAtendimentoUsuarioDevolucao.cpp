/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 


#include "../include/cWFAtendUsuarioDevolucao.h"

extern bool proCIncluirWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, char* order, XMLGen* saida);
extern void proCAtualizarWFAtendimentoUsuarioDevolucao(long _idAtendimento,int _idPessoaUsuario,int _idUsuarioAlteracao);

cWFAtendimentoUsuarioDevolucao::cWFAtendimentoUsuarioDevolucao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}


cWFAtendimentoUsuarioDevolucao::cWFAtendimentoUsuarioDevolucao(st_AtendimentoUsuarioDevolucao* dados, st_vlAtendimentoUsuarioDevolucao* status, XMLGen*xml_g)
{
	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));

	entrada = 0;
	saida   = xml_g;
}

bool cWFAtendimentoUsuarioDevolucao::incluir()
{
	return proCIncluirWFAtendimentoUsuarioDevolucao(&m_stDados, &m_vlDados, saida);
}

void cWFAtendimentoUsuarioDevolucao::atualizar(long idAtendimento,int idPessoaUsuario,int idUsuarioAlteracao)
{
	proCAtualizarWFAtendimentoUsuarioDevolucao(idAtendimento,idPessoaUsuario,idUsuarioAlteracao);
}

int cWFAtendimentoUsuarioDevolucao::alterar()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoUsuarioDevolucao(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoUsuarioDevolucao::excluir()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoUsuarioDevolucao(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoUsuarioDevolucao::consultar()
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

	return proCConsultaWFAtendimentoUsuarioDevolucao(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoUsuarioDevolucao::carregaDados()
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

	if (p=tx.walkTree( entrada, "idPessoaUsuario", 0 ),p) 
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
        XMLString::release(&p);
	}
}


int cWFAtendimentoUsuarioDevolucao::getIdPessoa()
{
	return this->m_stDados.idPessoaUsuario;
}
