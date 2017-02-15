/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/ 


#include "../include/cWFAtendimentoTeste.h"

extern long proCIncluirWFAtendimentoTeste(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status,XMLDPR *xmlDpr);
extern bool proCIncluirWFTesteResposta(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLGen* saida);
extern bool proCAlterarWFAtendimentoTeste(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoTeste(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoTeste(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, char* order, XMLGen* saida);

cWFAtendimentoTeste::cWFAtendimentoTeste(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoTeste::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoTeste::incluir() indevido!");
}

long cWFAtendimentoTeste::incluir(XMLDPR *xmlDpr)
{
	return proCIncluirWFAtendimentoTeste(&m_stDados, &m_vlDados, xmlDpr);
}

long cWFAtendimentoTeste::incluir(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status,XMLDPR *xmlDpr)
{
	return proCIncluirWFAtendimentoTeste(dados, status, xmlDpr);
}

bool cWFAtendimentoTeste::incluirTesteResposta(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLGen* saidaIns)
{
	return proCIncluirWFTesteResposta(dados, status, saidaIns);
}

int cWFAtendimentoTeste::alterar()
{

	if (m_vlDados.idAtendimentoTeste == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoTeste(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoTeste::excluir()
{

	if (m_vlDados.idAtendimentoTeste == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoTeste(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoTeste::consultar()
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

	return proCConsultaWFAtendimentoTeste(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoTeste::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(m_stDados));
	memset(&m_vlDados,-1,sizeof(m_vlDados));

	if (p=tx.walkTree( entrada, "idAtendimentoTeste", 0 ),p) 
	{
		m_stDados.idAtendimentoTeste = atol(p);
		m_vlDados.idAtendimentoTeste = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idAtendimento", 0 ),p) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dsTeste", 0 ),p) 
	{
		strcpy(m_stDados.dsTeste, p);
		m_vlDados.dsTeste = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "dtTeste", 0 ),p) 
	{
		strcpy(m_stDados.dtTeste, p);
		m_vlDados.dtTeste = 1;
        XMLString::release(&p);
	}

	if (p=tx.walkTree( entrada, "idPessoaUsuario", 0 ),p) 
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
        XMLString::release(&p);
	}
}

