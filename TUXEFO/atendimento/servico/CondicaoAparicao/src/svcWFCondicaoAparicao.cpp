/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/ 


#include "../include/cWFCondicaoAparicao.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFConApa);

void implWFConApa::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFConApa::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFCondicaoAparicao cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idCondicaoAparicao' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");
		
   ULOG_END("implWFConApa::Execute()");		

}


BEGIN_DECLARE_ROUTER_PARM(WFConApaIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFConApaAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFConApaExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFConApaConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFCndAcoes)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFCnd)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFConApaIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFConApaAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFConApaExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFConApaConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFCndAcoes,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFCnd,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFConApaIncluir,1)
	cWFCondicaoAparicao objeto(pWFConApaIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFConApaAlterar,1)
	cWFCondicaoAparicao objeto(pWFConApaAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFConApaExcluir,1)
	cWFCondicaoAparicao objeto(pWFConApaExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFConApaConsultar,1)
	cWFCondicaoAparicao objeto(pWFConApaConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemWFCndAcoes,1)
	cWFCondicaoAparicao objeto(pObtemWFCndAcoes[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFCndAcoes();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemWFCnd,1)
	cWFCondicaoAparicao objeto(pObtemWFCnd[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFCnd();
END_DECLARE_ROUTER_INTERF
