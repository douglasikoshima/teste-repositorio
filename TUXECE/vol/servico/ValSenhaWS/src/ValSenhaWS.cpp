#define GETEMAIL__

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Senha/Senha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(VALSENHAWS);


void implVALSENHAWS::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	CSenha csenha;
	CTuxHelperClever helper;
	char *cdNumTelefone = helper.walkTree(dnode,"cdNumTelefone",0);
	char *cdDDD = helper.walkTree(dnode,"cdDDD",0);
	char *senha = helper.walkTree(dnode,"senha",0);
	try
	{
		int retorno = csenha.validarSenhaWS(cdDDD,cdNumTelefone,senha);
		
		switch(retorno)
		{
			case -1: 
				xml_g->addItem("cdCodigoRetorno","01");
				setStatusCode("00W0001","Linha não existe"); break;
			case -2: 
				xml_g->addItem("cdCodigoRetorno","02");
				setStatusCode("00W0002","Senha precisa ser reiniciada"); break;
			case -3: 
				xml_g->addItem("cdCodigoRetorno","03");
				setStatusCode("00W0003","Senha não está com status ativo"); break;
			case -4: 
				xml_g->addItem("cdCodigoRetorno","04");
				setStatusCode("00W0004","Senha inválida"); break;
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
		XMLString::release(&senha);
		throw new TuxBasicSvcException("00W0009","erro de oracle");
	}

	XMLString::release(&cdNumTelefone);
	XMLString::release(&cdDDD);
	XMLString::release(&senha);
}