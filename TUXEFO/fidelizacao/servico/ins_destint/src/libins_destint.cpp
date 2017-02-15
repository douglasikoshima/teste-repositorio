//
// $Id: libins_destint.cpp,v 1.1 2009/07/31 15:33:59 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int ins_destint(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(INSDSTINT);

void implINSDSTINT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int rc = 0;
  int idUsr;

  ULOG_START("INSDSTINT");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  rc = ins_destint(idUsr,dnode,xml_g);

  xml_g->closeTag();
  
  if (rc == 1){
    setStatusCode(OKFID,OKMSG);
  }else{
    setStatusCode(NOKFID,"Existe registro");
  }

  ULOG_END("INSDSTINT");
}
