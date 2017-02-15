#define TOINSERIRCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(TOINSERIR);

void implTOINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implTOINSERIR::Execute()");
   
	CSafePointer oSafePointer;
	CTipoOrganizacao oTipoOrganizacao;
	
	
	char* cdsTipoOrganizacao = oSafePointer.getTag(dnode,"dsTipoOrganizacao",0);
	
	if( strlennull( cdsTipoOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","dsTipoOrganizacao está nulo");
		ULOG_END("implTOINSERIR::Execute()");
		return;
	}
	
	char* cidUsuarioAlteracao = getUser();
	
	switch(oTipoOrganizacao.Insert( cdsTipoOrganizacao, cidUsuarioAlteracao))
	{
		case 0:	
			ULOGI("TOINSERT - 0");
			setStatusCode("14E0000","Falha na execução do serviço.");
			break;
		case 1:
			ULOGI("TOINSERT - 1");
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14I0000","Serviço finalizado com sucesso");
			break;
		case 2:
			ULOGI("TOINSERT - 2");
			oTipoOrganizacao.GetXml("ListaTipoOrganizacaoVO","TipoOrganizacaoVO",xml_g); 
			setStatusCode("14W0001","Registro já existe.");
			break;
		default: 
			ULOGI("TOINSERT - default");
			setStatusCode("14E0001","Erro inesperado"); 
		break;
	}
	
	ULOG_END("implTOINSERIR::Execute()");
}
