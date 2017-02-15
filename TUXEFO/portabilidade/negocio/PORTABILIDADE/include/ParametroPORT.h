#ifndef PARAMETROHPORT
#define PARAMETROHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "ParametropcPORT.h"

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
