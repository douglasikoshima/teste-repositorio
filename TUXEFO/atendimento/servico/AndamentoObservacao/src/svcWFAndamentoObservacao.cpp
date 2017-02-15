/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:49 $
 **/

#include "../include/cWFAndamentoObservacao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAndObs);

void implWFAndObs::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAndamentoObservacao ao(dnode, xml_g);

	if (operacao[0] == 'I')
		ao.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = ao.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = ao.excluir();
	}
	else if (operacao[0] == 'C')
		ao.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAndamento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAndObsIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndObsAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndObsExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndObsConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAndObsIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndObsAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndObsExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndObsConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAndObsIncluir,1)
	cWFAndamentoObservacao objeto(pWFAndObsIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndObsAlterar,1)
	cWFAndamentoObservacao objeto(pWFAndObsAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndObsExcluir,1)
	cWFAndamentoObservacao objeto(pWFAndObsExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndObsConsultar,1)
	cWFAndamentoObservacao objeto(pWFAndObsConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF