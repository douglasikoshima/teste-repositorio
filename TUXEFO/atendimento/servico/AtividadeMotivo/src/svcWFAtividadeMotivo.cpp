/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:29 $
 **/ 

#include "../include/cWFAtividadeMotivo.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(AtividadeMotivo);

void implAtividadeMotivo::Execute(DOMNode* dnode,XMLGen* xml_g)
{
	char operacao=0;
	int ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFAtividadeMotivo ao(dnode, xml_g);

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

        default:
            setStatusCode("04E0002","Operacao invalida");
            ret = -2;
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAtividadeMotivo' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}


BEGIN_DECLARE_ROUTER_PARM(AtividadeMotivoIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtividadeMotivoAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(AtividadeMotivoExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AtividadeMotivoIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtividadeMotivoAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(AtividadeMotivoExcluir,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(AtividadeMotivoIncluir,1)
	cWFAtividadeMotivo objeto(pAtividadeMotivoIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtividadeMotivoAlterar,1)
	cWFAtividadeMotivo objeto(pAtividadeMotivoAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(AtividadeMotivoExcluir,1)
	cWFAtividadeMotivo objeto(pAtividadeMotivoExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF
