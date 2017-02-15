#define ADMDOMLISTACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDomTbl.h"

DECLARE_TUXEDO_SERVICE(ADMDOMLISTA);

void implADMDOMLISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMLISTA::Execute()");
   
	CSafePointer oSafePointer;
	CTabelaDominio oCDominio;
	char *cnmTabelaDominio = NULL;
	char *cidUser = NULL;
	cnmTabelaDominio = oSafePointer.getTag( dnode, "nmTabelaDominio", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmTabelaDominiosVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(oCDominio.ListByName(cnmTabelaDominio)  > 0)
	{			
		oCDominio.GetXml("AdmTabelaDominioVO",xml_g); 						
		setStatusCode( "00I0000", "Sucesso" );
	}
	else
	{
		setStatusCode( "00I0001", "Nenhum registro encontrado" );
	}
	xml_g->closeTag();
	
	ULOG_END("implADMDOMLISTA::Execute()");
	
}