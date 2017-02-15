//
// $Id: libget_paramcmp.cpp,v 1.1 2009/07/31 15:34:06 a5110702 Exp $
//

#include "../../negocio/cmputil/include/campanha.hpp"

DECLARE_TUXEDO_SERVICE(GETPARAMCMP);

extern int get_listaparamcmp(char * usuario,DOMNode*dnode,XMLGen*xml);
int codErroBase = COD_BASE_CAMPANHA;

void implGETPARAMCMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  	char parm[255];
  	char * ptUsr;

  	ULOG_START("GETPARAMCMP");

	ptUsr = getUser();

  	xml_g->createTag("tns:CampanhaParametrosVO ");
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	xml_g->addProp("xsi:schemaLocation","campanha.fo.vivo.com.br/vo");

	
	get_listaparamcmp(ptUsr,dnode,xml_g);
  
  	xml_g->closeTag();

    ULOG_END("GETPARAMCMP");

    setStatusCode(OKCMP,"Succes Execution");
}
