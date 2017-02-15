//
// $Id: libins_campanhapesq.cpp,v 1.1 2009/07/31 15:34:19 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSCAMPANHAPE);

extern int ins_campanhapesq(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSCAMPANHAPE::Execute(DOMNode*dnode, XMLGen*xml_g)
{
        char * ptUsr;

        ULOG_START("INSCAMPANHAPE");
        ptUsr = getUser();
        xml_g->createTag("tns:retornoVO");
        xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
                ins_campanhapesq(ptUsr, dnode, xml_g);
        xml_g->closeTag();

        ULOG_END("INSCAMPANHAPE");
        setStatusCode(OKCMP,"Succes Execution");
}

