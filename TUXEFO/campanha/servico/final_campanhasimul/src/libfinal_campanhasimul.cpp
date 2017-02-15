//
// $Id: libfinal_campanhasimul.cpp,v 1.1 2009/07/31 15:33:55 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int final_campanhaSimul( int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(FINALCAMPSIMUL);

void implFINALCAMPSIMUL::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("FINALCAMPSIMUL");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:scriptCampanhaSimulVO");
  xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  final_campanhaSimul(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP, OKMSG);

  ULOG_END("FINALCAMPSIMUL");
}

