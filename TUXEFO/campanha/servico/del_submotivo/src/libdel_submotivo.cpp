//
// $Id: libdel_submotivo.cpp,v 1.1 2009/07/31 15:33:57 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELSUBMOTIVO);  

extern int del_submotivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELSUBMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELSUBMOTIVO");

	ptUsr = getUser();

		del_submotivo(ptUsr, dnode, xml_g);

	ULOG_END("DELSUBMOTIVO");

	setStatusCode(OKCMP,"Succes Execution");
}
