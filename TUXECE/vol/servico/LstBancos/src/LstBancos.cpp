#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Banco/Banco.hpp>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTBANCOS);

void implLSTBANCOS::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CBanco        oBanco;
	list<CBanco>  listaBancos;
	bool          bTemBancos = false;
 
	char*         pcTagXmlIn = NULL;
	int           iIdTipoBanco;
	int			  iCdAreaRegistro;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTipoBanco", 0);
	ASSERT_INT(iIdTipoBanco, pcTagXmlIn, "idTipoBanco");

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	try
	{
		CBanco::consultarBancos(iIdTipoBanco, listaBancos, iCdAreaRegistro);
	}
	catch(...)
	{
		setStatusCode("11W0002", "NAO FOI POSSIVEL EFETUAR A CONSULTA");
		return;
	}

	xml_g->createTag("LSTBANCOSVO");

	xml_g->addProp("xmlns", "contas.vol.vivo.com.br/vo");

	while(0 < listaBancos.size())
	{

		bTemBancos = true;

		oBanco = listaBancos.front();

		xml_g->createTag("BANCOS");

			xml_g->addItem("idBanco", oBanco.getIdBanco());
			xml_g->addItem("nmBanco", oBanco.getNmBanco());
			xml_g->addItem("dsURLBanco", oBanco.getDsURLBanco());
			xml_g->addItem("inSiteaSite", oBanco.getInSiteaSite());

		xml_g->closeTag();

		listaBancos.pop_front();
	}


	// seta mensagem de retorno - header
    if(bTemBancos)
	{
		// registrando contato
		// REG_CONTATO(iRes, REG_VALIDA_TAG);
		REG_CONTATO_PROTOCOLO(iResult,REG_NAO_VALIDA_TAG,protocolo);

		xml_g->createTag("ProtocoloVO");
		//xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
		xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	
		xml_g->addItem("inProtocolo",protocolo.inProtocolo);
		xml_g->addItem("inRelacionamento",protocolo.inRelacionamento);
		xml_g->addItem("exibeProtocolo",protocolo.exibeProtocolo);
		xml_g->closeTag();

        setStatusCode("11I0000", "CONSULTA EFETUADA COM SUCESSO");
	}
    else
        setStatusCode("11W0001", "NAO EXISTEM BANCOS PARA ESTE IDTIPOBANCO");

	xml_g->closeTag();

}