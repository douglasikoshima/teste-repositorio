/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 


#include "../include/cWFAtendimentoMensagem.h"

extern bool proCIncluirWFAtendimentoMensagem(st_AtendimentoMensagem* dados, st_vlAtendimentoMensagem* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoMensagem(st_AtendimentoMensagem* dados, st_vlAtendimentoMensagem* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoMensagem(st_AtendimentoMensagem* dados, st_vlAtendimentoMensagem* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoMensagem(st_AtendimentoMensagem* dados, st_vlAtendimentoMensagem* status, char* order, XMLGen* saida);
extern bool proCObterWFAtividadeMensagem(int sIdAtividade, XMLGen* saida);
extern bool proCObterWFMensagemDinamico(long sIdAtendimento, XMLGen* saida);

cWFAtendimentoMensagem::cWFAtendimentoMensagem(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoMensagem::cWFAtendimentoMensagem(st_AtendimentoMensagem* dados, st_vlAtendimentoMensagem* indicator, XMLGen*xml_g)
{

	memcpy( &m_stDados , dados,		sizeof(st_AtendimentoMensagem)); 
	memcpy( &m_vlDados , indicator, sizeof(st_vlAtendimentoMensagem)); 

	saida   = xml_g;
}

bool cWFAtendimentoMensagem::incluir()
{
	return proCIncluirWFAtendimentoMensagem(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoMensagem::alterar()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoMensagem(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoMensagem::excluir()
{

	if (m_vlDados.idAtendimentoMensagem == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoMensagem(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoMensagem::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;
	
	if (p = tx.walkTree( entrada, "order", 0 ), p)
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoMensagem(&m_stDados, &m_vlDados, order, saida);
}

int cWFAtendimentoMensagem::obterMensagem()
{

	if (m_vlDados.idAtividade == -1) 
	{
		return -1;
	}

    return proCObterWFAtividadeMensagem(m_stDados.idAtividade, saida);
}

int cWFAtendimentoMensagem::obterAlertaDinamica()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

    return proCObterWFMensagemDinamico(m_stDados.idAtendimento, saida);
}

void cWFAtendimentoMensagem::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoMensagem", 0 ), p ) 
	{
		m_stDados.idAtendimentoMensagem = atol( p );
		m_vlDados.idAtendimentoMensagem = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idMensagemAtendimento", 0 ), p ) 
	{
		m_stDados.idMensagemAtendimento = atol( p );
		m_vlDados.idMensagemAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtividade", 0 ), p ) 
	{
		m_stDados.idAtividade = atoi( p );
		m_vlDados.idAtividade = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtMensagem", 0 ), p ) 
	{
		strcpy(m_stDados.dtMensagem, p );
		m_vlDados.dtMensagem = 1;
		XMLString::release(&p);
	}

}

