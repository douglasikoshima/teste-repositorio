//
// $Id: libupd_motivo.cpp,v 1.1 2009/07/31 15:34:31 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(UPDMOTIVO);  

extern int upd_motivo(char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implUPDMOTIVO::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

	ULOG_START("UPDMOTIVO");
	ptUsr = getUser();

		upd_motivo(ptUsr, dnode, xml_g);

	ULOG_END("UPDMOTIVO");
	setStatusCode(OKCMP,"Succes Execution");
}
