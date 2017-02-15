#ifndef SISTEMA_ORIGEMHTMA
#define SISTEMA_ORIGEMHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "SistemaOrigempcTMA.h"

class CSistemaOrigem
{
public:
	TSistemaOrigem		tSistemaOrigem;
	CSistemaOrigempc    clSistemaOrigempc;

    CSistemaOrigem(void);
    bool buscaSistemaOrigem(void);

    void setSgSistemaOrigem(char *pszSgSistemaOrigem);
    char *getIdSistemaOrigem(void);
};

#endif
