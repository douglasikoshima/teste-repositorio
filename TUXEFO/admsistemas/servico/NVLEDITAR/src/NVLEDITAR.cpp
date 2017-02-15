#define NVLEDITARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"

DECLARE_TUXEDO_SERVICE(NVLEDITAR);

void implNVLEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLEDITAR::Execute()");
	CSafePointer oSafePointer;
	CNivel oNivel;
	
	char* cidNivel = oSafePointer.getTag(dnode,"idNivel",0);
	char* cdsNivel = oSafePointer.getTag(dnode,"dsNivel",0);
	if( strlennull( cidNivel ) <= 0 )
	{
		setStatusCode("14E0000","idNivel está nulo");
		ULOG_END("implNVLEDITAR::Execute()");
		return;
	}
	if( strlennull( cdsNivel ) <= 0 )
	{
		setStatusCode("14E0000","dsNivel está nulo");
		ULOG_END("implNVLEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	switch(oNivel.Update(cidNivel, cdsNivel, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Erro durante a execução do serviço.");
		break;

		case -1:	
			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
		break;

		default: 
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	ULOG_END("implNVLEDITAR::Execute()");
	return;
}
