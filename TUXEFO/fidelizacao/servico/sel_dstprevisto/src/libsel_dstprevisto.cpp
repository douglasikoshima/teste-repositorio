//
// $Id: libsel_dstprevisto.cpp,v 1.1 2009/07/31 15:34:19 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_dstprevisto(int usuario, DOMNode*dnode, XMLGen*xml);
int sel_dstprevistoCfg(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELDSTPREV);

void implSELDSTPREV::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  char parm[255];

  ULOG_START("SELDSTPREV");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  if(get_tag(parm,dnode,"inConfiguracao",0,-1)==-1)
  {
	  ULOG("sel_dstprevisto");
	  sel_dstprevisto(idUsr,dnode,xml_g);
  }
  else
  {
	   ULOG("sel_dstprevistoCfg");
	   sel_dstprevistoCfg(idUsr,dnode,xml_g);
  }

  xml_g->closeTag();


  setStatusCode(OKFID,OKMSG);
  
  ULOG_END("SELDSTPREV");
}
