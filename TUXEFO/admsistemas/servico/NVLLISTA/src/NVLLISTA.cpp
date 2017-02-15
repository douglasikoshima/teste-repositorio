#define NVLLISTACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"
#include "../../../negocio/admatdCmm/include/CCrg.h"

DECLARE_TUXEDO_SERVICE(NVLLISTA);

void implNVLLISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLLISTA::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CNivel oNivel;
	CCargo oCargo;
	int    iNivel;
	int    iContTag;
	char*  cidNivel = oSafePointer.getTag( dnode, "idNivel", 0 );
	int    iidNivel = strlennull( cidNivel );

	xml_g->createTag("AdmOrgNivelContainerVO");
	xml_g->addProp("xmlns", "usuario.fo.vivo.com.br/vo" );

	if( iidNivel )
		oNivel.ListId( cidNivel );
	else
		oNivel.ListAll();
	
	if( oNivel.Quantidade() > 0 )
	{
		iNivel   = 0;
		iContTag = 0;
		
		//Monta o menu raiz
		xml_g->createTag("NivelOrganogramaVO");
		xml_g->addItem("idNivel", oNivel.Registro(0)->cidNivel );
		xml_g->addItem("dsNivel", oNivel.Registro(0)->cdsNivel );
		xml_g->addItem("idNivelPai", oNivel.Registro(0)->cidNivelPai );
		xml_g->addItem("nrNivel", oNivel.Registro(0)->iLevel );
		xml_g->addItem("dsPath", oNivel.Registro(0)->cdsPath );
		
		//O laco continua apos o raiz
		for( int x = 1; x < oNivel.Quantidade(); x++ )
		{
			if( oNivel.Registro( x ) != NULL )
			{
				if( oNivel.Registro(x)->iLevel > iNivel )
				{
					iNivel = oNivel.Registro(x)->iLevel;
					xml_g->createTag("NivelOrganogramaVO");
					iContTag++;
				}//if( Registro(x)->iLevel > iNivel )
				else
				{
					xml_g->closeTag();
					if( oNivel.Registro(x)->iLevel < iNivel )
					{
						while( iNivel > oNivel.Registro(x)->iLevel )
						{
							xml_g->closeTag();
							iNivel--;
						}
						iNivel = oNivel.Registro(x)->iLevel;
					}
					xml_g->createTag("NivelOrganogramaVO");
				}// else if( Registro(x)->iLevel > iNivel )

				xml_g->addItem("idNivel", oNivel.Registro(x)->cidNivel );
				xml_g->addItem("dsNivel", oNivel.Registro(x)->cdsNivel );
				xml_g->addItem("idNivelPai", oNivel.Registro(x)->cidNivelPai );
				xml_g->addItem("nrNivel", oNivel.Registro(x)->iLevel );
				xml_g->addItem("dsPath", oNivel.Registro(x)->cdsPath );


				oCargo.RelacaoNC( oNivel.Registro(x)->cidNivel  );
				oCargo.GetXml("CargoVO",xml_g); 

			}// if( Registro( x ) != NULL )
		}// for( int x = 0; x < Quantidade(); x++ )
		// Fecha todas as tags
		while( iContTag > 0 )
		{
			xml_g->closeTag();
			iContTag--;
		}
	}// if( oNivel.Quantidade() > 0 )
	
	xml_g->closeTag();//AdmArvoreNivelContainerVO

	setStatusCode("14I0000","Operação concluída com sucesso!");
	
	ULOG_END("implNVLLISTA::Execute()");
}
