//------------------------------------------------------------------------------------------------------------------------------
// Basic CSenhaBase
// Roberto Borges dos Santos - 14-01-04
// Implementation 
//------------------------------------------------------------------------------------------------------------------------------

#include "../include/CSenhaBasePC.h"

//------------------------------------------------------------------------------------------------------------------

char TData[13][128] = 
{
	{"Número do CPF"},
	{"Número do RG (documento de identidade)"},
	{"Número do Logradouro (número de endereço de Cobrança cadastrado em nosso sistema)"},
	{"CEP (CEP do endereço de Cobrança cadastrado em nosso sistema)"},
	{"Número do Telefone de Contato (conforme cadastrado em nosso sistema)"},
	{"Data de Nascimento"},
	{"Informe o número do CPF"},
	{"Informe o número do RG (documento de identidade)"},
	{"Informe o número do endereço (sem pontos ou hífen)"},
	{"Informe o CEP (CEP  do endereço de Cobrança cadastrado em nosso sistema)"},
	{"Número do Telefone de Contato (conforme cadastrado em nosso sistema)"},
	{"Informe a data de Nascimento"},
	{NULL}
};

char TtxtAjuda[13][512] = 
{
	{"Informe o número do CPF do titular da linha, digitando apenas os números, sem pontuação ou hífen."},
	{"Informe o número do documento de identidade do titular da linha, digitando apenas os números, sem pontuação ou hífen."},
	{"Digite o número do logradouro informado no endereço de cobrança cadastrado em nosso sistema. Insira somente números. Se você mora em um apartamento, utilize o número do prédio. O complemento do endereço não deve ser utilizado (apartamento, casa, fundos, bloco, etc)."},
	{"Informe o número do CEP do endereço de cobrança cadastrado em nosso sistema."},
	{"Informe o número do Telefone de Contato (conforme cadastrado em nosso sistema)."},
	{"(dd/mm/aaaa)"},	
	{"Informe o número do CPF do titular da linha, digitando apenas os números, sem pontuação ou hífen."},
	{"Informe o número do documento de identidade do titular da linha, digitando apenas os números, sem pontuação ou hífen."},
	{"Digite o número do logradouro informado no endereço de cobrança cadastrado em nosso sistema. Insira somente números. Se você mora em um apartamento, utilize o número do prédio. O complemento do endereço não deve ser utilizado (apartamento, casa, fundos, bloco, etc."},
	{"Informe o número do CEP do endereço de cobrança cadastrado em nosso sistema."},
	{"Informe o número do Telefone de Contato (conforme cadastrado em nosso sistema)."},
	{"(dd/mm/aaaa"},	
	{NULL}
};


//------------------------------------------------------------------------------------------------------------------

CSenhaBase::CSenhaBase()
{
}

//------------------------------------------------------------------------------------------------------------------
// SetNodesDef 
//------------------------------------------------------------------------------------------------------------------

void CSenhaBase::SetNodesDef(DOMNode*pdnode, XMLGen* pxml_g)
{
	bdnode              = pdnode;
	dnode               = pdnode;
	xml_g               = pxml_g;
	ind					= 0; 
	ActPos				= 0;
	QtTentativaErro		= 0; 
    InitLista(); 
    srand(time(NULL));
	
}

//------------------------------------------------------------------------------------------------------------------
// SetNode - Set o node atual
//------------------------------------------------------------------------------------------------------------------

bool CSenhaBase::SetNode( char *cnode )
{
	dnode = NULL; 
	dnode = walkDOM(bdnode, cnode ); 
	return ( dnode != NULL ); 
}

//----------------------------------------------------------------------------------------------------------------------------------------------
// SetNode - Set o node atual e subnode
//----------------------------------------------------------------------------------------------------------------------------------------------

bool CSenhaBase::SetNode( char *cnode, char *subnode )
{
	dnode = NULL; 
	dnode = walkDOM(bdnode, cnode ); 
	dnode = walkDOM(dnode,  subnode ); 
	return ( dnode != NULL ); 
}

//------------------------------------------------------------------------------------------------------------------

TString CSenhaBase::GetXML(char *Campo, bool tpObrigatorio) 
{
	char* p = walkTree(dnode, Campo, ind); 

	TString ret = (p ? p : "");
	
	if ( tpObrigatorio && (!strcmp(p,"null") || !*p ))
		XMLException( Campo ); 
	
	XMLString::release(&p);

    return ret; 
}

