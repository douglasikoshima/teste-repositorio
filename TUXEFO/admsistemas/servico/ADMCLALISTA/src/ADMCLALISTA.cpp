#define ADMCLALISTACPP
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCmpCls.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

DECLARE_TUXEDO_SERVICE(ADMCLALISTA);

void implADMCLALISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCLALISTA::Execute()");
   
	CSafePointer oSafePointer;
	CCampoClassificador oCCampoClassificador;
	char* cnmClassificadorCampo = NULL;
	char* cidUser = NULL;
	cnmClassificadorCampo = oSafePointer.getTag( dnode, "nmClassificadorCampo", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmClassificadorCamposVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(oCCampoClassificador.ListByName(cnmClassificadorCampo) > 0)
	{			
		oCCampoClassificador.GetXml("AdmClassificadorCampoVO",xml_g); 						
		setStatusCode( "00I0000", "Sucesso" );
	}
	else
	{
		setStatusCode( "00I0001", "Nenhum registro encontrado" );
	}
	xml_g->closeTag();
	
	ULOG_END("implADMCLALISTA::Execute()");
}