//
// $Id: libins_canal.cpp,v 1.1 2009/07/31 15:34:22 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSCANAL);  

extern int ins_canal(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSCANAL::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSCANAL");
	ptUsr = getUser();
  
		ins_canal(ptUsr, dnode, xml_g);

	ULOG_END("INSCANAL");
	setStatusCode(OKCMP,"Succes Execution");
}

