//--------------------------------------------------------------------------------------------------------------
// CListaIDPos - Component Implementation
//--------------------------------------------------------------------------------------------------------------

#include "../include/ListaIDPosPC.h"


//--------------------------------------------------------------------------------------------------------------
// Construtor
//--------------------------------------------------------------------------------------------------------------

CListaIDPos::CListaIDPos(DOMNode* dnode, XMLGen* xml_g, char *user) 
{
     SetNodesDef( dnode, xml_g ); 
     User = user; 
     setStatus(SWSUCESS);
     setStaMsg(MSSUCESS);
	 VoName = "LISTAIDPOSVO"; 
}

//--------------------------------------------------------------------------------------------------------------
// Executar
//--------------------------------------------------------------------------------------------------------------

void CListaIDPos::Executar() 
{
   ULOG_START("CListaIDPos::Executar()");
   
	TString nr;

	CSenhaBase::Executar(); 

	getStatusLinha();		

	// Preenche a lista com dados possiveis 

	if ( getIdSenha() == true )
	{
		//if ( DtPrimeiroAcesso == sSysDate || DtBloqueio == sSysDate ) 
			//if ( QtTentativaErro.ToInt() >= 3 ) 
			//{

				//Log( "ValidaQuestoes >= 3 "); 
				//setStatus("11W0007");
				//setStaMsg(MSRESPEX);
				//ULOG_END("CListaIDPos::Executar()");
			    //return; 
			//}
	}

	if ( !GetListSort() ) 
	{
		setStatus("11W0009");	
		setStaMsg("Favor entrar em contato com a central de relacionamento com o cliente para atualização cadastral");
		return; 
	}

	int iNumPerguntas = 1;

	if ( inVivoZAP.ToInt() == 1 )
		iNumPerguntas = getNumeroPerguntasZAP();

	if ( idCanal == "9")
		iNumPerguntas = 3;

	for( int i = 0; i < iNumPerguntas; i++ )
			AddPergunta( i ); 


	
	SetMessage( "Consulta Concluida com Sucesso", "S" ); 

/*	if ( getIdSenha() )
	{
		setStatus(SWERRSEX);
		setStaMsg(MSERRSEX);
	}
*/

	//Verfica se a senha já está cadastrada.
//	if ( verificarSenha() == true )
//	{
	   setStatus(SWSUCESS);
	   setStaMsg(MSSUCESS);
//	}

    xml_g->closeTag();
    
    ULOG_END("CListaIDPos::Executar()");
}

//--------------------------------------------------------------------------------------------------------------