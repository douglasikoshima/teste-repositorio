//
// $Id: libupd_migracao.cpp,v 1.1 2009/07/31 15:34:42 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int upd_migracao(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(UPDMIGRACAO);

void implUPDMIGRACAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("UPDMIGRACAO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  upd_migracao(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("UPDMIGRACAO");
}

