//
// $Id: libget_apoiomtzap.cpp,v 1.1 2009/07/31 15:34:58 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_Seg_e_Grupo(DOMNode*dnode,XMLGen*xml);
extern int sel_tpcli(DOMNode*dnode,XMLGen*xml);
extern int sel_grupo(DOMNode*dnode,XMLGen*xml);
extern int get_list_regional(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETAPOIOMTZAP);

void implGETAPOIOMTZAP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[50];
  int idUsr;

  ULOG_START("GETAPOIOMTZAP");

  idUsr = get_idUsuario(getUser());

  get_tag(parm,dnode,"getreg",0,0);

  xml_g->createTag("tns:arrayListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  get_list_regional(idUsr, dnode, xml_g);
  xml_g->closeTag();

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  sel_tpcli(dnode,xml_g);
  xml_g->closeTag();

  //apenas nesta funcao foi removido a tag
  //header de XML para facilitar a criação da 
  //tag de grupo
  sel_Seg_e_Grupo(dnode,xml_g);
  


  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("GETAPOIOMTZAP");
}

