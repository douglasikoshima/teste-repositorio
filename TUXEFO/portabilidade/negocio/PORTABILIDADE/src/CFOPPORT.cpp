#include "CFOPPORT.h"

CCFOP::CCFOP(void) {
    memset(&tCFOP, 0x00, sizeof(TCFOP));
}

bool CCFOP::buscaCFOP(void) {
    return clCFOPpc.proCBuscaCFOP(&tCFOP);
}

void CCFOP::setSgCFOP(char *pszSgCFOP) {
    strcpy(tCFOP.szSgCFOP, pszSgCFOP);
}

char *CCFOP::getIdCFOP(void) {
    static char szAux[LEN_IDCFOP + LEN_EOS];

    strcpy(szAux, tCFOP.szIdCFOP);
    return szAux;
}

char *CCFOP::getSgCFOP(void) {
    static char szAux[LEN_SGCFOP + LEN_EOS];

    strcpy(szAux, tCFOP.szSgCFOP);
    return szAux;
}
