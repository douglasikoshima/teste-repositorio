#include<tuxfw.h>
#include "../../negocio/CadastroMSGSitef/include/CadastroMSGSitef/CadastroMSGSitef.hpp"

DECLARE_TUXEDO_SERVICE(MSGCADSITEF);


void implMSGCADSITEF::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	CadastroMSGSitef cadastro;
	char *dsOperacao = walkTree(dnode,"dsOperacao",0);
	char*idMensagemRecarga = walkTree(dnode,"idMensagemRecarga",0);
	char*dsMensagemSitef = walkTree(dnode,"dsMensagemSitef",0);
	char*dsMensagemTav = walkTree(dnode,"dsMensagemTav",0);
	char*nmLoginUsuario  = walkTree(dnode,"nmLoginUsuario",0);

	try
	{
		if(!strcmp(dsOperacao,"consultarMensagem"))
		{
			cadastro.getMensagens(xml_g);
		}
		else
		if(!strcmp(dsOperacao,"gravarMensagem"))
		{
			cadastro.addMensagem(dsMensagemTav,dsMensagemSitef,nmLoginUsuario);
		}
		else
		if(!strcmp(dsOperacao,"atualizarMensagem"))
		{
			cadastro.updateMensagem(idMensagemRecarga,dsMensagemTav);
		}
		else
		if(!strcmp(dsOperacao,"removerMensagem"))
		{
			cadastro.deleteMensagem(idMensagemRecarga);
		}
	}
	catch(TuxBasicOraException tboe)
	{
		setStatusCode("00W0001","Problemas de acesso a base de dados");
		return;
	}
	
	setStatusCode("00I0000","Sucesso");
}