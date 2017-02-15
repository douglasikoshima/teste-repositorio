#ifndef ESTADOCIVILHPORT
#define ESTADOCIVILHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "EstadoCivilpcPORT.h"

class CEstadoCivil
{

public:
	TEstadoCivil tEstadoCivil;
	CEstadoCivilpc clEstadoCivilpc;

    CEstadoCivil(void);
    bool buscaEstadoCivil(void);


    void setSgEstadoCivil(char *pszSgEstadoCivil);

    char *getIdEstadoCivil(void);
    char *getSgEstadoCivil(void);
};

#endif
