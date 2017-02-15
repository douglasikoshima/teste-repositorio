//
// $Id: libins_mtoferta.cpp,v 1.1 2009/07/31 15:34:16 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int ins_mtoferta(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(INSMTOFERTA);

void implINSMTOFERTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("INSMTOFERTA");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  ins_mtoferta(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("INSMTOFERTA");
}

