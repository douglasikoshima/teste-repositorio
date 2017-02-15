/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/ 


#include "../include/cWFAtendimentoNivel.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFAtdNiv);

void implWFAtdNiv::Execute(DOMNode*dnode,XMLGen*xml_g)
{

	char operacao=0;
	int  ret = 0;
	char *p;

	if (p=walkTree( dnode, "tipoOperacao", 0 ),p)
	{
		operacao = *p;
		XMLString::release(&p);
	}

	cWFAtendimentoNivel cs(dnode, xml_g);

    switch (operacao )
    {
        case 'I':
		    cs.incluir();
        break;
        
	    case 'A':
		    ret = cs.alterar();
        break;
	    
	    case 'E':
		    ret = cs.excluir();
        break;
	    
	    case 'C':
		    cs.consultar();
        break;
        
        default:
            setStatusCode("04E0002","Operacao invalida");
            ret = -2;
        break;

    }

	if (ret == -1)
    {
		setStatusCode("04E0001","O atributo 'idAtendimento' é obrigatório para essa operação.");
	}
    else if (ret >= 0)
    {
		setStatusCode("04I0000","Processo concluído.");
    }
}

BEGIN_DECLARE_ROUTER_PARM(WFAtdNivIncluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdNivAlterar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdNivExcluir)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(WFAtdNivConsultar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemDetalheAtend)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemNivGrAt)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

BEGIN_DECLARE_ROUTER_PARM(ObtemNivContato)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(WFAtdNivIncluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdNivAlterar,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdNivExcluir,AC_XMLGEN)
DECLARE_RETURN_TYPE(WFAtdNivConsultar,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemDetalheAtend,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemNivGrAt,AC_XMLGEN)
DECLARE_RETURN_TYPE(ObtemNivContato,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(WFAtdNivIncluir,1)
	cWFAtendimentoNivel objeto(pWFAtdNivIncluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.incluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdNivAlterar,1)
	cWFAtendimentoNivel objeto(pWFAtdNivAlterar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.alterar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdNivExcluir,1)
	cWFAtendimentoNivel objeto(pWFAtdNivExcluir[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.excluir();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(WFAtdNivConsultar,1)
	cWFAtendimentoNivel objeto(pWFAtdNivConsultar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.consultar();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemDetalheAtend,1)
	cWFAtendimentoNivel objeto(pObtemDetalheAtend[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemDetalheAtend();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemNivGrAt,1)
	cWFAtendimentoNivel objeto(pObtemNivGrAt[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemNivGrAt();
END_DECLARE_ROUTER_INTERF

BEGIN_DECLARE_ROUTER_INTERF(ObtemNivContato,1)
	cWFAtendimentoNivel objeto(pObtemNivContato[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.ObtemNivContato();
END_DECLARE_ROUTER_INTERF
