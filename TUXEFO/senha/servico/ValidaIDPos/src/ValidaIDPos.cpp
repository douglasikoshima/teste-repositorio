
//--------------------------------------------------------------------------------------------------------------
// CValidaIDPos - Component Implementation
/*--------------------------------------------------------------------------------------------------------------
  Features:
----------------------------------------------------------------------------------------------------------------

	-	Verificar se a linha está ativa.

	-	Será retornado o  body apenas se ocorrer o  statusCode "11W0001", ou  seja, caso  uma das questões tenha 
		sido  respondida incorretamente,  então retornará  uma 4a. pergunta.  Nesse caso, na  próxima  validação 
		deverão ser passadas do Java para o serviço todas as 4 perguntas.

	-	Quando for passado como parâmetro de entrada  4  perguntas e uma ou mais possuírem respostas incorretas:

			A> Atualizar a quantidade de tentativas na tabela
			B> Verificar se foi excedido 3 tentativas

	-	O período para contagem das 3 tentativas de acesso sem sucesso, é das 0:00h às 24:00h do dia em questão.

	-	As perguntas que serão retornadas deverão obrigatoriamente  ter conteúdo no  banco de  dados para serem 
		validades posteriormente.

--------------------------------------------------------------------------------------------------------------*/


#include "../include/ValidaIDPosPC.h"
#include <time.h>

//--------------------------------------------------------------------------------------------------------------
// Construtor
//--------------------------------------------------------------------------------------------------------------

CValidaIDPos::CValidaIDPos(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     User = user; 
     setStatus(SWSUCESS);
     setStaMsg(MSSUCESS);
	 VoName = "VALIDAIDPOSVO"; 
	 InitLista();
}

//--------------------------------------------------------------------------------------------------------------
// Executar
//--------------------------------------------------------------------------------------------------------------

void CValidaIDPos::Executar() 
{
   ULOG_START("CValidaIDPos::Executar()");
	TString status; 
	CSenhaBase::Executar(); 

	getSysDate();

	// Verifica se a linha esta ativa 

	if ( idTipoRelacionamento == "0")
		validaIdNaoIdentificado();

	getStatusLinha(); 
	
	FillSortable();

	// Valida questoes recebidas
	if ( ValidaQuestoes() ) 
		SetMessage( "Consulta Concluida com Sucesso", "S" ); 
	
    //xml_g->closeTag();
    
    ULOG_END("CValidaIDPos::Executar()");
}
 
//--------------------------------------------------------------------------------------------------------------
// ValidaQuestoes - Valida Questoes e retorna se houve sucesso ou nao
//--------------------------------------------------------------------------------------------------------------

bool CValidaIDPos::validaIdNaoIdentificado()
{
    TString dsResposta; 
	
	dsResposta = GetXML( "dsResposta", false );

	for (int i = 1; i <= 2; i++ )
	{
		setIdTipoRelacionamento(i);

		getStatusLinha();
	
		if ( getCPF(dsResposta) || getRG  (dsResposta) || getPAS (dsResposta) )
			return true;

		if (i == 2) /*já tentou com o usuário e com o cliente*/
		{
			logTentativas(1);
			return false;

		}
	
	}
}

