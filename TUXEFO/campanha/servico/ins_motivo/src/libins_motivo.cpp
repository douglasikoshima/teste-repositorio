//
// $Id: libins_motivo.cpp,v 1.1 2009/07/31 15:33:29 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSMOTIVO);  

extern int ins_motivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSMOTIVO");

	ptUsr = getUser();
  
		ins_motivo(ptUsr, dnode, xml_g);

	ULOG_END("INSMOTIVO");

	setStatusCode(OKCMP,"Succes Execution");
}

