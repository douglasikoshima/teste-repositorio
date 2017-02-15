#ifndef CTipoApresentacaoPerguntaH

#define CTipoApresentacaoPerguntaH



#include <tuxfw.h>



#include "CTipoApresentacaoPerguntaItr.h"





class CTipoApresentacaoPergunta : public CTipoApresentacaoPerguntaItr

{

    private:

		int InserirRaiz();

		int InserirNode();

		

	public:

		CTipoApresentacaoPergunta();

		~CTipoApresentacaoPergunta();

		int ListId( char* cid );

		int ListAll( void );



		int Insert( char* csgTipoApresentacaoPergunta, 

					char* cdsTipoApresentacaoPergunta );



		int Update( char* cidTipoApresentacaoPergunta, 

				    char* csgTipoApresentacaoPergunta, 

					char* cdsTipoApresentacaoPergunta );



		void GetXml( char* cNomeTag, XMLGen*xml );

};



#endif