//------------------------------------------------------------------------------------------------------------------
// XMLException
//------------------------------------------------------------------------------------------------------------------
void CSenhaBase::SenhaBaseException() 
{
	throw new TuxBasicSvcException(retCode.c_str(),retMsg.c_str());
}


//------------------------------------------------------------------------------------------------------------------
// XMLException
//------------------------------------------------------------------------------------------------------------------
void CSenhaBase::XMLException(char *Campo) 
{
	setStatus(SWNODATA);
	setStaMsg(MSNODATA);
	throw new TuxBasicSvcException(SWNODATA,MSNODATA);
}

//------------------------------------------------------------------------------------------------------------------
// SetMessage - Seta a mensagem de retorno
//------------------------------------------------------------------------------------------------------------------

void CSenhaBase::SetMessage(char *msg, char *status) 
{
  TString txt; 
/*
			txt = "<SenhaBaseRetornoVO xmlns=\"senhas.vol.vivo.com.br/vo\">\n";
	      	txt += "<acaoExecucao>";
			txt += status;
			txt += "</acaoExecucao>\n";
	      	txt += "<mensagem>";
			txt += msg;
			txt += "</mensagem>\n";
	      	txt += "</SenhaBaseRetornoVO>\n";
			xml_g->aggregateXML(txt.c_str());
*/
}


//------------------------------------------------------------------------------------------------------------------
// Init - Metodo Inicial
//------------------------------------------------------------------------------------------------------------------

bool CSenhaBase::Init() 
{
	cdAreaRegistro			= GetXML( "cdAreaRegistro",         true	);
	nrLinha					= GetXML( "nrLinha",				true	);
	idTipoRelacionamento	= GetXML( "idTipoRelacionamento",   true	);
	idCanal					= GetXML( "idCanal",				true	);
	iIdTerminal				= GetXML( "idTerminal",			false	);
	inVivoZAP				= GetXML( "inVivoZAP",          false);
	iInValidaIdPos			= 0;
	iIdTipoSenha            = 1;
	getSysDate();
	return true; 
}

//------------------------------------------------------------------------------------------------------------------
// Executar - Virtual 
//------------------------------------------------------------------------------------------------------------------

void CSenhaBase::Executar() 
{
	Init();

	/*if ( VoName == "LISTAIDPOSVO" ) 
		Agregar(); */

	xml_g->createTag(VoName.c_str());
	xml_g->addProp( "xmlns","senhas.fo.vivo.com.br/vo" );
}

//------------------------------------------------------------------------------------------------------------------

void CSenhaBase::Log(TString msg) 
{
  ULOG("%s",msg.c_str());
}

//------------------------------------------------------------------------------------------------------------------

void CSenhaBase::AddPergunta(int Indice, TString Status, TString Resposta)
{
  TString nr; 
  int iIndiceCanal = 0;


 		nr = Lista[Indice]; 

		char *Campo="";
		if (atoi(nr.c_str()) == -1)
		{
			XMLException( Campo ); 
			return;
		}

		if (idCanal == "13") //a descrição do TAV é diferente do VOL
		{
			iIndiceCanal = 6;
		}

		xml_g->createTag("IDPOS");
		xml_g->addItem( "idPergunta", nr.c_str());
		xml_g->addItem( "dsPergunta", TData[Lista[Indice] + iIndiceCanal]);
		xml_g->addItem( "dsResposta", Resposta.c_str());
		xml_g->addItem( "idStatus", Status.c_str() ); 
		xml_g->addItem( "dsAjuda", TtxtAjuda[Lista[Indice]]); 
		xml_g->closeTag();
}




//--------------------------------------------------------------------------------------------------------------
// Get random number
//--------------------------------------------------------------------------------------------------------------
int CSenhaBase::getRandom()
{

   for(int i=0;i<=30;i++)
	if ( setValue(rand()%indsort) )
		break; 

	return 0; 
}

//--------------------------------------------------------------------------------------------------------------
// set values 
//--------------------------------------------------------------------------------------------------------------

bool CSenhaBase::setValue(int pind, bool vlista )
{
   
   int Valor = vlista == true ? pind : Sortable[pind]; 
    
   if ( vlista == false )
   {
	   if ( !chkValue (Valor) && chkQuestoes(Valor) )  
	   {
 		   Lista[ActPos++] = Valor ; 
		   return true; 
	   }
   }
   else
   {
	  if ( !chkValue (Valor) )  
	  {
		   Lista[ActPos++] = Valor ; 
		   return true; 
	  }
   }
   return false; 
}

