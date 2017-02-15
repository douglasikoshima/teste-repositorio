#ifndef CSisOrgH
#define CSisOrgH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CSisOrgItr.h"


class CSisOrg : public CSisOrgItr
{
	public:
		CSisOrg();
		~CSisOrg();
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif