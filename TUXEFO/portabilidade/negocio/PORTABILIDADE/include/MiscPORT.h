#ifndef MISCHPORT
#define MISCHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "MiscpcPORT.h"

class CMisc
{

public:
	TMisc tMisc;
	CMiscpc clMiscpc;

    CMisc(void);

    bool buscaCliente(void);

    void setIdTipoLinha(char *pszSgTipoLinha);
    void setIdTipoPessoa(char *pszSgTipoPessoa);
    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setNrLinha(char *pszNrLinha);

    char *getIdPessoa(void);
    char *getIdTipoLinha(void);
    char *getIdTipoPessoa(void);
    char *getCdAreaRegistro(void);
    char *getNrLinha(void);
};

#endif
