#ifndef CContatoFolhaMensagemAvisoH
#define CContatoFolhaMensagemAvisoH

#include <tuxfw.h>
#include "../include/CFlhAvsItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoFolhaMensagemAviso : public CContatoFolhaMensagemAvisoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolhaMensagemAviso();
		~CContatoFolhaMensagemAviso();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera todos os registros de um contato
		int ListId( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( char* cidContato,
					char* cidMensagemAviso,
					char* cidUsuarioAlteracao );
		//Edita um registro
		int Update( char* cidContato,
					char* cidMensagemAviso,
					char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContato );
		//Lista relacionados
		int Relacao(  );
		//Lista existentes
		int Existem(  );
};

#endif

