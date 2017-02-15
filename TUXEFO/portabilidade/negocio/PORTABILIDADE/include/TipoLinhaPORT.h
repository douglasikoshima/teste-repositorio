#ifndef TIPOLINHAHPORT
#define TIPOLINHAHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoLinhapcPORT.h"

class CTipoLinha
{

public:
	TTipoLinha tTipoLinha;
	CTipoLinhapc clTipoLinhapc;

    CTipoLinha(void);
    bool buscaTipoLinha(void);

    void setSgTipoLinha(char *pszSgTipoLinha);

    char *getIdTipoLinha(void);
    char *getSgTipoLinha(void);
};

#endif
