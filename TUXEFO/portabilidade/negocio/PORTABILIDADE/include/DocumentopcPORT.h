#ifndef DOCUMENTOPCPORT
#define DOCUMENTOPCPORT

#include "PPGlobalPORT.h"

class CDocumentopc
{
public:
    void proCInsereDocumento(TDocumento *ptDocumento);
    void proCAtualizaDocumento(TDocumento tDocumento);
    bool proCBuscaDocumento(TDocumento *ptDocumento);
    void proCApagaDocumento(TDocumento tDocumento);
	bool proCBuscaDocumentoChaveComposta(TDocumento *ptDocumento);
};

#endif
