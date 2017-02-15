
//--------------------------------------------------------------------------------------

#undef SQLCA
#define SQLCA_NONE
#include <sqlca.h>
#include <sqlda.h>
#include <ctype.h>
#include <time.h>
#include "TString.h"
//--------------------------------------------------------------------------------------
#include <tuxfw.h>
//#include <SmallString.h>
//--------------------------------------------------------------------------------------
//#include "../../../../commons/msgPadrao.h"

//--------------------------------------------------------------------------------------

typedef enum tipoSort { ECPF = 0, ERG = 1,  NRRESIDENCIA = 2, CEP = 3, 
						TELCONTATO = 4, DATANASC = 5 }; 

//--------------------------------------------------------------------------------------

#define SEP				"%%"
#define MAXOPT			8
#define IDSENHAINATIVA	"3"
#define IDSENHAATIVA	"1"
#define CANAL_VOL		"15"
#define CANAL_TAV		"13"

//--------------------------------------------------------------------------------------
// Error status and Messages
//--------------------------------------------------------------------------------------

#define SWSUCESS "11I0000"
#define MSSUCESS "ID-Pós retornado com sucesso."

#define SWTENTAT "11W0001"
#define MSTENTAT "Número de tentativas excedido, não será possível validar o ID-Pós."

#define SWNOATIV "11W0002"
#define MSNOATIV "Linha não ativa"

#define SWNODATA "11W0003"
#define MSNODATA "Não existem dados suficientes para validação do ID-Pós."

#define SWOPCINV "11W0008"
#define MSOPCINV "Opção inválida"

#define SWERDATA "11W0007" 
#define MSERDATA "Não existem dados suficientes para retornar a 4a pergunta."

#define SWERRGRA "11W0001"
#define MSERRGRA "Não foi possível efetuar a gravação da senha."

#define SWSUCPAS "11I0000"
#define MSSUCPAS "Senha gravada com sucesso."

#define SWERRSEQ "11W0002"
#define MSERRSEQ "Senha Sequencial."

#define SWERRIGU "11W0003"
#define MSERRIGU "Senha com digitos iguais."

#define SWERRSEX "11W0004"
#define MSERRSEX "Senha já cadastrada."

//--------------------------------------------------------------------------------------

#define SWRESPER "11I0001" 
#define MSRESPER "Uma das questões foi respondida incorretamente, será retornada uma quarta pergunta."

#define SWRESP01 "11W0002" 
#define MSRESP01 "Respostas incorretas. 1a Tentativa."

#define SWRESP02 "11W0003" 
#define MSRESP02 "Respostas incorretas. 2a Tentativa."

#define SWRESP03 "11W0004" 
#define MSRESP03 "Respostas incorretas. 3a Tentativa."

#define SWRESPEX "11W0005" 
#define MSRESPEX "Número de tentativas excedido, não será possível validar o ID-Pós."

#define SWPESSJR "11W0006" 
#define MSPESSJR "Funcionalidade específica para pessoa física. A pessoa em questão não é física."

#define	SWRESINV "11W0004"
#define	MSRESINV "Senha inválida."
//--------------------------------------------------------------------------------------

#define STRCPY_TO_ORA(dest, source){ \
        dest.len = (unsigned short) strlen(source); \
        strcpy((char *) dest.arr, (const char *) source);}

//--------------------------------------------------------------------------------------

#define STRCPY_FROM_ORA(dest, source)\
        dest[source.len] = 0;\
        strncpy((char *)dest,(const char *)source.arr, source.len)

//------------------------------------------------------------------------------		
// CSenhaBase 
//------------------------------------------------------------------------------		

class CSenhaBase : private TuxHelper
{
	public:
		TString TipoPessoa;
		TString VoName;
		//------------------------------------------------------------------------------		
		CSenhaBase			();		// Constructor
	   ~CSenhaBase			(){};	// Destructor
		virtual void Executar(); 

		TString getStatus() { return retCode; }; 
		TString getStaMsg() { return retMsg; }; 

