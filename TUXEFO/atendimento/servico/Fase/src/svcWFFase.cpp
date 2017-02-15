/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:26 $
 **/ 

#include "../include/cWFFase.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFFase);

void implWFFase::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFFase::Execute()");

    char operacao;
    char *p;
    int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }
    else
    {
        operacao = 0;
    }

    cWFFase cs(dnode, xml_g);

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
            setStatusCode("04E0002","Operação invalida");
            ret = -2;
        break;
    }

    if (ret == -1)
    {
        setStatusCode("04E0001","O atributo 'idFase' é obrigatório para essa operação.");
    }

    if (ret >= 0)
    {
        setStatusCode("04I0000","Processo concluído.");
    }

   ULOG_END("implWFFase::Execute()");
}


BEGIN_DECLARE_ROUTER_PARM(WFFaseIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFaseAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFaseExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFaseConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFFaseIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFaseAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFaseExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFaseConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFFaseIncluir,1)
	cWFFase objeto(pWFFaseIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFaseAlterar,1)
	cWFFase objeto(pWFFaseAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFaseExcluir,1)
	cWFFase objeto(pWFFaseExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFaseConsultar,1)
	cWFFase objeto(pWFFaseConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
