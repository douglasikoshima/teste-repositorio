
#include <tuxfw.h>
#include "../include/cWFApoioProcedencia.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFApoioProced);

void implWFApoioProced::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFApoioProcedencia cs(dnode, xml_g);

	if (operacao[0] == 'I')
		cs.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = cs.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = cs.excluir();
	}
	else if (operacao[0] == 'C')
		cs.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idApoioProcedencia' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFApoioProcedIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFApoioProcedAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFApoioProcedExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFApoioProcedConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFApoioProcedIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFApoioProcedAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFApoioProcedExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFApoioProcedConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFApoioProcedIncluir,1)
	cWFApoioProcedencia objeto(pWFApoioProcedIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFApoioProcedAlterar,1)
	cWFApoioProcedencia objeto(pWFApoioProcedAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFApoioProcedExcluir,1)
	cWFApoioProcedencia objeto(pWFApoioProcedExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFApoioProcedConsultar,1)
	cWFApoioProcedencia objeto(pWFApoioProcedConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF