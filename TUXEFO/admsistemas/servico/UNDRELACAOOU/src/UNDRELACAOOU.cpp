#define UNDRELACAOOUCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(UNDRELACAOOU);

void implUNDRELACAOOU::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implUNDRELACAOOU::Execute()");
	CSafePointer oSafePointer;
	CDepartamento oDepartamento;
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao está nulo");
		ULOG_END("implUNDRELACAOOU::Execute()");
		return;
	}
	oDepartamento.RelacaoOU(cidOrganizacao, "idOrganizacao", 
		"OrganizacaoUnidadeRelacionamentoVO", "Relacionados", 
		"UnidadeOrganogramaVO", "Existentes", "UnidadeOrganogramaVO", xml_g);
	setStatusCode("14I0000","Sucesso na execução do método RelacaoOU da Classe CDepartamento"); 
	ULOG_END("implUNDRELACAOOU::Execute()");
}
