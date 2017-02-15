#ifndef CListaCanalCampanhaH
#define CListaCanalCampanhaH


#include <tuxfw.h>
#include "CListaCanalCampanhaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CListaCanalCampanha : public CListaCanalCampanhaItr

{
    private:
		int InserirRaiz();
		int InserirNode();

	public:

		CListaCanalCampanha();
		~CListaCanalCampanha();

		int ListId( char* cidCanalCampanha );

		int Insert( char* cidLista,
	                char* cidCanalCampanha,
	                char* cinAtivo,
	                char* cidUser );
};
#endif

