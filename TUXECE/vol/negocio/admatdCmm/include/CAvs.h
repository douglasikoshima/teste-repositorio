#ifndef CMensagemAvisoH
#define CMensagemAvisoH

#include <tuxfw.h>
#include "../include/CAvsItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CMensagemAviso : public CMensagemAvisoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CMensagemAviso();
		~CMensagemAviso();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidMensagemAviso );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cdsMensagemAviso,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidMensagemAviso,
			char* cdsMensagemAviso,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidMensagemAviso );
		//Apaga um ou mais registros
		int EraseFlhAvs( char* cidMensagemAviso );
};

#endif

