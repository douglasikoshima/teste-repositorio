#ifndef SEXOHPORT
#define SEXOHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "SexopcPORT.h"

class CSexo
{

public:
	TSexo tSexo;
	CSexopc clSexopc;

    CSexo(void);
    bool buscaSexo(void);


    void setSgSexo(char *pszSgSexo);

    char *getIdSexo(void);
    char *getSgSexo(void);
};

#endif
