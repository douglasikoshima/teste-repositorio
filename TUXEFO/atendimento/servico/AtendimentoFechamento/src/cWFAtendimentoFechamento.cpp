/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/


#include "../include/cWFAtendimentoFechamento.h"

extern long proCIncluirWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* status, XMLGen* saida, XMLDPR *xmlDpr);
extern bool proCConsultaWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* status, char* order, XMLGen* saida);
extern bool procExisteAtendimentoFechamento(long _idAtendimento);

cWFAtendimentoFechamento::cWFAtendimentoFechamento()
{
	locado = false;

	m_stDados = 0;
	m_vlDados = 0;

    entrada = 0;
	saida   = 0;
}

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoFechamento::cWFAtendimentoFechamento(st_AtendimentoFechamento* dados, st_vlAtendimentoFechamento* indicator, XMLGen*xml_g)
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
cWFAtendimentoFechamento::cWFAtendimentoFechamento(DOMNode*dnode, XMLGen*xml_g)
{
	m_stDados = (st_AtendimentoFechamento*) malloc(sizeof(st_AtendimentoFechamento));
	m_vlDados = (st_vlAtendimentoFechamento*) malloc(sizeof(st_vlAtendimentoFechamento));

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
cWFAtendimentoFechamento::~cWFAtendimentoFechamento()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoFechamento::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoFechamento::incluir() indevido!");
}

long cWFAtendimentoFechamento::incluir(XMLDPR *xmlDpr)
{
	long idAtendimento = proCIncluirWFAtendimentoFechamento(m_stDados, m_vlDados, xmlDpr);

    // saida->createTag("WFAtendimentoFechamentoVO");
    //     saida->addItem("idAtendimento", idAtendimento);
    // saida->closeTag();
   
	return idAtendimento;
}

int cWFAtendimentoFechamento::alterar()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoFechamento(m_stDados, m_vlDados, saida);
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoFechamento::excluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoFechamento::excluir() indevido!");
}

int cWFAtendimentoFechamento::excluir(XMLDPR *xmlDpr)
{
	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoFechamento(m_stDados,m_vlDados,saida,xmlDpr);
}

bool cWFAtendimentoFechamento::consultar()
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

	return proCConsultaWFAtendimentoFechamento(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoFechamento::existeAtendimentoFechamento(long idAtendimento)
{
	return procExisteAtendimentoFechamento(idAtendimento);
}

void cWFAtendimentoFechamento::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoFechamento));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoFechamento));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol( p );
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idResultadoFechamento", 0 ), p ) 
	{
		m_stDados->idResultadoFechamento = atoi( p );
		m_vlDados->idResultadoFechamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p ) 
	{
		m_stDados->idAndamento = atol( p );
		m_vlDados->idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtFechamento", 0 ), p ) 
	{
		strcpy(m_stDados->dtFechamento, p );
		m_vlDados->dtFechamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p ) 
	{
		m_stDados->idPessoaUsuario = atoi( p );
		m_vlDados->idPessoaUsuario = 1;
		XMLString::release(&p);
	}

}

