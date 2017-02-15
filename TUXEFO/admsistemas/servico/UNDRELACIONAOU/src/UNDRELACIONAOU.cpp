#define UNDRELACIONAOUCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COU.h"

DECLARE_TUXEDO_SERVICE(UNDRELACIONAOU);

void implUNDRELACIONAOU::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDRELACIONAOU::Execute()");
	CSafePointer oSafePointer;
	COrganizacaoDepartamento oOrganizacaoDepartamento;
	
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao está nulo");
		ULOG_END("implUNDRELACIONAOU::Execute()");
		return;
	}
	char* cidUser = getUser();
	
	switch(oOrganizacaoDepartamento.RelacionaUnd(cidOrganizacao, "idOrganizacao", cidUser, dnode))
	{
		case 0: 
			setStatusCode("14E0000","Falha ao relacionar os registros."); 
		break;
		case -2292: 
			setStatusCode("14W0000","Problemas ao relacionar os registros."); 
		break;
		default: 
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	ULOG_END("implUNDRELACIONAOU::Execute()");
}
