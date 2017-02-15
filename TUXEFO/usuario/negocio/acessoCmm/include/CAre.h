#ifndef CAreH
#define CAreH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CAreItr.h"


class CAre : public CAreItr
{
	public:
		CAre();
		~CAre();
		char* RTrim(char *pszString);
		int Insert( char* cnmDepartamento );
		int Update( char* cidArea, 
					char* cnmDepartamento );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif