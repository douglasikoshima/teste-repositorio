#ifndef CContatoTipoRetornoH
#define CContatoTipoRetornoH

#include <tuxfw.h>
#include "../include/CContatoTipoRetornoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoTipoRetorno : public CContatoTipoRetornoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoTipoRetorno();
		~CContatoTipoRetorno();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoTipoRetorno );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidTipoRetornoContato,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidContatoTipoRetorno,
				char* cidContato,
				char* cidTipoRetornoContato,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidContatoTipoRetorno );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
		//Procupra por idContato e idTipoRetornoContato
		int Find( char* cidContato, char* cidTipoRetornoContato );
};

#endif

