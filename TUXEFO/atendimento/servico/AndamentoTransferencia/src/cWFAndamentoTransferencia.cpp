/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:07:32 $
 **/

#include "../include/cWFAndamentoTransferencia.h"

extern bool proCIncluirWFAndamentoTransferencia(st_AndamentoTransferencia* dados, st_vlAndamentoTransferencia* status, XMLGen* saida);
extern bool proCAlterarWFAndamentoTransferencia(st_AndamentoTransferencia* dados, st_vlAndamentoTransferencia* status, XMLGen* saida);
extern bool proCExcluirWFAndamentoTransferencia(st_AndamentoTransferencia* dados, st_vlAndamentoTransferencia* status, XMLGen* saida);
extern bool proCConsultaWFAndamentoTransferencia(st_AndamentoTransferencia* dados, st_vlAndamentoTransferencia* status, char* order, XMLGen* saida);
extern bool proCObtemWFAndamentoTransferencia(long sIdAtendimento, XMLGen* saida);

cWFAndamentoTransferencia::cWFAndamentoTransferencia(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAndamentoTransferencia::cWFAndamentoTransferencia(st_AndamentoTransferencia* dados, st_vlAndamentoTransferencia* status, XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;

	memcpy( &m_stDados , dados, sizeof(st_AndamentoTransferencia)); 
	memcpy( &m_vlDados , status, sizeof(st_vlAndamentoTransferencia)); 
}


bool cWFAndamentoTransferencia::incluir()
{
	return proCIncluirWFAndamentoTransferencia(&m_stDados, &m_vlDados, saida);
}

int cWFAndamentoTransferencia::alterar()
{
	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAndamentoTransferencia(&m_stDados, &m_vlDados, saida);
}

int cWFAndamentoTransferencia::excluir()
{
	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAndamentoTransferencia(&m_stDados, &m_vlDados, saida);
}

bool cWFAndamentoTransferencia::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char *p;
	    if (p = tx.walkTree(entrada,"order",0), p)
	    {
            sprintf(order,"%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAndamentoTransferencia(&m_stDados, &m_vlDados, order, saida);
}

long cWFAndamentoTransferencia::obtemTrf(long idAtendimento,XMLGen* saida)
{
	return proCObtemWFAndamentoTransferencia(idAtendimento, saida);
}

long cWFAndamentoTransferencia::obtemTrf()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCObtemWFAndamentoTransferencia(m_stDados.idAtendimento, saida);
}

void cWFAndamentoTransferencia::carregaDados()
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

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p) 
	{
		m_stDados.idAndamento = atol(p);
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p) 
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idMotivoEncaminhamento", 0 ), p) 
	{
		m_stDados.idMotivoEncaminhamento = atoi(p);
		m_vlDados.idMotivoEncaminhamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtInicioTransferencia", 0 ), p) 
	{
		strcpy(m_stDados.dtInicioTransferencia, p);
		m_vlDados.dtInicioTransferencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtFinTransferencia", 0 ), p) 
	{
		strcpy(m_stDados.dtFinTransferencia, p);
		m_vlDados.dtFinTransferencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

}


void cWFAndamentoTransferencia::setIdAndamento(long id)
{
  m_stDados.idAndamento = id;
}
