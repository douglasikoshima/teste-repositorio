/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.2.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:36:16 $
 **/


#include "../include/cWFAtendimentoContato.h"

extern long proCIncluirWFAtendimentoContato(st_AtendimentoContato* dados, st_vlAtendimentoContato* status, XMLDPR *xmlDpr);
extern bool proCAlterarWFAtendimentoContato(st_AtendimentoContato* dados, st_vlAtendimentoContato* status, XMLGen* saida);
extern bool proCExcluirWFAtendimentoContato(st_AtendimentoContato* dados, st_vlAtendimentoContato* status, XMLGen* saida);
extern bool proCConsultaWFAtendimentoContato(long idAtendimento,XMLGen* saida,AtendimentoContato *ac);
extern bool proCConsultaWFAtendimentoContatoEx(long idAtendimento,XMLGen* saida,AtendimentoContato *ac);
extern bool proCConsultaWFAtendimentoContatoEx_Migracao(long idAtendimento,XMLGen* saida,AtendimentoContato *ac);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContato::cWFAtendimentoContato(st_AtendimentoContato* dados, st_vlAtendimentoContato* indicator, XMLGen*xml_g)
{

	locado = false;

	m_stDados = dados;
	m_vlDados = indicator;

	saida   = xml_g;
    entrada = 0;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContato::cWFAtendimentoContato(DOMNode*dnode, XMLGen*xml_g)
{

	m_stDados = (st_AtendimentoContato*) malloc(sizeof(st_AtendimentoContato));
	m_vlDados = (st_vlAtendimentoContato*) malloc(sizeof(st_vlAtendimentoContato));

	locado = true;
	
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

/**
	Destrutor da classe.
	Libera a estruturas de dados quando elas foram alocadas internamente e não recebidas no construtor.
	@autor Renato Teixeira
	@since 22/10/2004
*/
cWFAtendimentoContato::~cWFAtendimentoContato()
{
	if (locado)
	{
		free(m_stDados);
		free(m_vlDados);
	}
}

// nao é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
int cWFAtendimentoContato::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAtendimentoContato::incluir() indevido!");
}

long cWFAtendimentoContato::incluir(XMLDPR *xmlDpr)
{
	long idAtendimento = proCIncluirWFAtendimentoContato(m_stDados, m_vlDados, xmlDpr);

	saida->createTag("WFAtendimentoContatoVO");
		saida->addItem("idAtendimento", idAtendimento);
	saida->closeTag();

	return idAtendimento;
}

int cWFAtendimentoContato::alterar()
{

	if (m_vlDados->idAtendimento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAtendimentoContato(m_stDados, m_vlDados, saida);
}

int cWFAtendimentoContato::excluir()
{

	if (m_vlDados->idAtendimento== -1) 
	{
		return -1;
	}

	return proCExcluirWFAtendimentoContato(m_stDados, m_vlDados, saida);
}

bool cWFAtendimentoContato::consultar()
{
    return proCConsultaWFAtendimentoContato(m_stDados->idAtendimento,saida,0);
}

bool cWFAtendimentoContato::consultarEx()
{
    return proCConsultaWFAtendimentoContatoEx(m_stDados->idAtendimento,saida,0);
}

bool cWFAtendimentoContato::ObterAtendimentoContato(long idAtendimento,AtendimentoContato *ac)
{
    return proCConsultaWFAtendimentoContato(idAtendimento,0,ac);
}



bool cWFAtendimentoContato::ObterAtendimentoContatoEx(long idAtendimento,AtendimentoContato *ac)
{
    return proCConsultaWFAtendimentoContatoEx(idAtendimento,0,ac);
}



bool cWFAtendimentoContato::ObterAtendimentoContatoEx_Migracao(long idAtendimento,AtendimentoContato *ac)
{
    return proCConsultaWFAtendimentoContatoEx_Migracao(idAtendimento,0,ac);
}



void cWFAtendimentoContato::carregaDados()
{
    ULOG_START( "cWFAtendimentoContato::carregaDados()" );

	// Inicializa as estruturas de dados para armazenar os valores.
	memset(m_stDados,0,sizeof(st_AtendimentoContato));
	memset(m_vlDados,-1,sizeof(st_vlAtendimentoContato));

	char* p;

	if (p = tx.walkTree( entrada, "idAtendimento", 0 ), p ) 
	{
		m_stDados->idAtendimento = atol( p );
		m_vlDados->idAtendimento = 1;
		XMLString::release(&p);

		ULOG("idAtendimento = [%d]",m_stDados->idAtendimento);
	}

	if (p = tx.walkTree( entrada, "nmContato", 0 ), p ) 
	{
		strcpy(m_stDados->nmContato, p );
		m_vlDados->nmContato = 1;
		XMLString::release(&p);

		ULOG("nmContato = [%s]",m_stDados->nmContato);
	}

	if (p = tx.walkTree( entrada, "nrTelefoneContato", 0 ), p ) 
	{
		strcpy(m_stDados->nrTelefoneContato, p );
		m_vlDados->nrTelefoneContato = 1;

		ULOG("nrTelefoneContato = [%s]",m_vlDados->nrTelefoneContato);
		
		char cConv[3];
		if ( m_stDados->nrTelefoneContato[0]=='0' ) 
		{
			strncpy(cConv,m_stDados->nrTelefoneContato+1,2);cConv[2]=0;
			m_stDados->cdAreaRegistro = atoi(cConv);
		}
		else
		{
			strncpy(cConv,m_stDados->nrTelefoneContato,2);cConv[2]=0;
			m_stDados->cdAreaRegistro = atoi(cConv);
		}

		ULOG("cdAreaRegistro = [%d]",m_stDados->cdAreaRegistro);

		m_vlDados->cdAreaRegistro = 1;
		
		XMLString::release(&p);
	}

	if (p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ), p ) 
	{
		m_stDados->idUsuarioAlteracao = atoi( p );
		m_vlDados->idUsuarioAlteracao = 1;
		XMLString::release(&p);

		ULOG("dtUltimaAlteracao = [%s]",m_stDados->dtUltimaAlteracao );

	}

	if (p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ), p ) 
	{
		strcpy(m_stDados->dtUltimaAlteracao, p );
		m_vlDados->dtUltimaAlteracao = 1;
		XMLString::release(&p);

		ULOG("dtUltimaAlteracao = [%s]",m_stDados->dtUltimaAlteracao );
	}


	if (p = tx.walkTree(entrada,"nmFalandoCom",0),p) 
	{
		sprintf(m_stDados->nmFalandoCom,"%.*s",sizeof(m_stDados->nmFalandoCom)-1,p);
		m_vlDados->nmFalandoCom = 1;
		XMLString::release(&p);

		ULOG("nmFalandoCom = [%s]",m_stDados->nmFalandoCom );
	}

    ULOG_END( "cWFAtendimentoContato::carregaDados()" );


}

