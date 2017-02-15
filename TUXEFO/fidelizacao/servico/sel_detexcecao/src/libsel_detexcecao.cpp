//
// $Id: libsel_detexcecao.cpp,v 1.1 2009/07/31 15:34:18 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_detexcecao(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELDETEXCECAO);

void implSELDETEXCECAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELDETEXCECAO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:ofertaExcecaoDetalheVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_detexcecao(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELDETEXCECAO");
}

