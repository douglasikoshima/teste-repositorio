#ifndef COrganizacaoH
#define COrganizacaoH

#include <tuxfw.h>
#include "../include/COrgItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class COrganizacao : public COrganizacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		COrganizacao();
		~COrganizacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml com um NO interno
		void GetXml( char* cNomeTagArray, char* cNomeTag, char* cNomeNo, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidOrganizacao );
		//Recupera todos os registros
		int ListAll( void );
		//Recupera todos os registros de Organizacao e Hierarquia
        int ListarTodosOrganizacaoHierarquia( void );
		//Insere um registro
		int Insert( char* cidTipoOrganizacao,
			char* cidOrganizacaoPai,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update(
			char* cidOrganizacao,
			char* cidTipoOrganizacao,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidOrganizacao );
		//Lista relacionados e existentes
		int RelacaoOU( char* cid, char* cNomeIdTag, char* cNomeTag, char* cNomeTagAssociadasExterna, char* cNomeTagAssociadas, char* cNomeTagExistentesExterna, char* cNomeTagExistentes, XMLGen*xml );
		//Lista relacionados
		int RelacaoOU( char* cidDepartamento );
		//Lista existentes
		int ExistemOU( char* cidDepartamento );
		//Lista Organizacao relacionada a departamento
		int RelacaoOrgUnd( char* cidDepartamento );
		//Insere um registro
		int Insert( char* cidOrganizacaoPai,
			        char* cidTipoOrganizacao,
			        char* cdsTipoOrganizacao,
			        char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( char* cidOrganizacao,
			        char* cidTipoOrganizacao,
			        char* cdsTipoOrganizacao,
			        char* cidUsuarioAlteracao );
		char* getPath( char* cidOrganizacao );
		char* getPath( char* cidOrganizacao, char* cidOrganizacaoPai );
		char* getPath() { return pzcPath; };
		void atualizaPath( char* cidOrganizacao );

protected:
		char pzcPath[2000];

};

#endif

