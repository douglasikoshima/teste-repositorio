#define CRGINSERIRCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGINSERIR);

void implCRGINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGINSERIR::Execute()");
	CSafePointer oSafePointer;
	CCargo oCargo;
	
	
	char* cnmCargo = oSafePointer.getTag(dnode,"nmCargo",0);
	if( strlennull( cnmCargo ) <= 0 )
	{
		setStatusCode("14E0000","nmCargo esta nulo");
		ULOG_END("implCRGINSERIR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	// Insere o Item e retorna apenas este item inserido, conforme incidência 2600
	switch(oCargo.InsertList( cnmCargo, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implCRGINSERIR::Execute()");
			return;
		break;
		case -1:
			oCargo.ListAll();
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14W0000","Ja existe esta descrição ou registro, tente novamente com outra diferente.");
			break;
		default: 
			oCargo.GetXml("ListaCargoVO","CargoVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implCRGINSERIR::Execute()");
}