bool CValidaIDPos::ValidaQuestoes()
{ 
   ULOG_START("CValidaIDPos::ValidaQuestoes()");
   
   int  i;
   int  ctErro=0, ctPergunta=0; 
   bool res;
   bool QuartaPergunta = false; 
   
   TString idPergunta; 
   TString dsResposta; 
   TString dsstatus  ; 

   //-----------------------------------------------------------------------------------------------------------
   // Verifica se ja estao expiradas as tentativas
   //-----------------------------------------------------------------------------------------------------------

	if ( getIdSenha() == true ) 
	{
		if ( DtPrimeiroAcesso == sSysDate ) 
			if ( QtTentativaErro.ToInt() >= 3 ) 
			{

				Log( "ValidaQuestoes >= 3 "); 
				setStatus(SWRESPEX);
				setStaMsg(MSRESPEX);
				ULOG_END("CValidaIDPos::ValidaQuestoes()");
			    return false; 
			}
	}

   //-----------------------------------------------------------------------------------------------------------
   // Loop para validação das perguntas
   //-----------------------------------------------------------------------------------------------------------
	 
   for(i=0;;i++)
   {

	idPergunta = GetXML( "idPergunta" );

	if ( idPergunta == "" ) 
		  break; 
	
	int sta = GetXML("idStatus").ToInt();


	if ( sta == 1 ) 
	{
	    Next(); 
		continue; 
	}
	else
	if ( sta == 2 && i == 3) 
		  QuartaPergunta = true; 

	dsResposta = GetXML( "dsResposta", false );

	//Caso a resposta seja nula, considerar como incorreta.
	if (dsResposta == "")
		res = false;
	
	else if  (idPergunta == ""  ||  idPergunta == "-1" ) 	
		return validaIdNaoIdentificado();

	else
	{ 
		switch( idPergunta.ToInt() ) 		
		{ 
			case ECPF			:	res = getCPF	      (dsResposta); break; 
			case ERG			:	res = getRG 	      (dsResposta); break; 		
			case NRRESIDENCIA	:	res = getNumEndereco  (dsResposta); break; 
			case CEP			:	res = getCEP		  (dsResposta); break; 
			case TELCONTATO 	 :	res = getTelefone	  (dsResposta); break; 
			case DATANASC		:	res = getDataNasc	  (dsResposta); break; 

			default:	
								SetMessage ( MSOPCINV, "N" ); 
								setStatus  ( SWOPCINV );
								setStaMsg  ( MSOPCINV );
								ULOG_END("CValidaIDPos::ValidaQuestoes()");
								return false; 
		}
	}

	dsstatus = 0; 

	if ( res == false ) 
	{
		TString slog = "** ERRO: Dados não validados : idPergunta = "; 
		slog += idPergunta; 
		slog += " dsReposta = "; 
		slog += dsResposta; 
		Log( slog ); 
		ctErro++;
		dsstatus = 1; 
	}

	setValue( idPergunta.ToInt(), true );
	AddPergunta( ctPergunta++, dsstatus, dsResposta ); 
	Next(); 
   }

   //-----------------------------------------------------------------------------------------------------------------
   // Fim do Loop. 
   //-----------------------------------------------------------------------------------------------------------------

   if ( ctErro ) 
   { 
		// Verifica nº tentativas. Case excedido, retorna erro. 

		if ( !logTentativas(ctErro) )  
		{
		    ULOG_END("CValidaIDPos::ValidaQuestoes()");
			return false; 
		}
      ULOG_END("CValidaIDPos::ValidaQuestoes()");
		return false; 
   }
   
   ULOG_END("CValidaIDPos::ValidaQuestoes()");
   return true;
}

//---------------------------------------------------------------------------------------------------------------------

bool CValidaIDPos::chkTentativas(int ctErro)
{
	//Verifica se apenas 1 das questões está incorreta, então retorna a quarta pergunta.

	if (ctErro == 1)
		return true; 
	else //do contrário atualiza o número de tentativas.
		return logTentativas(ctErro); 

}

//---------------------------------------------------------------------------------------------------------------------

bool CValidaIDPos::logTentativas(int ctErro)
{
   ULOG_START("CValidaIDPos::logTentativas()");

//		if (( idTipoRelacionamento == "2" && IdPessoa.c_str() == "") ||
			//( idTipoRelacionamento == "1" && IdPessoaLinha.c_str() == ""))
		

		// Busca dados de Senha
		if ( getIdSenha() == false ) 
		{
			// Senha nao Cadastrada, incluir registro para este cliente. 
			if ( IncluirSenha( IDSENHAINATIVA ) == false )
			{
				// Deu Erro, Nao foi possivel cadastrar senha. 
				setStatus(SWERRGRA);
				setStaMsg(MSERRGRA);
				ULOG_END("CValidaIDPos::logTentativas()");
				return false; 
			}
			else
				getIdSenha();
		}

		// Senha encontrada..
		// verificando se ultimo acesso foi hoje. 
		// Altera Senha com qtd de tentativas.
		if ( DtPrimeiroAcesso != sSysDate )
			QtTentativaErro = 1; // Set 1 
		else
			QtTentativaErro = QtTentativaErro.ToInt() + 1; // Adiciona 1 para tentativa

		//Identifica que que o serviço é o ValidaIdPos para não registrar histórico de senha como Reinicializar Senha.
		setInValidaIdPos(1);

		if ( AlterarSenha() == false )
		{
			setStatus(SWERRGRA);
			setStaMsg(MSERRGRA);
			ULOG_END("CValidaIDPos::logTentativas()");
			return false; 
		}


		switch( QtTentativaErro.ToInt() ) 
		{
			case 1:

				if (idCanal == CANAL_VOL) // VOL
				{
					setStatus(SWRESP02);
					setStaMsg(MSRESP02);
				}
				else 
				{
					setStatus(SWRESP01);
					setStaMsg(MSRESP01);
				}

				
				ULOG_END("CValidaIDPos::logTentativas()");
				return false; 
				break;
				
			case 2:

				if (idCanal == CANAL_VOL) // VOL
				{
					setStatus(SWRESP03);
					setStaMsg(MSRESP03);
				}
				else				//TAV
				{
					setStatus(SWRESP02);
					setStaMsg(MSRESP02);
				}

				
				ULOG_END("CValidaIDPos::logTentativas()");
				return false; 
				break;

	

			default:
				setStatus("11W0005");
				setStaMsg(MSRESPEX);
				ULOG_END("CValidaIDPos::logTentativas()");
				return false; 

		}

}



char* CValidaIDPos::getIdTipoRelacionamento()
{	
	return CSenhaBase::getIdTipoRelacionamento();
}

