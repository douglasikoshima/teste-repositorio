#define UNDLISTAPARCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(UNDLISTAPAR);

void implUNDLISTAPAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDLISTAPAR::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;
	char* cidDepartamento = oSafePointer.getTag(dnode,"idUnidade",0);
	char* cnmDepartamento = oSafePointer.getTag(dnode,"nmUnidade",0);
	char* cidUsuarioAlteracao = getUser();
	
	oDepartamento.List(cidDepartamento, cnmDepartamento, cidUsuarioAlteracao);
	oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 
	setStatusCode("14I0099","Sucesso na execucao do metodo List da Classe CDepartamento"); 
	ULOG_END("implUNDLISTAPAR::Execute()");
}
