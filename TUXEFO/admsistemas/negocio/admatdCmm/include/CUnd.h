#ifndef CDepartamentoH
#define CDepartamentoH

#include <tuxfw.h>
#include "../include/CUndItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CDepartamento : public CDepartamentoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CDepartamento();
		~CDepartamento();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidDepartamento );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidDepartamento,
			char* cnmDepartamento,
			char* cidUsuarioAlteracao );
        //Lista todos Departamentos(unidades) por IdOrganizacao
        int ListDeptoPorIdOrganizacao( char* cidOrganizacao );
		//Insere um registro
		int Insert( 
			char* cnmDepartamento,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidDepartamento,
			char* cnmDepartamento,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidDepartamento );
		//Lista relacionados e existentes
		int RelacaoOU( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoOU( char* cidOrganizacao );
		//Lista existentes
		int ExistemOU( char* cidOrganizacao );
		//Insere e lista o item inserido
		int InsertList( char* cnmDepartamento, char* cidUsuarioAlteracao );
};

#endif

