//
// $Id: libget_arvoferta.cpp,v 1.1 2009/07/31 15:33:22 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_arvoferta(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETARVOFERTA);

void implGETARVOFERTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[50];
  int idUsr;

  ULOG_START("GETARVOFERTA");


  idUsr = atoi(getUser());

  get_arvoferta(idUsr,dnode,xml_g);

  setStatusCode(OKFID, OKMSG);

  ULOG_END("GETARVOFERTA");
}

