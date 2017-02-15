/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 


#include "../include/cWFAtendimentoLinha.h"

extern long proCIncluirWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* status, char* order, XMLGen* saida);
extern bool proCObtemWFLinhaAtendimento(long sIdAtendimento, XMLGen* saida,struct LinhaAtendimento *linhaatendimento);
extern bool proCObtemWFLinhaAtendimentoEx(long sIdAtendimento, XMLGen* saida,struct LinhaAtendimento *linhaatendimento);
extern bool proCObtemWFDadosLinhaAtendimento(long sIdAtendimento, XMLGen* saida,int *_idTipoLinha);
extern bool proCObtemWFDadosLinhaAtendimentoCri(long sIdPessoaLinhaHistorico, XMLGen* saida);
/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoLinha::cWFAtendimentoLinha(st_AtendimentoLinha* dados, st_vlAtendimentoLinha* status, XMLGen*xml_g)
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
cWFAtendimentoLinha::cWFAtendimentoLinha(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoLinha::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoLinha::incluir() indevido!");
}

long cWFAtendimentoLinha::incluir(XMLDPR *xmlDpr)
{
	long idAtendimento = proCIncluirWFAtendimentoLinha(&m_stDados, &m_vlDados,xmlDpr);

	//saida->createTag("WFAtendimentoLinhaVO");
	//	saida->addItem("idAtendimento", idAtendimento);
	//saida->closeTag();

	return idAtendimento;
}

int cWFAtendimentoLinha::alterar()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoLinha(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoLinha::excluir()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoLinha(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoLinha::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
	{
		char* p;
	    if (p = tx.walkTree( entrada, "order", 0 ), p)
		{
		    strcpy( order, p);
			XMLString::release(&p);
		}
	}

	return proCConsultaWFAtendimentoLinha(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoLinha::ObtemDetalheAtend(long idAtendimento,XMLGen *saida)
{
	return proCObtemWFLinhaAtendimento( idAtendimento,saida,0 );
}

bool cWFAtendimentoLinha::ObtemDetalheAtend(long idAtendimento,struct LinhaAtendimento *linhaatendimento)
{
	return proCObtemWFLinhaAtendimento( idAtendimento,0,linhaatendimento );
}

bool cWFAtendimentoLinha::ObtemDetalheAtendEx(long idAtendimento,struct LinhaAtendimento *linhaatendimento)
{
	return proCObtemWFLinhaAtendimentoEx( idAtendimento,0,linhaatendimento );
}

bool cWFAtendimentoLinha::ObtemDetalheAtend()
{
	if (m_vlDados.idAtendimento == -1)
    {
		return false;
    }

	return proCObtemWFLinhaAtendimento( m_stDados.idAtendimento,saida,0 );
}

bool cWFAtendimentoLinha::ObtemDetalheAtendEx()
{
	if (m_vlDados.idAtendimento == -1)
    {
		return false;
    }

	return proCObtemWFLinhaAtendimentoEx( m_stDados.idAtendimento,saida,0 );
}

bool cWFAtendimentoLinha::ObtemDadosAtendLinhaCri()
{
	if (m_vlDados.idPessoaLinhaHistorico == -1)
		return false;
	return proCObtemWFDadosLinhaAtendimentoCri( m_stDados.idPessoaLinhaHistorico , saida );
}

bool cWFAtendimentoLinha::ObtemDadosAtendLinha(long idAtendimento, XMLGen *saida)
{
	return proCObtemWFDadosLinhaAtendimento( idAtendimento,saida,0 /*idTipoLinha*/);
}

bool cWFAtendimentoLinha::ObtemDadosAtendLinha()
{
	return proCObtemWFDadosLinhaAtendimento( m_stDados.idAtendimento,saida,0 /*idTipoLinha*/ );
}

bool cWFAtendimentoLinha::ObtemDadosAtendLinha(long idAtendimento,int *idTipoLinha)
{
	return proCObtemWFDadosLinhaAtendimento( idAtendimento,0,idTipoLinha );
}

void cWFAtendimentoLinha::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtendimentoLinha));
	memset(&m_vlDados,-1,sizeof(st_vlAtendimentoLinha));

    if ( !entrada )
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaLinhaHistorico", 0 ), p ) 
	{
		m_stDados.idPessoaLinhaHistorico = atol( p );
		m_vlDados.idPessoaLinhaHistorico = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idEstadoLinha", 0 ), p ) 
	{
		m_stDados.idEstadoLinha = atoi( p );
		m_vlDados.idEstadoLinha = 1;
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
