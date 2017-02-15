#define VIVOPLAY__

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Senha/Senha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(VIVOPLAY);


void implVIVOPLAY::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	CSenha csenha;
	CTuxHelperClever helper;
	char *cdNumTelefone = helper.walkTree(dnode,"cdNumTelefone",0);
	char *cdDDD = helper.walkTree(dnode,"cdDDD",0);
	char *senha = helper.walkTree(dnode,"senha",0);

	try
	{
		int retorno = csenha.validarSenhaWS(cdDDD,cdNumTelefone,senha);
		
        tuxfw_getlogger()->debug("retorno %d",retorno);
        
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
				
			case -8: 
				xml_g->addItem("cdCodigoRetorno","10");
				setStatusCode("00W0010","Senha inválida. 1a. Tentativa"); 
				break;
			case -9: 
				xml_g->addItem("cdCodigoRetorno","11");
				setStatusCode("00W0011","Senha inválida. 2a. Tentativa"); 
				break;
			case -10: 
				xml_g->addItem("cdCodigoRetorno","12");
				setStatusCode("00W0012","Usuário Bloqueado."); 
				break;
			case -4: 
				xml_g->addItem("cdCodigoRetorno","04");
				setStatusCode("00W0004","Senha inválida"); 
				break;
			case -6: 
				xml_g->addItem("cdCodigoRetorno","06");
				setStatusCode("00W0006","Senha não cadastrada."); break;
			case -7: 
				xml_g->addItem("cdCodigoRetorno","07");
				setStatusCode("00W0007","Linha PJ não possui acesso ao VOL."); break;
			case 1:
				tuxfw_getlogger()->debug("Tratamento para retorno 1");
				xml_g->addItem("statCom","1"); 
				xml_g->addItem("cdCodigoRetorno","00"); 

				try
				{
					tuxfw_getlogger()->debug("csenha.consultarDadosCliente(cdDDD,cdNumTelefone)");
					csenha.consultarDadosCliente(cdDDD,cdNumTelefone);
				}
				catch(...)
				{
					tuxfw_getlogger()->debug("validarSenhaWS --> Erro na chamada [csenha.consultarDadosCliente(cdDDD,cdNumTelefone);]");
				}
				
				try
				{
					xml_g->addItem("idTipoLinha",csenha.getIdTipoLinha()); 
					xml_g->addItem("nmPessoa",csenha.getNmPessoa()); 
					
					// DEMANDA SAPO
					xml_g->addItem( "primeiroNome", csenha.getNmPessoa() );
					xml_g->addItem( "nome", csenha.getNmNomeCompleto() );
					xml_g->addItem( "sgSexo", csenha.getSgSexo() );
					xml_g->addItem( "dtNascimento", csenha.getDtNascimento() );
					
					xml_g->addItem( "e_mail", csenha.getEmail() );
				}
				catch(...)
				{
					tuxfw_getlogger()->debug("validarSenhaWS --> Erro no preenchimento de TAGs XML...");
				}
				
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