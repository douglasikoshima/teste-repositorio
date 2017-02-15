#define NVLRELACAONCCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(NVLRELACAONC);

void implNVLRELACAONC::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLRELACAONC::Execute()");
	CSafePointer oSafePointer;
	CCargo oCargo;
	
	char* cidNIvelCargo = oSafePointer.getTag(dnode,"idNivel",0);
	if( strlennull( cidNIvelCargo ) <= 0 )
	{
		setStatusCode("14E0000","idNIvelCargo está nulo");
		ULOG_END("implNVLRELACAONC::Execute()");
		return;
	}
	
    xml_g->createTag("ListaCargoPorNivelVO");
	//Adiciona a propriedade necessaria para o xml
	xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
	//Adiciona as tags necessarias
	xml_g->addItem("idNivel", cidNIvelCargo );

	    switch(oCargo.ListCargoPorIdNivel(cidNIvelCargo))
	    {
		    case -1:	
				setStatusCode("14E0000","Problemas no método List da Classe CCargo");
		    break;
		    default: 
				oCargo.GetXml("CargoVO",xml_g); 
				setStatusCode("14I0099","Sucesso na execução do método List da Classe CCargo"); 
		    break;
	    }

    xml_g->closeTag();	
    
    ULOG_END("implNVLRELACAONC::Execute()");
	
	return;
}
