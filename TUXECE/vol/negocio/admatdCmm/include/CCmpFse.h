#ifndef CFaseProcessoH
#define CFaseProcessoH

#include <tuxfw.h>
#include "../include/CCmpFseItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CFaseProcesso : public CFaseProcessoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CFaseProcesso();
		~CFaseProcesso();

		void GetXml( char* cNomeTag, XMLGen*xml );
		int ListId( char* cidFaseProcesso );
		int ListAll( void );
		int Insert( 
			char* cnmFaseProcesso,
			char* cidUsuarioAlteracao );
		int Update( 
			char* cidFaseProcesso,
			char* cnmFaseProcesso,
			char* cidUsuarioAlteracao );
		int Delete( char* cidFaseProcesso );
		int ListaFases( XMLGen * xml );

};

#endif

