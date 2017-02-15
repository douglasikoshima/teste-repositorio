#include <tuxfw.h>
#include "CTipoGrupoItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CTipoGrupo : public CTipoGrupoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CTipoGrupo();
		~CTipoGrupo();
		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Recupera um registro
		int ListId( char* cidTipoGrupo );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert(
					 	 char* csgTipoGrupo
					 	,char* cdsTipoGrupo
					 	,char* cidUser
		);
		//Edita um registro
		int Update(
					 	 char* cidTipoGrupo
					 	,char* csgTipoGrupo
					 	,char* cdsTipoGrupo
					 	,char* cidUser
		);
		//Apaga um registro
		int Delete( char* cidTipoGrupo );
};
// #endif