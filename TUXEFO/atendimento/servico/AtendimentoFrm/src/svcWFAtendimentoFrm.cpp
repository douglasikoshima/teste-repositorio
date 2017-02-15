/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:30 $
 **/


#include "../include/cWFAtendimentoFrm.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdFrm);

void implWFAtdFrm::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoFrm frm(dnode, xml_g);

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
	else if (operacao[0] == 'F')
		frm.obtemFormulario();
	else if (operacao[0] == 'P')
		frm.obtemFormularioPesquisa();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAtendimentoFrm' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrmIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrmAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrmExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdFrmConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdFrmIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrmAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrmExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdFrmConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrmIncluir,1)
	cWFAtendimentoFrm objeto(pWFAtdFrmIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrmAlterar,1)
	cWFAtendimentoFrm objeto(pWFAtdFrmAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrmExcluir,1)
	cWFAtendimentoFrm objeto(pWFAtdFrmExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdFrmConsultar,1)
	cWFAtendimentoFrm objeto(pWFAtdFrmConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF


// Obtem o formulário dinamico.
BEGIN_DECLARE_ROUTER_PARM(ObtemFormulario)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ObtemFormulario,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ObtemFormulario,1)
	cWFAtendimentoFrm objeto(pObtemFormulario[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemFormulario();
END_DECLARE_ROUTER_INTERF

// Obtem o campo dinamico.
BEGIN_DECLARE_ROUTER_PARM(ObtemCpDin)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ObtemCpDin,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ObtemCpDin,1)
	cWFAtendimentoFrm objeto(pObtemCpDin[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemCampoDinamico();
END_DECLARE_ROUTER_INTERF

// Obtem campos para pesquisa dinamica.
BEGIN_DECLARE_ROUTER_PARM(ObtemFrmPsq)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(ObtemFrmPsq,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(ObtemFrmPsq,1)
	cWFAtendimentoFrm objeto(pObtemFrmPsq[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemFormularioPesquisa();
END_DECLARE_ROUTER_INTERF

