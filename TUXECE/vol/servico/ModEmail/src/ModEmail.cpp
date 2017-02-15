#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Email/Email.hpp>
//Registrar de Contato/Comunicar Usuário
#include <Pessoa/Pessoa.hpp>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
//---------------------------------
 
DECLARE_TUXEDO_SERVICE( MODEMAIL );

static void incluir( DOMNode * dnode, XMLGen * xml_g, CStatusCode & statusCode )
{
	CTuxHelperClever helper;
	CEmail oEmail;

	//CRelacionamento oRelacionamento;

	char*   pPointer = NULL;
	int		iIdTipoEmail = 0;
	long	iIdPessoa = 0;
	char*	cDsEmail = NULL;
	int		iNrLinha = 0;
	int		iCdAreaRegistro = 0;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pPointer = helper.walkTree(dnode,"idPessoa", 0);
	
	if (pPointer != NULL)	
		iIdPessoa = atol(pPointer);

   // if(pPointer) XMLString::release(&pPointer);

	pPointer = helper.walkTree(dnode,"idTipoEmail", 0);
	ASSERT_INT(iIdTipoEmail, pPointer, "idTipoEmail");
  //  if(pPointer) XMLString::release(&pPointer);

	pPointer = helper.walkTree(dnode,"dsEmail", 0);
	ASSERT_STR(pPointer, "dsEmail");
	oEmail.setDsEmail(pPointer);

	pPointer = helper.walkTree(dnode,"nrLinha", 0);
	if (pPointer !=  NULL)
		iNrLinha = atoi(pPointer);
	

	pPointer = helper.walkTree(dnode,"cdAreaRegistro", 0);
	if (pPointer !=  NULL)
		iCdAreaRegistro = atoi(pPointer);

	oEmail.setIdTipoEmail(iIdTipoEmail);

   // if(pPointer) XMLString::release(&pPointer);

	if (iIdPessoa == 0)	
		iIdPessoa = oEmail.consultarIdPessoa(iCdAreaRegistro, iNrLinha );

	try	
	{
		if (oEmail.verificarTipoEmail() == false){ //Verifica se o tipo do e-mail existe.
			// seta mensagem de retorno - header	
			statusCode.setCod("11W0001");
			statusCode.setMsg("idTipoEmail inválido.");
		}else if (oEmail.verificarEmail(iIdPessoa) == false){ //Verifica se o e-mail já está cadastrado
			// seta mensagem de retorno - header	
			statusCode.setCod("11W0002");
			statusCode.setMsg("E-mail já cadastrado.");
		}else{
			//Inclui E-mail
			oEmail.incluirEmail(iIdPessoa);

			// seta mensagem de retorno - header	
			statusCode.setCod("11I0000");
			statusCode.setMsg("Inclusão Efetuada com Sucesso");
		}
	}

	catch(TuxBasicSvcException)
	{
				
		// seta mensagem de retorno - header
		statusCode.setCod("11W0099");
		statusCode.setMsg("Operação Não Efetuada.");
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod("11W0099");
		statusCode.setMsg("Operação Não Efetuada.");
	}
	catch(...)
	{
		statusCode.setCod("11W9999");
		statusCode.setMsg("ERRO DESCONHECIDO");
	}

	// Monta o XML de saída
	//xml_g->createTag( "incluirEmailVO" );
	//xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );
	//xml_g->closeTag();
}

