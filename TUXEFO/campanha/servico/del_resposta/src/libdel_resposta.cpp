//
// $Id: libdel_resposta.cpp,v 1.1 2009/07/31 15:34:35 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELRESPOSTA);  

extern int del_resposta(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELRESPOSTA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELRESPOSTA");
	
	ptUsr = getUser();

		del_resposta(ptUsr, dnode, xml_g);

	ULOG_END("DELRESPOSTA");
	
	setStatusCode(OKCMP,"Succes Execution");
}
