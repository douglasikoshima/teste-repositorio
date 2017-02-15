//
// $Id: libupd_subcampanha.cpp,v 1.1 2009/07/31 15:33:50 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDSUBCAMPANHA);  

extern int upd_subcampanha(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDSUBCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDSUBCAMPANHA");
	ptUsr = getUser();

	if(upd_subcampanha(ptUsr, dnode, xml_g)==1)
	{
		ULOG_END("UPDSUBCAMPANHA");
		setStatusCode(OKCMP,"Succes Execution");
	}
	else
	{
		ULOG_END("UPDSUBCAMPANHA");
		setStatusCode("05W0001",
			"Já existe uma sub campanha com este mesmo nome para esta campanha");
	}
}
