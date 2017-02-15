#include "TipoEnderecoCobrancaPORT.h"

CTipoEnderecoCobranca::CTipoEnderecoCobranca(void) {
    memset(&tTipoEnderecoCobranca, 0x00, sizeof(TTipoEnderecoCobranca));
}

bool CTipoEnderecoCobranca::buscaTipoEnderecoCobranca(void) {
    return clTipoEnderecoCobrancapc.proCBuscaTipoEnderecoCobranca(&tTipoEnderecoCobranca);
}

void CTipoEnderecoCobranca::setSgTipoEnderecoCobranca(char *pszSgTipoEnderecoCobranca) {
    strcpy(tTipoEnderecoCobranca.szSgTipoEnderecoCobranca, pszSgTipoEnderecoCobranca);
}

char *CTipoEnderecoCobranca::getIdTipoEnderecoCobranca(void) {
    static char szAux[LEN_IDTIPOENDERECOCOBRANCA + LEN_EOS];

    strcpy(szAux, tTipoEnderecoCobranca.szIdTipoEnderecoCobranca);
    return szAux;
}

char *CTipoEnderecoCobranca::getSgTipoEnderecoCobranca(void) {
    static char szAux[LEN_SGTIPOENDERECOCOBRANCA + LEN_EOS];

    strcpy(szAux, tTipoEnderecoCobranca.szSgTipoEnderecoCobranca);
    return szAux;
}
