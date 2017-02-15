#ifndef CPesquisaSatisfacaoH
#define CPesquisaSatisfacaoH

#include <tuxfw.h>
#include "../include/CSatItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CPesquisaSatisfacao : public CPesquisaSatisfacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CPesquisaSatisfacao();
		~CPesquisaSatisfacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidPesquisaSatisfacao );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera registros a partir de uma busca por nome, completa ou parcial
		int ListPar( char* cdsQuestionario );
		//Insere um registro
		int Insert( char* cnmPesquisaSatisfacao,
			        char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( char* cidPesquisaSatisfacao,
					char* cnmPesquisaSatisfacao,
					char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidPesquisaSatisfacao );
		//Lista relacionados
		int RelacaoSatPer( char* cidPergunta );
		//Lista existentes
		int ExistemSatPer( char* cidPergunta );
};

#endif

