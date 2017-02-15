//
// $Id: libins_agenda.cpp,v 1.1 2009/07/31 15:34:43 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSCMPAGENDA);  

extern int InsereAgenda( DOMNode *XMLIn , XMLGen *XMLOut );

int codErroBase = COD_BASE_CAMPANHA;

void implINSCMPAGENDA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
	char * ptUsr;

	ULOG_START("INSCMPAGENDA");
	
	ptUsr = getUser();

    InsereAgenda( dnode,xml_g );

	ULOG_END("INSCMPAGENDA");
	
	setStatusCode(OKCMP,"Succes Execution");
}

