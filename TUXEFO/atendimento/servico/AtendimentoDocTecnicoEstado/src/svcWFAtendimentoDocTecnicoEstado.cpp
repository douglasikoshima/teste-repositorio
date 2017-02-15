
#include <tuxfw.h>
#include "../include/cWFAtendimentoDocTecnicoEstado.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdDocTecEst);

void implWFAtdDocTecEst::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoDocTecnicoEstado cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoDocTecnicoEstado' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecEstIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecEstAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecEstExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecEstConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdDocTecEstIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecEstAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecEstExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecEstConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecEstIncluir,1)
	cWFAtendimentoDocTecnicoEstado objeto(pWFAtdDocTecEstIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecEstAlterar,1)
	cWFAtendimentoDocTecnicoEstado objeto(pWFAtdDocTecEstAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecEstExcluir,1)
	cWFAtendimentoDocTecnicoEstado objeto(pWFAtdDocTecEstExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecEstConsultar,1)
	cWFAtendimentoDocTecnicoEstado objeto(pWFAtdDocTecEstConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF