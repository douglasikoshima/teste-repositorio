#ifndef CAtribuicaoH
#define CAtribuicaoH

#include <tuxfw.h>
#include "../include/CAtvItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CAtribuicao : public CAtribuicaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CAtribuicao();
		~CAtribuicao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidAtribuicao );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidAtribuicao,
			char* cnmAtribuicao,
			char* cidUsuarioAlteracao );
		//Insere um registro
		int Insert( 
			char* cnmAtribuicao,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidAtribuicao,
			char* cnmAtribuicao,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidAtribuicao );
		//Lista relacionados e existentes
		int RelacaoCA( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoCA( char* cidCargo );
		//Lista existentes
		int ExistemCA( char* cidCargo );
};

#endif

