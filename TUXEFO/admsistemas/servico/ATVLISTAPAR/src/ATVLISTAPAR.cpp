#define ATVLISTAPARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CAtv.h"

DECLARE_TUXEDO_SERVICE(ATVLISTAPAR);

void implATVLISTAPAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implATVLISTAPAR::Execute()");
	CSafePointer oSafePointer;
	CAtribuicao oAtribuicao;
	char* cidAtribuicao = oSafePointer.getTag(dnode,"idAtividade",0);
	char* cnmAtribuicao = oSafePointer.getTag(dnode,"nmAtividade",0);
	char* cidUsuarioAlteracao = getUser();
	
 	oAtribuicao.List(cidAtribuicao, cnmAtribuicao, cidUsuarioAlteracao);
	oAtribuicao.GetXml("ListaAtividadeVO","AtividadeVO",xml_g); 
	setStatusCode("14I0000","Sucesso na execução do método List da Classe CAtribuicao"); 
	ULOG_END("implATVLISTAPAR::Execute()");
}
