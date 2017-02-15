#ifndef CCampoDominioH
#define CCampoDominioH

#include <tuxfw.h>
#include "../include/CCmpDomItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CCampoDominio : public CCampoDominioItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCampoDominio();
		~CCampoDominio();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		void GetXml( XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidCampo );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(		 char* cidCampo
					 	,char* cdsQuery
					 	,char* cnmAtributoIdentificador
					 	,char* cnmAtributoDescritivo
					 	,char* cidTabelaDominio
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidCampo
					 	,char* cdsQuery
					 	,char* cnmAtributoIdentificador
					 	,char* cnmAtributoDescritivo
					 	,char* cidTabelaDominio
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidCampo );
};

#endif

