/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <Pessoa/Pessoa.hpp>
#include <Relacionamento/Relacionamento.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTHISTRELAC);

void implLSTHISTRELAC::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CPessoa oPessoa;
	CRelacionamento oRelac;
	list< CRelacionamento >	lstORelac;

	char *  pcTagXmlIn = NULL;
	char *  pcAreaRegistro = NULL;
	char *  pcNrLinha = NULL;
	char *	pcQtRelacionamentos = NULL;
	char *	pcDtRelacionamentoFinal = NULL;
	char *	pcDtRelacionamentoInicial = NULL;
	long	iIdPessoa;
	char *	pcIdCanalUltimoAcesso = NULL;
	int Achou = 1;

	// Navega o XML e recupera as informacoes obrigatorias
	
	pcAreaRegistro = helper.walkTree(dnode, "cdAreaRegistro", 0);
	pcNrLinha = helper.walkTree(dnode, "nrLinha", 0);
	pcQtRelacionamentos = helper.walkTree(dnode, "qtRelacionamentos", 0);
	pcDtRelacionamentoFinal = helper.walkTree(dnode, "dtRelacionamentoFinal", 0);
	pcDtRelacionamentoInicial = helper.walkTree(dnode, "dtRelacionamentoInicial", 0);
	pcIdCanalUltimoAcesso = helper.walkTree(dnode, "idCanalUltimoAcesso", 0);

	//idPessoa não é obrigatório
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	if (pcTagXmlIn != NULL)
		iIdPessoa = atol(pcTagXmlIn);
	else
		iIdPessoa = 0;

	if ( NULL == pcQtRelacionamentos || 
			NULL == pcDtRelacionamentoFinal ||
			NULL == pcDtRelacionamentoInicial) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}


/*  NÃO GERA EXCEÇÃO, quando o idPessoa por nulo.
	O Relatório de acessos por cliente FO, irá passar apenas a linha e data de até para este serviço e considerar todas as pessoas, ou seja, cliente e usuário podem ser distintos. Nesse caso considerar idPessoa=0

	if ( '\0' == *pcIdPessoa ) 
	{
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	if ( 0 >= atol(pcIdPessoa) )
	{
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}
*/


	//Setando o id antes de decidir quem eh quem
	oPessoa.setIdPessoa(iIdPessoa);

	oPessoa.getRel()->setCdAreaRegistro(atoi(pcAreaRegistro));
	oPessoa.getRel()->setNrLinha(atoi(pcNrLinha));


	if ( strcmp(pcDtRelacionamentoFinal, " ") >= 0 && 
			strcmp(pcDtRelacionamentoInicial, " ") >= 0 ){
		/*Consulta pela data Limitada entre as datas*/
		// Monta o objeto Pessoa
		try{
			oPessoa.consultarHistRangeDate( lstORelac, pcDtRelacionamentoInicial, pcDtRelacionamentoFinal, pcIdCanalUltimoAcesso);
		}catch( ... ){
			//setStatusCode("11W0002", "Consulta Nao Realizada");
			//return;
		}
	}
	else if ( strcmp(pcQtRelacionamentos, " ") >= 0 ){
		/*Consulta pela Quantidade de Relacionamentos*/
		// Monta o objeto Pessoa
		try{
		oPessoa.consultarHistQtdRelac( lstORelac, pcQtRelacionamentos, pcIdCanalUltimoAcesso);
		}
		catch ( ... ){
			//setStatusCode("11W0002", "Consulta_Nao_Realizada");
		}
	}
	else {
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

//	if ( lstORelac.size() == 0){
//		setStatusCode("11W0001", "Dados_Nao_encontrados");			
//		return;
//	}
	xml_g->createTag("LSTHISTRELACVO");
	xml_g->addProp("xmlns", "acessos.vol.vivo.com.br/vo");

	// registrando contato
	//REG_CONTATO(iRes, REG_VALIDA_TAG);
	REG_CONTATO_PROTOCOLO(iResult, REG_VALIDA_TAG, protocolo);
	xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	

	// Monta o XML de saída
	while( 0 < lstORelac.size() ) {
		xml_g->createTag("RELACIONAMENTOS");
		oRelac = lstORelac.front();

		/*Salvando o ultimo acesso*/
		xml_g->addItem( "dsOperacao", oRelac.getDsOperacao() );
		xml_g->addItem( "dtRelaciomaneto", oRelac.getDtRelacionamento() );
		xml_g->addItem( "hrRelaciomaneto", oRelac.getHrRelacionamento() );
		xml_g->addItem( "idCanal", oRelac.getIdCanal());
		xml_g->addItem( "dsCanal", oRelac.getDsCanal());

		lstORelac.pop_front();
		xml_g->closeTag();
	}

	xml_g->closeTag();


	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Consulta efetuada com sucesso");
	return;
}

