//
// $Id: libsel_migracao.cpp,v 1.1 2009/07/31 15:33:35 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_migracao(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELMIGRACAO);

void implSELMIGRACAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELMIGRACAO");

  idUsr = get_idUsuario(getUser());

  sel_migracao(idUsr,dnode,xml_g);

  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("SELMIGRACAO");
}

