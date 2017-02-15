#ifndef CContatoTipoCarteiraH
#define CContatoTipoCarteiraH

#include <tuxfw.h>
#include "../include/CContatoTipoCarteiraItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoTipoCarteira : public CContatoTipoCarteiraItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoTipoCarteira();
		~CContatoTipoCarteira();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoTipoCarteira );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidTipoCarteira,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidContatoTipoCarteira,
				char* cidContato,
				char* cidTipoCarteira,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidContatoTipoCarteira );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
};

#endif

