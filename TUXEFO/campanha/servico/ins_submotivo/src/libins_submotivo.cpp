//
// $Id: libins_submotivo.cpp,v 1.1 2009/07/31 15:34:20 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSSUBMOTIVO);  

extern int ins_submotivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSSUBMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSSUBMOTIVO");
	ptUsr = getUser();
  
		ins_submotivo(ptUsr, dnode, xml_g);

	ULOG_END("INSSUBMOTIVO");
	setStatusCode(OKCMP,"Succes Execution");
}

