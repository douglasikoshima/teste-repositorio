//
// $Id: libget_ofaparelho.cpp,v 1.1 2009/07/31 15:34:04 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_ofaparelho(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETOFAPARELHO);

void implGETOFAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  ULOG_START("GETOFAPARELHO");    
  int idUsr;

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:ofertaAparelhoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  get_ofaparelho(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);
  
  ULOG_END("GETOFAPARELHO");
}

