//
// $Id: libins_submotivopesq.cpp,v 1.1 2009/07/31 15:35:11 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSSUBMOTIVOP);  

extern int ins_submotivopesq(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSSUBMOTIVOP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSSUBMOTIVOPES");
	ptUsr = getUser();
        xml_g->createTag("tns:retornoVO");
        xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");  
		ins_submotivopesq(ptUsr, dnode, xml_g);
        xml_g->closeTag();

	ULOG_END("INSSUBMOTIVOPES");
	setStatusCode(OKCMP,"Succes Execution");
}

