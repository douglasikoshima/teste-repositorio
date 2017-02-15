#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Defines/Defines.h>

DECLARE_TUXEDO_SERVICE(ENVALERTAVIP);


void implENVALERTAVIP::Execute(DOMNode* dnode, XMLGen* xml_g)
{
	int		iNrLinha			= 0;
	int		iCdAreaRegistro 	= 0;
	char	cDtAlerta[256];
	char	cDsLoja[256];
	CLinha	oLinha;
	char	cDtVisitaLoja[256];
	char*	pcTagXmlIn = NULL;
	char    operacao[10];
	CTuxHelperClever helper;

	pcTagXmlIn = helper.walkTree(dnode, "nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode, "cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	pcTagXmlIn = helper.walkTree(dnode, "dsLoja", 0);
	if (pcTagXmlIn != NULL)
		strcpy(cDsLoja, pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode, "dtVisitaLoja", 0);
	if (pcTagXmlIn != NULL)
		strcpy(cDtVisitaLoja, pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode, "operacao", 0);
	if (pcTagXmlIn != NULL)
		strcpy(operacao, pcTagXmlIn);
	
	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

	try{

		oLinha.enviaAlertaVIP(cDsLoja, operacao, cDtVisitaLoja, this->getUser());

	}
	catch( ... )
	{	}

	setStatusCode("11I0000", "MENSAGEM ENVIADA COM SUCESSO");

}