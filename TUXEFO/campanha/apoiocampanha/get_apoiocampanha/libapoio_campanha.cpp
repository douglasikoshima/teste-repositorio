//
// $Id: libapoio_campanha.cpp,v 1.1 2009/07/31 15:34:11 a5110702 Exp $
//

#include "TuxFW10.h"

DECLARE_TUXEDO_SERVICE(GETAPOIOCAMPANHA);

extern int get_tipomotivocampanha(DOMNode*dnode,XMLGen*xml);
extern int get_tiposubmotivocampanha(DOMNode*dnode,XMLGen*xml);
extern int get_canal(DOMNode*dnode,XMLGen*xml);
extern int get_tipocampanha(DOMNode*dnode,XMLGen*xml);
extern int get_campanha(DOMNode*dnode,XMLGen*xml);
extern int get_tipostatuscampanha(DOMNode*dnode,XMLGen*xml);

void implGETAPOIOCAMPANHA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char *parm;

  parm = walkTree(dnode,"getreg",0);
  if(!parm){
    throw new TuxBasicSvcException("00E1000","Cannot get [getreg] clause from XML string");
  }

  xml_g->createTag("tns:GrupoCampanhaApoioVO");
  xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
  xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
//  xml_g->addProp("xsi:schemaLocation","campanha.fo.vivo.com.br/vo");

  xml_g->createTag("tns:subGrupoApoioVO");
  get_tipomotivocampanha(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_tiposubmotivocampanha(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_canal(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_tipocampanha(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_campanha(dnode,xml_g);
  xml_g->closeTag();
  xml_g->createTag("tns:subGrupoApoioVO");
  get_tipostatuscampanha(dnode,xml_g);
  xml_g->closeTag();
  xml_g->closeTag();

  setStatusCode("00I0000","Succes Execution");
}
