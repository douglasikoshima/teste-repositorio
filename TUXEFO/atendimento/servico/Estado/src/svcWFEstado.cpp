#include "tuxfw.h"
#include "../include/cWFEstado.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFEstado);

void implWFEstado::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFEstado::Execute()");     
   
	char operacao;
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	cWFEstado es(dnode,xml_g);

    switch (operacao)
    {
        case 'I':
		    es.incluir();
        break;
        case 'A':
		    ret = es.alterar();
        break;
        case 'E':
		    ret = es.excluir();
        break;
        case 'C':
		    es.consultar();
        break;
        default:
		    ret = -2;
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idEstado' é obrigatório para essa operação.");
	}
	else if (ret == -2)
    {
		setStatusCode("04E0002","Operação invalida");
	}
	else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }

    ULOG_END("implWFEstado::Execute()");     
}

BEGIN_DECLARE_ROUTER_PARM(EstadoInc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(EstadoAlt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(EstadoExc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(EstadoCon)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(PesqEstado)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(EstadoInc,AC_XMLGEN)
DECLARE_RETURN_TYPE(EstadoAlt,AC_XMLGEN)
DECLARE_RETURN_TYPE(EstadoExc,AC_XMLGEN)
DECLARE_RETURN_TYPE(EstadoCon,AC_XMLGEN)
DECLARE_RETURN_TYPE(PesqEstado,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(EstadoInc,1)
	cWFEstado objeto(pEstadoInc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(EstadoAlt,1)
	cWFEstado objeto(pEstadoAlt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(EstadoExc,1)
	cWFEstado objeto(pEstadoExc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(EstadoCon,1)
	cWFEstado objeto(pEstadoCon[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(PesqEstado,1)
	cWFEstado objeto(pPesqEstado[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.PesquisaTodos();
END_DECLARE_ROUTER_INTERF