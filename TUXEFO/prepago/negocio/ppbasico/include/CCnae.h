#ifndef CCNAEH
#define CCNAEH

//#include<XMLImpl.h>
#include<tuxfw.h>
#include <CCnaeItr.h>
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CCnae : public CCnaeItr
{
	public:
		CCnae();
		~CCnae();

        int ListAll( void );
		int ListNrCNAE( char* cnrCNAE );

        void getXmlCombo( char* cNomeTag,
                          char* idCNAE,
                          char* cdCNAE,
                          char* dsCNAE,
                          char* cdCFOP,
                          XMLGen* xml );

        void getXml( char* cNomeTag,
                     XMLGen* xml );
};

#endif