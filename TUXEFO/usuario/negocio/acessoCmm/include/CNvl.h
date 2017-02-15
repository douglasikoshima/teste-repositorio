#ifndef CNvlH
#define CNvlH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CNvlItr.h"


class CNvl : public CNvlItr
{
	public:
		CNvl();
		~CNvl();
		char* RTrim(char *pszString);
		int Insert( char* cdsNivel );
		int Update( char* cidNivel, 
					char* cdsNivel );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif