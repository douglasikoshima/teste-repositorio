#ifndef CSesH
#define CSesH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CSesItr.h"


class CSes : public CSesItr
{
	public:
		CSes();
		~CSes();
		char* RTrim(char *pszString);
		int Insert( char* cdsSessao );
		int Update( char* cidSessao, 
					char* cdsSessao );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif