//
// $Id: libsel_msgencerrar.cpp,v 1.1 2009/07/31 15:34:13 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_msgencerrar( char * usuario, DOMNode*dnode, XMLGen*xml);
int codErroBase = COD_BASE_RETENCAO;

DECLARE_TUXEDO_SERVICE(SELMSGENCERRAR);

void implSELMSGENCERRAR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char * ptUsr;

  ULOG_START("SELMSGENCERRAR");

  ptUsr = getUser();

  xml_g->createTag("tns:listaMensagemResultadoVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  sel_msgencerrar(ptUsr,dnode,xml_g);

  xml_g->closeTag();

  setStatusCode(OKFID, OKMSG);

  ULOG_END("SELMSGENCERRAR");
}

