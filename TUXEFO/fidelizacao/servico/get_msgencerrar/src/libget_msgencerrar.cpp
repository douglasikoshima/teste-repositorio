//
// $Id: libget_msgencerrar.cpp,v 1.1 2009/07/31 15:34:09 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_msgencerrar(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(GETMSGENCERRAR);

void implGETMSGENCERRAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;

  ULOG_START("GETMSGENCERRAR");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  get_msgencerrar(idUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("GETMSGENCERRAR");
}

