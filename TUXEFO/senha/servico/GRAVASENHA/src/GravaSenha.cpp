//--------------------------------------------------------------------------------------------------------------
// CGravaSenha - Component Implementation
//--------------------------------------------------------------------------------------------------------------

#include "../include/GravaSenha.h"
#include <time.h>


//--------------------------------------------------------------------------------------------------------------
// Construtor
//--------------------------------------------------------------------------------------------------------------

CGravaSenha::CGravaSenha(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     User = user; 
     setStatus(SWSUCPAS);
     setStaMsg(MSSUCPAS);
     VoName = "GRAVASENHAVO"; 
	 iIdTipoSenha = 1;
}


CGravaSenha::CGravaSenha(char *cSenha) 
{
	
      cdSenha = cSenha;

}
void CGravaSenha::setSenha(char *cSenha) 
{
     cdSenha = cSenha;
     VoName = "SOLICITARSENHAVO"; 
    
}

//--------------------------------------------------------------------------------------------------------------
// Executar
//--------------------------------------------------------------------------------------------------------------

void CGravaSenha::Executar() 
{
    ULOG_START("CGravaSenha::Executar()");
	TString status; 

	CSenhaBase::Executar(); 

	getStatusLinha(); 

	// Verifica se a senha informa é fraca
	//if ( !VerificarSenhaFraca() )
	//	return;


	if (cdSenha ==  NULL)	
		cdSenha  = GetXML( "cdSenha",  true );
	else
		iIdTipoSenha = 2;

	dsLembreteSenha  = GetXML( "dsLembreteSenha", false );  //Tag não obrigatória.

	cdMsg = GetXML("cdMsg", false);

	// Verifica se Cliente e Usuário é a mesma pessoa e possui senhas iguais. Não permitir e retornar senha inválida.
	if (verificarSenhaInvalida() == true){
		setStatus(SWRESINV);
		setStaMsg(MSRESINV);
	}else{
		// Verifica se a senha vai ser alterada ou incluida
		if ( !RegistrarSenha() )
		{
		   ULOG_END("CGravaSenha::Executar()"); 
			return;
		}
	}
		
	xml_g->closeTag();
	//SetMessage( "Consulta Concluida com Sucesso", "S" ); 

    xml_g->closeTag();
    ULOG_END("CGravaSenha::Executar()");
}


bool CGravaSenha::VerificarSenhaFraca()
{
   ULOG_START("CGravaSenha::VerificarSenhaFraca()");
	char atual;
	bool sequencia;
	int contador;
	int i, j, anterior; 

	cdSenha  = GetXML( "cdSenha", true );

	// Permite que apenas 2 numeros se repitam
	contador = 0;
	for (i = 0; i < cdSenha.Length(); i++) {

		// Compara um dos caracteres com todos os demais
		atual = cdSenha.charAt(i);
		for (j = 0; j < cdSenha.Length(); j++)
		{
			if (i != j && cdSenha.charAt(i) == cdSenha.charAt(j))
			{
				contador++;
			}
		}

		if (contador > 2)
		{
			setStatus ( SWERRIGU );
			setStaMsg ( MSERRIGU );
			ULOG_END("CGravaSenha::VerificarSenhaFraca() return false");
			return false;
		}
	}

    // Variavel que informa se a cdSenha e uma sequencia
    sequencia = true;

	// Verifica se a cdSenha e uma sequencia crescente
	anterior = cdSenha.parseInt(0);
	for (i = 1; i < cdSenha.Length(); i++)
	{
		atual = cdSenha.parseInt(i);
		if (atual == (anterior + 1))
		{
			anterior = atual;
		}
		else
		{
			sequencia = false;
		}
	}

    if (sequencia)
	{
        // Mensagem de cdSenha invalida - sequencia numerica
		setStatus ( SWERRSEQ );
		setStaMsg ( MSERRSEQ );
		ULOG_END("CGravaSenha::VerificarSenhaFraca() return false");
        return false;
    }

    // Verifica se a cdSenha e uma sequencia decrescente
	anterior = cdSenha.parseInt(0);
	for (i = 1; i < cdSenha.Length(); i++)
	{
		atual = cdSenha.parseInt(i);
		if (atual == (anterior - 1))
		{
			anterior = atual;
		}
		else
		{
		   ULOG_END("CGravaSenha::VerificarSenhaFraca() return true");
			return true;
		}
	}

    // Mensagem de cdSenha invalida - sequencia numerica
	setStatus ( SWERRGRA );
	setStaMsg ( MSERRGRA );
	
	ULOG_END("CGravaSenha::VerificarSenhaFraca() return false");
    return false;
}


