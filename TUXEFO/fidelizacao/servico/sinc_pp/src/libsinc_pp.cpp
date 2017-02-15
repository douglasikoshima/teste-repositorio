//
// $Id: libsinc_pp.cpp,v 1.1.8.1 2015/09/21 20:14:54 a5110705 Exp $
//

#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE(SINCPP);  

extern int sinc_pp(int usuario, DOMNode*dnode, XMLGen*xml);

void implSINCPP::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr=0;
  
  ULOG_START("SINCPP");
 // idUsr = get_idUsuario(getUser());
  
  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

   int iRet = sinc_pp(idUsr, dnode, xml_g);

  xml_g->closeTag();
  
  ULOG_END("SINCPP");
  if ( iRet < 0 )
  {
	setStatusCode("10E1000", "Erro na execução");  
  }
  else
  {
	setStatusCode(OKFID,OKMSG);
  }
  
}
