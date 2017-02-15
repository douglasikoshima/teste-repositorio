//
// $Id: libget_questionario.cpp,v 1.1 2009/07/31 15:33:51 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int get_questionario( char* usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETQUESTIONAR);

void implGETQUESTIONAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char* cidUsr;

  ULOG_START("GETQUESTIONAR");

  cidUsr = getUser();

  xml_g->createTag("tns:scriptQuestionarioVO");
  xml_g->addProp("xmlns:tns","questionario.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  get_questionario(cidUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP, OKMSG);

  ULOG_END("GETQUESTIONAR");
}

