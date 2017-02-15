//
// $Id: libupd_canal.cpp,v 1.1 2009/07/31 15:34:12 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDCANAL);  

extern int upd_canal(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDCANAL::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDCANAL");
	ptUsr = getUser();

		upd_canal(ptUsr, dnode, xml_g);

	ULOG_END("UPDCANAL");
	setStatusCode(OKCMP,"Succes Execution");
}
