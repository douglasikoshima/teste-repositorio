#ifndef CSistemaOrigemH
#define CSistemaOrigemH

#include <tuxfw.h>
#include "../include/CSistemaOrigemItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CSistemaOrigem : public CSistemaOrigemItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CSistemaOrigem();
		~CSistemaOrigem();

		//Monta o xml padrao desta tabela
		void GetXml( XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidSistemaOrigem );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidSistemaOrigem,
			char* cnmSistemaOrigem,
			char* cidUsuarioAlteracao );
		//Insere um registro
		int Insert( 
			char* cnmSistemaOrigem,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidSistemaOrigem,
			char* cnmSistemaOrigem,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidSistemaOrigem );
		//Lista relacionados e existentes
		int RelacaoCA( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoCA( char* cidCargo );
		//Lista existentes
		int ExistemCA( char* cidCargo );
};

#endif

