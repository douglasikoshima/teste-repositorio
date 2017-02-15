/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @remark  
 * @author  Charles Santos
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:37 $
 **/


#include "../include/cWFAtendimentoPausa.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPausa);

void implWFAtdPausa::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = 0;
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	cWFAtendimentoPausa cs(dnode, xml_g);

    switch (operacao)
    {
        case 'I':
		    cs.incluir();
        break;
        
	    case 'A':
		    cs.alterar();
        break;
	    
	    case 'E':
		    cs.excluir();
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

BEGIN_DECLARE_ROUTER_PARM(WFAtdPausaIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPausaAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPausaExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPausaConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPausaIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPausaAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPausaExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPausaConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPausaIncluir,1)
	cWFAtendimentoPausa objeto(pWFAtdPausaIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPausaAlterar,1)
	cWFAtendimentoPausa objeto(pWFAtdPausaAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPausaExcluir,1)
	cWFAtendimentoPausa objeto(pWFAtdPausaExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPausaConsultar,1)
	cWFAtendimentoPausa objeto(pWFAtdPausaConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF