
#include <tuxfw.h>
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE( WFTabSegment );

extern bool proCPesquisaTabelaSegmentacao( DOMNode*entrada, XMLGen* saida );


void implWFTabSegment::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFTabSegment::Execute()");
	setStatusCode("00I0000","Operação Concluida.");
	ULOG_END("implWFTabSegment::Execute()");
}

BEGIN_DECLARE_ROUTER_PARM(PsqTabSegment)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabSegment,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabSegment,1)
	proCPesquisaTabelaSegmentacao(pPsqTabSegment[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

