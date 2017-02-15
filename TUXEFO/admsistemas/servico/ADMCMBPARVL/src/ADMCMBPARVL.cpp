#define ADMCMBPARVLCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDom.h"

DECLARE_TUXEDO_SERVICE(ADMCMBPARVL);

void implADMCMBPARVL::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCMBPARVL::Execute()");
   
	CSafePointer oSafePointer;
	CDominio oCDominio;
	xml_g->createTag("AdmDominioComboVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	if(oCDominio.ListTabelaDominio() > 0)
	{
		oCDominio.GetXmlTabelaDominio("AdmTabelaDominioVO", xml_g);
	}
	if(oCDominio.ListUFOperadora() > 0)
	{
		oCDominio.GetUFOperadora("AdmUFOperadoraSimplVO", xml_g);
	}
	if(oCDominio.ListTipoLinha() > 0)
	{
		oCDominio.GetTipoLinha("AdmTipoLinhaSimplVO",xml_g);
	}
	setStatusCode("00I0000","Sucesso");
	xml_g->closeTag();
	
	ULOG_END("implADMCMBPARVL::Execute()");
}
