#ifndef PESSOADPCPORT
#define PESSOADPCPORT

#include "PPGlobalPORT.h"

class CPessoaDocumentopc
{
public:
	void proCInserePessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    void proCAtualizaPessoaDocumento(TPessoaDocumento tPessoaDocumento);
    bool proCBuscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    void proCApagaPessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
};

#endif
