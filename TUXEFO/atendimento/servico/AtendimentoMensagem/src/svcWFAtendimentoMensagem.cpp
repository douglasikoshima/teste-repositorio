/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:33 $
 **/ 


#include "../include/cWFAtendimentoMensagem.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdMsg);

void implWFAtdMsg::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoMensagem cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoMensagem' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}


BEGIN_DECLARE_ROUTER_PARM(WFAtdMsgIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdMsgAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdMsgExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdMsgConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFObterAtdMsg)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdMsgIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdMsgAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdMsgExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdMsgConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFObterAtdMsg,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdMsgIncluir,1)
	cWFAtendimentoMensagem objeto(pWFAtdMsgIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdMsgAlterar,1)
	cWFAtendimentoMensagem objeto(pWFAtdMsgAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdMsgExcluir,1)
	cWFAtendimentoMensagem objeto(pWFAtdMsgExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdMsgConsultar,1)
	cWFAtendimentoMensagem objeto(pWFAtdMsgConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFObterAtdMsg,1)
	cWFAtendimentoMensagem objeto(pWFObterAtdMsg[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.obterMensagem();
END_DECLARE_ROUTER_INTERF
