#ifndef CTipoOrganizacaoH
#define CTipoOrganizacaoH

#include <tuxfw.h>
#include "../include/CTOItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )
class CTipoOrganizacao : public CTipoOrganizacaoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoOrganizacao();
		~CTipoOrganizacao();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoOrganizacao );
		//Recupera todos os registros
		int ListAll( void );
		//Lista pesquisa
		int List( 
			char* cidTipoOrganizacao,
			char* cdsTipoOrganizacao,
			char* cidUsuarioAlteracao );
		//Insere um registro
		int Insert( 
			char* cdsTipoOrganizacao,
			char* cidUsuarioAlteracao );
		//Atualiza um registro
		int Update( 
			char* cidOrganizacao,
			char* cdsTipoOrganizacao,
			char* cidUsuarioAlteracao );
		//Atualiza um registro verificando dependencia
		int UpdateDescricao( 
			char* cidTipoOrganizacao,
			char* cdsTipoOrganizacao,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidTipoOrganizacao );
};

#endif

