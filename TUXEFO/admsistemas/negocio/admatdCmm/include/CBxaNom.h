#ifndef CNomeBaixaH
#define CNomeBaixaH

#include <tuxfw.h>
#include "../include/CBxaNomItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CNomeBaixa : public CNomeBaixaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNomeBaixa();
		~CNomeBaixa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNomeBaixa );
		//Recupera uma lista parcial de nomes
		int ListPar( char* cnmBaixa );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cnmBaixa,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidNomeBaixa,
			char* cnmBaixa,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNomeBaixa );
};

#endif

