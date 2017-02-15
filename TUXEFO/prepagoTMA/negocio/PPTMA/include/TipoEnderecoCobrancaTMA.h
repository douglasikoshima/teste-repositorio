#ifndef TIPO_ENDERECO_COBRANCAHTMA
#define TIPO_ENDERECO_COBRANCAHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "TipoEnderecoCobrancapcTMA.h"

class CTipoEnderecoCobranca
{
public:
	TTipoEnderecoCobranca tTipoEnderecoCobranca;
	CTipoEnderecoCobrancapc clTipoEnderecoCobrancapc;

    CTipoEnderecoCobranca(void);
    bool buscaTipoEnderecoCobranca(void);

    void setSgTipoEnderecoCobranca(char *pszSgTipoEnderecoCobranca);
    char *getIdTipoEnderecoCobranca(void);
};

#endif
