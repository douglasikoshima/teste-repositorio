//
// $Id: libfinal_campanha.cpp,v 1.1 2009/07/31 15:34:32 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int final_campanha( char* usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(FINALCAMPANHA);

void implFINALCAMPANHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char* idUsr;

  ULOG_START("FINALCAMPANHA");

  idUsr = getUser();

  xml_g->createTag("tns:scriptCampanhaVO");
  xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  final_campanha(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP, OKMSG);

  ULOG_END("FINALCAMPANHA");
}

