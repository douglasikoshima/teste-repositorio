//
// $Id: libupd_mtoferta.cpp,v 1.1 2009/07/31 15:34:40 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int upd_mtoferta(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(UPDMTOFERTA);

void implUPDMTOFERTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("UPDDMTOFERTA");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  upd_mtoferta(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("UPDMTOFERTA");
}

