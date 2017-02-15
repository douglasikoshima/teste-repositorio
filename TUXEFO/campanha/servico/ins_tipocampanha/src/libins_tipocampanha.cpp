//
// $Id: libins_tipocampanha.cpp,v 1.1 2009/07/31 15:33:58 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSTIPOCAMPAN);  

extern int ins_tipocampanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implINSTIPOCAMPAN::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("INSTIPOCAMPAN");
	ptUsr = getUser();
  
		ins_tipocampanha(ptUsr, dnode, xml_g);

	ULOG_END("INSTIPOCAMPAN");
	setStatusCode(OKCMP,"Succes Execution");
}

