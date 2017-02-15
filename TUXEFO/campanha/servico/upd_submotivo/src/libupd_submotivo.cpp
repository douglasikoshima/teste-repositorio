//
// $Id: libupd_submotivo.cpp,v 1.1 2009/07/31 15:34:34 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDSUBMOTIVO);  

extern int upd_submotivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDSUBMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDSUBMOTIVO");
	ptUsr = getUser();

		upd_submotivo(ptUsr, dnode, xml_g);

	ULOG_END("UPDSUBMOTIVO");
	setStatusCode(OKCMP,"Succes Execution");
}
