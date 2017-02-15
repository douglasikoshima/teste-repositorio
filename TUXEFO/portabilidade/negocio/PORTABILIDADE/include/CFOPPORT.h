#ifndef CFOPHPORT
#define CFOPHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "CFOPpcPORT.h"

class CCFOP
{

public:
	TCFOP tCFOP;
	CCFOPpc clCFOPpc;

    CCFOP(void);
    bool buscaCFOP(void);


    void setSgCFOP(char *pszSgCFOP);

    char *getIdCFOP(void);
    char *getSgCFOP(void);
};

#endif
