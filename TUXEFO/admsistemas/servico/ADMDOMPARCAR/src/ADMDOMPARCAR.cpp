#define ADMDOMPARCARCPP
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCmpDom.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

DECLARE_TUXEDO_SERVICE(ADMDOMPARCAR);

void implADMDOMPARCAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMPARCAR::Execute()");
   
	CSafePointer oSafePointer;
	CCampoDominio oCCampoDominio;
	char* cidCampo = NULL;
	cidCampo = oSafePointer.getTag( dnode, "idCampo", 0 );
	xml_g->createTag("AdmCampoDominioVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(oCCampoDominio.ListId(cidCampo) > 0)
	{
		oCCampoDominio.GetXml(xml_g);
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00I0001","Nenhum registro encontrado");
	}
	xml_g->closeTag();
	
	ULOG_END("implADMDOMPARCAR::Execute()");
}
