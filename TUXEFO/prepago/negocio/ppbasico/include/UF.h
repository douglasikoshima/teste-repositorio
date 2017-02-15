///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase UF
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef UFH
#define UFH

#include "Global.h"
#include "UFpc.h"

class CUF
{
public:
    TUF    tUF;

    CUFpc  clUFpc;

    CUF(void);

    bool buscaUF(void);
    void setIdUF(char *pszIdUF);
    char *getSgUF(void);
};

#endif
