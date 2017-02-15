/*
 * Serviço de Consulta a Lembrete da Senha
*/

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>


DECLARE_TUXEDO_SERVICE(ENVLEMBSENHA);

void implENVLEMBSENHA::Execute(DOMNode* dnode, XMLGen* xml_g) {

	CTuxHelperClever helper;

	CLinha oLinha;

	TuxMessage tm;
	XMLGen gen;


	char* pcArea = NULL;
	char* pcNumber = NULL;
	char cDestinatario [10 + 1];
	char cMensagem[255];
	int iArea = -1;
	int iNroLinha = -1;
	bool bEnviarSMS=true;

	// Navega o XML e recupera as informacoes obrigatorias
	pcArea = helper.walkTree(dnode,"cdAreaRegistro", 0);
	pcNumber = helper.walkTree(dnode,"nrLinha", 0);
	memset(cMensagem,' ', sizeof(cMensagem) );


	if (pcArea == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_INEXISTENTE");
	}
	if (!*pcArea) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_VALOR_VAZIO");
	}

	if (pcNumber == NULL) {
		throw new TuxBasicSvcException("11E0000", "TAG_nrLinha_INEXISTENTE");
	}
	if (!*pcNumber) {
		throw new TuxBasicSvcException("11E0000", "TAG_nrLinha_VALOR_VAZIO");
	}

	if ((iArea = atoi(pcArea)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_cdAreaRegistro_VALOR_INVALIDO");
	}

	if ((iNroLinha = atoi(pcNumber)) <= 0) {
		throw new TuxBasicSvcException("11E0000", "TAG_idPessoa_VALOR_INVALIDO");
	}
	

	// Monta o objeto Linha
	oLinha.setCdAreaRegistro(iArea);	
	oLinha.setNrLinha(iNroLinha);

	sprintf(cDestinatario, "%d%d", iArea, iNroLinha);
	// Execução OK.
	//INFORMATION(NRO_OK);
	try{
	/*	memcpy(cMensagem, CUtil::trim(oLinha.obterLembreteSenha()), sizeof(cMensagem));*/
		//sprintf(cMensagem, "Vivo Informa: Seu lembrete de senha e' %s.", CUtil::trim(oLinha.obterLembreteSenha()) );
		oLinha.getPessoa()->setIdTipoRelacionamento(1);
		oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(), oLinha.getNrLinha());
		oLinha.getPessoa()->getSenha().consultarLembreteFraseSecreta(oLinha.getPessoa()->getIdPessoa(),
																	 oLinha.getPessoa()->getIdPessoaLinha(),
																	 oLinha.getPessoa()->getIdTipoRelacionamento());

		sprintf(cMensagem, "Vivo Informa: Seu lembrete de senha e%s %s.", "'" , oLinha.getPessoa()->getSenha().getDsLembreteSenha() );
	}catch( ... ) 
	{ 
		bEnviarSMS = false;
		setStatusCode("11W0003", "Lembrete da Senha nao cadastrado"); 
		return;
	}

	if ( strcmp(cMensagem," " ) == 0 ){
		bEnviarSMS = false;
		setStatusCode("11W0003", "Lembrete da Senha nao cadastrado"); 
		return;
	}

	if (bEnviarSMS == true)
	{
		//tuxfw_getlogger()->information("\r\t\nVai Criar o xml\r\t\n"); 

		gen.addItem( "message" , cMensagem );
		gen.addItem( "recipient" , cDestinatario );

		//tuxfw_getlogger()->information("\r\t\nCriou o xml\r\t\n"); 
		try 
		{
			//tuxfw_getlogger()->information("\r\t\nVai chamar o remoteCall\r\t\n"); 
			remoteCall("SMSSend", &gen, &tm);
			//tuxfw_getlogger()->information("\r\t\nVoltou do remoteCall\r\t\n"); 

			xml_g->createTag("SMSSend");
				xml_g->addItem("inEnviado", "1");
			xml_g->closeTag();

			setStatusCode("11I0000", "Envio Efetuado com Sucesso");
			return;
			}
		catch(TuxException* pTux)
		{
			xml_g->createTag("SMSSend");
				xml_g->addItem("inEnviado", "0");
			xml_g->closeTag();

			setStatusCode(pTux->getCode(),pTux->getMessage());
			delete pTux;

			setStatusCode("11W0001", "Envio Não Efetuado");
			return;
		}
	}
	
	return;
}




