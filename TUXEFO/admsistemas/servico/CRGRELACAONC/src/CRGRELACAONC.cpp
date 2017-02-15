#define CRGRELACAONCCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGRELACAONC);

void implCRGRELACAONC::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGRELACAOCA::Execute()");
   
	CSafePointer oSafePointer;
	CCargo oCargo;
	char* cidNivel = oSafePointer.getTag(dnode,"idNivel",0);
	if( strlennull( cidNivel ) <= 0 )
	{
		setStatusCode("14E0000","idNivel esta nulo");
		return;
	}
	oCargo.RelacaoNC(cidNivel, "idNivel", "NivelCargoRelacionamentoVO", 
		"Relacionados", "CargoVO", "Existentes", "CargoVO", xml_g);
	setStatusCode("14I0099","Sucesso na execução do método RelacaoNC da Classe CCargo"); 
	
	ULOG_END("implCRGRELACAOCA::Execute()");
	
}
