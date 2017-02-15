#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

//Habilitar para depurar no windows
//#define USE_DEBUG

DECLARE_TUXEDO_SERVICE(ENVMSG);

void implENVMSG::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

#ifdef USE_DEBUG

	int valTag = 0;

//  Código utilizado em Defines.h para a macro ENV_MSG
//	Pode ser testado por aqui com mais precisao...

//#define ENV_MSG(iResult, valTag) 

	int iResult = 0; 
	try 
	{ 
		CLinha     oLinhaEnv; 
		char*  pcTagXmlIn = NULL; 
		char   cdMsg[256]; 
		int    iCdAreaRegistro = -1; 
 		int    iNrLinha = -1; 
		int    iIdTipoRelacionamento = -1; 
		int    iInComunicarUsuario = 1; 
		//utilizado somente para alteração de e-mail
		//oLinhaEnv.getPessoa()->setDsContato(oPessoa.getDsContato()); 
		if(valTag) 
		{ 
			pcTagXmlIn = helper.walkTree(dnode,"inComunicarUsuario", 0); 
			ASSERT_INT(iInComunicarUsuario, pcTagXmlIn, "inComunicarUsuario"); 
		} 
		if(iInComunicarUsuario) 
		{ 
			pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0); 
			ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro"); 
			pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0); 
			ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha"); 
			pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0); 
			ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento"); 
			pcTagXmlIn = helper.walkTree(dnode,"cdMsg", 0); 
			ASSERT_STR(pcTagXmlIn, "cdMsg"); 
			strcpy(cdMsg, pcTagXmlIn); 
			oLinhaEnv.setCdAreaRegistro(iCdAreaRegistro); 
			oLinhaEnv.setNrLinha(iNrLinha); 
			oLinhaEnv.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento); 
			oLinhaEnv.enviarMensagem(cdMsg, this->getUser()); 
		} 
		iResult = 0; 
	} 
	catch(...){ iResult = 1; }

#else

	// registrando contato
	ENV_MSG(iResult, REG_NAO_VALIDA_TAG);

#endif

	if(iResult)
	{
		setStatusCode("11W0001", "NAO FOI POSSIVEL ENVIAR A MENSAGEM");
	}
	else
	{
		setStatusCode("11I0000", "MENSAGEM ENVIADA COM SUCESSO");
	}

}