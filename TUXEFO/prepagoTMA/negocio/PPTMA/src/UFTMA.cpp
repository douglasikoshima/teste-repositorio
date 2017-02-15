#include "UFTMA.h"

CUF::CUF(void) {
    memset(&tUF, 0x00, sizeof(TUF));
}

bool CUF::buscaUF(void) {
    return clUFpc.proCBuscaUF(&tUF);
}

void CUF::setIdUF(char *pszIdUF) {
    strcpy(tUF.szIdUf, pszIdUF);
}

char *CUF::getSgUF(void) {
    static char szAux[LEN_SGUF + LEN_EOS];

    strcpy(szAux, tUF.szSgUf);
    return szAux;
}
