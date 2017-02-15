#ifndef CProcedenciaH
#define CProcedenciaH

#include <tuxfw.h>
#include "../include/CProcedenciaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CProced : public CProcedItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CProced();
		~CProced();

		//Monta o xml padrao desta tabela
		void GetXml( XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidProcedencia );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidProcedencia,
			char* cnmProcedencia,
			char* cidUsuarioAlteracao );
		//Insere um registro
		int Insert( 
			char* cnmProcedencia,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidProcedencia,
			char* cnmProcedencia,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidProcedencia );
		//Lista relacionados e existentes
		int RelacaoCA( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoCA( char* cidCargo );
		//Lista existentes
		int ExistemCA( char* cidCargo );
};

#endif

