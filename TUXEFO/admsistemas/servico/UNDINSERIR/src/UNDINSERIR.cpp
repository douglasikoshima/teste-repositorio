#define UNDINSERIRCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(UNDINSERIR);

void implUNDINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDINSERIR::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;
	
	char* cnmDepartamento = oSafePointer.getTag(dnode,"nmUnidade",0);
	if( strlennull( cnmDepartamento ) <= 0 )
	{
		setStatusCode("14E0000","nmDepartamento esta nulo");
		ULOG_END("implUNDINSERIR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	

	// Insere e lista apenas o item inserido, conforme incid�ncia 2604
	switch(oDepartamento.InsertList( cnmDepartamento, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execu��o do servi�o.");
		break;
		case -1:
			oDepartamento.ListAll();
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 
			setStatusCode("14W0000","J� existe esta descri��o ou registro, tente novamente com outra diferente.");
		break;
		default: 
			oDepartamento.GetXml("ListaUnidadeVO","UnidadeOrganogramaVO",xml_g); 
			setStatusCode("14I0000","Falha na execu��o do servi�o."); 
		break;
	}
	ULOG_END("implUNDINSERIR::Execute()");
}
