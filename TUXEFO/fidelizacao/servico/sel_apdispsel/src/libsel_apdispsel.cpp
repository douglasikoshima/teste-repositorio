//
// $Id: libsel_apdispsel.cpp,v 1.1 2009/07/31 15:34:33 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_apdispsel(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELAPDISPSEL);

void implSELAPDISPSEL::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELAPDISPSEL");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:arrayListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_apdispsel(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELAPDISPSEL");
}

