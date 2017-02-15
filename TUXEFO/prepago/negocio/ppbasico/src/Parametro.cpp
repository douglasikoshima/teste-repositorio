///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase Parametro
 * @author  Renato Striitzel Russo
 * @author  Eder Jani Martins
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <string.h>
#include "Parametro.h"

CParametro::CParametro(void)
{
	memset(&tParametro, 0x00, sizeof(TParametro));
}

bool CParametro::buscaParametro(void)
{
    return clParametropc.proCBuscaParametro(&tParametro);
}

void CParametro::setCdParametro(char *pszCdParametro)
{
    strcpy(tParametro.szCdParametro, pszCdParametro);
}

char *CParametro::getDsValorParametro(void)
{
    static char szAux[LEN_DSVALORPARAMETRO + LEN_EOS];

    strcpy(szAux, tParametro.szDsValorParametro);
    return szAux;
}

void CParametro::clearStruct(void) {
	memset(&tParametro, 0x00, sizeof(TParametro));
}

char *CParametro::getCdParametro(void) {
    static char szAux[LEN_CDPARAMETRO + LEN_EOS];

    strcpy(szAux, tParametro.szCdParametro);
    return szAux;
}
