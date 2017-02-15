/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Pessoa/Pessoa.hpp>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTPESSOA);

void implLSTPESSOA::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CPessoa oPessoa;
	CLinha oLinha;
	list < CLinha > lstLinha;

	char * cNmPessoa;
	char * cCPF;
	long iRet;

	cNmPessoa = helper.walkTree(dnode,"nmPessoa", 0);
	cCPF = helper.walkTree(dnode,"nuCpf", 0);


	if ( NULL == cNmPessoa || NULL == cCPF ) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_CD_AREA_REGISTRO);
		throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
	}
	if ( '\0' == *cNmPessoa || '\0' == *cCPF ) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_CD_AREA_REGISTRO);
		throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
	}


	oPessoa.setNomeCliente(cNmPessoa);
	oPessoa.setCpf(cCPF);
	iRet = oPessoa.getIdPessoaByCpf();

	if (iRet < 0){
		setStatusCode("11W0002", "Pesquisa Não Efetuada");
		return;
	} else if ( iRet == 0){
		setStatusCode("11W0001", "Pessoa não cadastrada");
		return;
	}else {
		try{
			oLinha.consultarLinhasDisp(oPessoa.getIdPessoa(), lstLinha);
		}
		catch( ... ){
				lstLinha.clear() ;
		}

		xml_g->createTag("LSTPESSOAVO");
		xml_g->addProp("xmlns", "dados.vol.vivo.com.br/vo");
			xml_g->addItem( "idPessoa", oPessoa.getIdPessoa() );
			while( 0 < lstLinha.size() ) {
				xml_g->createTag("Linhas");
				oLinha = lstLinha.front();
					xml_g->addItem( "cdAreaRegistro", oLinha.getCdAreaRegistro() );
					xml_g->addItem( "nrLinha", oLinha.getNrLinha() );
				lstLinha.pop_front();
				xml_g->closeTag();
			}
		xml_g->closeTag();
		setStatusCode("11I0000", "Pesquisa Efetuada com Sucesso");
		return;
	}
}

