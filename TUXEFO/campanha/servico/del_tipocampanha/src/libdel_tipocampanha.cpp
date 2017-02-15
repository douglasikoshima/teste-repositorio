//
// $Id: libdel_tipocampanha.cpp,v 1.1 2009/07/31 15:34:43 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELTIPOCAMPAN);  

extern int del_tipocampanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELTIPOCAMPAN::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELTIPOCAMPAN");

	ptUsr = getUser();

		del_tipocampanha(ptUsr, dnode, xml_g);

	ULOG_END("DELTIPOCAMPAN");

	setStatusCode(OKCMP,"Succes Execution");
}
