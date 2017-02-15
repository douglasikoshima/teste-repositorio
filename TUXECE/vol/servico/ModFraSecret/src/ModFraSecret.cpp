
/*
 * Serviço de cadastro básico de tipo de correspondencia
 * Versão inicial, 19/05/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(MODFRASECRET);

void implMODFRASECRET::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CPessoa oPessoa;
	CLinha oLinha;

	char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
	int   iNrLinha = -1;
	int   iIdTipoRelacionamento = -1;
	int   iIdCanal = -1;
	char  cDsFraseSecreta[256];
	char  cDsLembreteFraseSecreta[256];

	int   iError = 0;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	///
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	///
	pcTagXmlIn = helper.walkTree(dnode,"idTipoRelacionamento", 0);
	ASSERT_INT(iIdTipoRelacionamento, pcTagXmlIn, "idTipoRelacionamento");

	///
	pcTagXmlIn = helper.walkTree(dnode,"idCanal", 0);
	if(pcTagXmlIn != NULL)
		ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal");

	///
	pcTagXmlIn = helper.walkTree(dnode,"dsFraseSecreta", 0);
	ASSERT_STR(pcTagXmlIn, "dsFraseSecreta");
	strcpy(cDsFraseSecreta, pcTagXmlIn);

	///
	pcTagXmlIn = helper.walkTree(dnode,"dsLembreteFraseSecreta", 0);
	ASSERT_STR(pcTagXmlIn, "dsLembreteFraseSecreta");
	strcpy(cDsLembreteFraseSecreta, pcTagXmlIn);

	oPessoa.setIdTipoRelacionamento(iIdTipoRelacionamento);

	//Utilizado para verificar se o cliente e usuário são iguais.
	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

	xml_g->createTag("MODFRASECRETVO");

	try
	{
		oPessoa.obterIdPessoa(iCdAreaRegistro, iNrLinha);
		oPessoa.getSenha().setDsFraseSecreta(cDsFraseSecreta);
		oPessoa.getSenha().setDsLembreteFraseSecreta(cDsLembreteFraseSecreta);

		// executa a alteração
		try
		{
			//Verfica se o cliente e usuário são iguais, se sim altera a frase de ambos.
			if (oLinha.verificarClienteUsuarioIguais() == true)
			{
				//1-Altera a senha do tipo de relacionamento corrente.
				oPessoa.getSenha().alterarFraseSecreta(oPessoa.getIdPessoa(), 
											           oPessoa.getIdPessoaLinha(), 
													   oPessoa.getIdTipoRelacionamento(),
													   iIdCanal,
													   atoi(this->getUser()));

				//Inverte o tipo de relacionamento
				if (oPessoa.getIdTipoRelacionamento() == PESSOA_USUARIO)
					oPessoa.setIdTipoRelacionamento(PESSOA_CLIENTE);
				else
					oPessoa.setIdTipoRelacionamento(PESSOA_USUARIO);

				//Busca as informações do outro tipo de relacionamento
				oPessoa.obterIdPessoa(iCdAreaRegistro, iNrLinha);
				oPessoa.getSenha().setDsFraseSecreta(cDsFraseSecreta);
				oPessoa.getSenha().setDsLembreteFraseSecreta(cDsLembreteFraseSecreta);

				//2-Efetua a alteração da frase do outro tipo de relacionamento.
				oPessoa.getSenha().alterarFraseSecreta(oPessoa.getIdPessoa(), 
											           oPessoa.getIdPessoaLinha(), 
													   oPessoa.getIdTipoRelacionamento(),
													   iIdCanal,
													   atoi(this->getUser()));
				
			}
			else
				oPessoa.getSenha().alterarFraseSecreta(oPessoa.getIdPessoa(), 
											           oPessoa.getIdPessoaLinha(), 
													   oPessoa.getIdTipoRelacionamento(),
													   iIdCanal,
													   atoi(this->getUser()));
		}
		catch(...) 
		{
			iError = 1;

			/* nao vamos mais inserir senha
			try 
			{
				oPessoa.inserirFraseSecreta(cDsFraseSecreta, cDsLembreteFraseSecreta);	
			}
			catch(...)
			{
				iError = 1;
			}
			*/
		}

	}
	catch(...)
	{
		iError = 1;
	}
	
	xml_g->closeTag();

	//seta mensagem de retorno - header
	if(iError)
		setStatusCode("11W0001", "NAO FOI POSSIVEL EFETUAR A ALTERACAO");
	else
	{
		setStatusCode("11I0000", "ALTERACAO EFETUADA COM SUCESSO");


		// registrando contato
		//REG_CONTATO(iRes);

		// enviando mensagem de alerta
		ENV_MSG(iResMsg, ENV_MSG_VALIDA_TAG);
	}
}
