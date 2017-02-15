/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
 **/


#include "../include/cWFAtdPriorizacao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPrioriza);

void implWFAtdPrioriza::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao = 0;
	cWFAtendimentoPriorizacao cs(dnode, xml_g);
	int ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );
	int idPessoaUsuario = atoi( getUser() );

	if (p)
	{
        operacao = *p;
		XMLString::release(&p);
	}

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
		    setStatusCode("04E0002","operação invalida");
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

BEGIN_DECLARE_ROUTER_PARM(WFAtdPriorizaIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPriorizaAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPriorizaExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPriorizaConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM


DECLARE_RETURN_TYPE(WFAtdPriorizaIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPriorizaAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPriorizaExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPriorizaConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPriorizaIncluir,1)
	cWFAtendimentoPriorizacao objeto(pWFAtdPriorizaIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPriorizaAlterar,1)
	cWFAtendimentoPriorizacao objeto(pWFAtdPriorizaAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPriorizaExcluir,1)
	cWFAtendimentoPriorizacao objeto(pWFAtdPriorizaExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPriorizaConsultar,1)
	cWFAtendimentoPriorizacao objeto(pWFAtdPriorizaConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
