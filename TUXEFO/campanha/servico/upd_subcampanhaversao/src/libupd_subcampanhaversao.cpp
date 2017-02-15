//
// $Id: libupd_subcampanhaversao.cpp,v 1.1 2009/07/31 15:33:44 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDSBCAMPANHA);  

extern int upd_subcampanhaversao(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDSBCAMPANHA::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDSBCAMPANHA");

	ptUsr = getUser();

		upd_subcampanhaversao(ptUsr, dnode, xml_g);

	ULOG_END("UPDSBCAMPANHA");
	
	setStatusCode(OKCMP,"Succes Execution");
}
