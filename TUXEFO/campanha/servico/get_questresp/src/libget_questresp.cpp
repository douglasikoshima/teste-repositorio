//
// $Id: libget_questresp.cpp,v 1.1 2009/07/31 15:33:58 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETQUESTRESP);  

extern int ConsultaQuestoesRespostas(int usuario, DOMNode*XMLIn, XMLGen*XMLOut);

int codErroBase = COD_BASE_CAMPANHA;

void implGETQUESTRESP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETQUESTRESP");

  idUsr = get_idUsuario(getUser());

  ConsultaQuestoesRespostas(idUsr,dnode,xml_g );

  ULOG_END("GETQUESTRESP");

  setStatusCode(OKCMP,OKMSG);
}

