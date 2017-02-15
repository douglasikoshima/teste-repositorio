#include "TipoLinhaPORT.h"

CTipoLinha::CTipoLinha(void) {
    memset(&tTipoLinha, 0x00, sizeof(TTipoLinha));
}

bool CTipoLinha::buscaTipoLinha(void) {
    return clTipoLinhapc.proCBuscaTipoLinha(&tTipoLinha);
}

void CTipoLinha::setSgTipoLinha(char *pszSgTipoLinha) {
    strcpy(tTipoLinha.szSgTipoLinha, pszSgTipoLinha);
}

char *CTipoLinha::getIdTipoLinha(void) {
    static char szAux[LEN_IDTIPOLINHA + LEN_EOS];

    strcpy(szAux, tTipoLinha.szIdTipoLinha);
    return szAux;
}

char *CTipoLinha::getSgTipoLinha(void) {
    static char szAux[LEN_SGTIPOLINHA + LEN_EOS];

    strcpy(szAux, tTipoLinha.szSgTipoLinha);
    return szAux;
}
