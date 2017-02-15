//
// $Id: libupd_destint.cpp,v 1.1 2009/07/31 15:33:36 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int upd_destint(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(UPDDSTINT);

void implUPDDSTINT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("UPDDSTINT");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  upd_destint(idUsr,dnode,xml_g);

  xml_g->closeTag();
   
  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("UPDDSTINT");
}

