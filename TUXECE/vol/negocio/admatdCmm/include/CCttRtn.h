#ifndef CTipoRetornoContatoH
#define CTipoRetornoContatoH

#include <tuxfw.h>
#include "../include/CCttRtnItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoRetornoContato : public CTipoRetornoContatoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoRetornoContato();
		~CTipoRetornoContato();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoRetornoContato );
		//Recupera todos os registros de um contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cnmTipoRetornoContato,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidTipoRetornoContato,
			char* cnmTipoRetornoContato,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidTipoRetornoContato );
};

#endif

