/*
 * Serviço de Dados pessoais página inicial
 * Versão inicial, 07/08/2004
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(EXCFRASECRET);

void implEXCFRASECRET::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iCdAreaRegistro = -1;
 	int    iNrLinha = -1;
	int    iIdTipoRelacionamento;
	int   iIdCanal = -1;

	CLinha oLinha;
	
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

	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);
	oLinha.getPessoa()->setIdTipoRelacionamento(iIdTipoRelacionamento);

	try
	{

		//Verfica se o cliente e usuário são iguais, se sim altera a frase de ambos.
		if (oLinha.verificarClienteUsuarioIguais() == true)
		{
			//1-Reinicia a Frase Secreta do tipo de relacionamento corrente.
			oLinha.excluirFraseSecreta(iIdCanal, atoi(this->getUser()));

			//Inverte o tipo de relacionamento
			if (oLinha.getPessoa()->getIdTipoRelacionamento() == PESSOA_USUARIO)
				oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_CLIENTE);
			else
				oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_USUARIO);

			//2-Reinicia a frase do outro tipo de relacionamento.
			oLinha.excluirFraseSecreta(iIdCanal, atoi(this->getUser()));
		}
		else
			oLinha.excluirFraseSecreta(iIdCanal, atoi(this->getUser()));

		//seta mensagem de retorno - header
		setStatusCode("11I0000", "REINICIALIZACAO EFETUADA COM SUCESSO");

	}
	catch(TuxBasicOraException &e)
	{

		if(NO_DATA_FOUND == e.eCode)
		{
			//seta mensagem de retorno - header
			setStatusCode("11W0002", "NAO POSSUI FRASE SECRETA PARA SER REINICIALIZADA");
		}
		else
		{
			//seta mensagem de retorno - header
			setStatusCode("11W0001", "REINICIALIZACAO NAO EFETUADA");
		}		

	}
	catch(...)
	{
		//seta mensagem de retorno - header
		setStatusCode("11W9999", "ERRO DESCONHECIDO");
	}

	xml_g->createTag("EXCFRASECRETVO");
	xml_g->addProp("xmlns", "senhas.vol.vivo.com.br/vo");
	xml_g->closeTag();

	return;
}
