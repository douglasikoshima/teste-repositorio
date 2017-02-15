#ifndef CCampoClassificadorH
#define CCampoClassificadorH

#include <tuxfw.h>
#include "../include/CCmpClsItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CCampoClassificador : public CCampoClassificadorItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCampoClassificador();
		~CCampoClassificador();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidClassificadorCampo );
		//Recupera os registros com nomes parecidos
		int ListByName( char* cnmClassificadorCampo );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(
					 	 char* cnmClassificadorCampo
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidClassificadorCampo
					 	,char* cnmClassificadorCampo
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidClassificadorCampo );
};

#endif

