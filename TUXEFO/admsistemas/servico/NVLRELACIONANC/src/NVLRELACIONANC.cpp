#define NVLRELACIONANCCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNC.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"
#include "../../../negocio/admatdCmm/include/CHUP.h"
#include "../../../negocio/admatdCmm/include/CPss.h"

DECLARE_TUXEDO_SERVICE(NVLRELACIONANC);

void implNVLRELACIONANC::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLRELACIONANC::Execute()");
	CSafePointer oSafePointer;
	CNivelCargo oNivelCargo;
	CCargo oCargo;	
	
	char* cidNivel = oSafePointer.getTag(dnode,"idNivel",0);
	if( strlennull( cidNivel ) <= 0 )
	{
		setStatusCode("14E0000","idNivel está nulo");
		ULOG_END("implNVLRELACIONANC::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();

	switch(oNivelCargo.RelacionaNvl(cidNivel, "idNivel", cidUsuarioAlteracao, dnode))
	{
		case -1: 
			setStatusCode("14E0000","Falha ao relacionar os registros."); 
		break;
		case -2292: 
			oCargo.RelacaoNC(cidNivel, "idNivel", "NivelCargoRelacionamentoVO", 
				"Relacionados", "CargoVO", "Existentes", "CargoVO", xml_g);
			setStatusCode("14W0000","Este nível não pode ser excluído pois contém cargos relacionados."); 
		break;
		default: 
			oCargo.RelacaoNC(cidNivel, "idNivel", "NivelCargoRelacionamentoVO", 
				"Relacionados", "CargoVO", "Existentes", "CargoVO", xml_g);
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	
	ULOG_END("implNVLRELACIONANC::Execute()");
	
}
