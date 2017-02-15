/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:12 $
 **/ 


#include "../include/cWFFluxoFuncao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFFluFun);

void implWFFluFun::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFFluFun::Execute()");
	char operacao=0;
   char *p;
	int ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	cWFFluxoFuncao cs(dnode, xml_g);

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
		    setStatusCode("04E0002","Operacao invalida");
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idFluxoFuncao' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
    
    ULOG_END("implWFFluFun::Execute()");
    
}


BEGIN_DECLARE_ROUTER_PARM(WFFluFunIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFunAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFunExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFunConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFFluFunIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFunAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFunExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFunConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFFluFunIncluir,1)
	cWFFluxoFuncao objeto(pWFFluFunIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFunAlterar,1)
	cWFFluxoFuncao objeto(pWFFluFunAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFunExcluir,1)
	cWFFluxoFuncao objeto(pWFFluFunExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFunConsultar,1)
	cWFFluxoFuncao objeto(pWFFluFunConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
