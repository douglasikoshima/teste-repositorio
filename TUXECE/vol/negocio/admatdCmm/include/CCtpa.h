#ifndef CCTipoArvoreH
#define CCTipoArvoreH

#include <tuxfw.h>
#include "../include/CCtpaItr.h"
#include "../include/CSafePointer.h"

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CCTipoArvore : public CCTipoArvoreItr
{
	public:
        CCTipoArvore() {}
        ~CCTipoArvore() {}
        int ListTipoArvore( char* cdsTipoArvore );
		void GetXml( char* cNomeTag, XMLGen*xml );
};

#endif
