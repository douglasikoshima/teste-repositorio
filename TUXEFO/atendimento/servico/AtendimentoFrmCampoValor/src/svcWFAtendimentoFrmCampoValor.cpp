/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:53 $
 **/


#include "../include/cWFAtendimentoFrmCampoValor.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdFCV);

void implWFAtdFCV::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoFrmCampoValor frm(dnode, xml_g);

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

BEGIN_DECLARE_ROUTER_PARM(WFAtdFCVIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFCVAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFCVExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFCVConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemAtdFrmCpVl)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdFCVIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFCVAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFCVExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFCVConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemAtdFrmCpVl,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFCVIncluir,1)
	cWFAtendimentoFrmCampoValor objeto(pWFAtdFCVIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFCVAlterar,1)
	cWFAtendimentoFrmCampoValor objeto(pWFAtdFCVAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFCVExcluir,1)
	cWFAtendimentoFrmCampoValor objeto(pWFAtdFCVExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFCVConsultar,1)
	cWFAtendimentoFrmCampoValor objeto(pWFAtdFCVConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemAtdFrmCpVl,1)
	cWFAtendimentoFrmCampoValor objeto(pObtemAtdFrmCpVl[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemAtdFrmCpVl();
END_DECLARE_ROUTER_INTERF