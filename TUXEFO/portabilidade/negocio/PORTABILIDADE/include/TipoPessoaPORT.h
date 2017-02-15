#ifndef TIPOPESSOAHPORT
#define TIPOPESSOAHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoPessoapcPORT.h"

class CTipoPessoa
{

public:
	TTipoPessoa tTipoPessoa;
	CTipoPessoapc clTipoPessoapc;

    CTipoPessoa(void);
    bool buscaTipoPessoa(void);

    void setSgTipoPessoa(char *pszSgTipoPessoa);

    char *getIdTipoPessoa(void);
    char *getSgTipoPessoa(void);
};

#endif
