#ifndef UFHPORT
#define UFHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "UFpcPORT.h"

class CUF
{
public:
    TUF tUF;
    CUFpc clUFpc;

    CUF(void);

    bool buscaUF(void);
    bool buscaIdUF(void);
    void setIdUF(char *pszIdUF);
    void setSgUF(char *pszSgUF);

    char *getSgUF(void);
    char *getIdUF(void);

    void clearStruct(void);

};

#endif
