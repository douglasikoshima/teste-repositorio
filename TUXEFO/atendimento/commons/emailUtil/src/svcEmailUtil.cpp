/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:11 $
 **/ 


#include "../include/cEmailUtil.h"
#include "../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(EmailUtil);

void implEmailUtil::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	char operacao;
    char *p;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        operacao = *p;
        XMLString::release(&p);
    }

	if (operacao == 'E') 
    {
	    cEmailUtil eu(dnode, xml_g);
        eu.enviar();
    }
}


BEGIN_DECLARE_ROUTER_PARM(Enviar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(Enviar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(Enviar,1)
	cEmailUtil objeto(pEnviar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF
