#ifndef CSegmentacaoH
#define CSegmentacaoH

#include <tuxfw.h>
#include "../include/CSgmItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CSegmentacao : public CSegmentacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CSegmentacao();
		~CSegmentacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidSegmentacao );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoFlhPrz( char* cidContato );
		//Lista existentes
		int ExistemFlhPrz( char* cidContato );
};

#endif

