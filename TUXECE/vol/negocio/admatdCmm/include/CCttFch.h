#ifndef CTipoFechamentoContatoH
#define CTipoFechamentoContatoH

#include <tuxfw.h>
#include "../include/CCttFchItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoFechamentoContato : public CTipoFechamentoContatoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoFechamentoContato();
		~CTipoFechamentoContato();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoFechamentoContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cnmTipoFechamentoContato,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidTipoFechamentoContato,
			char* cnmTipoFechamentoContato,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidTipoFechamentoContato );
};

#endif

