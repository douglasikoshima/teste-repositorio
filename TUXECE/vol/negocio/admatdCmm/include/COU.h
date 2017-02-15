#ifndef COrganizacaoDepartamentoH
#define COrganizacaoDepartamentoH

#include <tuxfw.h>
#include "../include/COUItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class COrganizacaoDepartamento : public COrganizacaoDepartamentoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		COrganizacaoDepartamento();
		~COrganizacaoDepartamento();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera registros da pesquisa
		int List( 
			char* cidOrganizacaoDepartamento,
			char* cidDepartamento,
			char* cidOrganizacao,
			char* cidUsuarioAlteracao );
		//Recupera um registro
		int ListId( char* cidOrganizacaoDepartamento );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidDepartamento,
			char* cidOrganizacao,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidOrganizacaoDepartamento );
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

