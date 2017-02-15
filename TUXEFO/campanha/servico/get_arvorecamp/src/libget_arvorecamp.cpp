//
// $Id: libget_arvorecamp.cpp,v 1.1 2009/07/31 15:34:25 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETARVCAMP);  

extern int ConsultaResposta(int usuario, DOMNode*XMLIn, XMLGen*XMLOut);

int codErroBase = COD_BASE_CAMPANHA;

void implGETARVCAMP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETARVCAMP");

  idUsr = get_idUsuario(getUser());

  ConsultaResposta(idUsr,dnode,xml_g );

  ULOG_END("GETARVCAMP");

  setStatusCode(OKCMP,OKMSG);
}

