#define CRGEDITARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGEDITAR);

void implCRGEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGEDITAR::Execute()");
   
	CSafePointer oSafePointer;
	CCargo oCargo;

	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);
	char* cnmCargo = oSafePointer.getTag(dnode,"nmCargo",0);
	if( strlennull( cidCargo ) <= 0 )
	{
		setStatusCode("14E0000","idCargo esta nulo");
		ULOG_END("implCRGEDITAR::Execute()");
		return;
	}
	if( strlennull( cnmCargo ) <= 0 )
	{
		setStatusCode("14E0000","nmCargo esta nulo");
		ULOG_END("implCRGEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	switch(oCargo.Update(cidCargo, cnmCargo, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implCRGEDITAR::Execute()");
			return;
		break;
		case -1:
			oCargo.ListAll();
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14W0000","Ja existe esta descrição ou registro, tente novamente com outra diferente.");
		break;
		default: 
			oCargo.ListAll();
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	ULOG_END("implCRGEDITAR::Execute()");
}
