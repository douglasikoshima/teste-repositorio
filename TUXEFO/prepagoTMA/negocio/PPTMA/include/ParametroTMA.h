#ifndef PARAMETROHTMA
#define PARAMETROHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "ParametropcTMA.h"

class CParametro
{

public:
	TParametro tParametro;
	CParametropc clParametropc;


    CParametro(void);
    bool buscaParametro(void);

    void clearStruct(void);
    void setCdParametro(char *pszCdParametro);
    char *getDsValorParametro(void);
    char *getCdParametro(void);
};

#endif
