#ifndef TIPODOCUMENTOHPORT
#define TIPODOCUMENTOHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoDocumentopcPORT.h"

class CTipoDocumento
{

public:
	TTipoDocumento tTipoDocumento;
	CTipoDocumentopc clTipoDocumentopc;

    CTipoDocumento(void);
    bool buscaTipoDocumento(void);
    bool buscaTipoDocumentoInscricao(void);

    void setIdTipoDocumento(char *pszIdTipoDocumento);
    void setSgClassificacao(char *pszSgClassificacao);

    char *getIdTipoDocumento(void);
    char *getSgTipoDocumento(void);
    char *getSgClassificacao(void);

    void clearStruct(void);
};

#endif
