#ifndef DOCUMENTOPCTMA
#define DOCUMENTOPCTMA

#include "PPGlobalTMA.h"

class CDocumentopc
{
public:
    void proCInsereDocumento(TDocumento *ptDocumento);
    void proCAtualizaDocumento(TDocumento tDocumento);
    bool proCBuscaDocumento(TDocumento *ptDocumento);
    void proCApagaDocumento(TDocumento tDocumento);
    bool proCTipoDocumentoIsRequired(const char *idTipoDocumento,const char *sgClassificacao);
	bool proCBuscaDocumentoChaveComposta(TDocumento *ptDocumento);
};

#endif
