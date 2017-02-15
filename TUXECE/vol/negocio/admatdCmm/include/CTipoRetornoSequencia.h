#ifndef CTipoRetornoSequenciaH
#define CTipoRetornoSequenciaH

#include <tuxfw.h>
#include "../include/CTipoRetornoSequenciaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CTipoRetornoSequencia : public CTipoRetornoSequenciaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoRetornoSequencia();
		~CTipoRetornoSequencia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoRetornoSequencia );
		//Recupera todos os registros de um certo contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );

		//Verifica se existe
        bool Existe(
                char* cidContatoTipoRetorno,
                char* cidSequencia
            );

		//Insere um registro
		int Insert( 
				char* cidContatoTipoRetorno,
				char* cidSequencia,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidTipoRetornoSequencia,
				char* cidContatoTipoRetorno,
				char* cidSequencia,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidTipoRetornoSequencia );
};

#endif

