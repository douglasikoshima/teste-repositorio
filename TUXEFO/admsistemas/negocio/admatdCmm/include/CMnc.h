#ifndef CMunicipioH
#define CMunicipioH

#include <tuxfw.h>
#include "../include/CMncItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CMunicipio : public CMunicipioItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CMunicipio();
		~CMunicipio();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidMunicipio );
		//Recupera Todos os municipios de um estado
		int ListIdUF( char* cidUF );
		//Recupera todos os registros
		int ListAll( void );
		//Lista relacionados
		int RelacaoFrdMnc( char* cidFeriado );
		//Lista existentes
		int ExistemFrdMnc( char* cidFeriado );
};

#endif

