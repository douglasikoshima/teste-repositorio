#define TOLISTAPARCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(TOLISTAPAR);

void implTOLISTAPAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implTOLISTAPAR::Execute()");
	CSafePointer oSafePointer;
	CTipoOrganizacao oTipoOrganizacao;
	char* cidTipoOrganizacao = oSafePointer.getTag(dnode,"idTipoOrganizacao",0);
	char* cdsTipoOrganizacao = oSafePointer.getTag(dnode,"dsTipoOrganizacao",0);
	char* cidUsuarioAlteracao = getUser();
	
	oTipoOrganizacao.List(cidTipoOrganizacao, cdsTipoOrganizacao, cidUsuarioAlteracao);
	oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
	setStatusCode("14I0000","Sucesso na execução do método List da Classe CTipoOrganizacao"); 
	ULOG_END("implTOLISTAPAR::Execute()");
}
