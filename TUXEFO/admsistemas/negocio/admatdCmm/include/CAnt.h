#ifndef CIndicadorAnatelH
#define CIndicadorAnatelH

#include <tuxfw.h>
#include "../include/CAntItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CIndicadorAnatel : public CIndicadorAnatelItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CIndicadorAnatel();
		~CIndicadorAnatel();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidIndicadorAnatel );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoFlhAnt( char* cidContato );
		//Lista existentes
		int ExistemFlhAnt( char* cidContato );
		//Lista relacionados
		int RelacaoBxaAnt( char* cidContato );
		//Lista existentes
		int ExistemBxaAnt( char* cidContato );
};

#endif

