#ifndef CMunicipioFeriadoH
#define CMunicipioFeriadoH

#include <tuxfw.h>
#include "../include/CFrdMncItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CMunicipioFeriado : public CMunicipioFeriadoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CMunicipioFeriado();
		~CMunicipioFeriado();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidMunicipioFeriado );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidMunicipio,
			char* cidFeriado,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidMunicipioFeriado );
		//Apaga um ou mais registros
		int EraseMnc( char* cidMunicipio );
		//Apaga um ou mais registros
		int EraseFrd( char* cidFeriado, char* cidUf );
};

#endif

