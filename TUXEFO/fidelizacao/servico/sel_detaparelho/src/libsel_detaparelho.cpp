//
// $Id: libsel_detaparelho.cpp,v 1.1 2009/07/31 15:34:46 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_detaparelho(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELDETAPARELHO);

void implSELDETAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELDETAPARELHO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:DetalheAparelhoVO ");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_detaparelho(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELDETAPARELHO");
}

