/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:36:43 $
 **/ 
 

#include "../include/cWFAtendimentoGrupoAtual.h"

extern long proCIncluirWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLDPR *xmlDpr);
extern long proCIncluirWFAtendimentoGrupoBko(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status);
extern bool proCAlterarWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen* saida, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoGrupoBko(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen* saida, XMLDPR *xmlDpr);
extern bool proCExcluirLogWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, char* order, XMLGen* saida);
extern bool proCObtemWFAtdGrAtual(long sIdAtendimento,XMLGen *saida,int *idGrupoAtual,char *nmGrupoAtual);
extern bool proCObtemWFAtdGrAtualAndamento(long sIdAtendimento, XMLGen* saida, int *idGrupoAtual=0, bool gerarXMLOut=true);
extern bool proCAtualizaEntradaFila(int chamadaTelefonica, XMLGen* saida);
extern bool proCExcluirWFAtendimentoGrupoBko(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen* saida);
extern bool proCObtemWFAtdGrBko(long sIdAtendimento, XMLGen* saida);
extern bool proCExisteWFAtdGrupoAtual(long sIdAtendimento);
extern bool proCExisteWFAtdGrupoAtualBKO(long sIdAtendimento);
extern int proCObterWFAtdGrupoAtual(long sIdAtendimento);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 25/10/2004
*/
cWFAtendimentoGrupoAtual::cWFAtendimentoGrupoAtual(st_AtendimentoGrupoAtual* dados, st_vlAtendimentoGrupoAtual* status, XMLGen*xml_g)
{
    memcpy(&m_stDados,dados,sizeof(m_stDados));
    memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 25/10/2004
*/
cWFAtendimentoGrupoAtual::cWFAtendimentoGrupoAtual(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

cWFAtendimentoGrupoAtual::cWFAtendimentoGrupoAtual(XMLGen*xml_g)
{
	entrada = 0;
	saida   = xml_g;
}


// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoGrupoAtual::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoGrupoAtual::incluir() indevido!");
}

long cWFAtendimentoGrupoAtual::incluir(XMLDPR *xmlDpr)
{
	long idAtendimentoGrupoAtual = proCIncluirWFAtendimentoGrupoAtual(&m_stDados, &m_vlDados, xmlDpr);

	saida->createTag("WFAtendimentoGrupoAtualVO");
		saida->addItem("idAtendimento", idAtendimentoGrupoAtual);
	saida->closeTag();

	return idAtendimentoGrupoAtual;
}

long cWFAtendimentoGrupoAtual::incluirBko()
{
	long idAtendimentoGrupoBko = proCIncluirWFAtendimentoGrupoBko(&m_stDados, &m_vlDados);

	saida->createTag("WFAtendimentoGrupoAtualVO");
		saida->addItem("idAtendimento", idAtendimentoGrupoBko);
	saida->closeTag();

	return idAtendimentoGrupoBko;
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoGrupoAtual::alterar()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoGrupoAtual::alterar() indevido!");
}

int cWFAtendimentoGrupoAtual::alterar(XMLDPR *xmlDpr)
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoGrupoAtual(&m_stDados, &m_vlDados, saida, xmlDpr);
}

long cWFAtendimentoGrupoAtual::atualizarBko()
{
    long retorno=0;

    if ( proCExisteWFAtdGrupoAtualBKO(m_stDados.idAtendimento) )
    {
        retorno = proCAlterarWFAtendimentoGrupoBko(&m_stDados,&m_vlDados,saida);
    }
    else
    {
	    retorno = proCIncluirWFAtendimentoGrupoBko(&m_stDados,&m_vlDados);
    }

    return retorno;
}

int cWFAtendimentoGrupoAtual::alterarBko()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoGrupoBko(&m_stDados, &m_vlDados, saida);
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoGrupoAtual::excluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoGrupoAtual::excluir() indevido!");
}

int cWFAtendimentoGrupoAtual::excluir(XMLDPR *xmlDpr)
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoGrupoAtual(&m_stDados, &m_vlDados, saida, xmlDpr);
}

