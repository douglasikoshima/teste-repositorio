#define ORGEDITARCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COrg.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(ORGEDITAR);

void implORGEDITAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implORGEDITAR::Execute()");
	CSafePointer oSafePointer;
	COrganizacao oOrganizacao;
	CTipoOrganizacao oTipoOrganizacao;
	
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao está nulo");
		ULOG_END("implORGEDITAR::Execute()");
		return;
	}
	char* cdsTipoOrganizacao = oSafePointer.getTag(dnode,"dsOrganizacao",0);
	if( strlennull( cdsTipoOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","dsOrganizacao está nulo");
		ULOG_END("implORGEDITAR::Execute()");
		return;
	}
	char* cidUsuarioAlteracao = getUser();

	char cidTipoOrganizacao[21+1];
	switch(oTipoOrganizacao.Update(	cidOrganizacao, cdsTipoOrganizacao, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Erro na execução do serviço.");
			ULOG_END("implORGEDITAR::Execute()");
			return;
			break;
		case -1:
			setStatusCode("14W0000","Não pode haver nomes repetidos em toda a estrutura.");
			ULOG_END("implORGEDITAR::Execute()");
			return; 
			break;
		default: 
			strcpy(cidTipoOrganizacao,oTipoOrganizacao.Registro(0)->cidTipoOrganizacao);
		
	}

	switch(oOrganizacao.Update(	cidOrganizacao, cidTipoOrganizacao, cidUsuarioAlteracao))
	{
		case 0:	
			setStatusCode("14E0000","Falha na execução do serviço.");
		break;
		case -1:	
			setStatusCode("14W0000","Já existe esta descrição ou registro, tente novamente com outra diferente.");
		break;
		default: 
			setStatusCode("14I0000","Sucesso na execução do serviço!"); 
		break;
	}
	ULOG_END("implORGEDITAR::Execute()");
}
