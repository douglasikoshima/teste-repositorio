
#include <tuxfw.h>
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFTabTipoSeq);

extern bool proCPesquisaTabelaTipoSequencia(DOMNode*entrada,XMLGen* saida);


void implWFTabTipoSeq::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implWFTabTipoSeq::Execute()");
	setStatusCode("00I0000","Operação Concluida.");
	ULOG_END("implWFTabTipoSeq::Execute()");
}

BEGIN_DECLARE_ROUTER_PARM(PsqTabTpSeq)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabTpSeq,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabTpSeq,1)
	proCPesquisaTabelaTipoSequencia(pPsqTabTpSeq[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

