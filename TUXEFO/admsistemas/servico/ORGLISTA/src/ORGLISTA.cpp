#define ORGLISTACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/COrg.h"
#include "../../../negocio/admatdCmm/include/CTO.h"
#include "../../../negocio/admatdCmm/include/CUnd.h"

DECLARE_TUXEDO_SERVICE(ORGLISTA);

void implORGLISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_END("implORGLISTA::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	COrganizacao oOrganizacao;
	CTipoOrganizacao oTipoOrganizacao;
	CDepartamento oDepartamento;
	int    iNivel;
	int    iContTag;
	char*  cidOrganizacao = oSafePointer.getTag( dnode, "idOrganizacao", 0 );
	int    iidOrganizacao = strlennull( cidOrganizacao );

	xml_g->createTag("AdmOrgOrganizacaoContainerVO");
	xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );

	if( iidOrganizacao )
		oOrganizacao.ListId( cidOrganizacao );
	else
		oOrganizacao.ListAll();
	if( oOrganizacao.Quantidade() > 0 )
	{
		iNivel   = 0;
		iContTag = 0;
		
		//Monta o menu raiz
		xml_g->createTag("OrganizacaoVO");
		xml_g->addItem("idOrganizacao", oOrganizacao.Registro(0)->cidOrganizacao );
		xml_g->addItem("idOrganizacaoPai", oOrganizacao.Registro(0)->cidOrganizacaoPai );
		xml_g->createTag("TipoOrganizacaoVO");
		xml_g->addItem("idTipoOrganizacao", oOrganizacao.Registro(0)->cidTipoOrganizacao );
		xml_g->addItem("dsTipoOrganizacao", oOrganizacao.Registro(0)->cdsTipoOrganizacao );
		xml_g->closeTag();
		xml_g->addItem("nrNivel", oOrganizacao.Registro(0)->iLevel );
		xml_g->addItem("dsPath", oOrganizacao.Registro(0)->cdsPath );
		
		//O laco continua apos o raiz
		for( int x = 1; x < oOrganizacao.Quantidade(); x++ )
		{
			if( oOrganizacao.Registro( x ) != NULL )
			{
				if( oOrganizacao.Registro(x)->iLevel > iNivel )
				{
					iNivel = oOrganizacao.Registro(x)->iLevel;
					xml_g->createTag("OrganizacaoVO");
					iContTag++;
				}//if( Registro(x)->iLevel > iNivel )
				else
				{
					xml_g->closeTag();
					if( oOrganizacao.Registro(x)->iLevel < iNivel )
					{
						while( iNivel > oOrganizacao.Registro(x)->iLevel )
						{
							xml_g->closeTag();
							iNivel--;
						}
						iNivel = oOrganizacao.Registro(x)->iLevel;
					}
					xml_g->createTag("OrganizacaoVO");
				}// else if( Registro(x)->iLevel > iNivel )

				xml_g->addItem("idOrganizacao", oOrganizacao.Registro(x)->cidOrganizacao );
				xml_g->addItem("idOrganizacaoPai", oOrganizacao.Registro(x)->cidOrganizacaoPai );
				xml_g->createTag("TipoOrganizacaoVO");
				xml_g->addItem("idTipoOrganizacao", oOrganizacao.Registro(x)->cidTipoOrganizacao );
				xml_g->addItem("dsTipoOrganizacao", oOrganizacao.Registro(x)->cdsTipoOrganizacao );
				xml_g->closeTag();
				xml_g->addItem("nrNivel", oOrganizacao.Registro(x)->iLevel );
				xml_g->addItem("dsPath", oOrganizacao.Registro(x)->cdsPath );

				oDepartamento.ListDeptoPorIdOrganizacao( oOrganizacao.Registro(x)->cidOrganizacao );
				oDepartamento.GetXml("UnidadeOrganogramaVO",xml_g); 
				

			}// if( Registro( x ) != NULL )
		}// for( int x = 0; x < Quantidade(); x++ )
		// Fecha todas as tags
		while( iContTag > 0 )
		{
			xml_g->closeTag();
			iContTag--;
		}
	}// if( oOrganizacao.Quantidade() > 0 )
	
	xml_g->closeTag();//AdmArvoreOrganizacaoContainerVO

	setStatusCode("14I0000","Operação concluída com sucesso!");
	
	ULOG_END("implORGLISTA::Execute()");
}
