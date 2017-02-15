/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/

#include "../include/cWFAtendimentoPessoa.h"

extern long  proCIncluirWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* status, XMLDPR *xmlDpr);
extern int  proCObtemWFIdTipoRelacionamento(long sIdAtendimento);
extern int  proCObtemWFIdTipoRelacionamentoEx( long sIdAtendimento );
extern bool proCAlterarWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* status, char* order, XMLGen* saida);
extern bool proCObtemWFPessoaAtendimento(long sIdAtendimento, XMLGen* saida,AtendimentoPessoa *ap=0);
extern bool proCObtemWFPessoaAtendimentoEx(long sIdAtendimento, XMLGen* saida,AtendimentoPessoa *ap=0);
extern bool proCObtemWFDocumentoPessoa(int sIdPessoa, XMLGen* saida);
extern bool proCObtemWFEnderecoPessoa(int sIdPessoa, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoPessoa::cWFAtendimentoPessoa(st_AtendimentoPessoa* dados, st_vlAtendimentoPessoa* status, XMLGen*xml_g)
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
cWFAtendimentoPessoa::cWFAtendimentoPessoa(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
	carregaDadosConsultaPessoa();
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoPessoa::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoPessoa::incluir() indevido!");
}

long cWFAtendimentoPessoa::incluir(XMLDPR *xmlDpr)
{
	long idAtendimento = proCIncluirWFAtendimentoPessoa(&m_stDados, &m_vlDados, xmlDpr);

	saida->createTag("WFAtendimentoPessoaVO");
		saida->addItem("idAtendimentoPessoa", idAtendimento);
	saida->closeTag();

	return idAtendimento;
}

int cWFAtendimentoPessoa::alterar()
{

	if (m_vlDados.idAtendimentoPessoa == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoPessoa(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoPessoa::excluir()
{

	if (m_vlDados.idAtendimentoPessoa == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoPessoa(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoPessoa::consultar()
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

	return proCConsultaWFAtendimentoPessoa(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAtendimentoPessoa::ObtemAtendPessoa(AtendimentoPessoa *ap)
{
	return proCObtemWFPessoaAtendimento( m_stDados.idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoaEx(AtendimentoPessoa *ap)
{
	return proCObtemWFPessoaAtendimentoEx( m_stDados.idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoa(long idAtendimento,AtendimentoPessoa *ap,XMLGen* saida)
{
	return proCObtemWFPessoaAtendimento( idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoaEx(long idAtendimento,AtendimentoPessoa *ap,XMLGen* saida)
{
	return proCObtemWFPessoaAtendimentoEx( idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoa(long idAtendimento,AtendimentoPessoa *ap)
{
	if (m_vlDados.idAtendimento == -1)
    {
		return false;
    }

	return proCObtemWFPessoaAtendimento( idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoaEx(long idAtendimento,AtendimentoPessoa *ap)
{
	if (m_vlDados.idAtendimento == -1)
    {
		return false;
    }

	return proCObtemWFPessoaAtendimento( idAtendimento,saida,ap );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoa()
{
	if (m_vlDados.idAtendimento == -1)
		return false;
	return proCObtemWFPessoaAtendimento( m_stDados.idAtendimento  , saida );
}

bool cWFAtendimentoPessoa::ObtemAtendPessoa(long idAtendimento,XMLGen* saida)
{
	if (idAtendimento == -1)
		return false;

    return proCObtemWFPessoaAtendimento(idAtendimento  , saida );
}

bool cWFAtendimentoPessoa::ObtemDocumentoPessoa()
{
	carregaDadosConsultaPessoa();
	if (m_vlPessoa.idPessoa == -1)
		return false;
	return proCObtemWFDocumentoPessoa( m_stPessoa.idPessoa  , saida );
}

bool cWFAtendimentoPessoa::ObtemEnderecoPessoa(long idPessoa,XMLGen* saida)
{
	return proCObtemWFEnderecoPessoa( idPessoa , saida );
}

bool cWFAtendimentoPessoa::ObtemEnderecoPessoa()
{
	carregaDadosConsultaPessoa();
	if (m_vlPessoa.idPessoa == -1)
		return false;
	return proCObtemWFEnderecoPessoa( m_stPessoa.idPessoa  , saida );
}

int cWFAtendimentoPessoa::ObtemIdTipoRelacionamento(long idAtendimento)
{
    return proCObtemWFIdTipoRelacionamento(idAtendimento);
}



int cWFAtendimentoPessoa::ObtemIdTipoRelacionamentoEx(long idAtendimento)
{
    return proCObtemWFIdTipoRelacionamentoEx(idAtendimento);
}



void cWFAtendimentoPessoa::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtendimentoPessoa));
	memset(&m_vlDados,-1,sizeof(st_vlAtendimentoPessoa));

    if ( !entrada)
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoPessoa", 0 ), p ) 
	{
		m_stDados.idAtendimentoPessoa = atol( p );
		m_vlDados.idAtendimentoPessoa = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idPessoaDePara", 0 ), p ) 
	{
		m_stDados.idPessoaDePara = atoi( p );
		m_vlDados.idPessoaDePara = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "inResponsavelAbertura", 0 ), p ) 
	{
		m_stDados.idTipoRelacionamento = atoi( p );
		m_vlDados.idTipoRelacionamento = 1;
		XMLString::release(&p);
	}
}

void cWFAtendimentoPessoa::carregaDadosConsultaPessoa()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stPessoa,0,sizeof(st_AtendimentoConsultaPessoa));
	memset(&m_vlPessoa,-1,sizeof(st_vlAtendimentoConsultaPessoa));

    if ( !entrada)
    {
        return;
    }

	char* p;

	if (p = tx.walkTree( entrada, "idPessoa", 0 ), p) 
	{
		m_stPessoa.idPessoa = atol( p );
		m_vlPessoa.idPessoa = 1;
		XMLString::release(&p);
	}
}

