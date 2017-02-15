#ifndef CCampanhaH

#define CCampanhaH



#include <tuxfw.h>



#include "CCampanhaItr.h"





class CCampanha : public CCampanhaItr

{

    private:

        int InserirRaiz();

		int InserirNode();



	public:

		CCampanha();

		~CCampanha();

		int ListId( char* cid );

		int ListAll( void );



		int Insert( char* csgCampanha, 

					char* cdsCampanha, 

					char* cdtInclusao, 

					char* cdtAlteracao,

					char* cidPessoaUsuarioInclusao,

					char* cidPessoaUsuarioAlteracao,

					char* cinAtivo );



		int Update( char* cidCampanha, 

				    char* csgCampanha, 

					char* cdsCampanha, 

					char* cdtInclusao, 

					char* cdtAlteracao,

					char* cidPessoaUsuarioInclusao,

					char* cidPessoaUsuarioAlteracao,

					char* cinAtivo );



		void GetXml( char* cNomeTag, XMLGen*xml );

};



#endif

