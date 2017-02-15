#ifndef DOCUMENTOHPORT
#define DOCUMENTOHPORT

#include "tuxfw.h"
#include "DocumentopcPORT.h"

class CDocumento:private TuxHelper
{
    
public:
    TDocumento      tDocumento;
    CDocumentopc    clDocumentopc;

    CDocumento(void);
    ~CDocumento(void);

    bool    buscaDocumento(void);
    bool    buscaDocumento(TDocumento *ptDocumento);
	bool    buscaDocumentoChaveComposta(TDocumento *ptDocumento);

    void    clearStruct(void);

    char    *getIdDocumento(void);
    char    *getNrDocumento(void);
    char    *getIdTipoDocumento(void);
    char    *getDtEmissao(void);
    char    *getSgOrgaoExpedidor(void);
    char    *getIdUF(void);

    void    setIdPais(char *pszIdPais);
    void    setIdUf(char *pszIdUf);
    void    setStruct(TDocumento *ptDocumento);
    void    setIdDocumento(char *pszIdDocumento);
    void    setIdPessoa(char *pszIdPessoa);
    void    setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void    setNrDocumento(char *pszNrDocumento);
    void    setIdUF(char *pszIdUF);
    void    setIdTipoDocumento(char *pszIdTipoDocumento);
    void    setDtEmissao(char *pszDtEmissao);
    void    setSgOrgaoExpedidor(char *pszSgOrgaoExpedidor);

    void    apagaDocumento(void);
    void    insereDocumento(void);
    void    atualizaDocumento(void);
};

#endif
