//
// $Id: libins_campanhaquest.cpp,v 1.1 2009/07/31 15:33:43 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSCMPQUEST);  

extern int InsCampanhaQuest( char * usuario,DOMNode *XMLIn , XMLGen *XMLOut );

int codErroBase = COD_BASE_CAMPANHA;

void implINSCMPQUEST::Execute(DOMNode*dnode, XMLGen*xml_g)
{
	char * ptUsr;

  xml_g->createTag("tns:retornoVO");
        xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
        xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	ULOG_START("INSCMPQUEST");
	ptUsr = getUser();

		InsCampanhaQuest( ptUsr,dnode,xml_g );

  xml_g->closeTag();
	ULOG_END("INSCMPQUEST");
	setStatusCode(OKCMP,"Succes Execution");
}

