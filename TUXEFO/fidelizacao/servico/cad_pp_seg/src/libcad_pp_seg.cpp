
#include "../../negocio/fidutil/include/retencao.hpp"

DECLARE_TUXEDO_SERVICE( CADPPSEG );  

extern int cad_pp_seg(int usuario, DOMNode*dnode, XMLGen*xml);

void implCADPPSEG::Execute(DOMNode*dnode, XMLGen*xml_g)
{
  int idUsr=0;
  
  ULOG_START("CADPPSEG");
  
  xml_g->createTag("tns:retornoVO");
  xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

   int iRet = cad_pp_seg(idUsr, dnode, xml_g);

  xml_g->closeTag();
  
  ULOG_END("CADPPSEG");
  if ( iRet < 0 )
  {
	setStatusCode("10E1000", "Erro na execução");  
  }
  else
  {
	setStatusCode(OKFID,OKMSG);
  }
  
}
