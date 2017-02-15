
#include <tuxfw.h>
#include "../include/cWFAtendimentoDocTecnicoTipo.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdDocTecTipo);

void implWFAtdDocTecTipo::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoDocTecnicoTipo cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoDocTecnicoTipo' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecTipoIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecTipoAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecTipoExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocTecTipoConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdDocTecTipoIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecTipoAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecTipoExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocTecTipoConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecTipoIncluir,1)
	cWFAtendimentoDocTecnicoTipo objeto(pWFAtdDocTecTipoIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecTipoAlterar,1)
	cWFAtendimentoDocTecnicoTipo objeto(pWFAtdDocTecTipoAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecTipoExcluir,1)
	cWFAtendimentoDocTecnicoTipo objeto(pWFAtdDocTecTipoExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocTecTipoConsultar,1)
	cWFAtendimentoDocTecnicoTipo objeto(pWFAtdDocTecTipoConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF