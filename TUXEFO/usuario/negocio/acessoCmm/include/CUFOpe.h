#ifndef CUFOperadoraH
#define CUFOperadoraH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CUFOpeItr.h"


class CUFOperadora : public CUFOperadoraItr
{
	public:
		CUFOperadora();
		~CUFOperadora();
		int ListIdUser( char* cidUsuario );
		int ListId( char* cidUFOperadora );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif