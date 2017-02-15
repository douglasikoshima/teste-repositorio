#ifndef TIPO_ENDERECO_COBRANCAHPORT
#define TIPO_ENDERECO_COBRANCAHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoEnderecoCobrancapcPORT.h"

class CTipoEnderecoCobranca
{
public:
	TTipoEnderecoCobranca tTipoEnderecoCobranca;
	CTipoEnderecoCobrancapc clTipoEnderecoCobrancapc;

    CTipoEnderecoCobranca(void);
    bool buscaTipoEnderecoCobranca(void);

    void setSgTipoEnderecoCobranca(char *pszSgTipoEnderecoCobranca);

    char *getIdTipoEnderecoCobranca(void);
    char *getSgTipoEnderecoCobranca(void);
};

#endif
