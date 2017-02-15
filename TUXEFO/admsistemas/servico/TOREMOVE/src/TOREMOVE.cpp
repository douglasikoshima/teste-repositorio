#define TOREMOVECPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(TOREMOVE);

void implTOREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implTOREMOVE::Execute()");
	CSafePointer oSafePointer;
	CTipoOrganizacao oTipoOrganizacao;
	
	char* cidTipoOrganizacao = oSafePointer.getTag(dnode,"idTipoOrganizacao",0);
	if( strlennull( cidTipoOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idTipoOrganizacao esta nulo");
		ULOG_END("implTOREMOVE::Execute()");
		return;
	}
	
	switch(oTipoOrganizacao.Delete(cidTipoOrganizacao))
	{
		case 0:
			setStatusCode("14E0000","Falha na execu��o do servi�o.");
		break;
		case -2292:
			oTipoOrganizacao.ListAll();
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14W0000","Este registro n�o pode ser exclu�do, pois cont�m depend�ncias.");
		break;
		default: 
			oTipoOrganizacao.ListAll();
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execu��o!"); 
		break;
	}
	ULOG_END("implTOREMOVE::Execute()");
}
