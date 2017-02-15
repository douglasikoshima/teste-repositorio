/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:55 $
 **/ 


#include "../include/cWFAtendimentoPesquisaResp.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPqR);

void implWFAtdPqR::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    char operacao;
    int  ret = 0;

    char* p = walkTree( dnode, "tipoOperacao", 0 );

    if (p)
    {
        operacao = *p;
        XMLString::release(&p);
    }

    cWFAtendimentoPesquisaResp cs(dnode, xml_g,getUser());

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
        setStatusCode("04E0001","O atributo 'idAtendimentoPesquisaResp' é obrigatório para essa operação.");
    }
    else if (ret >= 0)
    {
        setStatusCode("04I0000","Processo concluído.");
    }
}


BEGIN_DECLARE_ROUTER_PARM(WFAtdPqRIncluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqRAlterar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqRExcluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqRConsultar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPqRIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqRAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqRExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqRConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqRIncluir,1)
    cWFAtendimentoPesquisaResp objeto(pWFAtdPqRIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
    objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqRAlterar,1)
    cWFAtendimentoPesquisaResp objeto(pWFAtdPqRAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
    objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqRExcluir,1)
    cWFAtendimentoPesquisaResp objeto(pWFAtdPqRExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
    objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqRConsultar,1)
    cWFAtendimentoPesquisaResp objeto(pWFAtdPqRConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
    objeto.consultar();
END_DECLARE_ROUTER_INTERF
