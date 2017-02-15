//
// $Id: libget_dethist.cpp,v 1.1 2009/07/31 15:34:56 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_dethist(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETDETHIST);

void implGETDETHIST::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETDETHIST");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:detalheHistoricoRetencaoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  get_dethist(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("GETDETHIST");
}

