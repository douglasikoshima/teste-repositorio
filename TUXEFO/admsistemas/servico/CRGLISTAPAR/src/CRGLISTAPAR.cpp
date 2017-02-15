#define CRGLISTAPARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGLISTAPAR);

void implCRGLISTAPAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGLISTAPAR::Execute()");
	CSafePointer oSafePointer;
	CCargo oCargo;
	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);
	char* cnmCargo = oSafePointer.getTag(dnode,"nmCargo",0);
	char* cidUsuarioAlteracao = getUser();
	
	oCargo.List(cidCargo, cnmCargo, cidUsuarioAlteracao);
	oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
	setStatusCode("14I0000","Sucesso na execução do método List da Classe CCargo"); 
	ULOG_START("implCRGLISTAPAR::Execute()");
}
