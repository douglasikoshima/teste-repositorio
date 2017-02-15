#ifndef CStsH
#define CStsH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CStsItr.h"


class CSts : public CStsItr
{
	public:
		CSts();
		~CSts();
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif