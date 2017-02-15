/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 


#include "../include/cWFChamadaAtendimento.h"

extern long proCIncluirWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status);
extern bool proCAlterarWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, XMLGen* saida);
extern bool proCExcluirWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, XMLGen* saida);
extern bool proCConsultaWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, char* order, XMLGen* saida);
extern bool proCConsultaContato(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFChamadaAtendimento::cWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, XMLGen*xml_g)
{
	locado = false;

	m_stDados = dados;
	m_vlDados = status;

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFChamadaAtendimento::cWFChamadaAtendimento(DOMNode*dnode, XMLGen*xml_g)
{
	m_stDados = (st_ChamadaAtendimento*) malloc(sizeof(st_ChamadaAtendimento));
	m_vlDados = (st_vlChamadaAtendimento*) malloc(sizeof(st_vlChamadaAtendimento));

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
cWFChamadaAtendimento::~cWFChamadaAtendimento()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

long cWFChamadaAtendimento::incluir()
{

	long idChamadaAtendimento = proCIncluirWFChamadaAtendimento(m_stDados, m_vlDados);

	saida->createTag("WFChamadaAtendimentoVO");
		saida->addItem("idChamadaAtendimento", idChamadaAtendimento);
	saida->closeTag();

	return idChamadaAtendimento;
}

int cWFChamadaAtendimento::alterar()
{

	if (m_vlDados->idChamadaAtendimento == -1)  return -1;

	return proCAlterarWFChamadaAtendimento(m_stDados, m_vlDados, saida);
}

int cWFChamadaAtendimento::excluir()
{

	if (m_vlDados->idChamadaAtendimento == -1)  return -1;

	return proCExcluirWFChamadaAtendimento(m_stDados, m_vlDados, saida);
}

bool cWFChamadaAtendimento::consultar()
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

	return proCConsultaWFChamadaAtendimento(m_stDados, m_vlDados, order, saida);
}

bool cWFChamadaAtendimento::consultarContato()
{
	return proCConsultaContato(m_stDados, m_vlDados, saida);
}

void cWFChamadaAtendimento::carregaDados()
{
    char *p;

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_ChamadaAtendimento));
	memset(m_vlDados,-1,sizeof(st_vlChamadaAtendimento));

	if ( p=tx.walkTree( entrada, "idChamadaAtendimento", 0 ),p )
    {
		m_stDados->idChamadaAtendimento = atoi(p);
		m_vlDados->idChamadaAtendimento = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idAtendimento", 0 ),p )
    {
		m_stDados->idAtendimento = atol(p);
		m_vlDados->idAtendimento = 1;
        XMLString::release(&p);
	}

	if ( p=tx.walkTree( entrada, "idChamadaTelefonica", 0 ),p )
    {
		m_stDados->idChamadaTelefonica = atoi(p);
		m_vlDados->idChamadaTelefonica = 1;
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
		strcpy(m_stDados->dtUltimaAlteracao,p);
		m_vlDados->dtUltimaAlteracao = 1;
        XMLString::release(&p);
	}
}
