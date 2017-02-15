#include "MiscPORT.h"

CMisc::CMisc(void) {
	memset(&tMisc, 0x00, sizeof(TMisc));
}

bool CMisc::buscaCliente(void) {
    return clMiscpc.proCBuscaCliente(&tMisc);
}

void CMisc::setIdTipoLinha(char *pszIdTipoLinha) {
    strcpy(tMisc.szIdTipoLinha, pszIdTipoLinha);
}

void CMisc::setIdTipoPessoa(char *pszIdTipoPessoa) {
    strcpy(tMisc.szIdTipoPessoa, pszIdTipoPessoa);
}

void CMisc::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tMisc.szCdAreaRegistro, pszCdAreaRegistro);
}

void CMisc::setNrLinha(char *pszNrLinha) {
    strcpy(tMisc.szNrLinha, pszNrLinha);
}


char *CMisc::getIdPessoa(void) {
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tMisc.szIdPessoa);
    return szAux;
}

char *CMisc::getIdTipoLinha(void) {
    static char szAux[LEN_IDTIPOLINHA + LEN_EOS];

    strcpy(szAux, tMisc.szIdTipoLinha);
    return szAux;
}

char *CMisc::getIdTipoPessoa(void) {
    static char szAux[LEN_IDTIPOPESSOA + LEN_EOS];

    strcpy(szAux, tMisc.szIdTipoPessoa);
    return szAux;
}

char *CMisc::getCdAreaRegistro(void) {
    static char szAux[LEN_CDAREAREGISTRO + LEN_EOS];

    strcpy(szAux, tMisc.szCdAreaRegistro);
    return szAux;
}

char *CMisc::getNrLinha(void) {
    static char szAux[LEN_NRLINHA + LEN_EOS];

    strcpy(szAux, tMisc.szNrLinha);
    return szAux;
}
