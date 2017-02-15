///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase SistemaOrigem
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include "SistemaOrigem.h"

CSistemaOrigem::CSistemaOrigem(void)
{
	memset(&tSistemaOrigem, 0x00, sizeof(TSistemaOrigem));
}

bool CSistemaOrigem::buscaSistemaOrigem(void)
{
    return clSistemaOrigempc.proCBuscaSistemaOrigem(&tSistemaOrigem);
}

void CSistemaOrigem::setSgSistemaOrigem(char *pszSgSistemaOrigem)
{
    strcpy(tSistemaOrigem.szSgSistemaOrigem, pszSgSistemaOrigem);
}

char *CSistemaOrigem::getIdSistemaOrigem(void)
{
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tSistemaOrigem.szIdSistemaOrigem);
    return szAux;
}
