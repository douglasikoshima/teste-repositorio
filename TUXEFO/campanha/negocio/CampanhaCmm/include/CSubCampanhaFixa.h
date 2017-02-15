#ifndef CSubCampanhaFixaH

#define CSubCampanhaFixaH

#include <tuxfw.h>
#include "CSubCampanhaFixaItr.h"

class CSubCampanhaFixa : public CSubCampanhaFixaItr

{
    private:
		int InserirRaiz();
		int InserirNode();
		
	public:
		CSubCampanhaFixa();
		~CSubCampanhaFixa();
		int ListId( char* cid );
		int ListAll( void );

		int ProcuraNome( char* cnmSubCampanhaFixa );

		int ProcuraNome( char* cnmSubCampanhaFixa,char *cidCampanha );//na mesma campanha
		
		int Insert( char* cidCampanha, 
					char* cinAtiva, 
					char* cinDisponibilidade );

		int Insert( char* cnmSubCampanhaFixa,
			        char* cidCampanha, 
					char* cinAtiva, 
					char* cinDisponibilidade );

		int Update( char* cidSubCampanhaFixa, 
				    char* cidCampanha, 
					char* cinAtiva, 
					char* cinDisponibilidade );

		void GetXml( char* cNomeTag, XMLGen*xml );
		
};

#endif

