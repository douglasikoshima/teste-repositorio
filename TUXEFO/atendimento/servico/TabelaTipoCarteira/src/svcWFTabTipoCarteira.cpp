
#include <tuxfw.h>
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFTabTipoCart);

extern bool proCPesquisaTabelaTipoCarteira(DOMNode*entrada,XMLGen* saida);


void implWFTabTipoCart::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFTabTipoCart::Execute()");
	setStatusCode("00I0000","Operação Concluida.");
	ULOG_END("implWFTabTipoCart::Execute()");
}

BEGIN_DECLARE_ROUTER_PARM(PsqTabTpCart)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabTpCart,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabTpCart,1)
	proCPesquisaTabelaTipoCarteira(pPsqTabTpCart[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

