/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 


#include "../include/cWFAtendimentoHierarquia.h"

extern bool proCIncluirWFAtendimentoHierarquia(st_AtendimentoHierarquia* dados, st_vlAtendimentoHierarquia* status, XMLGen* saida, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoHierarquia(st_AtendimentoHierarquia* dados, st_vlAtendimentoHierarquia* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoHierarquia(st_AtendimentoHierarquia* dados, st_vlAtendimentoHierarquia* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoHierarquia(st_AtendimentoHierarquia* dados, st_vlAtendimentoHierarquia* status, char* order, XMLGen* saida);

cWFAtendimentoHierarquia::cWFAtendimentoHierarquia(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoHierarquia::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoHierarquia::incluir() indevido!");
}

int cWFAtendimentoHierarquia::incluir(XMLDPR *xmlDpr)
{
	return proCIncluirWFAtendimentoHierarquia(&m_stDados, &m_vlDados, saida, xmlDpr);
}

int cWFAtendimentoHierarquia::alterar()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoHierarquia(&m_stDados, &m_vlDados, saida);
}

long cWFAtendimentoHierarquia::excluir()
{

	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoHierarquia(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoHierarquia::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoHierarquia(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoHierarquia::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoPai", 0 ), p ) 
	{
		m_stDados.idAtendimentoPai = atol( p );
		m_vlDados.idAtendimentoPai = 1;
		XMLString::release(&p);
	}

}

