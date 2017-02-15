#define NVLREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"
#include "../../../negocio/admatdCmm/include/CNH.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(NVLREMOVE);

void implNVLREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLREMOVE::Execute()");
	CSafePointer oSafePointer;
	CNivelHierarquia oNivelHierarquia;
	CNivel oNivel;
	CCargo oCargo;
	
	char* cidNivel = oSafePointer.getTag(dnode,"idNivel",0);
	if( strlennull( cidNivel ) <= 0 )
	{
		setStatusCode("14E0000","idNivel está nulo");
		ULOG_END("implNVLREMOVE::Execute()");
		return;
	}

	if (oCargo.RelacaoNC( cidNivel ) > 0)
	{
		setStatusCode("14W0000","Existem cargos associados");
		ULOG_END("implNVLREMOVE::Execute()");
		return;
	}

	switch(oNivelHierarquia.Delete(cidNivel))
	{
		case -2292://Foreign Key
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
			ULOG_END("implNVLREMOVE::Execute()");
			return;
		case -1403://Record Not Found
			setStatusCode("14W0000","Este registro não pode ser excluído, pois o registro não foi encotrado.");
			break;
		case -1: //Dependencia
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
			ULOG_END("implNVLREMOVE::Execute()");
			return;
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implNVLREMOVE::Execute()");
			return;
		break;
	}
	
	switch(oNivel.Delete(cidNivel))
	{
		case -1: //Dependencia
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
			break;
		case -2292://Foreign Key
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		break;
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implNVLREMOVE::Execute()");
			return;
		default: 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implNVLREMOVE::Execute()");
}