static void alterar(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CEmail oEmail;

	char*   pPointer = NULL;
	int		iIdEmail = 0;
	int		iIdTipoEmail = 0;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pPointer = helper.walkTree(dnode,"idEmail", 0);
	ASSERT_INT(iIdEmail, pPointer, "idEmail");
  //  if(pPointer) XMLString::release(&pPointer);

	pPointer = helper.walkTree(dnode,"idTipoEmail", 0);
	ASSERT_INT(iIdTipoEmail, pPointer, "idTipoEmail");
 //   if(pPointer) XMLString::release(&pPointer);

	pPointer = helper.walkTree(dnode,"dsEmail", 0);
	ASSERT_STR(pPointer, "dsEmail");


	oEmail.setIdEmail(iIdEmail);
	oEmail.setIdTipoEmail(iIdTipoEmail);
	oEmail.setDsEmail(pPointer);
  //  if(pPointer) XMLString::release(&pPointer);


	try	
	{
		if (oEmail.verificarTipoEmail() == false){ //Verifica se o tipo do e-mail existe.
			// seta mensagem de retorno - header	
			statusCode.setCod("11W0002");
			statusCode.setMsg("idTipoEmail inválido.");
		}else if (oEmail.verificarEmail(0) == false){ //Verifica se o e-mail já está cadastrado, passando o parametro 0 para ser pesquisado através do idEmail.
			// seta mensagem de retorno - header	
			statusCode.setCod("11W0003");
			statusCode.setMsg("E-mail já cadastrado.");
		}else{
			//Altera e-mail
			oEmail.alterarEmail();

			// seta mensagem de retorno - header	
			statusCode.setCod("11I0000");
			statusCode.setMsg("Alteração Efetuada com Sucesso");
		}
	}

	catch(TuxBasicSvcException)
	{
				
		// seta mensagem de retorno - header
		statusCode.setCod("11W0099");
		statusCode.setMsg("Operação Não Efetuada.");
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod("11W0001");
		statusCode.setMsg("idEmail inválido.");
	}
	catch(...)
	{
		statusCode.setCod("11W9999");
		statusCode.setMsg("ERRO DESCONHECIDO");
	}

	// Monta o XML de saída
	//xml_g->createTag( "alterarEmailVO" );
	//xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );
	//xml_g->closeTag();
}

static void excluir(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CEmail oEmail;

	char*   pPointer = NULL;
	int		iIdEmail = 0;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pPointer = helper.walkTree(dnode,"idEmail", 0);
	ASSERT_INT(iIdEmail, pPointer, "idEmail");
  //  if(pPointer) XMLString::release(&pPointer);

	oEmail.setIdEmail(iIdEmail);

	try	
	{
		//Exclui e-mail.
		oEmail.excluirEmail();

		// seta mensagem de retorno - header	
		statusCode.setCod("11I0000");
		statusCode.setMsg("Exclusão Efetuada com Sucesso");
	}

	catch(TuxBasicSvcException)
	{
				
		// seta mensagem de retorno - header
		statusCode.setCod("11W0099");
		statusCode.setMsg("Operação Não Efetuada.");
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod("11W0001");
		statusCode.setMsg("idEmail inválido.");
	}
	catch(...)
	{
		statusCode.setCod("11W9999");
		statusCode.setMsg("ERRO DESCONHECIDO");
	}

	// Monta o XML de saída
	//xml_g->createTag( "excluirEmailVO" );
	//xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );
	//xml_g->closeTag();
}


void implMODEMAIL::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pPointer = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pPointer = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pPointer, "operacao");

	// descobrindo qual operação executar
	if (!strcmp(pPointer, "incluir"))
		incluir(dnode, xml_g, statusCode);	
	else if (!strcmp(pPointer, "alterar"))
		alterar(dnode, xml_g, statusCode);	
	else if (!strcmp(pPointer, "excluir"))
		excluir(dnode, xml_g, statusCode);	

  //  if(pPointer) XMLString::release(&pPointer);

	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

	// registrando contato
	// REG_CONTATO(iRes, REG_VALIDA_TAG);
	REG_CONTATO_PROTOCOLO(iResult,REG_NAO_VALIDA_TAG,protocolo);

	xml_g->createTag("ProtocoloVO");
	xml_g->addProp("xmlns","cliente.fo.vivo.com.br/vo");
	xml_g->addItem("nrProtocolo",protocolo.nrProtocolo);	
	xml_g->closeTag();

	ENV_MSG(iResMsg, ENV_MSG_VALIDA_TAG);

	// enviando mensagem de alerta
//	ENV_MSG_EMAIL(iResMsg, ENV_MSG_VALIDA_TAG);

}
