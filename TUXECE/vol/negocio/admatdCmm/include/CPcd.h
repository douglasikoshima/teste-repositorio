#ifndef CProcedenciaH
#define CProcedenciaH

#include <tuxfw.h>
#include "../include/CPcdItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CProcedencia : public CProcedenciaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CProcedencia();
		~CProcedencia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidProcedencia );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoFlhPrz( char* cidContato );
		//Lista existentes
		int ExistemFlhPrz( char* cidContato );
};

#endif

