#define UNDREMOVECPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(UNDREMOVE);

void implUNDREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDREMOVE::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;
	
	char* cidDepartamento = oSafePointer.getTag(dnode,"idUnidade",0);
	if( strlennull( cidDepartamento ) <= 0 )
	{
		setStatusCode("14E0000","idDepartamento est� nulo");
		ULOG_END("implUNDREMOVE::Execute()");
		return;
	}
	
	switch(oDepartamento.Delete(cidDepartamento))
	{
		case 0:
			setStatusCode("14E0000","Falha na execu��o do servi�o.");
		break;
		case -2292:
			oDepartamento.ListAll();
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 				 
			setStatusCode("14W0000","Este registro n�o pode ser exclu�do, pois cont�m depend�ncias.");
		break;
		default: 
			oDepartamento.ListAll();
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 				 
			setStatusCode("14I0000","Sucesso na execu��o do servi�o."); 
		break;
	}
	ULOG_END("implUNDREMOVE::Execute()");
}
