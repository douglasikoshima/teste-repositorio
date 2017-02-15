//
// $Id: libins_cmpag.cpp,v 1.1 2009/07/31 15:34:18 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int ins_cmpag(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(INSCMPAG);

void implINSCMPAG::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("INSCMPAG");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  ins_cmpag(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP,OKMSG);
  
  ULOG_END("INSCMPAG");
}