//--------------------------------------------------------------------------------------------------------------
// Verifica se a senha vai ser alterada ou incluida
//--------------------------------------------------------------------------------------------------------------
bool CGravaSenha::RegistrarSenha()
{
      ULOG_START("CGravaSenha::RegistrarSenha()");
      
      bool ret;
		
		//Caso as pessoas cliente e usuário sejam iguais, grava a senha para ambas.
		if ( verificarClienteUsuarioIguais() == true ){
			if ( getIdSenha() ){ //verifica se existe o registro na customer.Senha
				ret = AlterarSenha();
			}else{
				ret = IncluirSenha(IDSENHAATIVA);
			}
			//Inverte o idTipoRelacionamento
			if ( idTipoRelacionamento.ToInt() == 1)
				idTipoRelacionamento = "2";
			else
				idTipoRelacionamento = "1";
			//Atualiza as variáveis IdPessoa e IdPessoaLinha para o novo idTipoRelacionamento
			getStatusLinha(); 
			//Grava a senha para a outra pessoa após inverter o tipo de relacionamento.
			if ( getIdSenha() ){ //verifica se existe o registro na customer.Senha
				ret = AlterarSenha();
			}else{
				ret = IncluirSenha(IDSENHAATIVA);
			}
		}else{
			if ( getIdSenha() ){ //verifica se existe o registro na customer.Senha
				ret = AlterarSenha();
			}else{
				ret = IncluirSenha(IDSENHAATIVA);
			}
		}

      ULOG_END("CGravaSenha::RegistrarSenha()");
		return ret;
}

bool CGravaSenha::EnviaSMS()
{
	ULOG_START("CGravaSenha::EnviaSMS()");
	XMLGen oEntrada;
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;
	char  c_statusCode[10]  = "";
	char  c_statusText[256] = "";

	// criar o XML para enviar SMS
	oEntrada.addItem("inComunicarUsuario","1");
	oEntrada.addItem("cdAreaRegistro",cdAreaRegistro.c_str());
	oEntrada.addItem("nrLinha",nrLinha.c_str());
	oEntrada.addItem("idTipoRelacionamento",idTipoRelacionamento.c_str());

	ULOG("cdMsg = %s", cdMsg.c_str());

	if ( strcmp(cdMsg.c_str(), "") == 0 )
		oEntrada.addItem("cdMsg","ComunicarAlteracaoSenha");
		
	else		
		oEntrada.addItem("cdMsg", cdMsg.c_str());

	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	inputMessage->setUser(User.c_str());
	inputMessage->setMessageBody(&oEntrada);
	inputMessage->setService("ENVMSG");
	// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
	remoteService->setServiceName("ENVMSG");
	remoteService->setInputMessage(inputMessage);
	
	if((remoteService->remoteCall() != TUXFWRET_OK) || (strlen(c_statusCode) >= 3 && c_statusCode[2] !='I'))
	{
		delete remoteService;
		delete inputMessage;

		char *pc_statusCode = remoteService->getOutputMessage()->getStatusCode();
		char *pc_statusText = remoteService->getOutputMessage()->getStatusText();

		if(pc_statusCode)
			strcpy(c_statusCode, pc_statusCode);

		if(pc_statusText)
		{
			if(strlen(pc_statusText) >= sizeof(c_statusText))
				strncpy(c_statusText, pc_statusText, sizeof(c_statusText) - 1);
			else
				strcpy(c_statusText, pc_statusText);
		}

		setStatus(c_statusCode); 
		setStaMsg(c_statusText); 

		free(pc_statusCode);
		free(pc_statusText);

		throw new TuxBasicSvcException("00E0010","Erro no serviço ENVMSG");
	}
	
   ULOG_END("CGravaSenha::EnviaSMS()");
	return true;
}


