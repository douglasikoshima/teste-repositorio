//
// $Id: libget_campanhasub.cpp,v 1.1 2009/07/31 15:34:48 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETCAMPANHSUB);

extern int get_campanhasub(char * usuario, DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETCAMPANHSUB::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char * ptUsr = getUser();

	ULOG_START("GETCAMPANHSUB");

	xml_g->createTag("tns:GrupoCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	xml_g->createTag("tns:SubGrupoApoioVO");
	get_campanhasub(ptUsr,dnode,xml_g);
	xml_g->closeTag();//tns:SubGrupoApoioVO

	xml_g->closeTag();//tns:GrupoCampanhaApoioVO

	ULOG_END("GETCAMPANHSUB");

	setStatusCode(OKCMP,"Succes Execution");
}

