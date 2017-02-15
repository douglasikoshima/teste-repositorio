#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Parametro/Parametro.hpp>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

static char *STR_11W0004						= "11W0004";

DECLARE_TUXEDO_SERVICE(GETLINHAVOZ);

void implGETLINHAVOZ::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	int			iCdAreaRegistro = -1;
 	int			iNrLinha = -1;	
	int			iIdTipoRelacionamento = -1;
	char*       pcTagXmlIn = NULL;
	CTuxHelperClever helper;
	CLinha           oLinha;


	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20E0000", "TAG_cdAreaRegistro_INEXISTENTE");
	}
	iCdAreaRegistro =  atoi(pcTagXmlIn);
	
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20E0000", "TAG_nrLinha_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20E0000", "TAG_nrLinha_VALOR_VAZIO");
	}

	iNrLinha =  atoi(pcTagXmlIn);


	pcTagXmlIn = helper.walkTree(dnode, "idTipoRelacionamento", 0);

	if (pcTagXmlIn == NULL) {
		throw new TuxBasicSvcException("20E0000", "TAG_idTipoRelacionamento_INEXISTENTE");
	}
	if (!*pcTagXmlIn) {
		throw new TuxBasicSvcException("20E0000", "TAG_idTipoRelacionamento_VALOR_VAZIO");
	}

	iIdTipoRelacionamento =  atoi(pcTagXmlIn);
		 
	try{
		oLinha.consultarLinhaVoz(iCdAreaRegistro, iNrLinha, iIdTipoRelacionamento);
	}

	catch ( ... )
	{		
		setStatusCode("11W0000", "LINHA DE VOZ NÃO ENCONTRADA");
		return;
	}



	xml_g->createTag("GETLINHAVOZVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");

		xml_g->addItem("nrLinha", oLinha.getNrLinha());
		xml_g->addItem("cdAreaRegistro", oLinha.getCdAreaRegistro());

	xml_g->closeTag();

	// Execução OK.
	//INFORMATION(NRO_OK);
	setStatusCode("11I0000", "LINHA ENCONTRADA");

}