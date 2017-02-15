#ifndef CNomeContatoH
#define CNomeContatoH

#include <tuxfw.h>
#include "../include/CCttNomItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CNomeContato : public CNomeContatoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNomeContato();
		~CNomeContato();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNomeContato );
		//Recupera todos os registros
		int ListPar( char* cnmContato,char* cidTipoArvore);
		//Recupera todos os registros
		int ListAll( char* cidTipoArvore = 0);
		//Insere um registro
		int Insert( 
			char* cnmContato,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidNomeContato,
			char* cnmContato,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNomeContato );
};

#endif

