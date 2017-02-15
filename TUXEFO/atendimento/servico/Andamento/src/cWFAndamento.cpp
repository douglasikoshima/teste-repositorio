/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.5.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2013/02/05 17:02:44 $
 **/

#include "../include/cWFAndamento.h"

extern long proCIncluirWFAndamento(st_Andamento* dados, st_vlAndamento* status, XMLDPR *xmlDpr, XMLGen* saida);
extern bool proCAlterarWFAndamento(st_Andamento* dados, st_vlAndamento* status, XMLGen* saida);
//extern bool proCExcluirWFAndamento(st_Andamento* dados, st_vlAndamento* status, XMLGen* saida);
extern bool proCConsultaWFAndamento(st_Andamento* dados, st_vlAndamento* status, char* order, XMLGen* saida);
extern bool proCObtemWFHistoricoAtendimento(long sIdAtendimento, XMLGen* saida);
extern bool proCObtemWFHistoricoAtdPesquisa(long sIdAtendimento, int sIdEstado, int sIdSubEstado, XMLGen* saida);
extern bool proCObtemWFHistAtdPesqEst(st_Andamento* dados, st_vlAndamento* status, XMLGen* saida);
extern bool proCObtemWFHistAtdPesqEstEx(st_AndamentoEx* dados, st_vlAndamento* status, XMLGen* saida);

/**
	Construtor da classe concebido para sobrepor o construtor original que recebia como parametros
	um DOMNode e um XMLGen, esse novo construtor recebe duas structs e um XMLGen, essas duas structs
	tem a função de fornecer os valores a serem manipulados e a indicação dos campos que possuem valor.
	O XMLGen continua existindo na declaração pois os métodos de consulta devolverão os dados diretamente
	ao XMLGen, assim não sendo necessário criar um segundo processo para a conversão do resultado diferente.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAndamento::cWFAndamento(st_Andamento* dados, st_vlAndamento* status, XMLGen*xml_g)
{
	memcpy(&m_stDados,dados,sizeof(m_stDados));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

cWFAndamento::cWFAndamento(st_AndamentoEx * dados, st_vlAndamento* status, XMLGen*xml_g)
{
	memcpy(&m_stDadosEx,dados,sizeof(m_stDadosEx));
	memcpy(&m_vlDados,status,sizeof(m_vlDados));

    entrada = 0;
	saida   = xml_g;
}

/**
	Esse construtor foi desenvolvido levando em conta as necessidades do SSKlunk10.
	@autor Renato Teixeira
	@since 21/10/2004
*/
cWFAndamento::cWFAndamento(DOMNode*dnode, XMLGen*xml_g)
{
	entrada = dnode;
	saida   = xml_g;

	carregaDados();
}

// Não é para usar este método, criado apenas monitorar chamadas pelo
// SSKlunk. Este método gera exceção!
long cWFAndamento::incluir()
{
    throw new TuxException("04E9999","Uso de cWFAndamento::incluir() indevido!");
}

long cWFAndamento::incluir(XMLDPR *xmlDpr)
{
    return incluir(&m_stDados, &m_vlDados, xmlDpr, saida);
}

long cWFAndamento::incluir(st_Andamento* dados, st_vlAndamento* status,XMLDPR *xmlDpr,XMLGen *saida)
{
    return proCIncluirWFAndamento(dados,status,xmlDpr,saida);
}

long cWFAndamento::alterar()
{

	if (m_vlDados.idAndamento == -1) 
	{
		return -1;
	}

	return proCAlterarWFAndamento(&m_stDados, &m_vlDados, saida);
}

// int cWFAndamento::excluir()
// {
// 
// 	if (m_vlDados.idAndamento == -1) 
// 	{
// 		return -1;
// 	}
// 
// 	return proCExcluirWFAndamento(&m_stDados, &m_vlDados, saida);
// }

bool cWFAndamento::consultar()
{
	char order[256];

	order[0] = 0;

    if ( entrada )
    {
    	char* p;
	    if ( p = tx.walkTree( entrada, "order", 0 ), p )
		{
            sprintf(order,"%.*s",sizeof(order)-1,p);
			XMLString::release(&p);
		}
    }

	return proCConsultaWFAndamento(&m_stDados, &m_vlDados, order, saida);
}

