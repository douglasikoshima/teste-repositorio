/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:44 $
 **/ 


#include "../include/cWFAtendimentoOrigem.h"

extern long proCIncluirWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* status,XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* status, char* order, XMLGen* saida);
extern long proCObterNumWFAtendimentoOrigem(long sIdAtendimento);
extern long proCObterNumWFAtendimentoOrigemEx(long sIdAtendimento);

cWFAtendimentoOrigem::cWFAtendimentoOrigem()
{
	locado = false;

}
/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoOrigem::cWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* indicator, XMLGen*xml_g)
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
cWFAtendimentoOrigem::cWFAtendimentoOrigem(DOMNode*dnode, XMLGen*xml_g)
{

	m_stDados = (st_AtendimentoOrigem*) malloc(sizeof(st_AtendimentoOrigem));
	m_vlDados = (st_vlAtendimentoOrigem*) malloc(sizeof(st_vlAtendimentoOrigem));

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
cWFAtendimentoOrigem::~cWFAtendimentoOrigem()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
long cWFAtendimentoOrigem::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoOrigem::incluir() indevido!");
}

long cWFAtendimentoOrigem::incluir(XMLDPR *xmlDpr)
{
	long idAtendimento = proCIncluirWFAtendimentoOrigem(m_stDados,m_vlDados,xmlDpr);

	saida->createTag("WFAtendimentoOrigemVO");
		saida->addItem("idAtendimento", idAtendimento);
	saida->closeTag();

	return idAtendimento;
}

long cWFAtendimentoOrigem::alterar()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoOrigem(m_stDados, m_vlDados, saida);
}

long cWFAtendimentoOrigem::excluir()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoOrigem(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoOrigem::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

    if ( entrada )
	    if ( p = tx.walkTree( entrada, "order", 0 ), p )
		{
		    strcpy( order, p );
			XMLString::release(&p);
		}

	return proCConsultaWFAtendimentoOrigem(m_stDados, m_vlDados, order, saida);
}

long cWFAtendimentoOrigem::ObterAtdOrigem(long sIdAtendimento)
{
	return proCObterNumWFAtendimentoOrigem(sIdAtendimento);
}



long cWFAtendimentoOrigem::ObterAtdOrigemEx(long sIdAtendimento)
{
	return proCObterNumWFAtendimentoOrigemEx(sIdAtendimento);
}



void cWFAtendimentoOrigem::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoOrigem));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoOrigem));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol( p );
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoOrigem", 0 ), p ) 
	{
		m_stDados->idAtendimentoOrigem = atol( p );
		m_vlDados->idAtendimentoOrigem = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idTipoReaberturaProcesso", 0 ), p ) 
	{
		m_stDados->idTipoReaberturaProcesso = atoi( p );
		m_vlDados->idTipoReaberturaProcesso = 1;
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

    ULOG("idAtendimento=[%lu]",            m_stDados->idAtendimento);                
    ULOG("idAtendimentoOrigem=[%lu]",      m_stDados->idAtendimentoOrigem);  
    ULOG("idTipoReaberturaProcesso=[%d]", m_stDados->idTipoReaberturaProcesso );     
    ULOG("idUsuarioAlteracao=[%d]",       m_stDados->idUsuarioAlteracao  );          
    ULOG("dtUltimaAlteracao=[%s]",        m_stDados->dtUltimaAlteracao );
}

