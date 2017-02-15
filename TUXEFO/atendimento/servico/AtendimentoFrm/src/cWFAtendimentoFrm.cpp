/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/06 12:35:55 $
 **/


#include "../include/cWFAtendimentoFrm.h"
#include "../../../commons/msgPadrao.h"

extern long  proCIncluirWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* status, char* order, XMLGen* saida);
extern bool proCObtemFormulario(st_FormularioDinamico* dados, st_vlFormularioDinamico* status, XMLGen* saida);
extern bool proCObtemFormularioPreview(st_FormularioDinamico* dados, st_vlFormularioDinamico* status, int idUFOperadora, XMLGen* saida);
extern bool proCObtemFormularioPesquisa(XMLGen* saida);
extern bool proCObtemDominioCampo(int idCampo, char* prQuery, XMLGen* saida);
extern bool proCObtemFormularioMassa(st_FormularioDinamico* dados, st_vlFormularioDinamico* status, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAtendimentoFrm::cWFAtendimentoFrm(st_AtendimentoFrm* dados, st_vlAtendimentoFrm* indicator, XMLGen*xml_g)
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
cWFAtendimentoFrm::cWFAtendimentoFrm(DOMNode*dnode, XMLGen*xml_g)
{
	m_stDados = (st_AtendimentoFrm*) malloc(sizeof(st_AtendimentoFrm));
	m_vlDados = (st_vlAtendimentoFrm*) malloc(sizeof(st_vlAtendimentoFrm));

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
cWFAtendimentoFrm::~cWFAtendimentoFrm()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoFrm::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoFrm::incluir() indevido!");
}

long cWFAtendimentoFrm::incluir(XMLDPR *xmlDpr)
{
	long idAtendimentoFrm = proCIncluirWFAtendimentoFrm(m_stDados, m_vlDados, xmlDpr);

    if ( saida )
    {
	    saida->createTag("AtendimentoFrmVO");
		    saida->addItem("idAtendimentoFrm", idAtendimentoFrm);
	    saida->closeTag();
    }

	return idAtendimentoFrm;
}

int cWFAtendimentoFrm::alterar()
{

	if (m_vlDados->idAtendimentoFrm == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoFrm(m_stDados, m_vlDados, saida);
}

int cWFAtendimentoFrm::excluir()
{

	if (m_vlDados->idAtendimentoFrm == -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoFrm(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoFrm::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
	    char* p;

        if (p = tx.walkTree( entrada, "order", 0 ) )
	    {
		    strcpy( order, p);
		    XMLString::release(&p);
	    }
    }

	return proCConsultaWFAtendimentoFrm(m_stDados, m_vlDados, order, saida);
}

void cWFAtendimentoFrm::obtemFormulario()
{
	carregaDadosObtemFormulario();
	proCObtemFormulario(&m_stFormulario, &m_vlFormulario, saida);
}

void cWFAtendimentoFrm::obtemFormularioPreview(int idUFOperadora)
{
	carregaDadosObtemFormulario();
    ULOG("cWFAtendimentoFrm::obtemFormularioPreview[%d]", idUFOperadora);
	proCObtemFormularioPreview(&m_stFormulario, &m_vlFormulario, idUFOperadora, saida);
}

void cWFAtendimentoFrm::obtemFormularioMassa()
{
	proCObtemFormularioMassa(&m_stFormulario, &m_vlFormulario, saida);
}

void cWFAtendimentoFrm::obtemFormularioPesquisa()
{
	carregaDadosObtemFormulario();
	proCObtemFormularioPesquisa(saida);
}

bool cWFAtendimentoFrm::obtemCampoDinamico()
{
	if (m_vlDados->idCampo == -1) 
	{
		return false;
	}

	if (m_vlDados->textoPesquisa == -1) 
	{
		return false;
	}

	proCObtemDominioCampo(m_stDados->idCampo, m_stDados->textoPesquisa, saida);

	return true;
}

void cWFAtendimentoFrm::carregaDados()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoFrm));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoFrm));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimentoFrm", 0 ), p ) 
	{
		m_stDados->idAtendimentoFrm = atol( p );
		m_vlDados->idAtendimentoFrm = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol( p );
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idContatoFolhaCampo", 0 ), p ) 
	{
		m_stDados->idContatoFolhaCampo = atoi( p );
		m_vlDados->idContatoFolhaCampo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idCampo", 0 ), p ) 
	{
		m_stDados->idCampo = atoi( p );
		m_vlDados->idCampo = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "textoPesquisa", 0 ), p ) 
	{
		strcpy(m_stDados->textoPesquisa, p );
		m_vlDados->textoPesquisa = 1;
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

void cWFAtendimentoFrm::carregaDadosObtemFormulario()
{
	// Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stFormulario,0,sizeof(m_stFormulario));
	memset(&m_vlFormulario,-1,sizeof(m_vlFormulario));

	char* p;

	if (p = tx.walkTree( entrada, "idContato", 0 ), p ) 
	{
		m_stFormulario.idContato = atoi( p );
		m_vlFormulario.idContato = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "nrTelefone", 0 ), p ) 
	{
		strcpy(m_stFormulario.nrTelefone, p );
		m_vlFormulario.nrTelefone = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idTipoLinha", 0 ), p ) 
	{
		m_stFormulario.idTipoLinha = atoi( p );
		m_vlFormulario.idTipoLinha = 1;
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idFaseProcesso", 0 ), p ) 
	{
		m_stFormulario.idFaseProcesso = atoi( p );
		m_vlFormulario.idFaseProcesso = 1;
		XMLString::release(&p);
	}
}

