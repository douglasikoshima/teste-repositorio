/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:32 $
 **/ 
 

#include "../include/cWFAtendimentoGrupoAtual.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdGrA);

void implWFAtdGrA::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoGrupoAtual aga(dnode, xml_g);

	if (operacao[0] == 'I')
		aga.incluir();
	else if (operacao[0] == 'A') 
	{
		ret = aga.alterar();
	}
	else if (operacao[0] == 'E')
	{
		ret = aga.excluir();
	}
	else if (operacao[0] == 'C')
		aga.consultar();

	if (ret == -1) {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdGrAIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdGrAAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdGrAExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdGrAConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemGrAt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdGrAIncluir,  AC_XMLGEN);
DECLARE_RETURN_TYPE(WFAtdGrAAlterar,  AC_XMLGEN);
DECLARE_RETURN_TYPE(WFAtdGrAExcluir,  AC_XMLGEN);
DECLARE_RETURN_TYPE(WFAtdGrAConsultar,AC_XMLGEN);
DECLARE_RETURN_TYPE(ObtemGrAt,AC_XMLGEN);

BEGIN_DECLARE_ROUTER_INTERF(WFAtdGrAIncluir,1)
	cWFAtendimentoGrupoAtual aga(pWFAtdGrAIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdGrAAlterar,1)
	cWFAtendimentoGrupoAtual aga(pWFAtdGrAAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdGrAExcluir,1)
	cWFAtendimentoGrupoAtual aga(pWFAtdGrAExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.excluirLogicamente();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdGrAConsultar,1)
	cWFAtendimentoGrupoAtual aga(pWFAtdGrAConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemGrAt,1)
	cWFAtendimentoGrupoAtual aga(pObtemGrAt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.ObtemGrAt();
END_DECLARE_ROUTER_INTERF


BEGIN_DECLARE_ROUTER_PARM(AtuEntradaFila)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(AtuEntradaFila,AC_XMLGEN);

BEGIN_DECLARE_ROUTER_INTERF(AtuEntradaFila,1)
	cWFAtendimentoGrupoAtual aga(pAtuEntradaFila[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	aga.AtuEntradaFila();
END_DECLARE_ROUTER_INTERF


