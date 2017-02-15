#include "../include/cWFSubEstado.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFSubEstado);

void implWFSubEstado::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFSubEstado::Execute()");
	char operacao[2];
   char *p;
	int ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFSubEstado sbes(dnode, xml_g);

	if (operacao[0] == 'I')
		sbes.incluir();
	else if (operacao[0] == 'A')
	{
		ret = sbes.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = sbes.excluir();
	}
	else if (operacao[0] == 'C')
		sbes.consultar();
	else if (operacao[0] == 'S')
		ret = sbes.consultarEstado();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idSubEstado' é obrigatório para essa operação.");
	}

	if (ret == -2) {
		setStatusCode("04E0002","O atributo 'idEstado' é obrigatório para essa operação.");
	}

	if (ret >= 0) {
		setStatusCode("04I0000","Processo concluído.");
	}
	
   ULOG_START("implWFSubEstado::Execute()");

}

BEGIN_DECLARE_ROUTER_PARM(SubEstadoInc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(SubEstadoAlt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(SubEstadoExc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(SubEstadoCon)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(SubEstadoConEst	)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(SubEstadoInc,AC_XMLGEN)
DECLARE_RETURN_TYPE(SubEstadoAlt,AC_XMLGEN)
DECLARE_RETURN_TYPE(SubEstadoExc,AC_XMLGEN)
DECLARE_RETURN_TYPE(SubEstadoCon,AC_XMLGEN)
DECLARE_RETURN_TYPE(SubEstadoConEst,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(SubEstadoInc,1)
	cWFSubEstado objeto(pSubEstadoInc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(SubEstadoAlt,1)
	cWFSubEstado objeto(pSubEstadoAlt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(SubEstadoExc,1)
	cWFSubEstado objeto(pSubEstadoExc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(SubEstadoCon,1)
	cWFSubEstado objeto(pSubEstadoCon[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(SubEstadoConEst,1)
	cWFSubEstado objeto(pSubEstadoConEst[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarEstado();
END_DECLARE_ROUTER_INTERF