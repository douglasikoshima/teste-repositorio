#ifndef CUFFeriadoH
#define CUFFeriadoH

#include <tuxfw.h>
#include "../include/CFrdUFItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CUFFeriado : public CUFFeriadoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CUFFeriado();
		~CUFFeriado();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidUFFeriado );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidUF,
			char* cidFeriado,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidUFFeriado );
		//Apaga um ou mais registros
		int EraseFrd( char* cidFeriado );
		//Apaga um ou mais registros
		int EraseUF( char* cidUF );
};

#endif

