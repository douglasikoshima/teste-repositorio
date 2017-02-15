//
// $Id: libins_campanha.cpp,v 1.1 2009/07/31 15:34:41 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSCAMPANHA);  

extern int ins_campanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSCAMPANHA");

	ptUsr = getUser();
  
	ins_campanha(ptUsr, dnode, xml_g);

	ULOG_END("INSCAMPANHA");
	
	setStatusCode(OKCMP,"Succes Execution");
}

