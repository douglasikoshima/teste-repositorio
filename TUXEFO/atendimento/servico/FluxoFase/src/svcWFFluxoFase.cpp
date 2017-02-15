/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:05 $
 **/ 


#include "../include/cWFFluxoFase.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFFluFas);

void implWFFluFas::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implWFFluFas::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFFluxoFase cs(dnode, xml_g);

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
		setStatusCode("04E0001","O atributo 'idFluxoFase' é obrigatório para essa operação.");
	}

	if (ret >= 0)
		setStatusCode("04I0000","Processo concluído.");
		
   ULOG_END("implWFFluFas::Execute()");
   
}


BEGIN_DECLARE_ROUTER_PARM(WFFluFasIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFasAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFasExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFFluFasConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFAcoes)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFFluxo)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFCanc)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemWFFluxoFt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFFluFasIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFasAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFasExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFFluFasConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFAcoes,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFFluxo,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFCanc,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemWFFluxoFt,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFFluFasIncluir,1)
	cWFFluxoFase objeto(pWFFluFasIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFasAlterar,1)
	cWFFluxoFase objeto(pWFFluFasAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFasExcluir,1)
	cWFFluxoFase objeto(pWFFluFasExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFFluFasConsultar,1)
	cWFFluxoFase objeto(pWFFluFasConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemWFAcoes,1)
	cWFFluxoFase objeto(pObtemWFAcoes[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFAcoes();
END_DECLARE_ROUTER_INTERF


BEGIN_DECLARE_ROUTER_INTERF(ObtemWFFluxo,1)
	cWFFluxoFase objeto(pObtemWFFluxo[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFFluxo();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemWFCanc,1)
	cWFFluxoFase objeto(pObtemWFCanc[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFCanc();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemWFFluxoFt,1)
	cWFFluxoFase objeto(pObtemWFFluxoFt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemWFFluxoFt();
END_DECLARE_ROUTER_INTERF
