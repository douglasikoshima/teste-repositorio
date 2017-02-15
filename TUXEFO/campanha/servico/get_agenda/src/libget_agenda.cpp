//
// $Id: libget_agenda.cpp,v 1.1 2009/07/31 15:34:52 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETCMPAGENDA);  

extern int ConsultaAgenda( DOMNode *XMLIn , XMLGen *XMLOut );

int codErroBase = COD_BASE_CAMPANHA;

void implGETCMPAGENDA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
	char * ptUsr;

	ULOG_START("GETCMPAGENDA");

	ptUsr = getUser();

		xml_g->createTag("tns:listaAgendamentoCampanhaVO");
            xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
            xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
                ConsultaAgenda( dnode,xml_g );
        xml_g->closeTag();

	ULOG_END("GETCMPAGENDA");

	setStatusCode(OKCMP,"Succes Execution");
}

