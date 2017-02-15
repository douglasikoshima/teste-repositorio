#ifndef CCargoAtribuicaoH
#define CCargoAtribuicaoH

#include <tuxfw.h>
#include "../include/CCAItr.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CCargoAtribuicao : public CCargoAtribuicaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CCargoAtribuicao();
		~CCargoAtribuicao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera registros da pesquisa
		int List( 
			char* cidCargoAtribuicao,
			char* cidCargo,
			char* cidAtribuicao,
			char* cidUsuarioAlteracao );
		//Recupera um registro
		int ListId( char* cidCargoAtribuicao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidCargo,
			char* cidAtribuicao,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidCargoAtribuicao );
		//Relaciona e Lista relacionados e existentes
		int RelacionaCrg( char*cidCargo, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseCrg( char* cidCargo );
		//Relaciona e Lista relacionados e existentes
		int RelacionaAtv( char*cidAtribuicao, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseAtv( char* cidAtribuicao );
};

#endif

