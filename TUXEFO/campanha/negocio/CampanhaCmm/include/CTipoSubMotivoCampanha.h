#ifndef CTipoSubMotivoCampanhaH

#define CTipoSubMotivoCampanhaH



#include <tuxfw.h>



#include "CTipoSubMotivoCampanhaItr.h"





class CTipoSubMotivoCampanha : public CTipoSubMotivoCampanhaItr

{

    private:

		int InserirRaiz();

		int InserirNode();

		

	public:

		CTipoSubMotivoCampanha();

		~CTipoSubMotivoCampanha();

		int ListId( char* cid );

		int ListAll( void );



		int Insert( char* csgTipoSubMotivoCampanha, 

					char* cdsTipoSubMotivoCampanha, 

					char* cinDisponibilidade, 

					char* cidPessoaUsuarioInclusao,

					char* cidPessoaUsuarioAlteracao,

					char* cdtInclusao,

					char* cdtAlteracao,

					char* cinAtivo );



		int Update( char* cidTipoSubMotivoCampanha, 

				    char* csgTipoSubMotivoCampanha, 

					char* cdsTipoSubMotivoCampanha, 

					char* cinDisponibilidade, 

					char* cidPessoaUsuarioInclusao,

					char* cidPessoaUsuarioAlteracao,

					char* cdtInclusao,

					char* cdtAlteracao,

					char* cinAtivo );



		void GetXml( char* cNomeTag, XMLGen*xml );

};



#endif

