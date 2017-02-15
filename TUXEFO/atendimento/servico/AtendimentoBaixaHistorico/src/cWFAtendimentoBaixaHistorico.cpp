/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:33:16 $
 **/


#include "../include/cWFAtendimentoBaixaHistorico.h"

extern long  proCIncluirWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, char* order, XMLGen* saida);
extern bool proCIncluirWFAtendimentoBaixaMensagem(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoBaixaHistorico::cWFAtendimentoBaixaHistorico(st_AtendimentoBaixaHistorico* dados, st_vlAtendimentoBaixaHistorico* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 24/10/2004
*/
cWFAtendimentoBaixaHistorico::cWFAtendimentoBaixaHistorico(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoBaixaHistorico::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoBaixaHistorico::incluir() indevido!");
}

long cWFAtendimentoBaixaHistorico::incluir(XMLDPR *xmlDpr)
{
	long idAtendimentoBaixaHistorico = proCIncluirWFAtendimentoBaixaHistorico(&m_stDados, &m_vlDados,xmlDpr);

    if ( saida )
    {
	    saida->createTag("WFAtendimentoBaixaHistoricoVO");
		    saida->addItem("idAtendimentoBaixaHistorico", idAtendimentoBaixaHistorico);
	    saida->closeTag();
    }

	return idAtendimentoBaixaHistorico;
}

bool cWFAtendimentoBaixaHistorico::incluirBaixaMensagem()
{
	if (m_vlDados.idAtendimentoBaixaHistorico == -1) 
	{
		return false;
	}

	return proCIncluirWFAtendimentoBaixaMensagem(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoBaixaHistorico::alterar()
{
	if (m_vlDados.idAtendimentoBaixaHistorico == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoBaixaHistorico(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoBaixaHistorico::excluir()
{
	if (m_vlDados.idAtendimentoBaixaHistorico == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoBaixaHistorico(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoBaixaHistorico::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p )
	    {
		    strcpy( order, p );
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAtendimentoBaixaHistorico(&m_stDados, &m_vlDados, order, saida);
}

void cWFAtendimentoBaixaHistorico::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtendimentoBaixaHistorico));
	memset(&m_vlDados,-1,sizeof(st_vlAtendimentoBaixaHistorico));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if (p = tx.walkTree( entrada, "idAtendimentoBaixaHistorico", 0 ), p ) 
	{
		m_stDados.idAtendimentoBaixaHistorico = atol( p );
		m_vlDados.idAtendimentoBaixaHistorico = 1;
		XMLString::release(&p);
	}
	
	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol( p );
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idBaixa", 0 ), p ) 
	{
		m_stDados.idBaixa = atoi( p );
		m_vlDados.idBaixa = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idFase", 0 ), p ) 
	{
		m_stDados.idFase = atoi( p );
		m_vlDados.idFase = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idBaixaMensagem", 0 ), p ) 
	{
		m_stDados.idBaixaMensagem = atoi( p );
		m_vlDados.idBaixaMensagem = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtBaixa", 0 ), p ) 
	{
		strcpy(m_stDados.dtBaixa, p );
		m_vlDados.dtBaixa = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaUsuario", 0 ), p ) 
	{
		m_stDados.idPessoaUsuario = atoi( p );
		m_vlDados.idPessoaUsuario = 1;
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

