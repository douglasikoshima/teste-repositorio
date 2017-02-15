#ifndef CUFOperadoraH
#define CUFOperadoraH

#include <tuxfw.h>
#include "../include/CUfoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CUFOperadora : public CUFOperadoraItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CUFOperadora();
		~CUFOperadora();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXmlFrm( char* cNomeTag, XMLGen*xml );
		void GetXmlPadrao( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidUFOperadora );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoCttUfo( char* cidContato );
		//Lista existentes
		int ExistemCttUfo( char* cidContato );
		//UFOperadoras relacionadas a formulario
		int Relacionadas( char* cidContato );
		//UFOperadoras relacionadas a formulario
		int NaoRelacionadas( char* cidContato );
		//UFOperadoras relacionadas a formulario filtrado por fase
		int Relacionadas( char* cidContato, char* cidFaseProcesso );
		//UFOperadoras relacionadas a formulario filtrado por fase
		int NaoRelacionadas( char* cidContato, char* cidFaseProcesso );
};

#endif

