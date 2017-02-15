#include "AcaoPortabilidadePORT.h"

CAcaoPortabilidade::CAcaoPortabilidade(void) {
    memset(&tAcaoPortabilidade, 0x00, sizeof(TAcaoPortabilidade));
}

bool CAcaoPortabilidade::buscaAcaoPortabilidade(void) {
    return clAcaoPortabilidadepc.proCBuscaAcaoPortabilidade(&tAcaoPortabilidade);
}

void CAcaoPortabilidade::setDsAcaoPortabilidade(char *pszDsAcaoPortabilidade) {
    strcpy(tAcaoPortabilidade.szDsAcaoPortabilidade, pszDsAcaoPortabilidade);
}

char *CAcaoPortabilidade::getIdAcaoPortabilidade(void) {
    static char szAux[LEN_IDACAOPORTABILIDADE + LEN_EOS];

    strcpy(szAux, tAcaoPortabilidade.szIdAcaoPortabilidade);
    return szAux;
}

char *CAcaoPortabilidade::getDsAcaoPortabilidade(void) {
    static char szAux[LEN_DSACAOPORTABILIDADE + LEN_EOS];

    strcpy(szAux, tAcaoPortabilidade.szDsAcaoPortabilidade);
    return szAux;
}
