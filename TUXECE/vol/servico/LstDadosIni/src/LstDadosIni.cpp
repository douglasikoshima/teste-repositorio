/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <Pessoa/Pessoa.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTDADOSINI);

void implLSTDADOSINI::Execute(DOMNode* dnode, XMLGen* xml_g) {
	
	CTuxHelperClever helper;

	CLinha	oLinha;

	char *	pcAuxiliar = NULL;
	long	iIdPessoa = -1;
 	int		iCdAreaRegistro = -1;
 	int		iNrLinha = -1;

	// Navega o XML e recupera as informacoes obrigatorias
	pcAuxiliar = helper.walkTree(dnode, "idPessoa", 0);
	if ( NULL == pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}
	if ( '\0' == *pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	iIdPessoa = atoi( pcAuxiliar );
	if ( 0 >= iIdPessoa ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	pcAuxiliar = helper.walkTree(dnode, "cdAreaRegistro", 0);
	if ( NULL == pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_CD_AREA_REGISTRO);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}
	if ( '\0' == *pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_CD_AREA_REGISTRO);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	iCdAreaRegistro = atoi( pcAuxiliar );
	if ( 0 >= iIdPessoa ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_CD_AREA_REGISTRO);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	pcAuxiliar = helper.walkTree(dnode, "nrLinha", 0);
	if ( NULL == pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_NR_LINHA);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}
	if ( '\0' == *pcAuxiliar ) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_NR_LINHA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}

	iNrLinha = atoi( pcAuxiliar );
	if ( 0 >= iIdPessoa ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_NR_LINHA);
		throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
	}

	// Monta o objeto Pessoa
	oLinha.getPessoa()->setIdPessoa( iIdPessoa );	
     
	// Consultar o Nome e o Contato armazenados no banco de dados
	oLinha.getPessoa()->consultarNome();
	oLinha.getPessoa()->consultarEMail();

	// Logs
	/*
	tuxfw_getlogger()->information("Valor de iIdPessoa: %ld\n", iIdPessoa);
	tuxfw_getlogger()->information("SAIDA: Valor de CPessoa.NmPessoa: %s\n", oPessoa.getNmPessoa()); 
	tuxfw_getlogger()->information("SAIDA: Valor de CPessoa.m_cDsContato: %s\n", oPessoa.getDsContato()); 
	*/
	// Monta o objeto Linha
	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);
     
	// Consultar o Tipo e a Descricao da linha armazenados no banco de dados
	oLinha.consultarTipoLinha();

	// Logs
	/*
	tuxfw_getlogger()->information("Valor de iIdPessoa: %ld\n", iIdPessoa);
	tuxfw_getlogger()->information("Valor de iCdAreaRegistro: %d\n", iCdAreaRegistro); 
	tuxfw_getlogger()->information("Valor de iNrLinha: %d\n", iNrLinha); 
	tuxfw_getlogger()->information("SAIDA: Valor de CLinha.m_iIdTipoLinha: %d\n", oLinha.getIdTipoLinha()); 
	tuxfw_getlogger()->information("SAIDA: Valor de CLinha.m_cDsTipoLinha: %s\n", oLinha.getDsTipoLinha()); 
	*/
	// Monta o XML de saída
	xml_g->createTag( "LSTDADOSINIVO" );
	xml_g->addProp( "xmlns", "capa.vol.vivo.com.br/vo" );

	xml_g->addItem( "nmPessoa", oLinha.getPessoa()->getNmPessoa() );
	xml_g->addItem( "idTipoLinha", oLinha.getIdTipoLinha() );
	xml_g->addItem( "dsTipoLinha", oLinha.getDsTipoLinha() );
	xml_g->addItem( "nmEmail", oLinha.getPessoa()->getDsContato() );

	xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header
	setStatusCode("20I0000", "MSG_OK");
}

