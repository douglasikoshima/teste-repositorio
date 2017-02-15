#include "TipoEnderecoPORT.h"

CTipoEndereco::CTipoEndereco(void) {
    memset(&tTipoEndereco, 0x00, sizeof(TTipoEndereco));
}

bool CTipoEndereco::buscaTipoEndereco(void) {
    return clTipoEnderecopc.proCBuscaTipoEndereco(&tTipoEndereco);
}

void CTipoEndereco::setSgTipoEndereco(char *pszSgTipoEndereco) {
    strcpy(tTipoEndereco.szSgTipoEndereco, pszSgTipoEndereco);
}

char *CTipoEndereco::getIdTipoEndereco(void) {
    static char szAux[LEN_IDTIPOENDERECO + LEN_EOS];

    strcpy(szAux, tTipoEndereco.szIdTipoEndereco);
    return szAux;
}

char *CTipoEndereco::getSgTipoEndereco(void) {
    static char szAux[LEN_SGTIPOENDERECO + LEN_EOS];

    strcpy(szAux, tTipoEndereco.szSgTipoEndereco);
    return szAux;
}
