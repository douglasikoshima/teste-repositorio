/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendimentoContatoComunic.h"

extern long  proCIncluirWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* status, char* order, XMLGen* saida);
extern bool proCObtemWFAtendimentoContatoComunic(long sIdAtendimento, XMLGen* saida);
extern bool proCObtemWFAtendimentoContatoComunicEx(long sIdAtendimento, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContatoComunic::cWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* indicator, XMLGen*xml_g)
{

	locado = false;

	m_stDados = dados;
	m_vlDados = indicator;

	saida   = xml_g;
    entrada = 0;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContatoComunic::cWFAtendimentoContatoComunic(DOMNode*dnode, XMLGen*xml_g)
{

	m_stDados = (st_AtendimentoContatoComunic*) malloc(sizeof(st_AtendimentoContatoComunic));
	m_vlDados = (st_vlAtendimentoContatoComunic*) malloc(sizeof(st_vlAtendimentoContatoComunic));

	locado = true;
	
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

/**
	Destrutor da classe.
	Libera a estruturas de dados quando elas foram alocadas internamente e não recebidas no construtor.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContatoComunic::~cWFAtendimentoContatoComunic()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoContatoComunic::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoContatoComunic::incluir() indevido!");
}

long cWFAtendimentoContatoComunic::incluir(XMLDPR *xmlDpr)
{
	long idAtendimentoContatoComunic = proCIncluirWFAtendimentoContatoComunic(m_stDados, m_vlDados,xmlDpr);
	
	saida->createTag("WFAtendimentoContatoComunicVO");
		saida->addItem("idAtendimentoContatoComunic", idAtendimentoContatoComunic);
	saida->closeTag();

	return idAtendimentoContatoComunic;
}

int cWFAtendimentoContatoComunic::alterar()
{

	if (m_vlDados->idAtendimentoContatoComunic == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoContatoComunic(m_stDados, m_vlDados, saida);
}

int cWFAtendimentoContatoComunic::excluir()
{

	if (m_vlDados->idAtendimentoContatoComunic == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoContatoComunic(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoContatoComunic::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

    if ( entrada )
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
		{
		    strcpy( order, p );
			XMLString::release(&p);
		}

	return proCConsultaWFAtendimentoContatoComunic(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoContatoComunic::obtemWFAtdCnC()
{
	if (m_vlDados->idAtendimento == -1)
		return false;
	return proCObtemWFAtendimentoContatoComunic( m_stDados->idAtendimento  , saida );
}

bool cWFAtendimentoContatoComunic::obtemWFAtdCnCEx()
{
	if (m_vlDados->idAtendimento == -1)
		return false;
	return proCObtemWFAtendimentoContatoComunicEx( m_stDados->idAtendimento  , saida );
}

void cWFAtendimentoContatoComunic::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoContatoComunic));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoContatoComunic));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoContatoComunic", 0 ), p ) 
	{
		m_stDados->idAtendimentoContatoComunic = atol( p );
		m_vlDados->idAtendimentoContatoComunic = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol(p);
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsComunicacao", 0 ), p ) 
	{
		strcpy(m_stDados->dsComunicacao, p );
		m_vlDados->dsComunicacao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "nrOrdemUtilizacao", 0 ), p ) 
	{
		m_stDados->nrOrdemUtilizacao = atoi( p );
		m_vlDados->nrOrdemUtilizacao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idTipoComunicacao", 0 ), p ) 
	{
		m_stDados->idTipoComunicacao = atoi( p );
		m_vlDados->idTipoComunicacao = 1;
		XMLString::release(&p);
	}

}

