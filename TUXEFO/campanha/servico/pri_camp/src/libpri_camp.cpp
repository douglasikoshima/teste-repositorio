//
// $Id: libpri_camp.cpp,v 1.1 2009/07/31 15:33:34 a5110702 Exp $
//

//#include "retencao.hpp"

#include "../../negocio/cmputil/include/campanha.hpp"

extern int pri_camp(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(PRICAMP);

void implPRICAMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("PRICAMP");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("ListaCampanhaSubCampanhaVO ");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  pri_camp(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP,"Operação realizada com sucesso");

  ULOG_END("PRICAMP");
}

