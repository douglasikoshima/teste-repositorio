//
// $Id: libget_submotivo.cpp,v 1.1 2009/07/31 15:34:21 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETSUBMOTIVO);

extern int get_submotivo(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETSUBMOTIVO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("GETSUBMOTIVO");

	xml_g->createTag("tns:GrupoCampanhaApoioVO");
	
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	
	xml_g->createTag("tns:SubGrupoApoioVO");

	get_submotivo("", dnode, xml_g);

	xml_g->closeTag();//xml_g->createTag("tns:SubGrupoApoioVO");

	xml_g->closeTag();//xml_g->createTag("tns:GrupoCampanhaApoioVO");

	ULOG_END("GETSUBMOTIVO");

	setStatusCode(OKCMP,"Succes Execution");
}
