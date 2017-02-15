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

#ifndef DOCUMENTOPC
#define DOCUMENTOPC

#include "Global.h"

class CDocumentopc
{
public:
    void proCInsereDocumento(TDocumento *ptDocumento);
    void proCAtualizaDocumento(TDocumento tDocumento);
    bool proCBuscaDocumento(TDocumento *ptDocumento);
    void proCApagaDocumento(TDocumento tDocumento);
    bool proCBuscaCPFCNPJPorIdPessoa(const char *idPessoa,TDocumento *ptDocumento);
};

#endif
