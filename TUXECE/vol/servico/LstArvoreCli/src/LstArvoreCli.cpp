#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTARVORECLI);

void implLSTARVORECLI::Execute(DOMNode* dnode, XMLGen* xml_g) {
	
	CTuxHelperClever helper;

	CLinha			oLinha;
	CPessoa			oPessoa;
	list< CLinha >  lstLinha;

	char*  pcTagXmlIn = NULL;
	int    iCdAreaRegistro = -1;
 	int    iNrLinha = -1;
	int    iIdTipoRelacionamento = -1;
	int    iTemLinha = 0;		

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	///
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento");

	//04/11/2004.Modificado para retornar a árvore tanto para cliente quanto usuário
	//Faz a pesquisa de linhas associadas a pessoa, apenas se for cliente. Usuário não possui árvore!.
	//if (iIdTipoRelacionamento == PESSOA_CLIENTE){
		oPessoa.setIdTipoRelacionamento(iIdTipoRelacionamento);
		oPessoa.obterIdPessoa(iCdAreaRegistro, iNrLinha);
		CLinha::consultarLinhasDisp(oPessoa.getIdPessoa() , lstLinha);


		xml_g->createTag("LSTARVORECLIVO");
		xml_g->addProp( "xmlns", "menu.vol.vivo.com.br/vo" );


		if(lstLinha.size() > 1){

			while( 0 < lstLinha.size() ) {
			
				oLinha = lstLinha.front();
				
					iTemLinha = 1;

					xml_g->createTag("ARVORE");
						xml_g->addItem("cdAreaRegistro", oLinha.getCdAreaRegistro());
						xml_g->addItem("nrLinha", oLinha.getNrLinha());
						xml_g->addItem("idTipoLinha", oLinha.getIdTipoLinha());
					xml_g->closeTag();			
			//}

			lstLinha.pop_front();

			}
		}
		xml_g->closeTag();
	//}

	//seta mensagem de retorno - header
	if(iTemLinha==1)
		setStatusCode("11I0000", "ARVORE RETORNADA COM SUCESSO");
	else
		setStatusCode("11W0001", "ARVORE NAO EXISTE");

	return;
}
