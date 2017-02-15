//
// $Id: libget_apmtaparelho.cpp,v 1.1 2009/07/31 15:34:46 a5110702 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

extern int get_marcas(DOMNode*dnode,XMLGen*xml);
extern int get_cores(DOMNode*dnode,XMLGen*xml);
extern int get_tipoaparelho( XMLGen * xml );

DECLARE_TUXEDO_SERVICE(GETAPAPARELHO);

void implGETAPAPARELHO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[100];

  ULOG_START("GETAPAPARELHO");
  get_tag(parm,dnode,"getreg",0,0);

  xml_g->createTag("tns:arrayListaGeralVO");
  xml_g->addProp("xmlns:tns","fidelizacao.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  get_cores(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  get_marcas(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:fidelizacaoListaGeralVO");
  get_tipoaparelho( xml_g );
  xml_g->closeTag();
  xml_g->closeTag();

  setStatusCode(OKFID,OKMSG);
  ULOG_END("GETAPAPARELHO");
}
