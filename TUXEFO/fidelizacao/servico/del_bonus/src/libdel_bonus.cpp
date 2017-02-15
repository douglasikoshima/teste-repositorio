//
// $Id: libdel_bonus.cpp,v 1.1 2009/07/31 15:34:44 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(DELBONUS);  

extern int delete_bonus(int usuario, DOMNode*dnode, XMLGen*xml);

void implDELBONUS::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr;
  
  ULOG_START("DELBONUS");
  idUsr = get_idUsuario(getUser());
  
  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  delete_bonus(idUsr, dnode, xml_g);

  xml_g->closeTag();
  
  ULOG_END("DELBONUS");
  setStatusCode(OKFID,OKMSG);
}
