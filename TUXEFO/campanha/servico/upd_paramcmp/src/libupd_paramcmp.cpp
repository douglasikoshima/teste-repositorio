//
// $Id: libupd_paramcmp.cpp,v 1.1 2009/07/31 15:33:37 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDPARAMCMP);  

extern int upd_paramcmp(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDPARAMCMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDPARAMCMP");
	ptUsr = getUser();

		upd_paramcmp(ptUsr, dnode, xml_g);

	ULOG_END("UPDPARAMCMP");
	setStatusCode(OKCMP,"Succes Execution");
}
