#ifndef CUFH
#define CUFH

#include <tuxfw.h>
#include "../include/CUFItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CUF : public CUFItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CUF();
		~CUF();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidUF );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoFrdUF( char* cidFeriado );
		//Lista existentes
		int ExistemFrdUF( char* cidFeriado );
};

#endif

