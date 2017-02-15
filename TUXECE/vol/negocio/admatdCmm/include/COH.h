#ifndef COrganizacaoHierarquiaH
#define COrganizacaoHierarquiaH

#include <tuxfw.h>
#include "../include/COHItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class COrganizacaoHierarquia : public COrganizacaoHierarquiaItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		COrganizacaoHierarquia();
		~COrganizacaoHierarquia();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidOrganizacao );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidOrganizacao,
			char* cidOrganizacaoPai,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidOrganizacao );
		//Relaciona e Lista relacionados e existentes
		int RelacionaOrg( char*cidOrganizacao, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseOrg( char* cidOrganizacao );
		//Relaciona e Lista relacionados e existentes
		int RelacionaUnd( char*cidDepartamento, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseUnd( char* cidDepartamento );
};

#endif

