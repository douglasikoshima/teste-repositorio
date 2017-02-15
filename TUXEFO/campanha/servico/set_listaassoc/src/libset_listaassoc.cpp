//
// $Id: libset_listaassoc.cpp,v 1.1 2009/07/31 15:33:28 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

extern int set_listaassoc( int usuario, DOMNode*dnode,XMLGen*xml);


DECLARE_TUXEDO_SERVICE(SETLISTAASSOC);

void implSETLISTAASSOC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SETLISTAASSOC");
        
  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  set_listaassoc(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKCMP,OKMSG);

  ULOG_END("SETLISTAASSOCIADA");
}

