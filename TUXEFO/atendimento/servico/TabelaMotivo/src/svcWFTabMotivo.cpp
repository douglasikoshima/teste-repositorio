/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:31 $
 **/

#include <tuxfw.h>
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFTabMotivo);

extern bool proCPesquisaTabelaMotivo(DOMNode*entrada,XMLGen* saida);
extern bool proCPesquisaMotivoAtividade(DOMNode*entrada,XMLGen* saida);


void implWFTabMotivo::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFTabMotivo::Execute()");
//	proCAltStatusUsuario(dnode);
	setStatusCode("00I0000","Operação Concluida.");
	ULOG_END("implWFTabMotivo::Execute()");
}

BEGIN_DECLARE_ROUTER_PARM(PsqTabMotivo)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabMotivo,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabMotivo,1)
	proCPesquisaTabelaMotivo(pPsqTabMotivo[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_PARM(PsqTabMotAt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(PsqTabMotAt,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(PsqTabMotAt,1)
	proCPesquisaMotivoAtividade(pPsqTabMotAt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
END_DECLARE_ROUTER_INTERF

