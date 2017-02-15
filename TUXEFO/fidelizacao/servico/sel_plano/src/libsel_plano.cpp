//
// $Id: libsel_plano.cpp,v 1.1 2009/07/31 15:34:52 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_plano(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELPLANO);

void implSELPLANO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELPLANO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:listaPlanoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_plano(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("SELPLANO");
}

