#ifndef PESSOADPCTMA
#define PESSOADPCTMA

#include "PPGlobalTMA.h"

class CPessoaDocumentopc
{
public:
    bool proCBuscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    bool proCBuscaPessoaPessoaDocumento(const char *nrDocumento,const char *idTipoDocumento,const char *nmNome,const char *nmNomeMeio,const char *nmSobreNome,char *idPessoa);
    void proCApagaPessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    void proCAtualizaPessoaDocumento(TPessoaDocumento tPessoaDocumento);
    void proCInserePessoaDocumento(TPessoaDocumento *ptPessoaDocumento);
    bool proCExistePessoaDocumento(const char *idDocumento,const char *idPessoa);
    void proCApagaPessoaDocumentoEspecifico(const char *idDocumento,const char *idPessoa);
};

#endif
