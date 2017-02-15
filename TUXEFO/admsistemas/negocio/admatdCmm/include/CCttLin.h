#ifndef CContatoTipoLinhaH
#define CContatoTipoLinhaH

#include <tuxfw.h>
#include "../include/CCttLinItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoTipoLinha : public CContatoTipoLinhaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoTipoLinha();
		~CContatoTipoLinha();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoTipoLinha );
		//Recupera todos os registros de um contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidContato,
			char* cidTipoLinha,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContatoTipoLinha );
		//Apaga um ou mais registros
		int EraseFlh( char* cidContato );
		//Apaga um ou mais registros
		int EraseLin( char* cidTipoLinha );
};

#endif

