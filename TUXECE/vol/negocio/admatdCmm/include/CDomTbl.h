#ifndef CTabelaDominioH
#define CTabelaDominioH

#include <tuxfw.h>
#include "../include/CDomTblItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTabelaDominio : public CTabelaDominioItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTabelaDominio();
		~CTabelaDominio();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTabelaDominio );
		//Recupera os registros por nome parecido
		int ListByName( char* cnmDominio );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(
					 	 char* cnmTabelaDominio
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidTabelaDominio
					 	,char* cnmTabelaDominio
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidTabelaDominio );
};

#endif

