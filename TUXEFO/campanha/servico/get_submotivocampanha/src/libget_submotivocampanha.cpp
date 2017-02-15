//
// $Id: libget_submotivocampanha.cpp,v 1.1 2009/07/31 15:33:31 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETSBMOTIVOCA);

extern int get_listatiposubmotivocampanha(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETSBMOTIVOCA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  char parm[255];
    char * ptUsr;

        ULOG_START("GETSBMOTIVOCA");
        ptUsr = getUser();
  //  Obtendo dados do xml
  get_tag(parm, dnode, "getreg", 0, -1);

  xml_g->createTag("tns:GrupoCampanhaApoioVO");
	xml_g->addProp("xmlns:tns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xxl","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	xml_g->createTag("tns:SubGrupoApoioVO");
		get_listatiposubmotivocampanha(ptUsr,dnode,xml_g);
	xml_g->closeTag();
  xml_g->closeTag();


        ULOG_END("GETSBMOTIVOCA");
        setStatusCode(OKCMP,"Succes Execution");
}
