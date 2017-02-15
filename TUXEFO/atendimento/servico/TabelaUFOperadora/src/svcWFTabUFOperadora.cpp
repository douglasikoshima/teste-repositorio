
#include <tuxfw.h>
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFTabUFOper);

extern bool proCPesquisaTabelaUFRegional( DOMNode*entrada,XMLGen* saida );
extern bool proCPesquisaOperadoras( DOMNode*entrada,XMLGen* saida );
  


void implWFTabUFOper::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFTabUFOper::Execute()");
	setStatusCode("00I0000","Operação Concluida.");
	ULOG_END("implWFTabUFOper::Execute()");
}

BEGIN_DECLARE_ROUTER_PARM(PsqTabUFReg)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(PsqOperadora)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabUFReg,AC_XMLGEN)
DECLARE_RETURN_TYPE(PsqOperadora,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabUFReg,1)
	proCPesquisaTabelaUFRegional(pPsqTabUFReg[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(PsqOperadora,1)
	proCPesquisaOperadoras(pPsqOperadora[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF
