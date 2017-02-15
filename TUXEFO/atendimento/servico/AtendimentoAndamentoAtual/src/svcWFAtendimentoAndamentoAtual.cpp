/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:55 $
 **/

#include <tuxfw.h>
#include "../include/cWFAtendimentoAndamentoAtual.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdAnA);

void implWFAtdAnA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao=0;
	int ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFAtendimentoAndamentoAtual cs(dnode, xml_g);

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
            setStatusCode("04E0002","Operacao invalida");
            ret = -2;
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

BEGIN_DECLARE_ROUTER_PARM(WFAtdAnAIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdAnAAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdAnAExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdAnAConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdAnAIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdAnAAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdAnAExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdAnAConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdAnAIncluir,1)
	cWFAtendimentoAndamentoAtual objeto(pWFAtdAnAIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdAnAAlterar,1)
	cWFAtendimentoAndamentoAtual objeto(pWFAtdAnAAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdAnAExcluir,1)
	cWFAtendimentoAndamentoAtual objeto(pWFAtdAnAExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdAnAConsultar,1)
	cWFAtendimentoAndamentoAtual objeto(pWFAtdAnAConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF