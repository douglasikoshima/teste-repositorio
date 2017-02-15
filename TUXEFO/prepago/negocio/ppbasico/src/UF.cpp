///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Prepago
 * @usecase UF
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <string.h>
#include <stdio.h>
#include "UF.h"

CUF::CUF(void)
{
    memset(&tUF, 0x00, sizeof(TUF));
}

bool CUF::buscaUF(void)
{
    return(clUFpc.proCBuscaUF(&tUF));
}


void CUF::setIdUF(char *pszIdUF)
{
    strcpy(tUF.szIdUf, pszIdUF);
}

char *CUF::getSgUF(void)
{
    static char szAux[LEN_SGUF + LEN_EOS];

    strcpy(szAux, tUF.szSgUf);
    return szAux;
}
