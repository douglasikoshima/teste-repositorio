#ifndef SISTEMA_ORIGEMHPORT
#define SISTEMA_ORIGEMHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "SistemaOrigempcPORT.h"

class CSistemaOrigem
{
public:
	TSistemaOrigem		tSistemaOrigem;
	CSistemaOrigempc    clSistemaOrigempc;

    CSistemaOrigem(void);
    bool buscaSistemaOrigem(void);

    void setSgSistemaOrigem(char *pszSgSistemaOrigem);
    char *getIdSistemaOrigem(void);
    char *getSgSistemaOrigem(void);
};

#endif
