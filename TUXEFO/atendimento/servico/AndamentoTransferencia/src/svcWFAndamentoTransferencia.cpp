/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/

#include "../include/cWFAndamentoTransferencia.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAndTrf);

void implWFAndTrf::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = -1;
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
        operacao = *p;
		XMLString::release(&p);
	}

	cWFAndamentoTransferencia cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAndamento' é obrigatório para essa operação.");
	}
    else if ( ret == -2 )
    {
		setStatusCode("04E0002","Operação invalida");
    }
	else if ( ret >= 0 )
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}


BEGIN_DECLARE_ROUTER_PARM(WFAndTrfIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndTrfAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndTrfExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndTrfConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFObtemAndTrf)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAndTrfIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndTrfAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndTrfExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndTrfConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFObtemAndTrf,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAndTrfIncluir,1)
	cWFAndamentoTransferencia objeto(pWFAndTrfIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndTrfAlterar,1)
	cWFAndamentoTransferencia objeto(pWFAndTrfAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndTrfExcluir,1)
	cWFAndamentoTransferencia objeto(pWFAndTrfExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndTrfConsultar,1)
	cWFAndamentoTransferencia objeto(pWFAndTrfConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFObtemAndTrf,1)
	cWFAndamentoTransferencia objeto(pWFObtemAndTrf[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemTrf();
END_DECLARE_ROUTER_INTERF