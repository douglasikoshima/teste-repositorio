//------------------------------------------------------------------------------		

#include <ctype.h>
#include "TString.h"
#include <tuxfw.h>
#include "SRelatorioCampanha.h"


//------------------------------------------------------------------------------		
// CGetHeader 
//------------------------------------------------------------------------------		

class CGetHeader : private TuxHelper
{
	public:
		//------------------------------------------------------------------------------		
		 CGetHeader(struct SRelatorioCampanha* , XMLGen* );	// Constructor
		~CGetHeader(){};															// Destructor
		virtual int Executar();
		//------------------------------------------------------------------------------		
	protected: 
		//------------------------------------------------------------------------------		
		int		getIdGrupo();
		void	XMLException	( char * ); 
		void	SetMessage		( char *, char *); 
		void	sql_error();
		bool	Init();
		//------------------------------------------------------------------------------		
		TString GetXML ( char *, bool tpObrigatorio = false );
		DOMNode *dnode  ;
		DOMNode *bdnode ;
		//------------------------------------------------------------------------------		
		XMLGen  *xml_g  ;
		XMLGen   oxml   ;
		//------------------------------------------------------------------------------		
		// Parametros de entrada
		//------------------------------------------------------------------------------		
		TString idCampanha;	
		TString idSubCampanha;
		TString idCanalCampanha;
		TString idGrupo;
		TString idRegional;
		TString dtInicio;
		TString dtFim;
		TString idMotivo;
		TString idPergunta;
		TString idOperador;
		TString sgOperador;
		TString idAreaRegistro;
		TString dtAniversari;
		TString nrLinha;
		TString idDistincao;
		//------------------------------------------------------------------------------		
		// Dados Header
		//------------------------------------------------------------------------------		
		TString DataEmissao; 
		TString Campanha;
		TString Versao;
		TString Perfil;
		TString InicioVigencia;
		TString FimVigencia;
		TString PublicoAlvo;
		TString OperadorRelatorio;
		TString SubCampanha;
		TString StatusSubCamp;
		TString Canal;
		TString OperadorSelecionado;
		TString Usuario;
		TString MetaDiaria;
		TString Operadora;
		TString idTxUsuario; 
		TString inDistincao;
		TString nmGrupo;
		TString nmRegional;
		TString cdAreaRegistro;
		//------------------------------------------------------------------------------		
		// Pro-C  - Header Functions definition
		//------------------------------------------------------------------------------
		bool getSysDate(); 
		void getXml(); 
		int  getData();
		void getUsuario(); 
		void getOperador(); 

};


