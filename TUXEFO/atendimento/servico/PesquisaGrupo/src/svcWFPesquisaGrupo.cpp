/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/


#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFPsqGru);

extern bool proCConsultaWFGrupos(XMLGen* saida);

void implWFPsqGru::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFPsqGru::Execute()");
    
	proCConsultaWFGrupos(xml_g);

	setStatusCode("04I0000","Processo concluído.");
	
	ULOG_END("implWFPsqGru::Execute()");

}

BEGIN_DECLARE_ROUTER_PARM(PsqGruCon)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqGruCon,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqGruCon,1)
	proCConsultaWFGrupos(ac->var.xml);
END_DECLARE_ROUTER_INTERF

