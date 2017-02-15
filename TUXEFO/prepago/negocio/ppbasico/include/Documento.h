///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Documento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef DOCUMENTOH
#define DOCUMENTOH

#include "tuxfw.h"
#include "Documentopc.h"
#include "Global.h"

class CDocumento:private TuxHelper
{
    
public:
    TDocumento      tDocumento;
    TDocumentoArr   tDocumentoArr;

    CDocumentopc    clDocumentopc;

    CDocumento(void);
    ~CDocumento(void);

    bool    buscaDocumento(void);
    bool    buscaDocumento(TDocumento *ptDocumento);
	bool    buscaDocumentoChaveComposta(TDocumento *ptDocumento);

    char    *getIdDocumento(void);
    char    *getNrDocumento(void);
    char    *getIdTipoDocumento(void);

    void    setIdPais(char *pszIdPais);
    void    setIdUf(char *pszIdUf);
    void    setStruct(TDocumento *ptDocumento);
    void    setIdDocumento(char *pszIdDocumento);
    void    setIdPessoa(char *pszIdPessoa);
    void    setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    void    apagaDocumento(void);
    void    insereDocumento(void);
    void    atualizaDocumento(void);

	int     getQuantidade( void ) { return tDocumentoArr.iQuantidade; };
	TDocumento* getRegistro( int iIndex );
    bool    buscaDocumentoPorIdPessoa( void );

};

#endif
