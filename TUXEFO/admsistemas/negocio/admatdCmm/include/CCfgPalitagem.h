#ifndef CCfgPalitagemH
#define CCfgPalitagemH

#include <tuxfw.h>
#include "../include/CCfgPalitagemItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CCfgPalitagem : public CCfgPalitagemItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCfgPalitagem();
		~CCfgPalitagem();

		//Monta o xml padrao desta tabela
		void GetXml( char * idContato, XMLGen * xml );
		//Recupera um registro
		int ListId( char* cidCfgPalitagem );
		//Recupera todos os registros
		int ListAll( char* cidContato );
		//Insere um registro
        int Insert(
            char* cidContato,
			char* cidSistemaOrigem,
			char* csgSistemaOrigem,
			char* cdsServico,
			char* cidProcedencia,
            char* idUser
            );
            
		//Atualiza um registro
		int Update( 
            char* cidContato,
            char* cidSistemaOrigem,
            char* csgSistemaOrigem,
            char* cdsServico,
            char* cidProcedencia,
            char* idUser
            );
		//Apaga um ou mais registros
		int Delete( char* cidContato, char* csgServico );
		//Apaga um ou mais registros
		int EraseFlhAvs( char* cidCfgPalitagem );
};

#endif

