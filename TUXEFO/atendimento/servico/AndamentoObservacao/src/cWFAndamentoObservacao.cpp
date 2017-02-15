/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:05:55 $
 **/


#include "../include/cWFAndamentoObservacao.h"
#include "../../../commons/msgPadrao.h"

extern long  proCIncluirWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* status, XMLGen* saida);
extern bool proCExcluirWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* status, XMLGen* saida);
extern bool proCConsultaWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* status, char* order, XMLGen* saida);
extern bool proCObterComentarioHistorico(st_AndamentoObservacao* dados,st_vlAndamentoObservacao* status,XMLGen* saida);

cWFAndamentoObservacao::cWFAndamentoObservacao() 
{
    entrada = 0;
    saida = 0;
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
cWFAndamentoObservacao::cWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAndamentoObservacao::cWFAndamentoObservacao(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAndamentoObservacao::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAndamentoObservacao::incluir() indevido!");
}

/**
	Inclui um registro de andamento observacao, a chave retornada é o mesmo valor de
	idAtendimento, ja que esse atributo é a chave primária.
	@autor Renato Teixeira
	@since 22/10/2004
*/
int cWFAndamentoObservacao::incluir(XMLDPR *xmlDpr)
{
	long idAndamentoObservacao = proCIncluirWFAndamentoObservacao(&m_stDados, &m_vlDados,xmlDpr);

    if ( saida )
    {
	    saida->createTag("Registro");
		    saida->addItem("idAndamento", idAndamentoObservacao);
	    saida->closeTag();
    }

	return idAndamentoObservacao;
}

int cWFAndamentoObservacao::alterar()
{

	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAndamentoObservacao(&m_stDados, &m_vlDados, saida);
}

int cWFAndamentoObservacao::excluir()
{

	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAndamentoObservacao(&m_stDados, &m_vlDados, saida);
}

bool cWFAndamentoObservacao::consultar(st_AndamentoObservacao *dados
                                      ,st_vlAndamentoObservacao *status
                                      ,DOMNode *_entrada
                                      ,XMLGen *_saida)
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
	    {
            sprintf(order,"%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAndamentoObservacao(dados, status, order, _saida);
}

bool cWFAndamentoObservacao::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
	    {
            sprintf(order,"%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAndamentoObservacao(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAndamentoObservacao::consultarComentarioHist()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
	    {
            sprintf(order,"%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCObterComentarioHistorico(&m_stDados, &m_vlDados, saida);
}

/**
	Esse método é usado para carregar os dados usados na classe a partir de um DOMNode.
	Os dados são carregados em duas estruturas, sendo a primeira, a que contém o valor obtido e a segunda
	indicado que aquele atributo da primeira estrutrua possui valor.
	O método é chamado pelo construtor que recebe como parametro um DOMNode e um XMLGen.
	@autor Renato Teixeira
	@since 22/10/2004
*/
void cWFAndamentoObservacao::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AndamentoObservacao));
	memset(&m_vlDados,-1,sizeof(st_vlAndamentoObservacao));

    if ( !entrada )
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p) 
	{
		m_stDados.idAndamento = atol(p);
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dsAndamentoObservacao", 0 ), p) 
	{
        sprintf(m_stDados.dsAndamentoObservacao,"%.*s",sizeof(m_stDados.dsAndamentoObservacao)-1,p);
		m_vlDados.dsAndamentoObservacao = 1;
        m_stDados.pdsAndamentoObservacao = m_stDados.dsAndamentoObservacao;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}

