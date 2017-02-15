#define ORGRELACIONAOUCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COU.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(ORGRELACIONAOU);

void implORGRELACIONAOU::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implORGRELACIONAOU::Execute()");
	CSafePointer oSafePointer;
	COrganizacaoDepartamento oOrganizacaoDepartamento;
	CDepartamento oDepartamento;
	
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao esta nulo");
		ULOG_END("implORGRELACIONAOU::Execute()");
		return;
	}
	char* cidUser = getUser();
	
	switch( oOrganizacaoDepartamento.RelacionaOrg(cidOrganizacao, "idOrganizacao", cidUser, dnode) )
	{
		case -1: 
			setStatusCode("14E0000","Falha ao relacionar os registros."); 
		break;
		case -2292: 
			oDepartamento.RelacaoOU(cidOrganizacao, "idOrganizacao", 
				"OrganizacaoUnidadeRelacionamentoVO", "Relacionados", 
				"UnidadeOrganogramaVO", "Existentes", "UnidadeOrganogramaVO", xml_g);
			setStatusCode("14W0000","Esta organização não pode ser excluída pois contém departamentos relacionados."); 
		break;
		default: 
			oDepartamento.RelacaoOU(cidOrganizacao, "idOrganizacao", 
				"OrganizacaoUnidadeRelacionamentoVO", "Relacionados", 
				"UnidadeOrganogramaVO", "Existentes", "UnidadeOrganogramaVO", xml_g);
			setStatusCode("14I0000","Sucesso na execução!"); 
		break;
	}
	ULOG_END("implORGRELACIONAOU::Execute()");
}
