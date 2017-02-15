#ifndef CNivelHierarquiaH
#define CNivelHierarquiaH

#include <tuxfw.h>
#include "../include/CNHItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CNivelHierarquia : public CNivelHierarquiaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNivelHierarquia();
		~CNivelHierarquia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNivel );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(	char* cidNivel,
					char* cidNivelPai,
					char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNivel );
};

#endif

