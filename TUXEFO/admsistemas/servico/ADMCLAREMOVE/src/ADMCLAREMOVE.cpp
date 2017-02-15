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
			setStatusCode( "00W0001", "Registro possui depend�ncia(s)" );
		}
		else
		{
			setStatusCode( "00W0002", "N�o foi poss�vel remover dado(s)" );
		}
	}
	else
	{
		setStatusCode( "00W0003", "Par�metro(s) de entrada inv�lido(s)" );
	}
	
   ULOG_END("implADMCLAREMOVE::Execute()");
}
