/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:47 $
 **/


#include "../include/cWFAtendimentoCPFila.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdCPFila);

void implWFAtdCPFila::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = 0;
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	cWFAtendimentoCPFila cs(dnode, xml_g);

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

BEGIN_DECLARE_ROUTER_PARM(WFAtdCPrevioIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCPrevioAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCPrevioExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCPrevioConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdCPrevioIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCPrevioAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCPrevioExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCPrevioConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCPrevioIncluir,1)
	cWFAtendimentoCPFila objeto(pWFAtdCPrevioIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCPrevioAlterar,1)
	cWFAtendimentoCPFila objeto(pWFAtdCPrevioAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCPrevioExcluir,1)
	cWFAtendimentoCPFila objeto(pWFAtdCPrevioExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCPrevioConsultar,1)
	cWFAtendimentoCPFila objeto(pWFAtdCPrevioConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF