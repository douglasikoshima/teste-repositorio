//
// $Id: libset_detaparelho.cpp,v 1.1 2009/07/31 15:33:58 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int set_detaparelho(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SETDETAPARELHO);

void implSETDETAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SETDETAPARELHO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  //set_detaparelho(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SETDETAPARELHO");
}