bool cWFAndamento::ObtemHistAtendEstado()
{
    return proCObtemWFHistAtdPesqEst(&m_stDados,&m_vlDados,saida);
}

bool cWFAndamento::ObtemHistAtendEstadoEx()
{
    return proCObtemWFHistAtdPesqEstEx(&m_stDadosEx,&m_vlDados,saida);
}

bool cWFAndamento::ObtemHistAtendEstadoCRI()
{
    // return proCObtemWFHistAtdPesqEstCRI(&m_stDados,&m_vlDados,saida);
	return proCObtemWFHistAtdPesqEst(&m_stDados,&m_vlDados,saida);
}

bool cWFAndamento::ObtemHistAtendEstado(st_Andamento* dados,st_vlAndamento* status,XMLGen* saida)
{
    return proCObtemWFHistAtdPesqEst(dados,status,saida);
}

bool cWFAndamento::ObtemHistPsq()
{
    if (m_vlDados.idAtendimento == -1) { return false; }

    return ObtemHistAtendEstado(&m_stDados,&m_vlDados,saida);
}

//bool cWFAndamento::ObtemHistAtend()
//{
//    if (m_vlDados.idAtendimento == -1) { return false; }
//
//	return proCObtemWFHistoricoAtendimento( m_stDados.idAtendimento,saida );
//}

/**
	Esse método é usado para carregar os dados usados na classe a partir de um DOMNode.
	Os dados são carregados em duas estruturas, sendo a primeira, a que contém o valor obtido e a segunda
	indicado que aquele atributo da primeira estrutrua possui valor.
	O método é chamado pelo construtor que recebe como parametro um DOMNode e um XMLGen.
	@autor Renato Teixeira
	@since 21/10/2004
*/
void cWFAndamento::carregaDados()
{
    ULOG_START( "void cWFAndamento::carregaDados()" );

    // Inicializa as estruturas de dados para armazenar os valores.
	memset(&m_stDados,0,sizeof(st_Andamento));
	memset(&m_vlDados,-1,sizeof(st_vlAndamento));

    if ( !entrada )
    {
        return;
    }

    char *p;

	if ( p = tx.walkTree( entrada, "idAndamento", 0 ),p )
	{
		m_stDados.idAndamento = atol(p);
		m_vlDados.idAndamento = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idAtividade", 0 ),p ) 
	{
		m_stDados.idAtividade = atoi(p);
		m_vlDados.idAtividade = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idEstado", 0 ),p ) 
	{
		m_stDados.idEstado = atoi(p);
		m_vlDados.idEstado = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idSubEstado", 0 ),p ) 
	{
		m_stDados.idSubEstado = atoi(p);
		m_vlDados.idSubEstado = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idAgrupamentoEstadoTProcFut", 0 ),p ) 
	{
		m_stDados.idAgrupamentoEstadoTpProc = atoi(p);
		m_vlDados.idAgrupamentoEstadoTpProc = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idAgrupamentoEstadoTpProc", 0 ),p ) 
	{
		m_stDados.idAgrupamentoEstadoTpProc = atoi(p);
		m_vlDados.idAgrupamentoEstadoTpProc = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idAtendimento", 0 ),p ) 
	{
		m_stDados.idAtendimento = atol(p);
		m_vlDados.idAtendimento = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idPessoaUsuario", 0 ),p )
	{
		m_stDados.idPessoaUsuario = atoi(p);
		m_vlDados.idPessoaUsuario = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idGrupo", 0 ),p )
	{
		m_stDados.idGrupo = atoi(p);
		m_vlDados.idGrupo = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "dtAndamento", 0 ),p ) 
	{
		strcpy(m_stDados.dtAndamento,p);
		m_vlDados.dtAndamento = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "idUsuarioAlteracao", 0 ),p ) 
	{
		m_stDados.idUsuarioAlteracao = atoi(p);
		m_vlDados.idUsuarioAlteracao = 1;
		XMLString::release(&p);
	}

	if ( p = tx.walkTree( entrada, "dtUltimaAlteracao", 0 ),p ) 
	{
		strcpy(m_stDados.dtUltimaAlteracao, p);
		m_vlDados.dtUltimaAlteracao = 1;
		XMLString::release(&p);
	}

    ULOG_END( "void cWFAndamento::carregaDados()" );

}
