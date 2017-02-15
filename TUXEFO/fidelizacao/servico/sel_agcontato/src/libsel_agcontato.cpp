//
// $Id: libsel_agcontato.cpp,v 1.1 2009/07/31 15:34:18 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_agcontato(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELAGCONTATO);

void implSELAGCONTATO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("SELAGCONTATO");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:listaHistoricoAgendamentoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_agcontato(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELAGCONTATO");
}

