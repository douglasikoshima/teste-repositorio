/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:20 $
 **/ 

#include <tuxfw.h>
#include "../include/cWFCancelamentoSolicitado.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFCanSol);

void implWFCanSol::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao=0;
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao=*p;
        XMLString::release(&p);
    }

	cWFCancelamentoSolicitado cs(dnode, xml_g);

    switch(operacao)
    {
	    case 'I':
		    cs.incluir();
        break;
    
	    case 'A':
		    ret = cs.alterar();
        break;
	    
	    case 'E':
		    ret = cs.excluir();
        break;

	    case 'C':
		    cs.consultar();
        break;

        default:
            ret = -2;
    		setStatusCode("04E0002","Operacao invalida");
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}

BEGIN_DECLARE_ROUTER_PARM(WFCanSolIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFCanSolAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFCanSolExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFCanSolConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFCanSolIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFCanSolAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFCanSolExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFCanSolConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFCanSolIncluir,1)
	cWFCancelamentoSolicitado objeto(pWFCanSolIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFCanSolAlterar,1)
	cWFCancelamentoSolicitado objeto(pWFCanSolAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFCanSolExcluir,1)
	cWFCancelamentoSolicitado objeto(pWFCanSolExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFCanSolConsultar,1)
	cWFCancelamentoSolicitado objeto(pWFCanSolConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
