/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:32:19 $
 **/


#include "../include/cWFAtendimentoBaixaAtual.h"

extern long  proCIncluirWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* status);
extern bool proCAlterarWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* status, char* order, XMLGen* saida);
extern bool proCObtemWFBaixaAtualAtendimento(long sIdAtendimento, XMLGen* saida);
extern bool proCObterWFIdAtdBxaHistorico( long _idAtendimento,long *_idAtendimentoBaixaHistorico);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoBaixaAtual::cWFAtendimentoBaixaAtual(st_AtendimentoBaixaAtual* dados, st_vlAtendimentoBaixaAtual* indicator, XMLGen*xml_g)
{
	locado = false;

	m_stDados = dados;
	m_vlDados = indicator;

	entrada = 0;
    saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoBaixaAtual::cWFAtendimentoBaixaAtual(DOMNode*dnode, XMLGen*xml_g)
{
	m_stDados = (st_AtendimentoBaixaAtual*) malloc(sizeof(st_AtendimentoBaixaAtual));
	m_vlDados = (st_vlAtendimentoBaixaAtual*) malloc(sizeof(st_vlAtendimentoBaixaAtual));

	locado = true;
	
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

/**
	Destrutor da classe.
	Libera a estruturas de dados quando elas foram alocadas internamente e não recebidas no construtor.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoBaixaAtual::~cWFAtendimentoBaixaAtual()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

long cWFAtendimentoBaixaAtual::incluir()
{
	long idBaixaAtual = proCIncluirWFAtendimentoBaixaAtual(m_stDados, m_vlDados);
		
	saida->createTag("WFAtendimentoBaixaAtualVO");
		saida->addItem("idAtendimento", idBaixaAtual);
	saida->closeTag();

	return idBaixaAtual;
}

long cWFAtendimentoBaixaAtual::alterar()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoBaixaAtual(m_stDados, m_vlDados, saida);
}

long cWFAtendimentoBaixaAtual::excluir()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoBaixaAtual(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoBaixaAtual::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

    if ( entrada )
	    if (p = tx.walkTree( entrada, "order", 0 ), p)
		{
		    strcpy( order, p );
			XMLString::release(&p);
		}


	return proCConsultaWFAtendimentoBaixaAtual(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoBaixaAtual::obterIdAtendimentoBaixaHistorico(long idAtendimento,long *idBaixaAtual)
{
	return proCObterWFIdAtdBxaHistorico(idAtendimento,idBaixaAtual);
}

bool cWFAtendimentoBaixaAtual::obterAtdBxA()
{
	if (m_vlDados->idAtendimento == -1) 
	{
		return false;
	}

	return proCObtemWFBaixaAtualAtendimento(m_stDados->idAtendimento, saida);
}

void cWFAtendimentoBaixaAtual::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoBaixaAtual));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoBaixaAtual));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol( p );
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoBaixaHistorico", 0 ), p ) 
	{
		m_stDados->idAtendimentoBaixaHistorico = atol( p );
		m_vlDados->idAtendimentoBaixaHistorico = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
	{
		m_stDados->idUsuarioAlteracao = atoi( p );
		m_vlDados->idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
	{
		strcpy(m_stDados->dtUltimaAlteracao, p );
		m_vlDados->dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}

