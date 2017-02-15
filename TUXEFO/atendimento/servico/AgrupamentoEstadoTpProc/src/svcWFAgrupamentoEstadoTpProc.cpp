/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:16 $
 **/


#include "../include/cWFAgrupamentoEstadoTpProc.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAgrETP);

void implWFAgrETP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFAgrETP::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;


    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFAgrupamentoEstadoTpProc cs(dnode, xml_g);

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
	else if (operacao[0] == 'P')
		cs.proximoAgrupamento();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAgrupamentoEstadoTpProc' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");
		
   ULOG_END("implWFAgrETP::Execute()");		

}


BEGIN_DECLARE_ROUTER_PARM(WFAgrETPIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrETPAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrETPExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAgrETPConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAgrETPIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrETPAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrETPExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAgrETPConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAgrETPIncluir,1)
	cWFAgrupamentoEstadoTpProc cs(pWFAgrETPIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrETPAlterar,1)
	cWFAgrupamentoEstadoTpProc cs(pWFAgrETPAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrETPExcluir,1)
	cWFAgrupamentoEstadoTpProc cs(pWFAgrETPExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAgrETPConsultar,1)
	cWFAgrupamentoEstadoTpProc cs(pWFAgrETPConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.consultar();
END_DECLARE_ROUTER_INTERF

// Esse método retorna o próximo estado do workflow de acordo com a atividade.
BEGIN_DECLARE_ROUTER_PARM(ProxAgrupamento)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ProxAgrupamento,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ProxAgrupamento,1)
	cWFAgrupamentoEstadoTpProc cs(pProxAgrupamento[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	cs.proximoAgrupamento();
END_DECLARE_ROUTER_INTERF
