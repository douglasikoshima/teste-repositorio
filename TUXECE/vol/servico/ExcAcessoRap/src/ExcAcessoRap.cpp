/*
 * Servi�o de Dados pessoais p�gina inicial
 * Vers�o inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <AcessoRapido/AcessoRapido.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(EXCACESSORAP);

void implEXCACESSORAP::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CAcessoRapido oMeAcRp;
	int ret;
	long idPessoa;
	int idItemMenu;

	// Navega o XML e recupera as informacoes obrigatorias

	DOMNode*dnodeItens = NULL;
	for(int i=0; (dnodeItens = helper.walkDOM(dnode,"item",i)) != NULL ; i++ )
	{
		char * pcIdPessoa = NULL;
		char * pcIdItemMenu = NULL;
		pcIdPessoa = helper.walkTree(dnodeItens, "idPessoa", 0);
		pcIdItemMenu = helper.walkTree(dnodeItens, "idItemMenu", 0);

		if ( NULL == pcIdPessoa || NULL == pcIdItemMenu ) {
			//ERROR(NRO_TP_CORR_ID_NE);
			//TAG_INEXISTENTE(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_INEXISTENTE");
		}

		if ( '\0' == *pcIdPessoa || NULL == *pcIdItemMenu ) {
			//ERROR(NRO_TP_CORR_ID_VV);
			//TAG_VALOR_VAZIO(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_VALOR_VAZIO");
		}

		if ( 0 >= atoi(pcIdPessoa) || 0 >= atoi(pcIdItemMenu) ) {
			//ERROR(NRO_TP_CORR_ID_VI);
			//TAG_VALOR_INVALIDO(XML_IN_ID_PESSOA);
			throw new TuxBasicSvcException("20", "TAG_VALOR_INVALIDO");
		}

		//Setando o id antes de decidir quem eh quem

		idPessoa = atol(pcIdPessoa);
		idItemMenu = atoi(pcIdItemMenu);

		ret = oMeAcRp.excluirAcessoRapido( idPessoa , idItemMenu );

		if(!ret || ret < 0)
			break;

	}	

	xml_g->createTag("EXCACESSORAPVO");
	xml_g->addProp("xmlns", "menu.vol.vivo.com.br/vo");

	if(!ret)
	{
		//seta mensagem de retorno - header
		setStatusCode("11W0001", "Item nao existe");			
	}  
	else if(ret < 0)
	{
		//seta mensagem de retorno - header
		setStatusCode("11W0002", "Exclusao nao efetuada");
	}
	else
	{
		// registrando contato
		REG_CONTATO(iRes, REG_VALIDA_TAG);

		//seta mensagem de retorno - header
		setStatusCode("11I0000", "Exclusao efetuada com Sucesso");
	}

	xml_g->closeTag();

	return;
}


