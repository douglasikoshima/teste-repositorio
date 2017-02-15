#include "SexoPORT.h"

CSexo::CSexo(void) {
    memset(&tSexo, 0x00, sizeof(TSexo));
}

bool CSexo::buscaSexo(void) {
    return clSexopc.proCBuscaSexo(&tSexo);
}

void CSexo::setSgSexo(char *pszSgSexo) {
    strcpy(tSexo.szSgSexo, pszSgSexo);
}

char *CSexo::getIdSexo(void) {
    static char szAux[LEN_IDSEXO + LEN_EOS];

    strcpy(szAux, tSexo.szIdSexo);
    return szAux;
}

char *CSexo::getSgSexo(void) {
    static char szAux[LEN_SGSEXO + LEN_EOS];

    strcpy(szAux, tSexo.szSgSexo);
    return szAux;
}
