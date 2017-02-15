//
// $Id: libins_bonus.cpp,v 1.1 2009/07/31 15:34:58 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(INSBONUS);  

extern int insert_bonus(int usuario, DOMNode*dnode, XMLGen*xml);

void implINSBONUS::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("INSBONUS");

  idUsr = get_idUsuario(getUser());
  
  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  insert_bonus(idUsr, dnode, xml_g);

  xml_g->closeTag();
  
  setStatusCode(OKFID,OKMSG);

  ULOG_END("INSBONUS");
}

