//
// $Id: libdel_pergunta.cpp,v 1.1 2009/07/31 15:33:39 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(DELPERGUNTA);  

extern int del_pergunta(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implDELPERGUNTA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
    char * ptUsr;

	ULOG_START("DELPERGUNTA");

	ptUsr = getUser();

		del_pergunta(ptUsr, dnode, xml_g);
	
	ULOG_END("DELPERGUNTA");

	setStatusCode(OKCMP,"Succes Execution");
}
