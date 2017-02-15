#include "UFPORT.h"

CUF::CUF(void) {
    memset(&tUF, 0x00, sizeof(TUF));
}

bool CUF::buscaUF(void) {
    return clUFpc.proCBuscaUF(&tUF);
}

bool CUF::buscaIdUF(void) {
    return clUFpc.proCBuscaIdUF(&tUF);
}

void CUF::setIdUF(char *pszIdUF) {
    strcpy(tUF.szIdUF, pszIdUF);
}

void CUF::setSgUF(char *pszSgUF) {
    strcpy(tUF.szSgUF, pszSgUF);
}

char *CUF::getSgUF(void) {
    static char szAux[LEN_SGUF + LEN_EOS];

    strcpy(szAux, tUF.szSgUF);
    return szAux;
}

char *CUF::getIdUF(void) {
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy(szAux, tUF.szIdUF);
    return szAux;
}

void CUF::clearStruct(void) {
    memset(&tUF, 0x00, sizeof(TUF));
}
