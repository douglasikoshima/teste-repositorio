#define CRGRELACIONANCCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNC.h"

DECLARE_TUXEDO_SERVICE(CRGRELACIONANC);

void implCRGRELACIONANC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCRGRELACIONANC::Execute()");
	CSafePointer oSafePointer;
	CNivelCargo oNivelCargo;

	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);
	if( strlennull( cidCargo ) <= 0 )
	{
		setStatusCode("14E0000","idCargo esta nulo");
		ULOG_END("implCRGRELACIONANC::Execute()");
		return;
	}
	char* cidUser = getUser();

	switch(oNivelCargo.RelacionaCrg(cidCargo, "idCargo", cidUser, dnode)==0)
	{
		case -1:
			setStatusCode("14E0000","Falha ao relacionar os registros.");
		break;
		case -2292:
			setStatusCode("14W0000","Problemas ao relacionar os registros.");
		break;
		default:
			setStatusCode("14I0000","Sucesso na execução!");
		break;
	}
	setStatusCode("14I0099","Sucesso na execução do método RelacionaCrg da Classe CNivelCargo");
	ULOG_END("implCRGRELACIONANC::Execute()");
}
