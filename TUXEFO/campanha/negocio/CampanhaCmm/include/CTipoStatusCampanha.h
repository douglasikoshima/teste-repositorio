#ifndef CTipoStatusCampanhaH

#define CTipoStatusCampanhaH



#include <tuxfw.h>



#include "CTipoStatusCampanhaItr.h"





class CTipoStatusCampanha : public CTipoStatusCampanhaItr

{

    private:

		int InserirRaiz();

		int InserirNode();

		

	public:

		CTipoStatusCampanha();

		~CTipoStatusCampanha();

		int ListId( char* cid );

		int ListAll( void );



		int Insert( char* csgTipoStatusCampanha, 

					char* cdsTipoStatusCampanha );



		int Update( char* cidTipoStatusCampanha, 

				    char* csgTipoStatusCampanha, 

					char* cdsTipoStatusCampanha );



		void GetXml( char* cNomeTag, XMLGen*xml );

};



#endif

