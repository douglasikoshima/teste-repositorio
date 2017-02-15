/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Menu/Menu.hpp>
#include <Linha/Linha.hpp>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTMENU);

void implLSTMENU::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CMenu oMenu;
	list< CMenu > lstMenu;
	CLinha oLinha;
	char *	pcIdCanal = NULL;
	char *	pcCdAreaReg = NULL;
	char *	pcNrCel = NULL;
	char *	pcIdTipoRelacionamento = NULL;
	char *	pcUsuario = NULL;
	char *	pcCliente = NULL;		
	bool	blMenuRestrito =  false;
	int		iIdGrupo = 1;
	char	*pcIdPessoa = NULL;
    char *  pcExibeMenuPacote = NULL;
	int     inExibeMenuPacote = 0;

	// Navega o XML e recupera as informacoes obrigatorias
	
	pcIdCanal = helper.walkTree(dnode, "idCanal", 0);
	pcCdAreaReg = helper.walkTree(dnode, "cdAreaRegistro", 0);
	pcNrCel = helper.walkTree(dnode, "nrLinha", 0);
	pcIdTipoRelacionamento = helper.walkTree(dnode, "idTipoRelacionamento", 0);
	pcUsuario = helper.walkTree(dnode, "usuario", 0);
	pcCliente = helper.walkTree(dnode, "cliente", 0);
	pcIdPessoa = helper.walkTree(dnode, "idPessoa", 0);
	pcExibeMenuPacote = helper.walkTree(dnode, "exibePacote", 0);

	if (pcExibeMenuPacote != NULL)
		inExibeMenuPacote = atoi(pcExibeMenuPacote);

	oLinha.setCdAreaRegistro(atoi(pcCdAreaReg));
	oLinha.setNrLinha(atoi(pcNrCel ));


	try{
		oLinha.consultarTipoLinha();
	
	}
	catch ( ... ){}

	if ( NULL == pcIdCanal || 
			NULL == pcCdAreaReg ||
			NULL == pcNrCel ) {
		//ERROR(NRO_TP_CORR_ID_NE);
		//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("11E0000", "TAG_idCanal_INEXISTENTE");
	}

	if ( '\0' == *pcIdCanal || '\0' == *pcCdAreaReg || '\0' == *pcNrCel) {
		//ERROR(NRO_TP_CORR_ID_VV);
		//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaReg_VALOR_VAZIO");
	}

	if ( 0 >= atoi(pcIdCanal) ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("11E0000", "TAG_idCanal_VALOR_INVALIDO");
	}

	if ( 0 >= atoi(pcIdTipoRelacionamento) ) {
		//ERROR(NRO_TP_CORR_ID_VI);
		//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
		throw new TuxBasicSvcException("11E0000", "TAG_idTipoRelacionamento_VALOR_INVALIDO");
	}

	if ((!strcmp(pcUsuario, "PF")) && (!strcmp(pcCliente, "PJ")))
		iIdGrupo = 3;

		oMenu.obterDadosMenu(atoi(pcIdCanal), atoi(pcCdAreaReg), atoi(pcNrCel), atoi(pcIdTipoRelacionamento), lstMenu, blMenuRestrito, iIdGrupo);

	if ( lstMenu.size() == 0){
		setStatusCode("11W0001", "Dados_Nao_encontrados");			
		return;
	}

	xml_g->createTag("MenusVO");
	xml_g->addProp("xmlns", "menu.vol.vivo.com.br/vo");

	// Monta o XML de saída
	while( 0 < lstMenu.size() ) {
	
		oMenu = lstMenu.front();


		if ((strcmp(CUtil::trim(oMenu.getNmAction()), "/servicos/comprovanteCancelamentoAction") == 0 || 
			 strcmp(CUtil::trim(oMenu.getNmAction()), "comprovanteCancelamento") == 0) 
			 &&  oLinha.getIdEstadoLinha() != 2 )
		{
			
			 lstMenu.pop_front();			
			 continue;

		}

		if (inExibeMenuPacote == 0 && ( strcmp(CUtil::trim(oMenu.getNmAction()), "listarPacotesServicos") == 0
			|| strcmp(CUtil::trim(oMenu.getNmAction()), "/servicos/vendaPacotes") == 0))
		{
			
			 lstMenu.pop_front();			
			 continue;

		}


		
		oLinha.consultarLinhaPremium();

		if (strcmp(CUtil::trim(oMenu.getNmAction()), "/agendamentoVIP/begin") == 0  && oLinha.getLinhaPremium() ==  false)
		{
			
			 lstMenu.pop_front();			
			 continue;

		}

		xml_g->createTag("MenuVO");
		/*Salvando o ultimo acesso*/
		xml_g->addItem( "idItemMenu", oMenu.getIdItemMenu() );
		if (oMenu.getIdItemMenuPai() > 0){
			xml_g->addItem( "idItemMenuPai", oMenu.getIdItemMenuPai());
		}else{
			xml_g->addItem( "idItemMenuPai", "");
		}
		xml_g->addItem( "nmItem", CUtil::trim(oMenu.getNmItem()) );
		xml_g->addItem( "nmAction", CUtil::trim(oMenu.getNmAction()) );
		xml_g->addItem( "inPermitido", oMenu.getInPermitido() );
		xml_g->addItem( "idContato", oMenu.getIdContato() );

		lstMenu.pop_front();
		xml_g->closeTag();
	}

	xml_g->closeTag();
	// Execução OK.
	//INFORMATION(NRO_OK);

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "Pesquisa_efetuada_com_sucesso");
	return;
}

