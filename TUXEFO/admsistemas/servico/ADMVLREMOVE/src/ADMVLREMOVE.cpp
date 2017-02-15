#define ADMVLREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDom.h"

DECLARE_TUXEDO_SERVICE(ADMVLREMOVE);

void implADMVLREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMVLREMOVE::Execute()");
	CSafePointer oSafePointer;
	CDominio oCDominio;
	char *cidDominio = NULL;
	cidDominio = oSafePointer.getTag(dnode,"idDominio");
	int sql = 0;
	sql = oCDominio.Delete(cidDominio);
	if(sql > 0)
	{
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00W0001","Registro possui dependência(s)");
	}	
	xml_g->closeTag();
	
	ULOG_END("implADMVLREMOVE::Execute()");
	
}
