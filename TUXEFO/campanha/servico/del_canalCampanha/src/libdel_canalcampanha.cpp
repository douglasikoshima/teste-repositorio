//
// $Id: libdel_canalcampanha.cpp,v 1.1 2009/07/31 15:34:41 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELCANALCAMP);  

extern int del_canalCampanha(char * usuario, DOMNode*dnode, XMLGen*xml);


void implDELCANALCAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELCANALCAMP");

	ptUsr = getUser();

		del_canalCampanha(ptUsr, dnode, xml_g);

	ULOG_END("DELCANALCAMP");
	
	setStatusCode(OKCMP,"Succes Execution");
}
