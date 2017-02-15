/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <Processo/Processo.hpp>

DECLARE_TUXEDO_SERVICE(LSTPROCESSOS);

void implLSTPROCESSOS::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CPessoa oPessoa;
	CProcesso oProc;
	list< CProcesso >	lstOProc;

	char *	pcDtProcessoFinal = NULL;
	char *	pcDtProcessoInicial = NULL;
	char * pcIdPessoa = NULL;
	char * pcQtdProcesso = NULL;
	
	// Navega o XML e recupera as informacoes obrigatorias
	

	pcDtProcessoFinal = walkTree(dnode, "dtAberturaFinal", 0);
	pcDtProcessoInicial = walkTree(dnode, "dtAberturaInicial", 0);
	pcIdPessoa = walkTree(dnode, "idPessoa", 0);
	pcQtdProcesso = walkTree(dnode, "qtProcessos", 0);



	if ( NULL == pcDtProcessoFinal && 
			NULL == pcDtProcessoInicial &&
			NULL == pcIdPessoa &&
			NULL == pcQtdProcesso) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}

	if ( '\0' == *pcIdPessoa ) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	if ( 0 >= atol(pcIdPessoa) ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	//Setando o id antes de decidir quem eh quem
	oPessoa.setIdPessoa(atol(pcIdPessoa));

	if ( strcmp(pcDtProcessoFinal, " ") >= 0 && 
			strcmp(pcDtProcessoInicial, " ") >= 0 ){
		/*Consulta pela data Limitada entre as datas*/
		// Monta o objeto Pessoa
		try{
			oPessoa.consultarRangeDateProcessos( lstOProc, pcDtProcessoInicial, pcDtProcessoFinal);
		}catch( ... ){
			setStatusCode("11E0001", "Erro: tipo da linha não encontro");
			return;
		}
	}
	else if ( strcmp(pcQtdProcesso, " ") >= 0 ){
		/*Consulta pela Quantidade de Relacionamentos*/
		// Monta o objeto Pessoa
		try{
		oPessoa.consultarUltimosProcessos( lstOProc, pcQtdProcesso);
		}
		catch ( ... ){
			setStatusCode("11E0001", "Erro: tipo da linha não encontro");
			return;
		}
	}
	else {
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	if ( lstOProc.size() == 0){
		setStatusCode("11W0001", "Linha não encontrada");			
		return;
	}

	xml_g->createTag("LSTPROCESSOSVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");

	// Monta o XML de saída
	while( 0 < lstOProc.size() ) {
		xml_g->createTag("PROCESSOS");
		oProc = lstOProc.front();

		/*Salvando o ultimo acesso*/
		xml_g->addItem( "idAtendimento", oProc.getIdAtendimentoProc() );
		xml_g->addItem( "dsAtendimento", oProc.getDsAtendimentoProc() );
		xml_g->addItem( "dtAbertura", oProc.getDtAberturaProc() );
		xml_g->addItem( "dtConclusao", oProc.getDtConclusaoProc());
		xml_g->addItem( "dsStatus", oProc.getDsStatusProc());

		lstOProc.pop_front();
		xml_g->closeTag();
	}

	xml_g->closeTag();
	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Linha existente");
	return;
}
