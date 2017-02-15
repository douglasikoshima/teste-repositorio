//
// $Id: librel_ofertas.cpp,v 1.1 2009/07/31 15:33:54 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int rel_ofertas(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(RELOFERTAS);

void implRELOFERTAS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("RELOFERTAS");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:relOfertasVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  rel_ofertas(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);
  
  ULOG_END("RELOFERTAS");
}

