/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:34:16 $
 **/


#include "../include/cWFAtendimentoConta.h"

extern long  proCIncluirWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* status);
extern bool proCAlterarWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* status, char* order, XMLGen* saida);
extern bool proCConsultaWFAtendimentoContaEx(st_AtendimentoConta* dados, st_vlAtendimentoConta* status, char* order, XMLGen* saida);
extern bool proCObterWFAtendimentoConta(long _idAtendimento, AtendimentoConta *atdConta);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoConta::cWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* indicator, XMLGen*xml_g)
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
	@since 21/10/2004
*/
cWFAtendimentoConta::cWFAtendimentoConta(DOMNode*dnode, XMLGen*xml_g) 
{
	m_stDados = (st_AtendimentoConta*) malloc(sizeof(st_AtendimentoConta));
	m_vlDados = (st_vlAtendimentoConta*) malloc(sizeof(st_vlAtendimentoConta));

	locado = true;

	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

/**
	Destrutor da classe.
	Libera a estruturas de dados quando elas foram alocadas internamente e não recebidas no construtor.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoConta::~cWFAtendimentoConta()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

long cWFAtendimentoConta::incluir()
{

	long idAtendimento = proCIncluirWFAtendimentoConta(m_stDados, m_vlDados);

	saida->createTag("Registro");
		saida->addItem("idAtendimento", idAtendimento);
	saida->closeTag();

	return idAtendimento;
}

int cWFAtendimentoConta::alterar()
{

	if (m_vlDados->idAtendimento == -1)  return -1;

	return proCAlterarWFAtendimentoConta(m_stDados, m_vlDados, saida);
}

int cWFAtendimentoConta::excluir()
{

	if (m_vlDados->idAtendimento == -1)  return -1;

	return proCExcluirWFAtendimentoConta(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoConta::consultar()
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

	return proCConsultaWFAtendimentoConta(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoConta::consultarEx()
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

	return proCConsultaWFAtendimentoContaEx(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoConta::obterAtendimentoConta(long idAtendimento,AtendimentoConta *atdConta)
{
    return proCObterWFAtendimentoConta(idAtendimento,atdConta);
}

/**
	Esse método é usado para carregar os dados usados na classe a partir de um DOMNode.
	Os dados são carregados em duas estruturas, sendo a primeira, a que contém o valor obtido e a segunda
	indicado que aquele atributo da primeira estrutrua possui valor.
	O método é chamado pelo construtor que recebe como parametro um DOMNode e um XMLGen.
	@autor Renato Teixeira
	@since 21/10/2004
*/
void cWFAtendimentoConta::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoConta));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoConta));

	if ( p=tx.walkTree( entrada, "idAtendimento", 0 ),p )
    {
		m_stDados->idAtendimento = atol(p);
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "cdConta", 0 ),p )
    {
		strcpy(m_stDados->cdConta, p); 
		m_vlDados->cdConta = 1;
		XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "cdDigitoConta", 0 ),p )
    {
		m_stDados->cdDigitoConta = atoi(p);
		m_vlDados->cdDigitoConta = 1;
		XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p )
	{
		m_stDados->idUsuarioAlteracao = atoi(p);
		m_vlDados->idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p )
	{
		strcpy(m_stDados->dtUltimaAlteracao, p);
		m_vlDados->dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

}