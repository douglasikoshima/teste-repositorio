/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:36 $
 **/ 


#include "../include/cWFAtendPesquisaSatisfa.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPqS);

void implWFAtdPqS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    char operacao;
    int  ret = 0;

    char *p = walkTree(dnode,"tipoOperacao",0);

    if (p)
    {
        operacao = *p;
        XMLString::release(&p);
    }

    cWFAtendimentoPesquisaSatisfa cs(dnode, xml_g);

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
        setStatusCode("04E0001","O atributo 'idAtendimentoPesquisaSatisfa' é obrigatório para essa operação.");
    }
    else if (ret >= 0)
    {
        setStatusCode("04I0000","Processo concluído.");
    }
}


BEGIN_DECLARE_ROUTER_PARM(WFAtdPqSIncluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqSAlterar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqSExcluir)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqSConsultar)
    ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPqSIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqSAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqSExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqSConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqSIncluir,1)
    cWFAtendimentoPesquisaSatisfa objeto(pWFAtdPqSIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqSAlterar,1)
    cWFAtendimentoPesquisaSatisfa objeto(pWFAtdPqSAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqSExcluir,1)
    cWFAtendimentoPesquisaSatisfa objeto(pWFAtdPqSExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqSConsultar,1)
    cWFAtendimentoPesquisaSatisfa objeto(pWFAtdPqSConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
    objeto.consultar();
END_DECLARE_ROUTER_INTERF
