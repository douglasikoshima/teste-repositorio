#ifndef CMotivoCampanhaH
#define CMotivoCampanhaH


#include <tuxfw.h>
#include "CMotivoCampanhaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CMotivoCampanha : public CMotivoCampanhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();		

	public:
		CMotivoCampanha();
		~CMotivoCampanha();
		int ListId( char* cid );
		int ListAll( void );
		
		//Pesquisa todos os itens de uma subcampanha historico
		int ListPorSubCampanhaHistorico( char* cidSubCampanhaHistorico );

		int Insert( char* cidSubCampanhaHistorico,
					char* cidTipoStatusCampanha,
					char* cidTipoMotivoCampanha,
					char* cidTipoSubMotivoCampanha,
					char* cidUsuarioAlteracao );

		int Insert( char* cidSubCampanhaHistorico,
					char* cidTipoStatusCampanha,
					char* cidTipoMotivoCampanha,
					char* cidTipoSubMotivoCampanha,
					char* cinAtivo,
					char* cidUsuarioAlteracao );

		int Update( char* cidMotivoCampanha,
				    char* cidSubCampanhaHistorico,
					char* cidTipoStatusCampanha,
					char* cidTipoMotivoCampanha,
					char* cidTipoSubMotivoCampanha,
					char* cidUsuarioAlteracao );

		void GetXml( char* cNomeTag, XMLGen*xml );
};


#endif

