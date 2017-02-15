/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.3 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 
 

#include "../include/cWFAtendimentoGrupoDevolucao.h"

extern bool proCIncluirWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados, st_vlAtendimentoGrupoDevolucao* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados, st_vlAtendimentoGrupoDevolucao* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados, st_vlAtendimentoGrupoDevolucao* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados, st_vlAtendimentoGrupoDevolucao* status, char* order, XMLGen* saida);
extern bool proCAtualizarWFAtendimentoGrupoDevolucao(long _idAtendimento,int _idGrupo,int _idUsuarioAlteracao);

cWFAtendimentoGrupoDevolucao::cWFAtendimentoGrupoDevolucao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoGrupoDevolucao::cWFAtendimentoGrupoDevolucao(st_AtendimentoGrupoDevolucao* dados, st_vlAtendimentoGrupoDevolucao* status, XMLGen*xml_g)
{
	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));

	entrada = 0;
	saida   = xml_g;
}

bool cWFAtendimentoGrupoDevolucao::incluir()
{
	return proCIncluirWFAtendimentoGrupoDevolucao(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoGrupoDevolucao::alterar()
{
	if (m_vlDados.idAtendimento == -1)  return -1;

	return proCAlterarWFAtendimentoGrupoDevolucao(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoGrupoDevolucao::excluir()
{
	if (m_vlDados.idAtendimento == -1)  return -1;

	return proCExcluirWFAtendimentoGrupoDevolucao(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoGrupoDevolucao::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
	    {
            sprintf(order,"%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAtendimentoGrupoDevolucao(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoGrupoDevolucao::atualizar(long idAtendimento,int idGrupo,int idUsuarioAlteracao)
{
	return proCAtualizarWFAtendimentoGrupoDevolucao(idAtendimento,idGrupo,idUsuarioAlteracao);
}

int cWFAtendimentoGrupoDevolucao::getIdGrupo()
{
	return this->m_stDados.idGrupo; 
}

void cWFAtendimentoGrupoDevolucao::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

    if ( !entrada )
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p )
    {
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idGrupo", 0 ), p )
    {
		m_stDados.idGrupo = atoi( p );
		m_vlDados.idGrupo = 1;
		XMLString::release(&p);
	}
}
