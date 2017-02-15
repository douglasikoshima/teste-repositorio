#define ADMCLAREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmpCls.h"

DECLARE_TUXEDO_SERVICE(ADMCLAREMOVE);

void implADMCLAREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCLAREMOVE::Execute()");
   
	CSafePointer oSafePointer;
	CCampoClassificador oCCampoClassificador;
	char *cidClassificadorCampo = NULL;
	char* cidUser = NULL;
	int sql = 0;
	cidClassificadorCampo = oSafePointer.getTag( dnode, "idClassificadorCampo", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );	
	if(cidClassificadorCampo != NULL)
	{		
		sql = oCCampoClassificador.Delete(cidClassificadorCampo);
		if(sql == 1)
		{				
			setStatusCode( "00I0000", "Sucesso" );
		}
		else
		if(sql == -2)
		{
			setStatusCode( "00W0001", "Registro possui dependência(s)" );
		}
		else
		{
			setStatusCode( "00W0002", "Não foi possível remover dado(s)" );
		}
	}
	else
	{
		setStatusCode( "00W0003", "Parâmetro(s) de entrada inválido(s)" );
	}
	
   ULOG_END("implADMCLAREMOVE::Execute()");
}
