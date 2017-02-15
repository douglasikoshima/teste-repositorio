#ifndef CTipoFeriadoH
#define CTipoFeriadoH

#include <tuxfw.h>
#include "../include/CFrdTipItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoFeriado : public CTipoFeriadoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoFeriado();
		~CTipoFeriado();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoFeriado );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cdsTipoFeriado,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidTipoFeriado,
			char* cdsTipoFeriado,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidTipoFeriado );
};

#endif

