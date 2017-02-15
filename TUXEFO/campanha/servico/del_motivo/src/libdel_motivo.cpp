//
// $Id: libdel_motivo.cpp,v 1.1 2009/07/31 15:34:06 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELMOTIVO);  

extern int del_motivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELMOTIVO");
	
	ptUsr = getUser();

		del_motivo(ptUsr, dnode, xml_g);

	ULOG_END("DELMOTIVO");
		
	setStatusCode(OKCMP,"Succes Execution");
}
