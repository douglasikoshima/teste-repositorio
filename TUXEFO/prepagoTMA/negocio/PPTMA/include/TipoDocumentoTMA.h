#ifndef TIPODOCUMENTOHTMA
#define TIPODOCUMENTOHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "TipoDocumentopcTMA.h"

class CTipoDocumento
{

public:
	TTipoDocumento tTipoDocumento;
	CTipoDocumentopc clTipoDocumentopc;

    CTipoDocumento(void);
    bool buscaTipoDocumento(void);
    bool buscaIdTipoDocumento(void);

    void setIdTipoDocumento(char *pszIdTipoDocumento);
    void setSgClassificacao(char *pszSgClassificacao);

    char *getIdTipoDocumento(void);
    char *getSgTipoDocumento(void);
    char *getSgClassificacao(void);
};

#endif
