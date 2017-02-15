//
// $Id: libget_tipostatuscampanha.cpp,v 1.1 2009/07/31 15:34:02 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETTPSTTSCMPNH);

extern int get_tipostatuscampnha(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETTPSTTSCMPNH::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[255];
  char *ptUsr;

  ULOG_START("GETTPSTTSCMPNH");

  ptUsr = getUser();

  xml_g->createTag("tns:GrupoCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	
	xml_g->createTag("tns:SubGrupoApoioVO");
		get_tipostatuscampnha(ptUsr, dnode, xml_g);
	xml_g->closeTag();
  xml_g->closeTag();

  ULOG_END("GETTPSTTSCMPNH");

  setStatusCode(OKCMP,"Succes Execution");
}
