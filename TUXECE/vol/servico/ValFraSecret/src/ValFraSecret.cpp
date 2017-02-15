#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(VALFRASECRET);

void implVALFRASECRET::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CLinha oLinha;

	char* pcTagXmlIn = NULL;
	int   iCdAreaRegistro = -1;
	int   iNrLinha = -1;
	int   iIdTipoRelacionamento = -1;
	int   iIdCanal;
	char  cDsFraseSecreta[256];

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
	ASSERT_INT(iIdCanal, pcTagXmlIn, "idCanal");

	///
	pcTagXmlIn = helper.walkTree(dnode,"dsFraseSecreta", 0);
	ASSERT_STR(pcTagXmlIn, "dsFraseSecreta");
	strcpy(cDsFraseSecreta, pcTagXmlIn);
	//CUtil::upper(cDsFraseSecreta);

	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);
	oLinha.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento);

	xml_g->createTag("VALFRASECRETVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");
	xml_g->closeTag();

	try
	{		
		oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(),
										  oLinha.getNrLinha());

		CSenha &oSenha = oLinha.getPessoa()->getSenha();

		oSenha.consultarLembreteFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
											 oLinha.getPessoa()->getIdPessoaLinha(),
											 oLinha.getPessoa()->getIdTipoRelacionamento());
		
		if(oSenha.getStatusFraseSecreta() == FRASE_SECRETA_ATIVADA)
		{
			
			if(!strcmp(cDsFraseSecreta,
					   oSenha.getDsFraseSecreta()))
			{
				
				if(oSenha.getQtTentFraseSecreta() != 0)
				{
					oSenha.setQtTentFraseSecreta(0);
					oSenha.alterarQtTentFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													 oLinha.getPessoa()->getIdPessoaLinha(),
													 oLinha.getPessoa()->getIdTipoRelacionamento());
				}

				setStatusCode("11I0000", "VALIDACAO EFETUADA COM SUCESSO");
				return;
			}
			else
			{
				oSenha.setQtTentFraseSecreta(oSenha.getQtTentFraseSecreta() + 1);

				//Verfica se o cliente e usuário são iguais, se sim altera a qt de tentativas para ambos.
				if (oLinha.verificarClienteUsuarioIguais() == true)
				{
					//1-Altera a qt de tentativas do tipo de relacionamento corrente.
					oSenha.alterarQtTentFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													 oLinha.getPessoa()->getIdPessoaLinha(),
													 oLinha.getPessoa()->getIdTipoRelacionamento());

					//Inverte o tipo de relacionamento
					if (oLinha.getPessoa()->getIdTipoRelacionamento() == PESSOA_USUARIO)
						oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_CLIENTE);
					else
						oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_USUARIO);

					//Busca as informações do outro tipo de relacionamento						
					oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(), oLinha.getNrLinha());					

					//2-Altera a qt de tentativas do outro tipo de relacionamento.
					oSenha.alterarQtTentFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													 oLinha.getPessoa()->getIdPessoaLinha(),
													 oLinha.getPessoa()->getIdTipoRelacionamento());
				}
				else
					oSenha.alterarQtTentFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													 oLinha.getPessoa()->getIdPessoaLinha(),
													 oLinha.getPessoa()->getIdTipoRelacionamento());

				if(oSenha.getQtTentFraseSecreta() >= 3)
				{
					// na terceira tentativa vamos bloquear a frase secreta
					int i = atoi(this->getUser());

					//Verfica se o cliente e usuário são iguais, se sim bloqueia a frase de ambos.
					if (oLinha.verificarClienteUsuarioIguais() == true)
					{
						//1-Bloqueia a frase do tipo de relacionamento corrente.
						oSenha.bloquearFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													oLinha.getPessoa()->getIdPessoaLinha(),
													oLinha.getPessoa()->getIdTipoRelacionamento(),
													iIdCanal,
													atoi(this->getUser()),
													oLinha.getPessoa()->getIdTipoRelacionamento() == PESSOA_CLIENTE ? 1 : 0);

						//Inverte o tipo de relacionamento
						if (oLinha.getPessoa()->getIdTipoRelacionamento() == PESSOA_USUARIO)
							oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_CLIENTE);
						else
							oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_USUARIO);

						//Busca as informações do outro tipo de relacionamento						
						oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(), oLinha.getNrLinha());

						//2-Bloqueia a frase do outro tipo de relacionamento.
						oSenha.bloquearFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													oLinha.getPessoa()->getIdPessoaLinha(),
													oLinha.getPessoa()->getIdTipoRelacionamento(),
													iIdCanal,
													atoi(this->getUser()),
													oLinha.getPessoa()->getIdTipoRelacionamento() == PESSOA_CLIENTE ? 1 : 0);
					}
					else
						oSenha.bloquearFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
													oLinha.getPessoa()->getIdPessoaLinha(),
													oLinha.getPessoa()->getIdTipoRelacionamento(),
													iIdCanal,
													atoi(this->getUser()),
													1);

					setStatusCode("11W0003", "FRASE SECRETA INVALIDA, 3a TENTATIVA. A FRASE FOI BLOQUEADA");

					// enviando mensagem de alerta
					ENV_MSG(iResMsg, ENV_MSG_VALIDA_TAG);

				}
				else
				{
					if(oSenha.getQtTentFraseSecreta() == 1)
						setStatusCode("11W0001", "FRASE SECRETA INVALIDA, 1a TENTATIVA");
					else
						setStatusCode("11W0002", "FRASE SECRETA INVALIDA, 2a TENTATIVA");
				}
			}
		}
		else
		{
			setStatusCode("11W0004", "FRASE SECRETA BLOQUEADA");
		}

	}
	catch(...)
	{
		setStatusCode("11W0005", "ERRO AO EFETUAR VALIDACAO");
	}

}
