/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:38 $
 **/ 


#include "../include/cWFAtendimentoPesquisaAtual.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPqA);

void implWFAtdPqA::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao;
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFAtendimentoPesquisaAtual cs(dnode, xml_g,getUser());

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
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}



BEGIN_DECLARE_ROUTER_PARM(WFAtdPqAIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqAAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqAExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqAConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPqAIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqAAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqAExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqAConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqAIncluir,1)
	cWFAtendimentoPesquisaAtual objeto(pWFAtdPqAIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqAAlterar,1)
	cWFAtendimentoPesquisaAtual objeto(pWFAtdPqAAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqAExcluir,1)
	cWFAtendimentoPesquisaAtual objeto(pWFAtdPqAExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqAConsultar,1)
	cWFAtendimentoPesquisaAtual objeto(pWFAtdPqAConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml,0);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
