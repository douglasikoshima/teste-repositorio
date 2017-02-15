/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:49 $
 **/


#include "../include/cWFAtendimentoFrmCampo.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdFrC);

void implWFAtdFrC::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoFrmCampo frm(dnode, xml_g);

	if (operacao[0] == 'I')
		frm.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = frm.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = frm.excluir();
	}
	else if (operacao[0] == 'C')
		frm.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAtendimentoContatoComunic' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrCIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrCAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrCExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrCConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFObtemDominio)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdFrCIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrCAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrCExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrCConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFObtemDominio,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrCIncluir,1)
	cWFAtendimentoFrmCampo objeto(pWFAtdFrCIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrCAlterar,1)
	cWFAtendimentoFrmCampo objeto(pWFAtdFrCAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrCExcluir,1)
	cWFAtendimentoFrmCampo objeto(pWFAtdFrCExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrCConsultar,1)
	cWFAtendimentoFrmCampo objeto(pWFAtdFrCConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFObtemDominio,1)
	cWFAtendimentoFrmCampo objeto(pWFObtemDominio[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemDominio();
END_DECLARE_ROUTER_INTERF