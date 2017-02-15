/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:07 $
 **/


#include "../include/cWFAtendimentoConta.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdCnt);

void implWFAtdCnt::Execute(DOMNode*dnode,XMLGen*xml_g) {
	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoConta cs(dnode, xml_g);

	if      (operacao[0] == 'I')	cs.incluir();
	else if (operacao[0] == 'A') 	ret = cs.alterar();
	else if (operacao[0] == 'E')	ret = cs.excluir();
	else if (operacao[0] == 'C')	cs.consultar();

	if (ret == -1)		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	if (ret >=  0)		setStatusCode("04I0000","Processo concluído.");
}

BEGIN_DECLARE_ROUTER_PARM(WFAtdCntIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCntAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCntExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCntConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdCntIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCntAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCntExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCntConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCntIncluir,1)
	cWFAtendimentoConta objeto(pWFAtdCntIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCntAlterar,1)
	cWFAtendimentoConta objeto(pWFAtdCntAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCntExcluir,1)
	cWFAtendimentoConta objeto(pWFAtdCntExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCntConsultar,1)
	cWFAtendimentoConta objeto(pWFAtdCntConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF