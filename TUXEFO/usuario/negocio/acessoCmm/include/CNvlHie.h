#ifndef CNvlHieH
#define CNvlHieH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CNvlHieItr.h"


class CNvlHie : public CNvlHieItr
{
	public:
		CNvlHie();
		~CNvlHie();
		char* RTrim(char *pszString);
		int Insert( char* cidNivelPai,
		            char* idNivel,
		            char* idCargo );
		int Update( char* cidNivelHierarquia, 
					char* cidNivelPai,
		            char* cidNivel,
		            char* cidCargo );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif