#define CRGRELACAOCACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(CRGRELACAOCA);

void implCRGRELACAOCA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGRELACAOCA::Execute()");
   
	CSafePointer oSafePointer;
	CCargo oCargo;	
	char* cidAtribuicao = oSafePointer.getTag(dnode,"idAtividade",0);
	if( strlennull( cidAtribuicao ) <= 0 )
	{
		setStatusCode("14E0000","idAtribuicao esta nulo");
		ULOG_END("implCRGRELACAOCA::Execute()");
		return;
	}
	
	oCargo.RelacaoCA(cidAtribuicao, "idAtividade", "CargoAtividadeRelacionamentoVO", 
		"Relacionados", "CargoVO", "Existentes", "CargoVO", xml_g);
	setStatusCode("14I0099","Sucesso na execução do método RelacaoCA da Classe CCargo"); 
	
	ULOG_END("implCRGRELACAOCA::Execute()");
	
}
