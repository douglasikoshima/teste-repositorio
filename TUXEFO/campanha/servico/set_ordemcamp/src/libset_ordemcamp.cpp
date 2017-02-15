//
// $Id: libset_ordemcamp.cpp,v 1.1 2009/07/31 15:33:51 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int set_ordemcamp( int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SETORDEMCAMP);

void implSETORDEMCAMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SETORDEMCAMP");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  set_ordemcamp(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP, OKMSG);

  ULOG_END("SETORDEMCAMP");
}

