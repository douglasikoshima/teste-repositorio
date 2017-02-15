#ifndef CContatoPesquisaSatisfacaoH
#define CContatoPesquisaSatisfacaoH

#include <tuxfw.h>
#include "../include/CContatoPesquisaSatisfacaoItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CContatoPesquisaSatisfacao : public CContatoPesquisaSatisfacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CContatoPesquisaSatisfacao();
		~CContatoPesquisaSatisfacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidContatoPesquisaSatisfacao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidContato,
				char* cidPesquisaSatisfacao,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidContatoPesquisaSatisfacao,
				char* cidContato,
				char* cidPesquisaSatisfacao,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidContatoPesquisaSatisfacao );
		//Relacao por Contato
		int RelacaoPorIdContato( char* cidContato );
		//Procura por um idContatoPesquisaSatisfacao, retorna a posicao
		int Find( char* cidContato, char* cidPesquisaSatisfacao );
};

#endif

