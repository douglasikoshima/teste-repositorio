//
// $Id: libsel_tpencerrar.cpp,v 1.1 2009/07/31 15:33:57 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_tpencerrar(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELTPENCERRAR);

void implSELTPENCERRAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[50];
  int idUsr;

  ULOG_START("SELTPENCERRAR");
  
  get_tag(parm,dnode,"getreg",0,0);

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_tpencerrar(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELTPENCERRAR");
}

