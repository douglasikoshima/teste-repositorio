/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:43 $
 **/ 


#include "../../../commons/smsUtil/include/cSMSUtil.h"
#include "../../../commons/routerLib/include/RouterClient.h"

DECLARE_TUXEDO_SERVICE(WFSMSSEND);

void implWFSMSSEND::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFSMSSEND::Execute()");
	char operacao[2];
    char *p;
	int  ret = 0;

    if ( p = walkTree( dnode, "tipoOperacao", 0 ),p )
    {
        sprintf(operacao,"%.*s",sizeof(operacao)-1,p);
        XMLString::release(&p);
    }

	cSMSUtil su(dnode, xml_g);

	ULOG("SMSUtil - Tipo de operacao recebido = [%s]", operacao);

	if (operacao[0] == 'E')	su.enviar();

	setStatusCode("00I0000","Mensagem Enviada");
	
	ULOG_END("implWFSMSSEND::Execute()");
}


BEGIN_DECLARE_ROUTER_PARM(Enviar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(Enviar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(Enviar,1)
	cSMSUtil objeto(pEnviar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF
