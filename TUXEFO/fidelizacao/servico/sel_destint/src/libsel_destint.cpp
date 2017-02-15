//
// $Id: libsel_destint.cpp,v 1.1 2009/07/31 15:33:21 a5110702 Exp $
//
//  SELDSTINT 
//   . Seleciona lista de destinos ou intencao de acordo com parametros
//      . idperg : Define se serah destino previsto ou intencao cancelamento
//      . texto  : Passa um texto para usar na pesquisa ou (*)
//                 para qualquer texto.
//
#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_destint(int usuario, DOMNode*dnode, XMLGen*xml);
extern int sel_destint2(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELDSTINT);

void implSELDSTINT::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  char parm[2000];
  
  ULOG_START("SELDSTINT");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  if ( get_tag(parm,dnode,"destinoObrigatorio",0,-1) == -1 )	
    sel_destint(idUsr,dnode,xml_g);
  else
	sel_destint2(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELDSTINT");
}

