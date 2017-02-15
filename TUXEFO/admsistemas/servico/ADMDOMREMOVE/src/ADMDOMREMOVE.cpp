#define ADMDOMREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDomTbl.h"

DECLARE_TUXEDO_SERVICE(ADMDOMREMOVE);

void implADMDOMREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMREMOVE::Execute()");
	CSafePointer oSafePointer;
	CTabelaDominio oCDominio;
	char *cidTabelaDominio = NULL;
	char *cidUser = NULL;
	int sql = 0;
	cidTabelaDominio = oSafePointer.getTag( dnode, "idTabelaDominio", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	if(cidTabelaDominio != NULL)
	{
		sql = oCDominio.Delete(cidTabelaDominio);
		if(sql == 1)
		{
			setStatusCode( "00I0000", "Sucesso" );
		}
		else
		if(sql == -2)
		{
			setStatusCode( "00W0002", "Registro possui depend�ncia(s)" );
		}
		else
		{
			setStatusCode( "00W0003", "N�o foi poss�vel remover dado(s)" );
		}
	}
	else
	{
		setStatusCode( "00W0004", "Par�metro(s) de entrada inv�lido(s)" );
	}
	
	ULOG_END("implADMDOMREMOVE::Execute()");
	
}
