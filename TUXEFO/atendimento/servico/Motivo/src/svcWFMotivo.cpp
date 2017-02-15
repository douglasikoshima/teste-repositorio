/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:14 $
 **/

#include "../include/cWFMotivo.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFMotivo);

void implWFMotivo::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFMotivo::Execute()");
	char operacao=0;
	int ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFMotivo ao(dnode, xml_g);

    switch(operacao)
    {
        case 'I':
            ao.incluir();
        break;

        case 'A':
            ret = ao.alterar();
        break;

        case 'E':
            ret = ao.excluir();
        break;

        case 'C':
            ao.consultar();
        break;

        default:
            setStatusCode("04E0002","Operacao invalida");
            ret = -2;
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idMotivo' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
    ULOG_END("implWFMotivo::Execute()");
}


BEGIN_DECLARE_ROUTER_PARM(WFMotivoIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFMotivoAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFMotivoExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFMotivoConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFMotivoIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFMotivoAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFMotivoExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFMotivoConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFMotivoIncluir,1)
	cWFMotivo objeto(pWFMotivoIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFMotivoAlterar,1)
	cWFMotivo objeto(pWFMotivoAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFMotivoExcluir,1)
	cWFMotivo objeto(pWFMotivoExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFMotivoConsultar,1)
	cWFMotivo objeto(pWFMotivoConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF