#ifndef CContatoFolhaEnvioDPRH
#define CContatoFolhaEnvioDPRH

#include <tuxfw.h>
#include "../include/CFlhDprItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CContatoFolhaEnvioDPR : public CContatoFolhaEnvioDPRItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolhaEnvioDPR();
		~CContatoFolhaEnvioDPR();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidContato,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContato );
};

#endif