//--------------------------------------------------------------------------------------------------------------
// Check if value exists
//--------------------------------------------------------------------------------------------------------------

bool CSenhaBase::chkValue(int Valor)
{

	for( int x = 0; x <= ActPos; x++ )
		if ( Lista[x] == Valor )
			return true; 

 return false; 
}


//--------------------------------------------------------------------------------------------------------------

void CSenhaBase::InitLista()
{
	for( int x=0; x<10; x++ ) {
		 Lista[x]=-1; 
	  Sortable[x]=-1; 
	}
	indsort = 0; 
	ActPos	= 0;
}


//--------------------------------------------------------------------------------------------------------------


bool CSenhaBase::chkQuestoes(int Quest)
{
bool res; 



	switch( Quest ) 
	{
		case ECPF		 :	res = getCPF	     (); break; 
		case ERG		 :	res = getRG 	     (); break; 
		case NRRESIDENCIA	:	res = getNumEndereco  (); break; 
		case CEP			:	res = getCEP		  (); break; 
		case TELCONTATO  	:	res = getTelefone	  (); break; 
		case DATANASC		:	res = getDataNasc	  (); break; 
			
		default:	
				SetMessage( MSOPCINV, "N" ); 
				setStatus ( SWOPCINV );
				setStaMsg ( MSOPCINV );
				return false; 
	}



	return res;
}


//--------------------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------------------------------------
// Quando o idCanal pertencer a URA (idCanal=9) os campos disponíveis serão apenas:CEP, Data nascimento, CPF, RG
//--------------------------------------------------------------------------------------------------------------

bool CSenhaBase::GetListSort( int NrPerguntas )
{
	int num[10] = { CEP, DATANASC, ECPF, ERG, -1 }; 
	int i = 0; 


	int iPerguntaZap = 0;
	
	if ( idCanal == "9" ) 
	{
		do
		{
		  if ( chkQuestoes(num[i]) ) 
			   Sortable[indsort++]=num[i]; 
 
		}while( num[i++] >= 0 );
		
	}
	else if ( inVivoZAP.ToInt() == 1 )	
	{
		NrPerguntas = getNumeroPerguntasZAP();
		//FillSortable();		
		for( int i = 0; i < 6; i++ ) 
		{
			if ( (iPerguntaZap = QuestionarioZAP(i)) >= 0)
			{
			
				if ( chkQuestoes(iPerguntaZap) ) 
				{
					Sortable[indsort++]=iPerguntaZap; 
					setValue(iPerguntaZap, true);			
					
				}	

			}
		
			if (indsort  == getNumeroPerguntasZAP())
				break;
		
		}
	}
	else
	{
		//FillSortable();		
		for( int i = 0; i < 6; i++ ) 
		{
				if ( chkQuestoes(i) ) 
				{
					Sortable[indsort++]=i; 
					setValue(i, true);			
				
			    }  
		}
	}
	if ( indsort < (NrPerguntas - 1) ) 
	{
		SetMessage( MSNODATA, "N" ); 
		setStatus ( SWNODATA );
		setStaMsg ( MSNODATA );
		xml_g->closeTag();
		return false; 
	}

	

	return true; 

}
//--------------------------------------------------------------------------------------------------------------

void CSenhaBase::FillSortable()
{
	/*	for( int i=0; i < MAXOPT; i++ ) 
			if ( chkQuestoes(i) ) {
				Sortable[indsort++]=i; 
				return;
		    }  */
}

//--------------------------------------------------------------------------------------------------------------

void CSenhaBase::Agregar()
{
	xml_g->addItem( "cdAreaRegistro", cdAreaRegistro.c_str());
	xml_g->addItem( "nrLinha", nrLinha.c_str());
	xml_g->addItem( "idTipoRelacionamento", idTipoRelacionamento.c_str());
	xml_g->addItem( "idCanal", idCanal.c_str() ); 
}

//--------------------------------------------------------------------------------------------------------------
void CSenhaBase::setInValidaIdPos(int value)
{
	iInValidaIdPos = value;
}

void CSenhaBase::setIdTipoRelacionamento(int value)
{
	char cValue[2];

	memset (cValue, 0, sizeof(cValue));

	sprintf (cValue, "%d", value);

	idTipoRelacionamento = cValue;

}


char* CSenhaBase::getIdTipoRelacionamento()
{	
	return idTipoRelacionamento.c_str();
}