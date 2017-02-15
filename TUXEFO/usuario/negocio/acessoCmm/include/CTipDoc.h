#ifndef CTipDocH
#define CTipDocH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CTipDocItr.h"


class CTipDoc : public CTipDocItr
{
	public:
		CTipDoc();
		~CTipDoc();
		int ListId( char* cid );
		int ListAll( void );
		int ListFiltrado( void );
		int ListSigla( char* csgTipoDocumento );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif