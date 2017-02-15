#ifndef CPaisH
#define CPaisH

#include<tuxfw.h>
#include <CPaisItr.h>


class CPais : public CPaisItr
{
	public:
		CPais();
		~CPais();
		int ListId( char* cid );
		int ListAll( void );
		int ListSigla( char* csgPais);
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif