#include "TipoDocumentoTMA.h"

CTipoDocumento::CTipoDocumento(void) {
	memset(&tTipoDocumento, 0x00, sizeof(TTipoDocumento));
}

bool CTipoDocumento::buscaTipoDocumento(void) {
    return clTipoDocumentopc.proCBuscaTipoDocumento(&tTipoDocumento);
}

bool CTipoDocumento::buscaIdTipoDocumento(void) {
    return clTipoDocumentopc.proCBuscaIdTipoDocumento(&tTipoDocumento);
}

void CTipoDocumento::setIdTipoDocumento(char *pszIdTipoDocumento) {
    strcpy(tTipoDocumento.szIdTipoDocumento, pszIdTipoDocumento);
}

void CTipoDocumento::setSgClassificacao(char *pszSgClassificacao) {
    strcpy(tTipoDocumento.szSgClassificacao, pszSgClassificacao);
}

char *CTipoDocumento::getSgTipoDocumento(void) {
    static char szAux[LEN_SGTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tTipoDocumento.szSgTipoDocumento);
    return szAux;
}

char *CTipoDocumento::getIdTipoDocumento(void) {
    static char szAux[LEN_IDTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tTipoDocumento.szIdTipoDocumento);
    return szAux;
}

char *CTipoDocumento::getSgClassificacao(void) {
    static char szAux[LEN_SGCLASSIFICACAO + LEN_EOS];

    strcpy(szAux, tTipoDocumento.szSgClassificacao);
    return szAux;
}
