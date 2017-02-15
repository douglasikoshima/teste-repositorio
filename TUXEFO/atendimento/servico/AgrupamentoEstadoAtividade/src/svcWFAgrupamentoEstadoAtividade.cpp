/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/


#include "../include/cWFAgrupEstadoAtividade.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAgrEsA);

void implWFAgrEsA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFAgrEsA::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFAgrupamentoEstadoAtividade cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAgrupamentoEstadoAtividade' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

   ULOG_END("implWFAgrEsA::Execute()");
}


BEGIN_DECLARE_ROUTER_PARM(WFAgrEsAIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrEsAAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrEsAExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrEsAConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAgrEsAIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrEsAAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrEsAExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrEsAConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAgrEsAIncluir,1)
	cWFAgrupamentoEstadoAtividade cs(pWFAgrEsAIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrEsAAlterar,1)
	cWFAgrupamentoEstadoAtividade cs(pWFAgrEsAAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrEsAExcluir,1)
	cWFAgrupamentoEstadoAtividade cs(pWFAgrEsAExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrEsAConsultar,1)
	cWFAgrupamentoEstadoAtividade cs(pWFAgrEsAConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.consultar();
END_DECLARE_ROUTER_INTERF

