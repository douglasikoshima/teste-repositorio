#define CRGRELACIONACACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCA.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(CRGRELACIONACA);

void implCRGRELACIONACA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implCRGRELACIONACA::Execute()");
	CSafePointer oSafePointer;
	CCargoAtribuicao oCargoAtribuicao;
	CAtribuicao oAtribuicao;

	char* cidCargo = oSafePointer.getTag(dnode,"idCargo",0);
	if( strlennull( cidCargo ) <= 0 )
	{
		setStatusCode("14E0000","idCargo esta nulo");
		ULOG_END("implCRGRELACIONACA::Execute()");
		return;
	}
	char* cidUser = getUser();

	switch(oCargoAtribuicao.RelacionaCrg(cidCargo, "idCargo", cidUser, dnode))
	{
		case -1: 
			setStatusCode("14E0000","Falha ao relacionar os registros."); 
		break;
		case -2292: 
			oAtribuicao.RelacaoCA(cidCargo, "idCargo", "CargoAtividadeRelacionamentoVO", 
				"Relacionados", "AtividadeVO", "Existentes", "AtividadeVO", xml_g);
			setStatusCode("14W0000","Este cargo não pode ser excluído pois contém atividades relacionadas."); 
		break;
		default: 
			oAtribuicao.RelacaoCA(cidCargo, "idCargo", "CargoAtividadeRelacionamentoVO", 
				"Relacionados", "AtividadeVO", "Existentes", "AtividadeVO", xml_g);
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	ULOG_END("implCRGRELACIONACA::Execute()");
}
