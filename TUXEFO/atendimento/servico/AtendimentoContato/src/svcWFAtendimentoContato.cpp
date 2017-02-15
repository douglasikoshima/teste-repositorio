/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/


#include "../include/cWFAtendimentoContato.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdCto);

void implWFAtdCto::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoContato cs(dnode, xml_g);

	if (operacao[0] == 'I')
		cs.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = cs.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = cs.excluir();
	}
	else if (operacao[0] == 'C')
		cs.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdCtoIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCtoAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCtoExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCtoConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdCtoIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCtoAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCtoExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCtoConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCtoIncluir,1)
	cWFAtendimentoContato objeto(pWFAtdCtoIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCtoAlterar,1)
	cWFAtendimentoContato objeto(pWFAtdCtoAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCtoExcluir,1)
	cWFAtendimentoContato objeto(pWFAtdCtoExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCtoConsultar,1)
	cWFAtendimentoContato objeto(pWFAtdCtoConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF