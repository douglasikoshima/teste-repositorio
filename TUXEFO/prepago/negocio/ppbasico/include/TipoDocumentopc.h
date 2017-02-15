///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase TipoDocumento
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef TIPO_DOCUMENTOPCH
#define TIPO_DOCUMENTOPCH

#include "Global.h"

class CTipoDocumentopc
{

public:
    bool proCBuscaTipoDocumento(TTipoDocumento *ptTipoDocumento);
    bool proCBuscaIdTipoDocumento(TTipoDocumento *ptTipoDocumento);
};

#endif
