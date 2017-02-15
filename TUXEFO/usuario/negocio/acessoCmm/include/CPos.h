#ifndef CPosH
#define CPosH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CPosItr.h"


class CPos : public CPosItr
{
	public:
		CPos();
		~CPos();
		char* RTrim(char *pszString);
		int Insert( char* cdsPosicao );
		int Update( char* cidPosicao, 
					char* cdsPosicao );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif