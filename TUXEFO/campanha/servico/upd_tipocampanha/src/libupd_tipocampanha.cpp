//
// $Id: libupd_tipocampanha.cpp,v 1.1 2009/07/31 15:34:10 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDTIPOCAMPAN);  

extern int upd_tipocampanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDTIPOCAMPAN::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDTIPOCAMPAN");

	ptUsr = getUser();

		upd_tipocampanha(ptUsr, dnode, xml_g);

	ULOG_END("UPDTIPOCAMPAN");
	
	setStatusCode(OKCMP,"Succes Execution");
}
