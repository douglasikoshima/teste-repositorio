#ifndef CNivelH
#define CNivelH

#include <tuxfw.h>
#include "../include/CNvlItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CNivel : public CNivelItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNivel();
		~CNivel();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
        //Recupera um campo a mais (para nao afetar o outro metodo GetXML */
        void GetXml2( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidNivel );
		//Recupera todos os registros
		int ListAll( void );

        //Recuperar todos os registros de Nivel e NivelHierarquia
        int ListarTodosNivelOrganograma( void );

		//Insere um registro
		int Insert(
			char* cdsNivel,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update(
			char* cidNivel,
			char* cdsNivel,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNivel );
		//Lista relacionados e existentes
		int RelacaoNC( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoNC( char* cidCargo );
		//Lista existentes
		int ExistemNC( char* cidCargo );
};

#endif

