#ifndef CCanalCampanhaH
#define CCanalCampanhaH

#include <tuxfw.h>
#include "CCanalCampanhaItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CCanalCampanha : public CCanalCampanhaItr

{
    private:
		int InserirRaiz();
		int InserirNode();

	public:

		CCanalCampanha();
		~CCanalCampanha();

		int ListId( char* cidSubCampanhaHistorico );

		int Insert( char* cidSubCampanhaHistorico,
	                char* cidCanalUFOperadora,
	                char* csqApresentacao,
	                char* cinDisponibilidade,
	                char* cinAtivo,
	                char* cidUser );
	                
	   int CopiaParametrizacao( char* cidCanalCampanhaOld,
		                        char* cidCanalCampanhaNew,
	                            char* cidUser );
			    
	    
};
#endif

