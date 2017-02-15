//
// $Id: libget_subcampanha.cpp,v 1.1 2009/07/31 15:34:25 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETSUBCAMPANH);

extern int get_subcampanha(char * usuario, DOMNode*dnode,XMLGen*xml);
extern int get_canalcampanha(char * usuario, DOMNode*dnode,XMLGen*xml);
extern int get_grupoperfil(char * usuario, DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETSUBCAMPANH::Execute(DOMNode*dnode,XMLGen*xml_g)
{
        char * ptUsr;

        ULOG_START("GETSUBCAMPANH");
        ptUsr = getUser();

  xml_g->createTag("SubCampanhaVO");
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
//	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

		get_subcampanha(ptUsr,dnode,xml_g);

	xml_g->createTag("CanalUtil");
		get_canalcampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();

	xml_g->createTag("PerfilUtil");
		get_grupoperfil(ptUsr,dnode,xml_g);
	xml_g->closeTag();

  xml_g->closeTag();

        ULOG_END("GETSUBCAMPANH");
        setStatusCode(OKCMP,"Succes Execution");
}

