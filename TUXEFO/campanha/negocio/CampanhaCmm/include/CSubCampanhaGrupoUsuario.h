#ifndef CSubCampanhaGrupoUsuarioH
#define CSubCampanhaGrupoUsuarioH

#include <tuxfw.h>
#include "CSubCampanhaGrupoUsuarioItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CSubCampanhaGrupoUsuario : public CSubCampanhaGrupoUsuarioItr

{
    private:
		int InserirRaiz();
		int InserirNode();

	public:

		CSubCampanhaGrupoUsuario();
		~CSubCampanhaGrupoUsuario();

		int ListId( char* cidSubCampanhaFixa );

		int Insert( char* cidSubCampanhaFixa,
	                char* cidGrupo,
	                char* cinAtivo,
	                char* cidUser );
	                
};
#endif

