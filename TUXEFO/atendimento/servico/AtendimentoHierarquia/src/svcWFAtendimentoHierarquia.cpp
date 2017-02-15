/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:53 $
 **/ 


#include "../include/cWFAtendimentoHierarquia.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdHie);

void implWFAtdHie::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoHierarquia ah(dnode, xml_g);

	if (operacao[0] == 'I')
		ah.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = ah.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = ah.excluir();
	}
	else if (operacao[0] == 'C')
		ah.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdHieIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdHieAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdHieExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdHieConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdHieIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdHieAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdHieExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdHieConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdHieIncluir,1)
	cWFAtendimentoHierarquia objeto(pWFAtdHieIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdHieAlterar,1)
	cWFAtendimentoHierarquia objeto(pWFAtdHieAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdHieExcluir,1)
	cWFAtendimentoHierarquia objeto(pWFAtdHieExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdHieConsultar,1)
	cWFAtendimentoHierarquia objeto(pWFAtdHieConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF