/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:11 $
 **/


#include "../include/cWFAtendimentoDocumento.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdDoc);

void implWFAtdDoc::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoDocumento cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoDocumento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdDocConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdDocIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdDocConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocIncluir,1)
	cWFAtendimentoDocumento objeto(pWFAtdDocIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocAlterar,1)
	cWFAtendimentoDocumento objeto(pWFAtdDocAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocExcluir,1)
	cWFAtendimentoDocumento objeto(pWFAtdDocExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdDocConsultar,1)
	cWFAtendimentoDocumento objeto(pWFAtdDocConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF