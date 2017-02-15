#include "TipoCarteiraPORT.h"

CTipoCarteira::CTipoCarteira(void) {
    memset(&tTipoCarteira, 0x00, sizeof(TTipoCarteira));
}

bool CTipoCarteira::buscaTipoCarteira(void) {
    return clTipoCarteirapc.proCBuscaTipoCarteira(&tTipoCarteira);
}

void CTipoCarteira::setSgTipoCarteira(char *pszSgTipoCarteira) {
    strcpy(tTipoCarteira.szSgTipoCarteira, pszSgTipoCarteira);
}

char *CTipoCarteira::getIdTipoCarteira(void) {
    static char szAux[LEN_IDTIPOCARTEIRA + LEN_EOS];

    strcpy(szAux, tTipoCarteira.szIdTipoCarteira);
    return szAux;
}

char *CTipoCarteira::getSgTipoCarteira(void) {
    static char szAux[LEN_SGTIPOCARTEIRA + LEN_EOS];

    strcpy(szAux, tTipoCarteira.szSgTipoCarteira);
    return szAux;
}
