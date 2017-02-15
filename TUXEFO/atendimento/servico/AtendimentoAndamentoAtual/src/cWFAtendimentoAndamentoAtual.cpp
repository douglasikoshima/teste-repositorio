/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:30:32 $
 **/

#include <tuxfw.h>
#include "../include/cWFAtendimentoAndamentoAtual.h"

extern long proCIncluirWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* status);
extern bool proCAlterarWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* status, char* order, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoAndamentoAtual::cWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoAndamentoAtual::cWFAtendimentoAndamentoAtual(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

long cWFAtendimentoAndamentoAtual::incluir()
{
    //////////////////////////////////////////////////////////////////////////////////
    // Esta operação ficou desnecessária pois a rotina de inclusão de andamento
    // executa esta operação a cada nova inserção. Out/2005 - Cassio//
    //////////////////////////////////////////////////////////////////////////////////
    //
	// long idAtendimento = proCIncluirWFAtendimentoAndamentoAtual(&m_stDados, &m_vlDados);

	saida->createTag("AtendimentoAndamentoAtualVO");
		saida->addItem("idAtendimento", m_stDados.idAtendimento);
	saida->closeTag();

	return m_stDados.idAtendimento;
}

int cWFAtendimentoAndamentoAtual::alterar()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

    //////////////////////////////////////////////////////////////////////////////////
    // Esta operação ficou desnecessária pois a rotina de inclusão de andamento
    // executa esta operação a cada nova inserção. Out/2005 - Cassio
    //////////////////////////////////////////////////////////////////////////////////
	//return proCAlterarWFAtendimentoAndamentoAtual(&m_stDados, &m_vlDados, saida);
    //
    return 1;
}

int cWFAtendimentoAndamentoAtual::excluir()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

    bool bExec = proCExcluirWFAtendimentoAndamentoAtual(&m_stDados, &m_vlDados, saida);
    if ( bExec == true )
        return 0;
    else
        return 1;

}

bool cWFAtendimentoAndamentoAtual::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if ( p = tx.walkTree( entrada, "order", 0 ), p )
	    {
		    sprintf(order, "%.*s",sizeof(order)-1,p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAtendimentoAndamentoAtual(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoAndamentoAtual::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtendimentoAndamentoAtual));
	memset(&m_vlDados,-1,sizeof(st_vlAtendimentoAndamentoAtual));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol( p );
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAndamento", 0 ), p ) 
	{
		m_stDados.idAndamento = atol( p );
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
	{
		m_stDados.idUsuarioAlteracao = atoi( p );
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p );
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}
}


void cWFAtendimentoAndamentoAtual::setIdAndamento(long id)
{
  	m_stDados.idAndamento = id; 
}
