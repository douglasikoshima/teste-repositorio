#define ATVRELACIONACACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCA.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVRELACIONACA);

void implATVRELACIONACA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVRELACIONACA::Execute()");
	CSafePointer oSafePointer;
	CCargoAtribuicao oCargoAtribuicao;
	char* cidAtribuicao = oSafePointer.getTag(dnode,"idAtividade",0);
	CAtribuicao oAtribuicao;
	char* cidAtividade = oSafePointer.getTag(dnode,"idAtividade",0);
	char* cidUser = getUser();
	
	switch(oCargoAtribuicao.RelacionaAtv(cidAtribuicao, "idAtividade", cidUser, dnode))
	{
		case 0: 
			setStatusCode("14E0000","Falha na execucao do metodo Relaciona."); 
		break;
		case -2292: 
			oAtribuicao.RelacaoCA(cidAtividade, "idAtividade", "CargoAtividadeRelacionamentoVO", 
				"Relacionados", "AtividadeVO", "Existentes", "AtividadeVO", xml_g);
			setStatusCode("14W0000","Existem registros relacionados"); 
		break;
		default: 
			oAtribuicao.RelacaoCA(cidAtividade, "idAtividade", "CargoAtividadeRelacionamentoVO", 
				"Relacionados", "AtividadeVO", "Existentes", "AtividadeVO", xml_g);
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}	
	ULOG_END("implATVRELACIONACA::Execute()");
}
