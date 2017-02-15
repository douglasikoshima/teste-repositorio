///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaDocumento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOADOCUMENTOH
#define PESSOADOCUMENTOH

#include "tuxfw.h"
#include "PessoaDocumentopc.h"
#include "Documento.h"
#include "Global.h"


class CPessoaDocumento:private TuxHelper
{
    
public:
    TPessoaDocumento    tPessoaDocumento;
    TPessoaDocumentoB01 tPessoaDocumentoB01;

    CPessoaDocumentopc  clPessoaDocumentopc;

    CPessoaDocumento(void);

    bool    buscaPessoaDocumento(void);
    bool    buscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumentoAux);
    void    inserePessoaDocumento(void);
    void    atualizaPessoaDocumento(void);
    void    apagaPessoaDocumento(void);
    bool    buscaPessoaDocumentoB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01);
    bool    buscaPessoaDocumentoB01(void);
    bool    buscaPessoaDocumentoApagaB01(void);

    void    setStruct(TPessoaDocumento *ptPessoaDocumento);
    void    setStructB01(TPessoaDocumentoB01 *ptPessoaDocumentoB01);
    void    setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    void    setIdPessoaDocumento(char *pszIdPessoaDocumento);
    char    *getIdPessoaDocumento(void);

    void    setIdPessoa(char *pszIdPessoa);
    char    *getIdPessoa(void);

    void    setIdDocumento(char *pszIdDocumento);
    char    *getIdDocumento(void);

    void    setTsSincronismo(char *pszTsSincronismo);
    char    *getTsSincronismo(void);

    void    setSqSincronismo(char *pszSqSincronismo);
    char    *getSqSincronismo(void);

    void    setIdSistemaOrigem(char *pszIdSistemaOrigem);
    char    *getIdSistemaOrigem(void);

    void    setIdDocumentoSistemaOrigem(char *pszIdDocumentoSistemaOrigem);
    char    *getIdDocumentoSistemaOrigem(void);


    void    setIdPessoaB01(char *pszIdPessoa);
    char    *getIdPessoaB01(void);

    void    setIdTipoDocumentoB01(char *pszIdTipoDocumento);
    char    *getIdTipoDocumentoB01(void);

    void    setIdPessoaDocumentoB01(char *pszIdPessoaDocumento);
    char    *getIdPessoaDocumentoB01(void);

    void    setIdDocumentoB01(char *pszIdDocumento);
    char    *getIdDocumentoB01(void);

    void    setTsSincronismoB01(char *pszTsSincronismo);
    char    *getTsSincronismoB01(void);

    void    setSqSincronismoB01(char *pszSqSincronismo);
    char    *getSqSincronismoB01(void);

    void    setIdDocumentoSistemaOrigemB01(char *pszIdDocumentoSistemaOrigem);
    char    *getIdDocumentoSistemaOrigemB01(void);

    void    setIdSistemaOrigemB01(char *pszIdSistemaOrigem);
    char    *getIdSistemaOrigemB01(void);

    void    setIdPessoaSistemaOrigemB01(char *pszIdPessoaSistemaOrigem);
    void    setNrDocumentoB01(char *pszNrDocumento);


    int     SincInserePessoaDocumento(CDocumento &pclDocumento);
};

#endif
