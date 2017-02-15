//
// $Id: libapoiocampanha.cpp,v 1.1 2009/07/31 15:34:22 a5110702 Exp $
//

#include"TuxFW10.h"

DECLARE_TUXEDO_SERVICE(GETAPOIOCAMPANHA);

void implGETAPOIOCAMPANHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char *parm;

  parm = walkTree(dnode,"getreg",0);
  if(!parm){
    throw new TuxBasicSvcException("00E1000","Cannot get [getreg] clause from XML string");
  }

  xml_g->createTag("tns:grupoApoioVO");
  xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
//  xml_g->addProp("xsi:schemaLocation","campanha.fo.vivo.com.br/vo");

  xml_g->createTag("tns:subGrupoApoioVO");
  get_cores(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_marcas(dnode,xml_g);
  xml_g->closeTag();
  xml_g->closeTag();
  

  setStatusCode("00I0000","Succes Execution");
}


