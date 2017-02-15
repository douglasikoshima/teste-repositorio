//
// $Id: libsel_script.cpp,v 1.1 2009/07/31 15:35:04 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_script(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELSCRIPT);

void implSELSCRIPT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELSCRIPT");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:arrayListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_script(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELSCRIPT");
}

