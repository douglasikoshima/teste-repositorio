//
// $Id: libget_regional.cpp,v 1.1 2009/07/31 15:34:41 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(GETREGIONAL);

extern int get_list_regional(int usuario, DOMNode*dnode, XMLGen*xml);

void implGETREGIONAL::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETREGIONAL");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
  
  get_list_regional(idUsr,dnode,xml_g);
    
  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("GETREGIONAL");
}
