#define UNDEDITARCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(UNDEDITAR);

void implUNDEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDEDITAR::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;
	
	
	char* cidDepartamento = oSafePointer.getTag(dnode,"idUnidade",0);
	char* cnmDepartamento = oSafePointer.getTag(dnode,"nmUnidade",0);
	if( strlennull( cidDepartamento ) <= 0 )
	{
		setStatusCode("14E0000","idDepartamento está nulo");
		ULOG_END("implUNDEDITAR::Execute()");
		return;
	}
	if( strlennull( cnmDepartamento ) <= 0 )
	{
		setStatusCode("14E0000","nmDepartamento está nulo");
		ULOG_END("implUNDEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	switch(oDepartamento.Update(cidDepartamento, cnmDepartamento, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implUNDEDITAR::Execute()");
			return;
		break;
		case -1:
			oDepartamento.ListAll();
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 
			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
			break;
		default: 
			oDepartamento.ListAll();
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implUNDEDITAR::Execute()");
}
