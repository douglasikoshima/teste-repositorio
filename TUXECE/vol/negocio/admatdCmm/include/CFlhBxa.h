#ifndef CContatoFolhaBaixaH
#define CContatoFolhaBaixaH

#include <tuxfw.h>
#include "../include/CFlhBxaItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoFolhaBaixa : public CContatoFolhaBaixaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoFolhaBaixa();
		~CContatoFolhaBaixa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoFolhaBaixa );
		int ListId( char* cidContato, char* cidBaixa );
		//Recupera todos os registros de um contato
		int ListIdContato( char* cidContato );
		//Conta quantas baixas ativas tem um contato
		int ContaBaixa( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( char* cidContato,
			        char* cidBaixa,
			        char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContatoFolhaBaixa );
		int Delete( char* cidContato, char* cidBaixa );
		//Apaga um ou mais registros
		int EraseFlh( char* cidContato );
		//Apaga um ou mais registros
		int EraseBxa( char* cidBaixa );
};

#endif

