//
// $Id: libsel_aparelho.cpp,v 1.1 2009/07/31 15:34:37 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_aparelho(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELAPARELHO);

void implSELAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELAPARELHO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:ListaAparelhoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_aparelho(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELAPARELHO");
}

