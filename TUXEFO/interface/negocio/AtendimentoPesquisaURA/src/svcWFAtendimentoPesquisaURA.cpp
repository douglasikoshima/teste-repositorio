/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:05 $
 **/ 


#include "../include/cWFAtendimentoPesquisaURA.h"
#include "RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdPqU);

void implWFAtdPqU::Execute(DOMNode*dnode,XMLGen*xml_g) {
	char operacao[2];
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
		XMLString::release(&p);
	}


	cWFAtendimentoPesquisaURA cs(dnode, xml_g);

	if      (operacao[0] == 'I')	cs.incluir();
	else if (operacao[0] == 'A') 	ret = cs.alterar();
	else if (operacao[0] == 'E')	ret = cs.excluir();
	else if (operacao[0] == 'C')	cs.consultar();

	if (ret == -1)		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	if (ret >=  0)		setStatusCode("04I0000","Processo concluído.");
}


BEGIN_DECLARE_ROUTER_PARM(WFAtdPqUIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqUAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqUExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdPqUConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdPqUIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqUAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqUExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdPqUConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqUIncluir,1)
	cWFAtendimentoPesquisaURA objeto(pWFAtdPqUIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqUAlterar,1)
	cWFAtendimentoPesquisaURA objeto(pWFAtdPqUAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqUExcluir,1)
	cWFAtendimentoPesquisaURA objeto(pWFAtdPqUExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdPqUConsultar,1)
	cWFAtendimentoPesquisaURA objeto(pWFAtdPqUConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
