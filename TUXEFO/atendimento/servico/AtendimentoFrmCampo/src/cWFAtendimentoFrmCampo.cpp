/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:18 $
 **/


#include "../include/cWFAtendimentoFrmCampo.h"

extern long  proCIncluirWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* status, char* order, XMLGen* saida);
extern bool proCObtemWFDominioCampo(int sIdCampo, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFrmCampo::cWFAtendimentoFrmCampo(st_AtendimentoFrmCampo* dados, st_vlAtendimentoFrmCampo* indicator, XMLGen*xml_g)
{

	locado = false;

	m_stDados = dados;
	m_vlDados = indicator;

	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFrmCampo::cWFAtendimentoFrmCampo(DOMNode*dnode, XMLGen*xml_g)
{

	m_stDados = (st_AtendimentoFrmCampo*) malloc(sizeof(st_AtendimentoFrmCampo));
	m_vlDados = (st_vlAtendimentoFrmCampo*) malloc(sizeof(st_vlAtendimentoFrmCampo));

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
cWFAtendimentoFrmCampo::~cWFAtendimentoFrmCampo()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoFrmCampo::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoFrmCampo::incluir() indevido!");
}

long cWFAtendimentoFrmCampo::incluir(XMLDPR *xmlDpr)
{
	long idAtendimentoFrmCampo = proCIncluirWFAtendimentoFrmCampo(m_stDados, m_vlDados, xmlDpr);

	saida->createTag("WFAtendimentoFrmCampoVO");
		saida->addItem("idAtendimentoFrmCampo", idAtendimentoFrmCampo);
	saida->closeTag();
	
	return idAtendimentoFrmCampo;
}

int cWFAtendimentoFrmCampo::alterar()
{

	if (m_vlDados->idAtendimentoFrmCampo == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoFrmCampo(m_stDados, m_vlDados, saida);
}

int cWFAtendimentoFrmCampo::excluir()
{

	if (m_vlDados->idAtendimentoFrmCampo == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoFrmCampo(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoFrmCampo::consultar()
{
	char order[256];

	order[0] = 0;

	char* p;

	if (p = tx.walkTree( entrada, "order", 0 ), p )
	{
		strcpy( order, p );
		XMLString::release(&p);
	}

	return proCConsultaWFAtendimentoFrmCampo(m_stDados, m_vlDados, order, saida);
}

bool cWFAtendimentoFrmCampo::obtemDominio()
{
	if (m_vlDados->idCampo == -1)
		return false;
	return proCObtemWFDominioCampo(m_stDados->idCampo, saida);
}

void cWFAtendimentoFrmCampo::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoFrmCampo));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoFrmCampo));

	if (p = tx.walkTree( entrada, "idAtendimentoFrmCampo", 0 ), p ) 
	{
		m_stDados->idAtendimentoFrmCampo = atol( p );
		m_vlDados->idAtendimentoFrmCampo = 1;
        XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimentoFrm", 0 ), p ) 
	{
		m_stDados->idAtendimentoFrm = atol( p );
		m_vlDados->idAtendimentoFrm = 1;
        XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idCampo", 0 ), p ) 
	{
		m_stDados->idCampo = atoi( p );
		m_vlDados->idCampo = 1;
        XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idDominio", 0 ), p ) 
	{
		m_stDados->idDominio = atoi( p );
		m_vlDados->idDominio = 1;
        XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsValor", 0 ), p ) 
	{
		strcpy(m_stDados->dsValor, p );
		m_vlDados->dsValor = 1;
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

