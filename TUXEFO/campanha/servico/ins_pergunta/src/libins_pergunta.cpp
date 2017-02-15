//
// $Id: libins_pergunta.cpp,v 1.1 2009/07/31 15:34:01 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(INSPERGUNTAQST);  

extern int ins_pergunta(int usuario, DOMNode*dnode, XMLGen*xml);

void implINSPERGUNTAQST::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("INSPERGUNTAQST");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  ins_pergunta(idUsr, dnode, xml_g);

  xml_g->closeTag();

  ULOG_END("INSPERGUNTAQST");

  setStatusCode(OKCMP,OKMSG);
}

