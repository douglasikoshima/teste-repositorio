#include "TipoPessoaPORT.h"

CTipoPessoa::CTipoPessoa(void) {
	memset(&tTipoPessoa, 0x00, sizeof(TTipoPessoa));
}

bool CTipoPessoa::buscaTipoPessoa(void) {
    return clTipoPessoapc.proCBuscaTipoPessoa(&tTipoPessoa);
}

void CTipoPessoa::setSgTipoPessoa(char *pszSgTipoPessoa) {
    strcpy(tTipoPessoa.szSgTipoPessoa, pszSgTipoPessoa);
}

char *CTipoPessoa::getIdTipoPessoa(void) {
    static char szAux[LEN_IDTIPOPESSOA + LEN_EOS];

    strcpy(szAux, tTipoPessoa.szIdTipoPessoa);
    return szAux;
}

char *CTipoPessoa::getSgTipoPessoa(void) {
    static char szAux[LEN_SGTIPOPESSOA + LEN_EOS];

    strcpy(szAux, tTipoPessoa.szSgTipoPessoa);
    return szAux;
}