		void    setStatus( char * sta ) { retCode = sta; }; 
		void    setStaMsg( char * sta ) { retMsg  = sta; }; 
		
		TString IdPessoa;
        TString IdPessoaLinha;
        TString IdSenha;		
		void	consultarIdPessoa(int iCdAreaRegistro, int iNrLinha);
		//------------------------------------------------------------------------------		
	protected: 
		void Agregar();
		void	AddPergunta		( int , TString Status = "2", TString Resposta = "" );		
		bool	getCPF			( TString param = "" );
		bool	getRG			( TString param = "" );
		bool	getPAS			( TString param = "" );

		bool	getTelefone		( TString param = "" );
		bool	getContaDebito	( TString param = "" );
		bool	getDataVenc		( TString param = "" );
		bool	getNumEndereco	( TString param = "" );
		bool	getCEP			( TString param = "" );
		bool	getDataNasc		( TString param = "" );
        bool	getIdSenha      ();
        bool	AlterarSenha    ();
        bool	IncluirSenha    ( TString tpSenha = IDSENHAATIVA );
		bool	verificarSenha	();
		bool	verificarClienteUsuarioIguais();
		bool	verificarSenhaInvalida();
		//------------------------------------------------------------------------------		
		int		ind; 
		bool	GetListSort		( int NrPerguntas = 3 );
		void	SetNodesDef		( DOMNode *, XMLGen * );
		bool	SetNode			( char * );
		void	XMLException	( char * ); 
		void	SetMessage		( char *, char * ); 
		bool	SetNode			( char *, char * );
		void	Log				( TString msg );
		void	sql_error		( );
		bool	Init			( );
		void    FillSortable    ( );
		void	SenhaBaseException( );
		void 	Next			( )			{ ind++; } 
		void	Back			( )			{ ind--; } 
		//------------------------------------------------------------------------------		
		bool	chkQuestoes		( int );
		bool	chkValue		( int );
		bool	setValue		( int , bool vlista = false );
		int		getRandom		( );
		void	InitLista		( );
		void	setInValidaIdPos( int );
		void	setIdTipoRelacionamento( int value );
		char*   getIdTipoRelacionamento( );

		
		int		QuestionarioZAP( int i );
		int		getNumeroPerguntasZAP ();

		//------------------------------------------------------------------------------		

		TString retCode ;
		TString retMsg  ;

		//------------------------------------------------------------------------------		
		// Pro-C Methods
		//------------------------------------------------------------------------------		

		bool	getSysDate	   ( );
		bool    getSysDate(char* cMascara);
		TString getStatusLinha ( );

		//------------------------------------------------------------------------------		
		TString GetXML		 ( char *, bool tpObrigatorio = false );
		TString User          ; 
	    TString sSysDate      ;
		TString UsuarioAtual  ;
		TString idUsuAtual	  ;
		TString cdAreaRegistro;
		TString nrLinha		  ;
		TString idCanal		  ;
		TString idTipoRelacionamento;
        TString cdSenha;
		TString iIdTerminal;		
		TString iIdTipoSenha;
		//------------------------------------------------------------------------------		
		TString CPF;
		TString RG;
		TString PAS;
		TString TelefoneContato;
		TString NrResidencia;
		TString ContaDebito;
		TString DataVenc;
		TString NumeroCEP;
		TString DataNascimento;
		TString DtPrimeiroAcesso;
		TString QtTentativaErro;
		TString dsLembreteSenha;
		TString inVivoZAP;
		TString DtBloqueio;

		//------------------------------------------------------------------------------		
		DOMNode *dnode  ;
		DOMNode *bdnode ;
		//------------------------------------------------------------------------------		
		XMLGen  *xml_g  ;
		XMLGen   oxml   ;
		//------------------------------------------------------------------------------		
		int Lista		[MAXOPT+2];
		int Sortable	[MAXOPT+2];
		int	indsort;
		int ActPos;
		int iInValidaIdPos;
	
		//------------------------------------------------------------------------------		



};