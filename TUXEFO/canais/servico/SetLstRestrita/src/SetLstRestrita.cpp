#include <stdio.h>
#include <tuxfw.h>
#define   COD_ERR_EXCEPT "13E0000"
#include "../../negocio/Linha/include/Linha/Linha.hpp"
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"


DECLARE_TUXEDO_SERVICE(SETLSTRESTRITA);


void implSETLSTRESTRITA::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;
	CLinha oLinha;
	char* pcTagXmlIn = NULL;
	int iNrLinha = 0;
	int iCdAreaRegistro = 0;
	int iCdBloqueio = 0;
	bool bBloqueia = false;


	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);

	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");
	
	oLinha.setNrLinha(iNrLinha);
	
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);

	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	oLinha.setCdAreaRegistro(iCdAreaRegistro);

	pcTagXmlIn = helper.walkTree(dnode,"cdBloqueio", 0);

	ASSERT_INT(iCdBloqueio, pcTagXmlIn, "cdAreaRegistro");

	if (oLinha.consultarLinha() ==  false)	
	{
		setStatusCode("13W0007", "Linha inexistente.");

		return;
	}
	else
	{
		pcTagXmlIn = helper.walkTree(dnode,"cdBloqueio", 0);
	
		bool bLinhaBloqueada = oLinha.consultarLinhaListaRestrita();

		if (bLinhaBloqueada && iCdBloqueio == 1)  //Linha já bloqueada		
		{
			setStatusCode("13W0005", "Linha já bloqueada.");
			return;
		}
		else if (bLinhaBloqueada == false && iCdBloqueio == 0)  //Linha já desbloqueada			
		{
			setStatusCode("13W0006", "Linha já desbloqueada.");
			return;
		}
		
		if (!strcmp(pcTagXmlIn, "1"))		
			bBloqueia = true;
		
		if (bBloqueia == true)		
			oLinha.insereLstRestrita();

		else			
			oLinha.removeLstRestrita();


		try{
			 oLinha.EnviaSMS (bBloqueia, getUser());	
		}

		catch ( ... ) {}


		 setStatusCode("13I0000", "Alteração efetuada com sucesso.");

	}
}