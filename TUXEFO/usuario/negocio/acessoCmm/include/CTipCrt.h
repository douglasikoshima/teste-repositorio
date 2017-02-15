#ifndef CTipCrtH
#define CTipCrtH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CTipCrtItr.h"


class CTipCrt : public CTipCrtItr
{
	public:
		CTipCrt();
		~CTipCrt();
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif