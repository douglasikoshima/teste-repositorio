//
// $Id: libdel_canal.cpp,v 1.1 2009/07/31 15:34:02 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELCANAL);  

extern int del_canal(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELCANAL::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELCANAL");
	
	ptUsr = getUser();

		del_canal(ptUsr, dnode, xml_g);

	ULOG_END("DELCANAL");
	setStatusCode(OKCMP,"Succes Execution");
}
