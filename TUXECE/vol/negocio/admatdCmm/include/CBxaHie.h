#ifndef CBaixaHierarquiaH
#define CBaixaHierarquiaH

#include <tuxfw.h>
#include "../include/CBxaHieItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CBaixaHierarquia : public CBaixaHierarquiaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CBaixaHierarquia();
		~CBaixaHierarquia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidBaixa );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidBaixa,
			char* cidBaixaHierarquia,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidBaixa );
};

#endif

