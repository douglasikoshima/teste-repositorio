#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(VALPRIACESSO);

void implVALPRIACESSO::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CLinha            oLinha;

	char*  pcTagXmlIn = NULL;
	int    iCdAreaRegistro = -1;
 	int    iNrLinha = -1;
	int    iIdTipoRelacionamento;
	char   cLembSenha[255] ;
	int    iIdCanal;
	int    iIdTerminal = 0;

	memset (cLembSenha,' ', sizeof(cLembSenha) );

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_VALOR_VAZIO");
	}

	if ((iCdAreaRegistro = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_VALOR_INVALIDO");
	}
	
	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_nrLinha_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("11E0000", "TAG_nrLinha_VALOR_VAZIO");
	}

	if ((iNrLinha = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_nrLinha_VALOR_INVALIDO");
	}
    
	///
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_idTipoRelacionamento_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("11E0000", "TAG_idTipoRelacionamento_VALOR_VAZIO");
	}

	if ((iIdTipoRelacionamento = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_idTipoRelacionamento_VALOR_INVALIDO");
	}

	///
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_idCanal_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("11E0000", "TAG_idCanal_VALOR_VAZIO");
	}

	if ((iIdCanal = atoi(pcTagXmlIn)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_idCanal_VALOR_INVALIDO");
	}


	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);

	if (pcTagXmlIn != NULL)
		iIdTerminal = atoi(pcTagXmlIn);


	// Monta o objeto oLinha
	oLinha.setCdAreaRegistro(iCdAreaRegistro);	
	oLinha.setNrLinha(iNrLinha);
	oLinha.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento);

	try {
        strcpy(  cLembSenha, CUtil::trim(oLinha.obterFraseSecreta( iIdCanal, atoi(this->getUser()), iIdTerminal ) ));
	}
	catch( ... ) {
		setStatusCode("11W0001", "Nao foi possível efetuar a validacao");
		return;
	}
	
	xml_g->createTag("VALPRIACESSOVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");
	xml_g->addItem("inPrimeiroAcesso", 0);
	xml_g->addItem("inGeradoSistema", oLinha.getPessoa()->getSenha().getIdTipoSenha() == 1 ? 0 : 1);
	xml_g->closeTag();
	
	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Validacao efetuada com sucesso");
}

