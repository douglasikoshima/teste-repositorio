//
// $Id: libupd_campanha.cpp,v 1.1 2009/07/31 15:33:30 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDCAMPANHA);  

extern int upd_campanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDCAMPANHA");
	ptUsr = getUser();

		upd_campanha(ptUsr, dnode, xml_g);

	ULOG_END("UPDCAMPANHA");
	setStatusCode(OKCMP,"Succes Execution");
}
