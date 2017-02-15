#include "TipoComunicacaoPORT.h"

CTipoComunicacao::CTipoComunicacao(void) {
	memset(&tTipoComunicacao, 0x00, sizeof(TTipoComunicacao));
}

bool CTipoComunicacao::buscaIdTipoComunicacao(void) {
    return clTipoComunicacaopc.proCBuscaIdTipoComunicacao(&tTipoComunicacao);
}


void CTipoComunicacao::setSgClassificacao(char *pszSgClassificacao) {
    strcpy(tTipoComunicacao.szSgClassificacao, pszSgClassificacao);
}

void CTipoComunicacao::setSgTipoComunicacao(char *pszSgTipoComunicacao) {
    strcpy(tTipoComunicacao.szSgTipoComunicacao, pszSgTipoComunicacao);
}

char *CTipoComunicacao::getSgTipoComunicacao(void) {
    static char szAux[LEN_SGTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szSgTipoComunicacao);
    return szAux;
}

char *CTipoComunicacao::getIdTipoComunicacao(void) {
    static char szAux[LEN_IDTIPOCOMUNICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szIdTipoComunicacao);
    return szAux;
}

char *CTipoComunicacao::getSgClassificacao(void) {
    static char szAux[LEN_SGCLASSIFICACAO + LEN_EOS];

    strcpy(szAux, tTipoComunicacao.szSgClassificacao);
    return szAux;
}
