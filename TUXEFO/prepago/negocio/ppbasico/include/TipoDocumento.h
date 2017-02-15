///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase TipoDocumento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TIPODOCUMENTOH
#define TIPODOCUMENTOH

#include "Global.h"
#include "TipoDocumentopc.h"

class CTipoDocumento
{

public:
    CTipoDocumento(void);
    bool buscaTipoDocumento(void);
    bool buscaIdTipoDocumento(void);

	TTipoDocumento		tTipoDocumento;
	CTipoDocumentopc    clTipoDocumentopc;

    void setIdTipoDocumento(char *pszIdTipoDocumento);
    void setSgClassificacao(char *pszSgClassificacao);

    char *getIdTipoDocumento(void);
    char *getSgTipoDocumento(void);
    char *getSgClassificacao(void);
};

#endif
