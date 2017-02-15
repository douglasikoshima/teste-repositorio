#ifndef CContatoHierarquiaH
#define CContatoHierarquiaH

#include <tuxfw.h>
#include "../include/CCttHieItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoHierarquia : public CContatoHierarquiaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoHierarquia();
		~CContatoHierarquia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidContato,
			char* cidContatoPai,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContato );
};

#endif

