//
// $Id: libins_paramcmp.cpp,v 1.1 2009/07/31 15:33:37 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSPARAMCMP);  

extern int ins_paramcmp(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSPARAMCMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSPARAMCMP");
	ptUsr = getUser();
  
		ins_paramcmp(ptUsr, dnode, xml_g);

	ULOG_END("INSPARAMCMP");
	setStatusCode(OKCMP,"Succes Execution");
}

