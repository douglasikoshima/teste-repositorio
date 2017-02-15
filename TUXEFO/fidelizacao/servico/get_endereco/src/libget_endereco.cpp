//
// $Id: libget_endereco.cpp,v 1.1 2009/07/31 15:34:31 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETENDERECO);

extern int get_endereco(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETENDERECO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  
  ULOG_START("GETENDERECO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("RetencaoAnaliseCreditoVO");
    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
    // xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
  get_endereco(idUsr, dnode,xml_g);
  
  xml_g->closeTag();
  
  setStatusCode(OKFID,OKMSG);

  ULOG_END("GETENDERECO");
}
