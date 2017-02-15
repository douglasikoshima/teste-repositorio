#ifndef DOCUMENTOHTMA
#define DOCUMENTOHTMA

#include "tuxfw.h"
#include "DocumentopcTMA.h"

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
    bool    documentoIsRequired(const char *idTipoDocumento,const char *sgClassificacao);

    char    *getIdDocumento(void);
    char    *getNrDocumento(void);
    char    *getIdTipoDocumento(void);
    char    *getDtEmissao(void);
    char    *getSgOrgaoExpeditor(void);
    char    *getIdUF(void);

    void    setIdPais(char *pszIdPais);
    void    setIdUf(char *pszIdUf);
    void    setStruct(TDocumento *ptDocumento);
    void    setIdDocumento(char *pszIdDocumento);
    void    setIdPessoa(char *pszIdPessoa);
    void    setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    void    apagaDocumento(void);
    void    insereDocumento(void);
    void    atualizaDocumento(void);
};

#endif
