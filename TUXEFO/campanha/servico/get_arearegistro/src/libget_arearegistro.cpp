//
// $Id: libget_arearegistro.cpp,v 1.1 2009/07/31 15:34:22 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETAREAREG);

extern int get_arearegistro(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETAREAREG::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETAREAREG");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("AreasRegistroVO");
	 xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	 //xml_g->addProp("xmlns","http://www.w3.org/2001/XMLSchema-instance");
  
  get_arearegistro(idUsr,dnode,xml_g);
    
  xml_g->closeTag();

  setStatusCode(OKCMP,OKMSG);

  ULOG_END("GETAREAREG");
}
