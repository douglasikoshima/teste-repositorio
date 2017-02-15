#ifndef CSmsH
#define CSmsH

#include <tuxfw.h>
#include "../include/CSmsItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CSms : public CCSmsItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CSms();
		~CSms();

		//Monta o xml padrao desta tabela
		void GetXml( XMLGen*xml );
		//Lista pesquisa
		int ListAll();
		int List( char * cidContato );
};

#endif

