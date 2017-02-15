//
// $Id: libget_analise.cpp,v 1.1 2009/07/31 15:33:41 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETANALISE);

extern int get_analise(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETANALISE::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  
  ULOG_START("GETANALISE()");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("RetencaoAnaliseCreditoVO");
    xml_g->addProp("xmlns","fidelizacao.fo.vivo.com.br/vo");
    xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
    get_analise(idUsr, dnode,xml_g);
  
  xml_g->closeTag();
  
  setStatusCode(OKFID,OKMSG);

  ULOG_END("GETANALISE()");
}
