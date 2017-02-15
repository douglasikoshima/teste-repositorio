#include <tuxfw.h>
#include "../include/cWFAgrupamentoEstado.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAgrEstado);

void implWFAgrEstado::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFAgrEstado::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFAgrupamentoEstado aes(dnode, xml_g);

	if (operacao[0] == 'I')
		aes.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = aes.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = aes.excluir();
	}
	else if (operacao[0] == 'C')
		aes.consultar();
	else if (operacao[0] == 'D')
		aes.consultarDetalhado();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAgrupamentoEstado' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");
		
   ULOG_END("implWFAgrEstado::Execute()");		
}


BEGIN_DECLARE_ROUTER_PARM(AgrEstadoInc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AgrEstadoAlt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AgrEstadoExc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AgrEstadoCon)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AgrEstadoDet)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AgrEstadoInc,AC_XMLGEN)
DECLARE_RETURN_TYPE(AgrEstadoAlt,AC_XMLGEN)
DECLARE_RETURN_TYPE(AgrEstadoExc,AC_XMLGEN)
DECLARE_RETURN_TYPE(AgrEstadoCon,AC_XMLGEN)
DECLARE_RETURN_TYPE(AgrEstadoDet,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(AgrEstadoInc,1)
	cWFAgrupamentoEstado objeto(pAgrEstadoInc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AgrEstadoAlt,1)
	cWFAgrupamentoEstado objeto(pAgrEstadoAlt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AgrEstadoExc,1)
	cWFAgrupamentoEstado objeto(pAgrEstadoExc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AgrEstadoCon,1)
	cWFAgrupamentoEstado objeto(pAgrEstadoCon[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AgrEstadoDet,1)
	cWFAgrupamentoEstado objeto(pAgrEstadoDet[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultarDetalhado();
END_DECLARE_ROUTER_INTERF
