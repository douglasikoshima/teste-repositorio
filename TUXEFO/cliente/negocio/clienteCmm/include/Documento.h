///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Documento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef DOCUMENTOH
#define DOCUMENTOH

#include <tuxfw.h>
#include "Documentopc.h"
#include "Global.h"
#include "TString.h"

class CDocumento:private TuxHelper
{
    
public:
    TDocumento      tDocumento;
    CDocumentopc    clDocumentopc;
    CDocumento(void);

    bool    buscaDocumento(void);
    bool    buscaDocumento(TDocumento *ptDocumento);

    TString getIdDocumento(void);
    char    *getNrDocumento(void);
    char    *getIdTipoDocumento(void);

    void    setIdPais(char *pszIdPais);
    void    setIdUf(char *pszIdUf);
	void    setCpf(char *cpf);
	TString unmask(char *doc);
	int		isGood(); 

    void    apagaDocumento(void);
    void    setData(TPessoaJuridicaXML xmJ);
	void	setData(TListaDocumento xm);
    void    insereDocumento(void);
    void    atualizaDocumento(void);
};

#endif

