#define ADMCAMCMBCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMCAMCMB);

void implADMCAMCMB::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
    ULOG_START("implADMCAMCMB::Execute()");
	CCampo oCCampo;
	xml_g->createTag("AdmCampoCombosVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");	
	if(oCCampo.ListClassificadorCampo() > 0)
	{
		oCCampo.GetXmlClassificador("AdmClassificadorCampoVO",xml_g);
	}
	if(oCCampo.ListTipoDadoCampo() > 0)
	{
		oCCampo.GetXmlTipoDado("AdmTipoDadoCampoVO",xml_g);
	}
	if(oCCampo.ListMascaraApresentacaoCampo() > 0)
	{
		oCCampo.GetXmlMascaraApresentacao("AdmMascaraApresentacaoVO",xml_g);
	}
	if(oCCampo.ListLayoutApresentacaoCampo() > 0)
	{
		oCCampo.GetXmlLayoutApresentacao("AdmLayoutApresentacaoCampoVO",xml_g);
	}
	setStatusCode("00I0000","Sucesso");
	xml_g->closeTag();
	ULOG_END("implADMCAMCMB::Execute()");
}