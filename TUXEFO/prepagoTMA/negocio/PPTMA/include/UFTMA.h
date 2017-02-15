#ifndef UFHTMA
#define UFHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "UFpcTMA.h"

class CUF
{
public:
    TUF tUF;
    CUFpc clUFpc;

    CUF(void);

    bool buscaUF(void);
    void setIdUF(char *pszIdUF);
    char *getSgUF(void);
};

#endif
