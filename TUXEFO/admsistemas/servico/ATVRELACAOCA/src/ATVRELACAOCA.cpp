#define ATVRELACAOCACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVRELACAOCA);

void implATVRELACAOCA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVRELACAOCA::Execute()");
	CSafePointer oSafePointer;
	CAtribuicao oAtribuicao;	
	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);	
	if( strlennull( cidCargo ) <= 0 )
	{
		setStatusCode("14E0000","idCargo esta nulo");
		return;
	}
	oAtribuicao.RelacaoCA(cidCargo, "idCargo", "CargoAtividadeRelacionamentoVO", 
		"Relacionados", "AtividadeVO", "Existentes", "AtividadeVO", xml_g);
	setStatusCode("14I0000","Sucesso na execução do método RelacaoCA da Classe CAtribuicao"); 
	ULOG_END("implATVRELACAOCA::Execute()");
}
