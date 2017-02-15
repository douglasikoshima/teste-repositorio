#ifndef CContatoTipoRelacionamentoH
#define CContatoTipoRelacionamentoH

#include <tuxfw.h>
#include "../include/CContatoTipoRelacionamentoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoTipoRelacionamento : public CContatoTipoRelacionamentoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoTipoRelacionamento();
		~CContatoTipoRelacionamento();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoTipoRelacionamento );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidTipoRelacionamento,
				char* cidUsuarioAlteracao 
		);
		int InsertSelect( 
						  char* cidContatoOrigem,
						  char* cidContatoDestino,
						  char* cidUsuarioAlteracao
		);
		//Atualiza um registro
		int Update( 
				char* cidContatoTipoRelacionamento,
				char* cidContato,
				char* cidTipoRelacionamento,
				char* cidUsuarioAlteracao 
		);
		//Apaga um ou mais registros
		int Delete( char* cidContatoTipoRelacionamento );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
};

#endif

