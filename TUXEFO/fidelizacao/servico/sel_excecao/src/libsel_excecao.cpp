//
// $Id: libsel_excecao.cpp,v 1.1 2009/07/31 15:34:02 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_excecao(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELEXCECAO);

void implSELEXCECAO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  char parm[100];
  
  ULOG_START("SELEXCECAO");

  idUsr = get_idUsuario(getUser());

  get_tag(parm,dnode,"getreg",0,0);

  xml_g->createTag("tns:ListaOfertaExecaoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_excecao(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("SELEXCECAO");
}
