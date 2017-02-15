#ifndef CTipPesH
#define CTipPesH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CTipPesItr.h"


class CTipPes : public CTipPesItr
{
	public:
		CTipPes();
		~CTipPes();
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif