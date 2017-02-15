#ifndef CPesquisaSatisfacaoPerguntaH
#define CPesquisaSatisfacaoPerguntaH

#include <tuxfw.h>
#include "../include/CSatPerItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesquisaSatisfacaoPergunta : public CPesquisaSatisfacaoPerguntaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPesquisaSatisfacaoPergunta();
		~CPesquisaSatisfacaoPergunta();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidPesquisaSatisfacaoPergunta );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidPesquisaSatisfacao,
			char* cidPergunta,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidPesquisaSatisfacaoPergunta );
		//Apaga um ou mais registros
		int ErasePer( char* cidPergunta );
		//Apaga um ou mais registros
		int EraseSat( char* cidPesquisaSatisfacao );
};

#endif

