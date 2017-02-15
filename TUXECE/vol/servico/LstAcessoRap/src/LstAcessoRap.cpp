/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <AcessoRapido/AcessoRapido.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTACESSORAP);

void implLSTACESSORAP::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CAcessoRapido        oMeAcRp;
	list <CAcessoRapido> lstOMenu;	

	char*  pcTagXmlIn = NULL;
	long   iIdPessoa = -1;
	int    iIdTipoLinha;
	int	   iCdAreaRegistro;
	int    iNrLinha = 0;
	int		iIdUF;
	int		iIdTipoRelacionamento = 0;
	char *	pcUsuario = NULL;
	char *	pcCliente = NULL;		
	int		iIdTipoPessoa = 0;
	int     iIdGrupo = 1;
	//Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idPessoa", 0);
	ASSERT_LONG(iIdPessoa, pcTagXmlIn, "idPessoa");
	///
	pcTagXmlIn = helper.walkTree(dnode,"idTipoLinha", 0);
	ASSERT_INT(iIdTipoLinha, pcTagXmlIn, "idTipoLinha");

	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");


	pcUsuario = helper.walkTree(dnode, "usuario", 0);
	pcCliente = helper.walkTree(dnode, "cliente", 0);


	iIdUF = oMeAcRp.consultarIdUF(iCdAreaRegistro);


	if ((!strcmp(pcUsuario, "PF")) && (!strcmp(pcCliente, "PJ")))
		iIdGrupo = 3;



	xml_g->createTag("LSTACESSORAPVO");
	xml_g->addProp("xmlns", "menu.vol.vivo.com.br/vo");

	try
	{ 
		oMeAcRp.consultarAcessoRapido(iIdPessoa, iIdTipoLinha, lstOMenu, iIdUF, iIdGrupo);
	}
	catch(...)
	{
		setStatusCode("11W0002", "Consulta Não Efetuada");
		xml_g->closeTag();
		return;
	}
	
	if(lstOMenu.size() == 0)
	{
		setStatusCode("11W0001", "idPessoa nao possui itens de Acesso Rapido");			
	}
	else
	{
		// Monta o XML de saída
		while(0 < lstOMenu.size())
		{
			xml_g->createTag("AcessoRapido");

			oMeAcRp = lstOMenu.front();

			/*Salvando o ultimo acesso*/
			xml_g->addItem("idItemMenu", oMeAcRp.getIdItemMenu());

			if (oMeAcRp.getIdItemMenuPai() > 0)
			{
				xml_g->addItem("idItemMenuPai", oMeAcRp.getIdItemMenuPai());
			}
			else
			{
				xml_g->addItem("idItemMenuPai", "");
			}
			
			xml_g->addItem("nmItem", oMeAcRp.getNmItem());
			xml_g->addItem("nmAction", oMeAcRp.getDsHint());
			xml_g->closeTag();

			lstOMenu.pop_front();
			
		}

		//seta mensagem de retorno - header
		setStatusCode("11I0000", "Consulta Efetuada com Sucesso");

	}

	xml_g->closeTag();

}

