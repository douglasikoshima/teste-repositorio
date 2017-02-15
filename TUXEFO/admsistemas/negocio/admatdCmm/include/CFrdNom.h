#ifndef CNomeFeriadoH
#define CNomeFeriadoH

#include <tuxfw.h>
#include "../include/CFrdNomItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CNomeFeriado : public CNomeFeriadoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNomeFeriado();
		~CNomeFeriado();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNomeFeriado );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cdsFeriado,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidNomeFeriado,
			char* cdsFeriado,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNomeFeriado );
};

#endif