int cWFAtendimentoGrupoAtual::excluirLogicamente()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirLogWFAtendimentoGrupoAtual(&m_stDados, &m_vlDados, saida);
}

int cWFAtendimentoGrupoAtual::excluirBko()
{
	if (m_vlDados.idAtendimento == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoGrupoBko(&m_stDados, &m_vlDados, saida);
}

bool cWFAtendimentoGrupoAtual::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree(entrada,"order",0),p )
		{
		    strcpy( order, p );
			XMLString::release(&p);
		}
    }

	return proCConsultaWFAtendimentoGrupoAtual(&m_stDados, &m_vlDados, order, saida);
}

int cWFAtendimentoGrupoAtual::ObterGrupoAtual(long idAtendimento)
{
    return proCObterWFAtdGrupoAtual(idAtendimento);
}

bool cWFAtendimentoGrupoAtual::ObtemGrAt(int *idGrupoAtual)
{
	if (m_vlDados.idAtendimento == -1)
		return false;
		
    return proCObtemWFAtdGrAtual(m_stDados.idAtendimento,saida,idGrupoAtual,0);
}

bool cWFAtendimentoGrupoAtual::ObtemGrAt(long idAtendimento,int *idGrupoAtual)
{
    return proCObtemWFAtdGrAtual(idAtendimento,saida,idGrupoAtual,0);
}

bool cWFAtendimentoGrupoAtual::ObtemGrAt(long idAtendimento,int *idGrupoAtual,char *nmGrupoAtual)
{
    return proCObtemWFAtdGrAtual(idAtendimento,saida,idGrupoAtual,nmGrupoAtual);
}

bool cWFAtendimentoGrupoAtual::ObtemGrAt(long idAtendimento)
{
    return proCObtemWFAtdGrAtual(idAtendimento,saida,0,0);
}

bool cWFAtendimentoGrupoAtual::ObtemGrAt()
{
	if (m_vlDados.idAtendimento == -1)
		return false;

    return proCObtemWFAtdGrAtual(m_stDados.idAtendimento,saida,0,0);
}

bool cWFAtendimentoGrupoAtual::ObtemGrBko()
{
	if (m_vlDados.idAtendimento == -1)
		return false;

	return proCObtemWFAtdGrBko( m_stDados.idAtendimento,saida );
}

bool cWFAtendimentoGrupoAtual::existegrupoAtual(long idAtendimento)
{
	return proCExisteWFAtdGrupoAtual(idAtendimento);
}

bool cWFAtendimentoGrupoAtual::AtuEntradaFila()
{
	int chamadaTelefonica = 0;

    if ( entrada )
    {
	    char* p;
	    if (p = tx.walkTree( entrada, "chamadaTelefonica", 0 ), p )
	    {
		    chamadaTelefonica = atoi( p );
		    XMLString::release(&p);
	    }
    }

	return proCAtualizaEntradaFila( chamadaTelefonica , saida );
}

void cWFAtendimentoGrupoAtual::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_AtendimentoGrupoAtual));
	memset(&m_vlDados,-1,sizeof(st_vlAtendimentoGrupoAtual));

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

	if (p = tx.walkTree( entrada, "idGrupo", 0 ), p ) 
	{
		m_stDados.idGrupo = atoi( p );
		m_vlDados.idGrupo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idSequencia", 0 ), p ) 
	{
		m_stDados.idSequencia = atoi( p );
		m_vlDados.idSequencia = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtEntradaBKO", 0 ), p ) 
	{
		strcpy(m_stDados.dtEntradaBKO, p );
		m_vlDados.dtEntradaBKO = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "dtEntradaFila", 0 ), p ) 
	{
		strcpy(m_stDados.dtEntradaFila, p );
		m_vlDados.dtEntradaFila = 1;
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


int cWFAtendimentoGrupoAtual::getGrupo()
{
  return ( m_stDados.idGrupo ); 
}
