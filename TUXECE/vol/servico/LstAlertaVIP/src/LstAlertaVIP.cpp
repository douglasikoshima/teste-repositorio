#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTALERTAVIP);

void implLSTALERTAVIP::Execute(DOMNode* dnode, XMLGen* xml_g)
{ 
	CTuxHelperClever helper;
	char*  pcTagXmlIn = NULL;
	CStatusCode	  statusCode;
	CLinha oLinha;
	int iNrLinha = 0;
	int iCdAreaRegistro = 0;

	pcTagXmlIn = helper.walkTree(dnode, "nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	pcTagXmlIn = helper.walkTree(dnode, "cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");


	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

	try{

		oLinha.consultarAlerta(xml_g);
		setStatusCode("11I0000", "Consulta efetuada com sucesso." );
	}
	catch ( ... )
	{	
		setStatusCode("11E0000", "Erro ao efetuar a consulta." );
	}

}