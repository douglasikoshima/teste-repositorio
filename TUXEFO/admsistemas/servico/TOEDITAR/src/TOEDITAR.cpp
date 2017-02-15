#define TOEDITARCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(TOEDITAR);

void implTOEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implTOEDITAR::Execute()");
	CSafePointer oSafePointer;
	CTipoOrganizacao oTipoOrganizacao;
	
	char* cidTipoOrganizacao = oSafePointer.getTag(dnode,"idTipoOrganizacao",0);
	char* cdsTipoOrganizacao = oSafePointer.getTag(dnode,"dsTipoOrganizacao",0);
	if( strlennull( cidTipoOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idTipoOrganizacao est� nulo");
		ULOG_END("implTOEDITAR::Execute()");
		return;
	}
	if( strlennull( cdsTipoOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","dsTipoOrganizacao est� nulo");
		ULOG_END("implTOEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();
	
	switch(oTipoOrganizacao.UpdateDescricao(cidTipoOrganizacao, cdsTipoOrganizacao, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execu��o do servi�o.");
			ULOG_END("implTOEDITAR::Execute()");
			return;
		break;
		case -1:
			oTipoOrganizacao.ListAll();
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14W0000","J� existe esta descri��o ou registro, tente novamente com outra diferente.");
			break;
		default: 
			oTipoOrganizacao.ListAll();
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14I0000","Sucesso na execu��o do servi�o!"); 
		break;
	}
	ULOG_END("implTOEDITAR::Execute()");
}
