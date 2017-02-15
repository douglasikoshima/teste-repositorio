//
// $Id: libupd_stmtcamp.cpp,v 1.1 2009/07/31 15:34:57 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

//#include "retencao.hpp"

extern int upd_stmtcamp(char* usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(UPDSTMTCAMP);

void implUPDSTMTCAMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char* cidUsr = getUser();

  ULOG_START("UPDSTMTCAMP");

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  upd_stmtcamp(cidUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP,OKMSG);

  ULOG_END("UPDSTMTCAMP");
}

