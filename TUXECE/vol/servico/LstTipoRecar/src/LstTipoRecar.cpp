#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <TipoRecarga/TipoRecarga.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTTIPORECAR);

void implLSTTIPORECAR::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CTipoRecarga        oTipoRecargas;
	list<CTipoRecarga>  listaTipoRecargas;
	bool                bTemTipoRecarga = false;

	char*   pcTagXmlIn = NULL;
	int     iCdAreaRegistro=0;
	int		iIdUFOperadora=0;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pcTagXmlIn != NULL)
		iCdAreaRegistro = atoi(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	if (pcTagXmlIn != NULL)
		iIdUFOperadora = atoi(pcTagXmlIn);


	try
	{
		CTipoRecarga::consultarTipoRecargas(iCdAreaRegistro, listaTipoRecargas, iIdUFOperadora);
	}
	catch(...)
	{
		setStatusCode("11W0002", "CONSULTA NAO EFETUADA");
		return;
	}

	xml_g->createTag("LSTTIPORECARVO");

	xml_g->addProp("xmlns", "creditos.tav.vivo.com.br");

	while(0 < listaTipoRecargas.size())
	{

		bTemTipoRecarga = true;

		oTipoRecargas = listaTipoRecargas.front();

		xml_g->createTag("TipoRecarga");

			xml_g->addItem("idTipoRecarga", oTipoRecargas.getIdTipoRecarga());
			xml_g->addItem("dsTipoRecarga", oTipoRecargas.getDsTipoRecarga());
			xml_g->addItem("dtValidade1", oTipoRecargas.getDtValidade1());
			xml_g->addItem("dtValidade2", oTipoRecargas.getDtValidade2());
			xml_g->addItem("vlTipoRecarga", oTipoRecargas.getVlTipoRecarga());

		xml_g->closeTag();

		listaTipoRecargas.pop_front();
	}

	xml_g->closeTag();

	// seta mensagem de retorno - header
    if(bTemTipoRecarga)
        setStatusCode("11I0000", "CONSULTA EFETUADA COM SUCESSO");
    else
        setStatusCode("11W0001", "NAO EXISTEM TIPOS DE RECARGA CADASTRADOS");

}