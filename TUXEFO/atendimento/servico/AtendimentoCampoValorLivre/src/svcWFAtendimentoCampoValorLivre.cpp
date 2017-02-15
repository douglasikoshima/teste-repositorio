/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:10 $
 **/


#include "../include/cWFAtendCampoValorLivre.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdCVL);

void implWFAtdCVL::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}

	cWFAtendimentoCampoValorLivre cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoCampo' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdCVLIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCVLAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCVLExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdCVLConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdCVLIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCVLAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCVLExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdCVLConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCVLIncluir,1)
	cWFAtendimentoCampoValorLivre objeto(pWFAtdCVLIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCVLAlterar,1)
	cWFAtendimentoCampoValorLivre objeto(pWFAtdCVLAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCVLExcluir,1)
	cWFAtendimentoCampoValorLivre objeto(pWFAtdCVLExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdCVLConsultar,1)
	cWFAtendimentoCampoValorLivre objeto(pWFAtdCVLConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF