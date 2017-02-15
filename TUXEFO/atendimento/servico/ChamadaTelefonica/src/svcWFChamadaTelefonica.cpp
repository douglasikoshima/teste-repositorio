/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:27 $
 **/ 


#include "../include/cWFChamadaTelefonica.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFChaTel);

void implWFChaTel::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cWFChamadaTelefonica cs(dnode, xml_g);

	if      (operacao[0] == 'I')	cs.incluir();
	else if (operacao[0] == 'A') 	ret = cs.alterar();
	else if (operacao[0] == 'E')	ret = cs.excluir();
	else if (operacao[0] == 'C')	cs.consultar();

	if (ret == -1)		setStatusCode("04E0001","O atributo 'idChamadaTelefonica' é obrigatório para essa operação.");
	if (ret >=  0)		setStatusCode("04I0000","Processo concluído.");
}


BEGIN_DECLARE_ROUTER_PARM(WFChaTelIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaTelAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaTelExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFChaTelConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFChaTelIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaTelAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaTelExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFChaTelConsultar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFChaTelIncluir,1)
	cWFChamadaTelefonica objeto(pWFChaTelIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaTelAlterar,1)
	cWFChamadaTelefonica objeto(pWFChaTelAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaTelExcluir,1)
	cWFChamadaTelefonica objeto(pWFChaTelExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFChaTelConsultar,1)
	cWFChamadaTelefonica objeto(pWFChaTelConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF
