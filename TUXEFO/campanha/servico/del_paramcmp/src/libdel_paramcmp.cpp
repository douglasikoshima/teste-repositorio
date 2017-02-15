//
// $Id: libdel_paramcmp.cpp,v 1.1 2009/07/31 15:34:31 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELPARAMCMP);  

extern int del_paramcmp(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELPARAMCMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("DELPARAMCMP");

	ptUsr = getUser();

		del_paramcmp(ptUsr, dnode, xml_g);

	ULOG_END("DELPARAMCMP");

	setStatusCode(OKCMP,"Succes Execution");
}
