#ifndef CUFH
#define CUFH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CUFItr.h"


class CUF : public CUFItr
{
	public:
		CUF();
		~CUF();
		int ListId( char* cid );
		int ListAll( void );
		int ListSigla( char* csgUF );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif