/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:11 $
 **/ 


#include "../include/cWFAtendimentoPessoa.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPes);

void implWFAtdPes::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoPessoa cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idAtendimentoPessoa' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");

}

BEGIN_DECLARE_ROUTER_PARM(WFAtdPesIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPesAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPesExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPesConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemAtendPessoa)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemDocumentoPessoa)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemEnderecoPessoa)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPesIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPesAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPesExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPesConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemAtendPessoa,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemDocumentoPessoa,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemEnderecoPessoa,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPesIncluir,1)
	cWFAtendimentoPessoa objeto(pWFAtdPesIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPesAlterar,1)
	cWFAtendimentoPessoa objeto(pWFAtdPesAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPesExcluir,1)
	cWFAtendimentoPessoa objeto(pWFAtdPesExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPesConsultar,1)
	cWFAtendimentoPessoa objeto(pWFAtdPesConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemAtendPessoa,1)
	cWFAtendimentoPessoa objeto(pObtemAtendPessoa[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemAtendPessoa();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemDocumentoPessoa,1)
	cWFAtendimentoPessoa objeto(pObtemDocumentoPessoa[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemDocumentoPessoa();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemEnderecoPessoa,1)
	cWFAtendimentoPessoa objeto(pObtemEnderecoPessoa[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemEnderecoPessoa();
END_DECLARE_ROUTER_INTERF
