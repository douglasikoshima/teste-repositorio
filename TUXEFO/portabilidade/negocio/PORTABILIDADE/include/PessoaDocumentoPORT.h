#ifndef PESSOADOCUMENTOHPORT
#define PESSOADOCUMENTOHPORT

#include "tuxfw.h"
#include "PessoaDocumentopcPORT.h"


class CPessoaDocumento:private TuxHelper
{
    
public:
    TPessoaDocumento    tPessoaDocumento;
    CPessoaDocumentopc  clPessoaDocumentopc;

    CPessoaDocumento(void);

    bool    buscaPessoaDocumento(void);
    bool    buscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumentoAux);
    void    inserePessoaDocumento(void);
    void    atualizaPessoaDocumento(void);
    void    apagaPessoaDocumento(void);

    void    setStruct(TPessoaDocumento *ptPessoaDocumento);
    void    setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void    setIdPessoaDocumento(char *pszIdPessoaDocumento);
    void    setIdPessoa(char *pszIdPessoa);
    void    setIdDocumento(char *pszIdDocumento);
    void    setTsSincronismo(char *pszTsSincronismo);
    void    setSqSincronismo(char *pszSqSincronismo);
    void    setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void    setIdDocumentoSistemaOrigem(char *pszIdDocumentoSistemaOrigem);

    char    *getIdPessoa(void);
    char    *getIdDocumento(void);
    char    *getIdSistemaOrigem(void);
    char    *getTsSincronismo(void);
    char    *getSqSincronismo(void);
    char    *getIdDocumentoSistemaOrigem(void);
    char    *getIdPessoaDocumento(void);

    bool apagouPessoaDocumento(void);

private:
    bool bApagouPessoaDocumento;
};

#endif
