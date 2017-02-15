#define ADMCMBPARDOMCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDomTbl.h"

DECLARE_TUXEDO_SERVICE(ADMCMBPARDOM);

void implADMCMBPARDOM::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCMBPARDOM::Execute()");
   
	CSafePointer oSafePointer;
	CTabelaDominio oCTabelaDominio;
	xml_g->createTag("AdmDominioComboVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );	
	if(oCTabelaDominio.ListAll() > 0)
	{
		oCTabelaDominio.GetXml("AdmTabelaDominioVO",xml_g);
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00I0001","Nenhum registro encontrado");
	}
	
	xml_g->closeTag();
	
	ULOG_END("implADMCMBPARDOM::Execute()");
}
