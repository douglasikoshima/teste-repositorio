/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.2 $
 * @CVS     $Author: drdantas $ - $Date: 2004/09/15 18:55:11 $
 **/ 


#include "../include/cSMSUtil.h"
#include <RouterClient.h>

DECLARE_TUXEDO_SERVICE(SMSUtil);

void implSMSUtil::Execute(DOMNode*dnode,XMLGen*xml_g) {

	char operacao[2];
	int  ret = 0;

	strcpy( operacao, walkTree( dnode, "tipoOperacao", 0 ) );

	cSMSUtil su(dnode, xml_g);

	if (operacao[0] == 'E')	su.enviar();

	setStatusCode("00I0000","Mensagem Enviada");
}


BEGIN_DECLARE_ROUTER_PARM(Enviar)
	ADD_ROUTER_FUNCTION_ITEM(AC_DOMNODE)
END_DECLARE_ROUTER_PARM

DECLARE_RETURN_TYPE(Enviar,AC_XMLGEN)

BEGIN_DECLARE_ROUTER_INTERF(Enviar,1)
	cSMSUtil objeto(pEnviar[0].accum->var.dom->RetrieveDOM(), ac->var.xml);
	objeto.enviar();
END_DECLARE_ROUTER_INTERF
