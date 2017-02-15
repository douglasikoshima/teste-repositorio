/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:42 $
 **/


#include "../include/cWFAtendimentoSuspeito.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdSusp);

void implWFAtdSusp::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = 0;
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	cWFAtendimentoSuspeito cs(dnode, xml_g);

    switch (operacao)
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
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}
	else if (ret == -2)
    {
		setStatusCode("04E0002","Operacao invalida");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}

BEGIN_DECLARE_ROUTER_PARM(WFAtdSuspIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdSuspAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdSuspExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdSuspConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdSuspIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdSuspAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdSuspExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdSuspConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdSuspIncluir,1)
	cWFAtendimentoSuspeito objeto(pWFAtdSuspIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdSuspAlterar,1)
	cWFAtendimentoSuspeito objeto(pWFAtdSuspAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdSuspExcluir,1)
	cWFAtendimentoSuspeito objeto(pWFAtdSuspExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdSuspConsultar,1)
	cWFAtendimentoSuspeito objeto(pWFAtdSuspConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF