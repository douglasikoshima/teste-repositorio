#ifndef CAreRegH
#define CAreRegH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CAreRegItr.h"


class CAreReg : public CAreRegItr
{
	public:
		CAreReg();
		~CAreReg();
		int ListId( char* cid );
		int ListAll( void );
		int ListSigla( char* ccdAreaRegistro);
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif