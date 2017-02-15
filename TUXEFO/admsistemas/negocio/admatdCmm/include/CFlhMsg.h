#ifndef CFolhaMensagemAvisoH
#define CFolhaMensagemAvisoH

#include <tuxfw.h>
#include "../include/CFlhMsgItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CFolhaMensagemAviso : public CFolhaMensagemAvisoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CFolhaMensagemAviso();
		~CFolhaMensagemAviso();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cdsMensagemAviso,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidContato,
			char* cdsMensagemAviso,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContato );
};

#endif

