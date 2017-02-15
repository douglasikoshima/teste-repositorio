#define ORGINSERIRCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COrg.h"
#include "../../../negocio/admatdCmm/include/CTO.h"
#include "../../../negocio/admatdCmm/include/COH.h"

DECLARE_TUXEDO_SERVICE(ORGINSERIR);

void implORGINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implORGINSERIR::Execute()");
	CSafePointer oSafePointer;
	COrganizacaoHierarquia oOrganizacaoHierarquia;
	COrganizacao oOrganizacao;
	CTipoOrganizacao oTipoOrganizacao;
	
	char* cidOrganizacao = oSafePointer.getTag(dnode,"idOrganizacao",0);
	char* cidOrganizacaoPai = oSafePointer.getTag(dnode,"idOrganizacaoPai",0);
	char* cidTipoOrganizacao = oSafePointer.getTag(dnode,"idTipoOrganizacao",0);
	char* cdsTipoOrganizacao = oSafePointer.getTag(dnode,"dsOrganizacao",0);
	char* cidUsuarioAlteracao = getUser();
	int   iNomeDiferente = 0;

	ULOGI("ORGINSERIR - Entrou [%s] [%s] [%s] [%s] [%s]",
		cidOrganizacao,
		cidOrganizacaoPai,
		cidTipoOrganizacao,
		cdsTipoOrganizacao,
		cidUsuarioAlteracao);

	if( strlennull( cidOrganizacaoPai ) <= 0 )
	{
		setStatusCode("14E0000","idOrganizacaoPai está nulo");
		ULOG_END("implORGINSERIR::Execute()");
		return;
	}	

	if( strlennull( cidTipoOrganizacao ) <= 0 )
	{
		if( strlennull( cdsTipoOrganizacao ) <= 0 )
		{
			setStatusCode("14E0000","dsOrganizacao e idTipoOrganizacao estão nulos");
			ULOG_END("implORGINSERIR::Execute()");
			return;
		} 

		oTipoOrganizacao.Insert( cdsTipoOrganizacao, cidUsuarioAlteracao );
		cidTipoOrganizacao = oTipoOrganizacao.Registro(0)->cidTipoOrganizacao;
		//Verifica se o nome digitado é igual ao achado na base
		if( strcmp( oTipoOrganizacao.Registro(0)->cdsTipoOrganizacao, cdsTipoOrganizacao ) != 0 )
			iNomeDiferente = 1;
	}

	int iResultado = oOrganizacao.Insert( cidTipoOrganizacao, cidOrganizacaoPai, cidUsuarioAlteracao);

	ULOGI("ORGINSERIR - iResultado %d",iResultado);

	if( iResultado == -1 )
		setStatusCode("14W0000","Não pode haver nomes repetidos sob o mesmo nível.");
	else
		if( iResultado )
		{
			cidOrganizacao = oOrganizacao.Registro(0)->cidOrganizacao;

			ULOGI("ORGINSERIR - cidOrganizacao [%s]",cidOrganizacao);

			if( oOrganizacaoHierarquia.Insert( cidOrganizacao, cidOrganizacaoPai, cidUsuarioAlteracao ) )
			{
				if( iNomeDiferente )
					setStatusCode("14W0001","Operação realizada com sucesso, no entanto o nome achado na base é diferente do digitado.");
				else
					setStatusCode("14I0000","Sucesso na execução!");
			}
			else
				setStatusCode("14E0000","Erro na inserção de hierarquia de organograma");
		}
	else
		setStatusCode("14E0000","Erro na inserção de organograma");

	ULOG_END("implORGINSERIR::Execute()");
	
	return;
}
