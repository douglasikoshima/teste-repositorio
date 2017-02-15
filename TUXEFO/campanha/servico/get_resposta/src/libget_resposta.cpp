//
// $Id: libget_resposta.cpp,v 1.1 2009/07/31 15:33:37 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETRESPOSTA);

extern int get_resposta(char * usuario, DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETRESPOSTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char * ptUsr;

  ULOG_START("GETRESPOSTA");

  ptUsr = getUser();

  xml_g->createTag("CampanhaViewRespostaVO");
  xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
  xml_g->addProp("xsi:schemaLocation","campanha.fo.vivo.com.br/vo");

  get_resposta(ptUsr,dnode,xml_g);

  xml_g->closeTag();

  ULOG_END("GETRESPOSTA");

  setStatusCode(OKCMP,OKMSG);
}

