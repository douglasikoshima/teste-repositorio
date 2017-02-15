//
// $Id: libdel_motivocampanha.cpp,v 1.1 2009/07/31 15:35:04 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELMOTIVOCAMP);  

extern int del_motivoCampanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELMOTIVOCAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELMOTIVOCAMP");

	ptUsr = getUser();

		del_motivoCampanha(ptUsr, dnode, xml_g);

	ULOG_END("DELMOTIVOCAMP");

	setStatusCode(OKCMP,"Succes Execution");
}
