#define GETEMAIL__

#include<tuxfw.h>
#include"Email.h"

DECLARE_TUXEDO_SERVICE(GETEMAIL);


void implGETEMAIL::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	CEmail email;
	char *cdNumTelefone = walkTree(dnode,"cdNumTelefone",0);
	char *cdDDD = walkTree(dnode,"cdDDD",0);
	try
	{
		int retorno = email.getEmail(xml_g,cdNumTelefone,cdDDD);
		
		switch(retorno)
		{
			case ERRO_LINHA_NAO_ENCONTRADA: 
				xml_g->addItem("cdCodigoRetorno","01");
				setStatusCode("00W0001",ERRO_MSG_LINHA_NAO_ENCONTRADA); break;
			case ERRO_EMAIL_NAO_ENCONTRADO: 
				xml_g->addItem("cdCodigoRetorno","03");
				setStatusCode("00W0002",ERRO_MSG_EMAIL_NAO_ENCONTRADO); break;
			case ERRO_LINHA_POSPAGA: 
				xml_g->addItem("cdCodigoRetorno","02");
				setStatusCode("00W0003",ERRO_MSG_LINHA_POSPAGA); break;
			case 1:
				xml_g->addItem("statCom","1"); 
				xml_g->addItem("cdCodigoRetorno","00"); 
				setStatusCode("00I0000","Sucesso");
				break;
		}
		
	}
	catch(TuxBasicOraException eboe)
	{
		tuxfw_getlogger()->debug("ERRO: %s COD: %d",eboe.pMsg,eboe.eCode);
		XMLString::release(&cdNumTelefone);
		XMLString::release(&cdDDD);
		throw new TuxBasicSvcException("00W0009","erro de oracle");
	}

	XMLString::release(&cdNumTelefone);
	XMLString::release(&cdDDD);
}