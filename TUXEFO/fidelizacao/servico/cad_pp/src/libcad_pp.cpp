//
// $Id: libcad_pp.cpp,v 1.1.2.4 2012/04/12 19:13:20 a5116174 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(CADPP);  

extern int cad_pp(int usuario, DOMNode*dnode, XMLGen*xml);

void implCADPP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr=0;
  
  ULOG_START("CADPP");
 // idUsr = get_idUsuario(getUser());
  
  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

   int iRet = cad_pp(idUsr, dnode, xml_g);

  xml_g->closeTag();
  
  ULOG_END("CADPP");
  if ( iRet < 0 )
  {
	setStatusCode("10E1000", "Erro na execução");  
  }
  else
  {
	setStatusCode(OKFID,OKMSG);
  }
  
}
