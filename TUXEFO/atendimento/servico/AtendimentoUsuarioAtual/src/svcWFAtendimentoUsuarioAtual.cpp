/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:29 $
 **/ 

#include <tuxfw.h>
#include "../include/cWFAtendimentoUsuarioAtual.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdUsA);

void implWFAtdUsA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    char operacao;
    char *p;
    int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

    cWFAtendimentoUsuarioAtual aua(dnode, xml_g);

    switch (operacao)
    {
        case 'I':
            aua.incluir();
        break;

        case 'A':
            ret = aua.alterar();
        break;

        case 'E':
            ret = aua.excluir();
        break;

        // case 'C':
        //     aua.consultar();
        // break;

        default:
            ret = -2;
            setStatusCode("04E0001","Operacao invalida");
        break;
    }

    if (ret >= 0)
    {
        setStatusCode("04I0000","Processo concluído.");
    }
}

BEGIN_DECLARE_ROUTER_PARM(WFAtdUsAIncluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdUsAAlterar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdUsAExcluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdUsAConsultar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdUsAIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdUsAAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdUsAExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdUsAConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdUsAIncluir,1)
    cWFAtendimentoUsuarioAtual objeto(pWFAtdUsAIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdUsAAlterar,1)
    cWFAtendimentoUsuarioAtual objeto(pWFAtdUsAAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdUsAExcluir,1)
    cWFAtendimentoUsuarioAtual objeto(pWFAtdUsAExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.excluirLogicamente();
END_DECLARE_ROUTER_INTERF

// BEGIN_DECLARE_ROUTER_INTERF(WFAtdUsAConsultar,1)
// 	cWFAtendimentoUsuarioAtual objeto(pWFAtdUsAConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
// 	objeto.consultar();
// END_DECLARE_ROUTER_INTERF
