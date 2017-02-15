//
// $Id: libsel_susptemp.cpp,v 1.1 2009/07/31 15:34:36 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(SELSUSPTEMP);

extern int sel_susptemp(int usuario, DOMNode*dnode, XMLGen*xml);

void implSELSUSPTEMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELSUSPTEMP");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_susptemp(idUsr,dnode,xml_g);
    
  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELSUSPTEMP");
}
