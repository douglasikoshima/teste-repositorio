#ifndef CContatoAcaoH
#define CContatoAcaoH

#include <tuxfw.h>
#include "../include/CCttAcaItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoAcao : public CContatoAcaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoAcao();
		~CContatoAcao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoAcao );
		//Recupera todos os registros de um contato
		int ListIdContato( char* cidContato );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmURLContatoAcao,
			char* cidContato,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidContatoAcao,
			char* cidUFOperadora,
			char* cidTipoLinha,
			char* cnmURLContatoAcao,
			char* cidContato,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidContatoAcao );
		//Apaga um ou mais registros
		int EraseFlh( char* cidContato );
		//Apaga um ou mais registros
		int EraseLin( char* cidTipoLinha );
		//Apaga um ou mais registros
		int EraseUfo( char* cidUFOperadora );
};

#endif

