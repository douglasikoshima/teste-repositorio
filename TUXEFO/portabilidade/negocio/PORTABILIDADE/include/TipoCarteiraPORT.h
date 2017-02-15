#ifndef TIPOCARTEIRAHPORT
#define TIPOCARTEIRAHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoCarteirapcPORT.h"

class CTipoCarteira
{

public:
	TTipoCarteira tTipoCarteira;
	CTipoCarteirapc clTipoCarteirapc;

    CTipoCarteira(void);
    bool buscaTipoCarteira(void);

    void setSgTipoCarteira(char *pszSgTipoCarteira);

    char *getIdTipoCarteira(void);
    char *getSgTipoCarteira(void);
};

#endif
