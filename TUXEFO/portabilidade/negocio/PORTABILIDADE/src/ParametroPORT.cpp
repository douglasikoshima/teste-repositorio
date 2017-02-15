#include "ParametroPORT.h"

CParametro::CParametro(void) {
	memset(&tParametro, 0x00, sizeof(TParametro));
}

bool CParametro::buscaParametro(void) {
    return clParametropc.proCBuscaParametro(&tParametro);
}

void CParametro::clearStruct(void) {
	memset(&tParametro, 0x00, sizeof(TParametro));
}

void CParametro::setCdParametro(char *pszCdParametro) {
    strcpy(tParametro.szCdParametro, pszCdParametro);
}

char *CParametro::getDsValorParametro(void) {
    static char szAux[LEN_DSVALORPARAMETRO + LEN_EOS];

    strcpy(szAux, tParametro.szDsValorParametro);
    return szAux;
}

char *CParametro::getCdParametro(void) {
    static char szAux[LEN_CDPARAMETRO + LEN_EOS];

    strcpy(szAux, tParametro.szCdParametro);
    return szAux;
}
