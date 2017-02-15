/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/

#include "../include/cWFAndamento.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAndame);

void implWFAndame::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao=0;
	int  ret = 0;

	char* p = walkTree( dnode, "tipoOperacao", 0 );

	if (p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFAndamento ao(dnode, xml_g);

    switch (operacao)
    {
        case 'I':
		    ao.incluir();
        break;

	    case 'A':
		    ret = ao.alterar();
        break;

        // Andamentos nunca são excluidos - fev/2007 - cassio
	    // case 'E':
		//     ret = ao.excluir();
        // break;

	    case 'C':
		    ao.consultar();
        break;

        default:
		    setStatusCode("04E0002","Operação invalida");
            ret = -2;
        break;
    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAndamento' é obrigatório para essa operação.");
	}

	if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}


BEGIN_DECLARE_ROUTER_PARM(WFAndameIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndameAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndameExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAndameConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

//BEGIN_DECLARE_ROUTER_PARM(ObtemHistAtend)
//	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
//END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemHistPsq)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAndameIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndameAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndameExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAndameConsultar,AC_XMLGEN)
//DECLARE_RETURN_TYPE(ObtemHistAtend,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemHistPsq,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAndameIncluir,1)
	cWFAndamento objeto(pWFAndameIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndameAlterar,1)
	cWFAndamento objeto(pWFAndameAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

// Andamentos nunca são excluidos - fev/2007 - cassio
// BEGIN_DECLARE_ROUTER_INTERF(WFAndameExcluir,1)
// 	cWFAndamento objeto(pWFAndameExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
// 	objeto.excluir();
// END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAndameConsultar,1)
	cWFAndamento objeto(pWFAndameConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

//BEGIN_DECLARE_ROUTER_INTERF(ObtemHistAtend,1)
//	cWFAndamento objeto(pObtemHistAtend[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
//	objeto.ObtemHistAtend();
//END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemHistPsq,1)
	cWFAndamento objeto(pObtemHistPsq[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemHistPsq();
END_DECLARE_ROUTER_INTERF