/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:49 $
 **/

#include "../include/cWFAndamentoMotivo.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAndMot);

void implWFAndMot::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAndamentoMotivo ao(dnode, xml_g);

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

BEGIN_DECLARE_ROUTER_PARM(WFAndMotIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndMotAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndMotExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndMotConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAndMotIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndMotAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndMotExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndMotConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAndMotIncluir,1)
	cWFAndamentoMotivo objeto(pWFAndMotIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndMotAlterar,1)
	cWFAndamentoMotivo objeto(pWFAndMotAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndMotExcluir,1)
	cWFAndamentoMotivo objeto(pWFAndMotExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndMotConsultar,1)
	cWFAndamentoMotivo objeto(pWFAndMotConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF