#ifndef CContatoSegmentacaoH
#define CContatoSegmentacaoH

#include <tuxfw.h>
#include "../include/CContatoSegmentacaoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoSegmentacao : public CContatoSegmentacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoSegmentacao();
		~CContatoSegmentacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoSegmentacao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidSegmentacao,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidContatoSegmentacao,
				char* cidContato,
				char* cidSegmentacao,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidContatoSegmentacao );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
};

#endif

