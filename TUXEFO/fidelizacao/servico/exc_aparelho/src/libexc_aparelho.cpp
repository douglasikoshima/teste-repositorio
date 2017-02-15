//
// $Id: libexc_aparelho.cpp,v 1.1 2009/07/31 15:33:58 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int exc_aparelho(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(EXCAPARELHO);

void implEXCAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("EXCAPARELHO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  exc_aparelho(idUsr, dnode, xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("EXCAPARELHO");
}
