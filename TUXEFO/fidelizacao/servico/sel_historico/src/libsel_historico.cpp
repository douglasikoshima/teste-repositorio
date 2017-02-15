//
// $Id: libsel_historico.cpp,v 1.1 2009/07/31 15:33:59 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_historico(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELHISTORICO);

void implSELHISTORICO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELHISTORICO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:listaHistoricoRetencaoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_historico(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELHISTORICO");
}

