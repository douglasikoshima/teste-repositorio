//
// $Id: libins_motivopesq.cpp,v 1.1 2009/07/31 15:34:54 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSMOTIVOPESQ);  

extern int ins_motivopesq(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSMOTIVOPESQ::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSMOTIVOPESQ");
	ptUsr = getUser();

        xml_g->createTag("tns:retornoVO");
        xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");  
		ins_motivopesq(ptUsr, dnode, xml_g);
        xml_g->closeTag();

	ULOG_END("INSMOTIVOPESQ");
	setStatusCode(OKCMP,"Succes Execution");
}

