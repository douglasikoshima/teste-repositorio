#define CRGREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGREMOVE);

void implCRGREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGREMOVE::Execute()");
	CSafePointer oSafePointer;
	CCargo oCargo;
	
	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);
	if( strlennull( cidCargo ) <= 0 )
	{
		setStatusCode("14E0000","idCargo esta nulo");
		ULOG_END("implCRGREMOVE::Execute()");
		return;
	}
	
	switch(oCargo.Delete(cidCargo))
	{
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
		break;
		case -2292:
			oCargo.ListAll();
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		break;
		default: 
			oCargo.ListAll();
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implCRGREMOVE::Execute()");
}
