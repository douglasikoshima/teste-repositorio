//
// $Id: libsel_ofdisp.cpp,v 1.1 2009/07/31 15:34:01 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int sel_ofdisp(int usuario, DOMNode*dnode, XMLGen*xml);
extern int sel_ofselc(int usuario, DOMNode*dnode, XMLGen*xml);

DECLARE_TUXEDO_SERVICE(SELOFDISP);

void implSELOFDISP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  int idUsr;
  int iOfertas=0;

  ULOG_START("SELOFDISP");

  idUsr = get_idUsuario(getUser());

  xml_g->createTag("tns:arrayListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  iOfertas=sel_ofdisp(idUsr,dnode,xml_g);
  xml_g->closeTag();

  if(iOfertas)
	  {		
		  xml_g->createTag("tns:fidelizacaoListaGeralVO");
		  sel_ofselc(idUsr,dnode,xml_g);
		  xml_g->closeTag();
	  }
  else
	  {
		xml_g->createTag("tns:fidelizacaoListaGeralVO");
		xml_g->closeTag();
	  }

  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);

  ULOG_END("SELOFDISP");
}