bool CGravaSenha::EnviaSMSSenhaGerada(char* cSenha, int iIdCanal, int iCdAreaRegistro, int iNrLinha)
{
	ULOG_START("CGravaSenha::EnviaSMS()");
	XMLGen oEntrada;
	TuxRemoteService* remoteService;
	TuxMessage* inputMessage;
	char  c_statusCode[10]  = "";
	char  c_statusText[256] = "";
	char cMensagem[255] = "";
	char cDataAtual[50] ="";
	char cNrLinha[15] ="";

	// criar o XML para enviar SMS


	sprintf (cMensagem, "%s%s", "Vivo Informa: Senha: ", cSenha);

	if ( iIdCanal == 13 || iIdCanal == 15 )
		strcat (cMensagem, ", gerada em ");
	
	else if ( iIdCanal == 1 || iIdCanal == 23608 )
		strcat (cMensagem, ". Esta senha foi criada, via internet, na Central de Relacionamento ao Cliente em ");

	else if (iIdCanal == 9)
		strcat (cMensagem, ". Esta senha foi criada na URA em ");


	CSenhaBase::getSysDate("dd/mm/yyyy");

	strcpy (cDataAtual, sSysDate.c_str());

	strcat(cMensagem, cDataAtual);

    if ( iIdCanal != 13 && iIdCanal != 15 )
    {
        strcat(cMensagem, " às ");
    }
    else
    {
        strcat(cMensagem, " ");
    }

	CSenhaBase::getSysDate("hh24:mi:ss");

	strcpy (cDataAtual, sSysDate.c_str());
	
	strcat(cMensagem, cDataAtual);

    if ( iIdCanal == 13 || iIdCanal == 15 )
    {
        strcat(cMensagem, ", para utilização na Internet e Auto-Atendimento Vivo.");
    }
    else
    {
        strcat(cMensagem, ".");
    }

	oEntrada.addItem("message", cMensagem);

	sprintf (cNrLinha, "%d%d", iCdAreaRegistro, iNrLinha);

	oEntrada.addItem("recipient", cNrLinha);

	remoteService = new TuxRemoteService();
	inputMessage = new TuxMessage();
	inputMessage->setUser(User.c_str());
	inputMessage->setMessageBody(&oEntrada);
	inputMessage->setService("SMSSend");
	// Repassa configuracoes ao manipulador do serviço remoto e invoca o servico.
	remoteService->setServiceName("SMSSend");
	remoteService->setInputMessage(inputMessage);
	
	if(remoteService->remoteCall() != TUXFWRET_OK)		
	{
		delete remoteService;
		delete inputMessage;
		throw new TuxBasicSvcException("00E0010","Erro no serviço SMSSend");
	}

		//tuxfw_getlogger()->debug("erro no envio de sms************\r\n");
	char *pc_statusCode = remoteService->getOutputMessage()->getStatusCode();
	char *pc_statusText = remoteService->getOutputMessage()->getStatusText();

	if(pc_statusCode)
		strcpy(c_statusCode, pc_statusCode);

	if(pc_statusText)
	{
		if(strlen(pc_statusText) >= sizeof(c_statusText))
			strncpy(c_statusText, pc_statusText, sizeof(c_statusText) - 1);
		else
			strcpy(c_statusText, pc_statusText);
	}

	if (strlen(c_statusCode) >= 3 && c_statusCode[2] !='I')
	{
		
		setStatus(c_statusCode); 
		setStaMsg(c_statusText); 

		free(pc_statusCode);
		free(pc_statusText);

		throw new TuxBasicSvcException("00E0010","Erro no serviço SMSSend");
	}
		
	
   ULOG_END("CGravaSenha::EnviaSMSSenhaGerada()");
	return true;
}




