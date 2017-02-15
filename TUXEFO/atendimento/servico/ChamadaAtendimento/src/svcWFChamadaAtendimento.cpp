/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:21 $
 **/ 


#include "../include/cWFChamadaAtendimento.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFChaAte);

void implWFChaAte::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFChamadaAtendimento cs(dnode, xml_g);

	if      (operacao[0] == 'I')	cs.incluir();
	else if (operacao[0] == 'A') 	ret = cs.alterar();
	else if (operacao[0] == 'E')	ret = cs.excluir();
	else if (operacao[0] == 'C')	cs.consultar();

	if (ret == -1)		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	if (ret >=  0)		setStatusCode("04I0000","Processo concluído.");
}


BEGIN_DECLARE_ROUTER_PARM(WFChaAteIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaAteAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaAteExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaAteConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFChaAteIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaAteAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaAteExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaAteConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFChaAteIncluir,1)
	cWFChamadaAtendimento objeto(pWFChaAteIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaAteAlterar,1)
	cWFChamadaAtendimento objeto(pWFChaAteAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaAteExcluir,1)
	cWFChamadaAtendimento objeto(pWFChaAteExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaAteConsultar,1)
	cWFChamadaAtendimento objeto(pWFChaAteConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

// Retorna os atendimento ligados a chamada telefonica com o path do contato.
BEGIN_DECLARE_ROUTER_PARM(ChaAteContato)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ChaAteContato,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ChaAteContato,1)
	cWFChamadaAtendimento objeto(pChaAteContato[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarContato();
END_DECLARE_ROUTER_INTERF

