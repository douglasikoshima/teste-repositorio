//
// $Id: libdel_campanha.cpp,v 1.1 2009/07/31 15:34:53 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELCAMPANHA);  

extern int del_campanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELCAMPANHA");
	
	ptUsr = getUser();

		del_campanha(ptUsr, dnode, xml_g);

	ULOG_END("DELCAMPANHA");
	
	setStatusCode(OKCMP,"Succes Execution");
}
