/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.4.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:04:19 $
 **/


#include "../include/cWFAndamentoMotivo.h"

extern bool proCIncluirWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, XMLGen* saida);
extern bool proCAlterarWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, XMLGen* saida);
extern bool proCExcluirWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, XMLGen* saida);
extern bool proCConsultaWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, char* order, XMLGen* saida);
extern int proCGetWFMotivoCancelamento(int sIdAgrupamentoEstadoTpProc);

cWFAndamentoMotivo::cWFAndamentoMotivo(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}


cWFAndamentoMotivo::cWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;

    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));
}

bool cWFAndamentoMotivo::incluir()
{
	return proCIncluirWFAndamentoMotivo(&m_stDados, &m_vlDados, saida);
}

int cWFAndamentoMotivo::alterar()
{

	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAndamentoMotivo(&m_stDados, &m_vlDados, saida);
}

int cWFAndamentoMotivo::excluir()
{

	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAndamentoMotivo(&m_stDados, &m_vlDados, saida);
}

bool cWFAndamentoMotivo::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAndamentoMotivo(&m_stDados, &m_vlDados, order, saida);
}

int cWFAndamentoMotivo::obterMotivoCancelamento(int idAgrupamentoEstadoTpProc)
{
	return proCGetWFMotivoCancelamento(idAgrupamentoEstadoTpProc);
}

void cWFAndamentoMotivo::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p ) 
	{
		m_stDados.idAndamento = atol( p );
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idMotivo", 0 ), p ) 
	{
		m_stDados.idMotivo = atoi( p );
		m_vlDados.idMotivo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idFase", 0 ), p ) 
	{
		m_stDados.idFase = atoi( p );
		m_vlDados.idFase = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
	{
		m_stDados.idUsuarioAlteracao = atoi( p );
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

}


void cWFAndamentoMotivo::setIdAndamento(long id)
{
	m_stDados.idAndamento = id; 
}
