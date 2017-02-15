#ifndef CPesquisaSatisfacaoTPPessoaH
#define CPesquisaSatisfacaoTPPessoaH

#include <tuxfw.h>
#include "../include/CPesquisaSatisfacaoTPPessoaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesquisaSatisfacaoTPPessoa : public CPesquisaSatisfacaoTPPessoaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPesquisaSatisfacaoTPPessoa();
		~CPesquisaSatisfacaoTPPessoa();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidPesquisaSatisfacao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
				char* cidTipoPessoa,
				char* cidUsuarioAlteracao 
			);
		//Atualiza um registro
		int Update( 
				char* cidPesquisaSatisfacao,
				char* cidTipoPessoa,
				char* cidUsuarioAlteracao 
			);
		//Apaga um ou mais registros
		int Delete( char* cidPesquisaSatisfacao );
		//Relacao por Contato
		int RelacaoPoridTipoPessoa( char* cidTipoPessoa );
};

#endif

