/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:26 $
 **/

#include <tuxfw.h>
#include "../include/cWFAtendimentoBaixa.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdBaixa);

void implWFAtdBaixa::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoBaixa ao(dnode, xml_g);

	if (operacao[0] == 'I')
		ao.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = ao.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = ao.excluir();
	}
	else if (operacao[0] == 'C')
		ao.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idContato' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaLista)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBaixaContato)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdContatoLista)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdContatoFolha)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdContatoRaiz)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdBxaHrq)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCtoHrq)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdBaixaIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBaixaAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBaixaExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBaixaConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBaixaLista,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBaixaContato,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdContatoLista,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdContatoFolha,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdContatoRaiz,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdBxaHrq,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCtoHrq,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaIncluir,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaAlterar,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaExcluir,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaConsultar,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaLista,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaLista[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemBLista();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBaixaContato,1)
	cWFAtendimentoBaixa objeto(pWFAtdBaixaContato[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemBContato();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdContatoLista,1)
	cWFAtendimentoBaixa objeto(pWFAtdContatoLista[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemCLista();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdContatoFolha,1)
	cWFAtendimentoBaixa objeto(pWFAtdContatoFolha[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemCFolha();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdContatoRaiz,1)
	cWFAtendimentoBaixa objeto(pWFAtdContatoRaiz[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemCRaiz();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdBxaHrq,1)
	cWFAtendimentoBaixa objeto(pWFAtdBxaHrq[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemAtdHrq();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCtoHrq,1)
	cWFAtendimentoBaixa objeto(pWFAtdCtoHrq[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obtemAtdCtHrq();
END_DECLARE_ROUTER_INTERF