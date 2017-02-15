#define ORGREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COrg.h"
#include "../../../negocio/admatdCmm/include/COH.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"
#include "../../../negocio/admatdCmm/include/CTO.h"

DECLARE_TUXEDO_SERVICE(ORGREMOVE);

void implORGREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implORGREMOVE::Execute()");
	CSafePointer oSafePointer;
	COrganizacaoHierarquia oOrganizacaoHierarquia;
	COrganizacao oOrganizacao;
	CDepartamento oDepartamento;
	CTipoOrganizacao oTipoOrganizacao;

	int idTipoOrganizacao = 0;
	char cidTipoOrganizacao[21+1];
	
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	if( strlennull( cidOrganizacao ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacao está nulo");
		ULOG_END("implORGREMOVE::Execute()");
		return;
	}


	if (oDepartamento.RelacaoOU( cidOrganizacao ) >0)
	{
		// grava no log o cidOrganizacao
		ULOGI("implORGREMOVE::Execute::return (1)");
		setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		ULOG_END("implORGREMOVE::Execute()");
		return;
	}

	switch(oOrganizacaoHierarquia.Delete(cidOrganizacao))
	{
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
			ULOG_END("implORGREMOVE::Execute()");
			return;
		break;
		case -1: //Dependencia
			// grava no log o cidOrganizacao
			ULOGI("implORGREMOVE::Execute::return (2)");
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
			ULOG_END("implORGREMOVE::Execute()");
			return;
		case -2292:
			// grava no log o cidOrganizacao
			ULOGI("implORGREMOVE::Execute::return (3)");
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
			ULOG_END("implORGREMOVE::Execute()");
			return;
		break;
	}
	
	
	idTipoOrganizacao = oOrganizacao.Delete(cidOrganizacao);
	switch (idTipoOrganizacao )
	{
		case -1:
			// grava no log o cidOrganizacao
			ULOGI("implORGREMOVE::Execute::return (4)");
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		break;
		case 0:
			setStatusCode("14E0000","Falha na execução do serviço.");
		return;
		case -2292:
			// grava no log o cidOrganizacao
			ULOGI("implORGREMOVE::Execute::return (5)");
			setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
		break;
		default:
		{
			memset(&cidTipoOrganizacao,0x00, sizeof(cidTipoOrganizacao));
			sprintf(cidTipoOrganizacao,"%d", idTipoOrganizacao);
			switch(oTipoOrganizacao.Delete(cidTipoOrganizacao) )
			{
				case -1:
					// grava no log o cidOrganizacao
					ULOGI("implORGREMOVE::Execute::return (6)");
					setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
				break;
				case 0:
					setStatusCode("14E0000","Falha na execução do serviço.");
					ULOG_END("implORGREMOVE::Execute()");
				return;
				case -2292:	// Caso possua dependência em TipoOrganizacao, pode ser ignorado, pois não é um erro
					ULOGI("implORGREMOVE::Execute::return (7)");
//					setStatusCode("14W0000","Este registro não pode ser excluído, pois contém dependências.");
//				break;	
				default:
					setStatusCode("14I0000","Sucesso na execução do serviço!"); 
				break;
			}
		}
		break;
	}
	ULOG_END("implORGREMOVE::Execute()");
}
