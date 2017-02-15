/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:56 $
 **/ 


#include "../include/cWFRouterAdmin.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFRouAdm);

void implWFRouAdm::Execute(DOMNode*dnode,XMLGen*xml_g)
{
        ULOG_START("implWFRouAdm::Execute()");
		cWFRouterAdmin objeto(dnode,xml_g);
		objeto.alterar();
		ULOG_END("implWFRouAdm::Execute()");

}

// Processa a inclusao do script.
BEGIN_DECLARE_ROUTER_PARM(RouAdmIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(RouAdmIncluir,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(RouAdmIncluir,1)
	cWFRouterAdmin objeto(pRouAdmIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

// Processa a alteracao do script.
BEGIN_DECLARE_ROUTER_PARM(RouAdmAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(RouAdmAlterar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(RouAdmAlterar,1)
	cWFRouterAdmin objeto(pRouAdmAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF
